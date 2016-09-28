import PIL.Image as Image
import numpy as np
import cv2 as cv2
import os as os
from matplotlib import pyplot as plt

VISUALIZE_TICKET_CLIP = True
VISUALIZE_TICKET_MATCH = False
VISUALIZE_CELL_CLIP = True
VISUALIZE_CELL_MATCH = False
LOG_SCORE_PROGRESS = True

TICKET_MAX_FEATURES=16384
TICKET_SIFT_OCTAVES=1
TICKET_SIFT_SIGMA=1.825

CELL_MAX_FEATURES=64
CELL_SIFT_OCTAVES=3
CELL_SIFT_SIGMA=5.1735

# Initiate ORB detector with default values
FLANN_INDEX_KDTREE = 0
INDEX_PARAMS = dict(algorithm = FLANN_INDEX_KDTREE, trees = 8)
SEARCH_PARAMS = dict(checks = 80)

TICKET_MIN_MATCH_PERCENT = 0.025
TICKET_MAX_DIST_RATIO = 0.667

DATA_DIR = 'data25'
EXEMPLAR_FILE_TEMPLATE = 'letters/%s%02d.tif'
CELL_LABEL_TEMPLATE = "(%d,%d) of %s"
MATCH_LABEL_TEMPLATE = "%s in %s"

TICKET_OVERLAY_SOURCE_PCT=0.65
TICKET_OVERLAY_EXAMPLE_PCT=1.0 - TICKET_OVERLAY_SOURCE_PCT

NUM_CELLS=11
CELL_SIZE=96
CELL_CORRECTION=np.array([-6,-6], dtype='uint32')

BLANK_SIZE=64
BLANK_CORRECTION=np.array([2 - CELL_CORRECTION[0],2 - CELL_CORRECTION[1]], dtype='uint32')
BLANK_CHECK_LOW=0.15
BLANK_CHECK_HIGH=0.50
BLANK_CHECK_TOLERANCE=24


def findPercentiles(sourceImg, targets):
   flat = sourceImg.reshape(-1)
   pixelCount = len(flat)
   numTargets = len(targets)
   nextBucketIdx = 0
   cumulativeSum = 0
   sortedTargets = np.sort(targets)
   (bucketCounts, bounds) = np.histogram(flat, 96)
   retVal = dict()
   for ii in range(0,numTargets):
      nextPctGoal = sortedTargets[ii]
      if nextPctGoal < 0 or nextPctGoal >= 1:
          print("## Error!  Illegal percentile %f.  Legal values are greater than zero, but less than one." % (nextPctGoal))
          return None
      nextAbsGoal = nextPctGoal * pixelCount
      while cumulativeSum < nextAbsGoal:
         cumulativeSum += bucketCounts[nextBucketIdx]
         nextBucketIdx += 1
      threshold = int(bounds[nextBucketIdx-1])
      # print("%f brightness is at %d" % (nextPctGoal, threshold))
      retVal[nextPctGoal] = threshold
   return retVal


class KnownLetter:
   def __init__(self, letter):
      self.letter = letter
      self.tiedWith = None
      self.bestScore = None
      self.bestCoords = None
      self.candidateImg = None
      self.exemplarFile = EXEMPLAR_FILE_TEMPLATE % (letter, 1)
      self.exemplarImg = cv2.imread(self.exemplarFile, 0)
      if self.exemplarImg is None:
         raise IOError("Failed to find image data for letter %s at %s" % (letter, self.exemplarFile))

   def scoreCandidateMatch(self, sourceLabel, candidateImg):
      self.candidateImg = candidateImg
      self.tiedWith = ''
      rawResult = cv2.matchTemplate(candidateImg, self.exemplarImg, cv2.TM_CCOEFF)
      (_, self.bestScore, _, self.bestCoords) = cv2.minMaxLoc(rawResult)
      return (self.bestScore, self.bestCoords)

   # Visualize each match--high score or not
   def testForMatch(self, cellImg, cellLabel, currentBestMatch, matchLog):
      self.scoreCandidateMatch(cellLabel, cellImg)
      if currentBestMatch is None:
         # The first comparison will land here and just elect itself
         return self
      isSelfBetter = self.compareTo(currentBestMatch)
      if isSelfBetter == True:
         if LOG_SCORE_PROGRESS == True:
            # print('At %s, %s defeats %s with %f at (x=%d, y=%d) versus %f at (x=%d, y=%d)' % (cellLabel, self.letter, currentBestMatch.letter, self.bestScore, self.bestCoords[1], self.bestCoords[0], currentBestMatch.bestScore, currentBestMatch.bestCoords[1], currentBestMatch.bestCoords[0]))
            matchLog.write('%s| win|%f|%s|%d|%d|lose|%f|%s|%d|%d\n' % (cellLabel, self.bestScore, self.letter, self.bestCoords[1], self.bestCoords[0], currentBestMatch.bestScore, currentBestMatch.letter, currentBestMatch.bestCoords[1], currentBestMatch.bestCoords[0]))
         return self
      if isSelfBetter == False:
         if LOG_SCORE_PROGRESS == True:
            # print('At %s, %s is defeated by %s with %f at (x=%d, y=%d) versus %f at (x=%d, y=%d)' % (cellLabel, self.letter, currentBestMatch.letter, self.bestScore, self.bestCoords[1], self.bestCoords[0], currentBestMatch.bestScore, currentBestMatch.bestCoords[1], currentBestMatch.bestCoords[0]))
            matchLog.write('%s|lose|%f|%s|%d|%d| win|%f|%s|%d|%d\n' % (cellLabel, self.bestScore, self.letter, self.bestCoords[1], self.bestCoords[0], currentBestMatch.bestScore, currentBestMatch.letter, currentBestMatch.bestCoords[1], currentBestMatch.bestCoords[0]))
         return currentBestMatch
      if LOG_SCORE_PROGRESS == True:
         # print('At %s, %s tied %s with %f at (x=%d, y=%d) versus %f at (x=%d, y=%d)' % (cellLabel, self.letter, currentBestMatch.letter, self.bestScore, self.bestCoords[1], self.bestCoords[0], currentBestMatch.bestScore, currentBestMatch.bestCoords[1], currentBestMatch.bestCoords[0]))
         matchLog.write('%s|tied|%f|%s|%d|%d|tied|%f|%s|%d|%d\n' % (cellLabel, self.bestScore, self.letter, self.bestCoords[1], self.bestCoords[0], currentBestMatch.bestScore, currentBestMatch.letter, currentBestMatch.bestCoords[1], currentBestMatch.bestCoords[0]))

      # In case of a tie link self to currentBestMatch by copying its letter
      # and any tiedWith values to self.tiedWith, which is cleared any time
      # new scores are calculated.
      self.tiedWith = currentBestMatch.letter + currentBestMatch.tiedWith
      return self
  
   # Return True if self has the better score, False if other has the better score, and None if the
   # scores are tied.  
   def compareTo(self, other):
      if self.bestScore > other.bestScore:
         self.tiedWith = ''
         return True
      if self.bestScore < other.bestScore:
         return False
      # Special case handling for ties
      tiedWithOther = self.letter + self.tiedWith
      tiedWithSelf  = other.letter + other.tiedWith
      self.tiedWith += tiedWithSelf
      other.tiedWith += tiedWithOther
      return None

   def visualizeByOverlay(self, outputFilePath):
      topLeft = self.bestCoords
      letterImg = self.exemplarImg
      cellImg = self.candidateImg
      (lettH,lettW) = letterImg.shape
      (cellH,cellW) = cellImg.shape
      # Truncate any overflow beyond the boundary if a match's top left corner is too close to candidate
      # patch's bottom and/or right-hand edges.
      cellLettH = min(lettH+topLeft[1], cellH)
      cellLettW = min(lettW+topLeft[0], cellW)
      btmRight = (cellLettW, cellLettH)
      # print("letter: %d, cell: %d, topLeft: %d, letterBtm: %d, cellBtm: %d, actualBtm: %d" % (lettH, cellH, topLeft[1], (topLeft[1]+lettH), cellH, btmRight[1]))
      # Allocate colorspace array to hold the visualization as its computed
      mask = np.zeros((cellH,cellW,3), dtype='uint8')
      for ii in range(topLeft[1],btmRight[1]):
         for jj in range(topLeft[0],btmRight[0]):
            # mask[ii][jj][0] = cellImg[ii][jj]
            sigma = cellImg[ii][jj] / 4
            if letterImg[ii-topLeft[1]][jj-topLeft[0]] < 128:
               if sigma > 32:
                  mask[ii][jj][0] = 128 - sigma
                  mask[ii][jj][1] = 170
                  mask[ii][jj][2] = 255 - sigma 
               else:
                  mask[ii][jj][0] = 255 - sigma - sigma
                  mask[ii][jj][1] = 128 + sigma
                  mask[ii][jj][2] = 0
            else:
               sigma = 64 - sigma
               if sigma > 32:
                  mask[ii][jj][0] = 255 - sigma - sigma
                  mask[ii][jj][1] = 85
                  mask[ii][jj][2] = 128 + sigma
               else:
                  mask[ii][jj][0] = 255
                  mask[ii][jj][1] = 128 - sigma
                  mask[ii][jj][2] = 255 - sigma 
      for ii in range(0, cellH):
         for jj in range(0, topLeft[0]):
            mask[ii][jj][0] = cellImg[ii][jj]
            mask[ii][jj][1] = mask[ii][jj][2] = (cellImg[ii][jj] / 1)
         for jj in range(btmRight[0], cellW):
            mask[ii][jj][0] = cellImg[ii][jj]
            mask[ii][jj][1] = mask[ii][jj][2] = (cellImg[ii][jj] / 1)
      for jj in range(topLeft[0],btmRight[0]):
         for ii in range(0, topLeft[1]):
            mask[ii][jj][0] = cellImg[ii][jj]
            mask[ii][jj][1] = mask[ii][jj][2] = (cellImg[ii][jj] / 1)
         for ii in range(btmRight[1], cellH):
            mask[ii][jj][0] = cellImg[ii][jj]
            mask[ii][jj][1] = mask[ii][jj][2] = (cellImg[ii][jj] / 1)
      # maskedCellImg = cv2.addWeighted(mask, TICKET_OVERLAY_SOURCE_PCT, mask, TICKET_OVERLAY_EXAMPLE_PCT, 0)
      # cv2.imwrite(matchFileTemplate % ('overlay', letter), mask)
      cv2.imwrite(outputFilePath, mask)
      # Normalized visualiations of match
      # result8 = cv2.normalize(resultRaw, None, 0, 255, cv2.NORM_MINMAX, cv2.CV_8U)
      # cv2.imwrite(self.matchFileTemplate % ('normScore', letter), result8)


class Alphabet:
   def __init__(self):
      self.knownLetters = { }
      self.alphabet = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z"
      self.alphabetSplit = self.alphabet.split(' ')
      for letter in self.alphabetSplit:
         self.knownLetters[letter] = KnownLetter(letter)

ALPHABET = Alphabet()

class TicketCell:
   def __init__(self, cellImg, cellLabel, ticketLabel, matchFileTemplate):
      self.cellImg = cellImg 
      self.cellLabel = cellLabel 
      self.ticketLabel = ticketLabel 
      self.matchFileTemplate = matchFileTemplate
      self.contentString = None

   def visualizeCell(self, filePath):
      if filePath is not None and self.cellImg is not None:
         cv2.imwrite(filePath, self.cellImg)

   def identifyContent(self, exemplars, matchLog):
      # Shortcut if we already predict no content.  A Cell with content has a much more
      # difference in the luminosity of pixels at the 15th an 50th percentiles than one
      # with no content does given the same percentile range.
      blankH = BLANK_CORRECTION[0]
      blankW = BLANK_CORRECTION[1]
      blankTestImg = self.cellImg[blankH:(blankH+BLANK_SIZE),blankW:(blankW+BLANK_SIZE)]
      percentiles = findPercentiles(blankTestImg, [BLANK_CHECK_LOW, BLANK_CHECK_HIGH])
      # print("<%d> to <%d> ==> <%d> difference at <%s>" % (percentiles[BLANK_CHECK_LOW], percentiles[BLANK_CHECK_HIGH], (percentiles[BLANK_CHECK_HIGH] - percentiles[BLANK_CHECK_LOW]), self.cellLabel))
      if (percentiles[BLANK_CHECK_LOW] + BLANK_CHECK_TOLERANCE) >= percentiles[BLANK_CHECK_HIGH]:
         self.contentString = '_'
         return '_'
      # Now search for the best match by finding the one with the best score.
      bestExemplar = None
      for letter in exemplars.itervalues():
         bestExemplar = letter.testForMatch(self.cellImg, self.cellLabel, bestExemplar, matchLog)
         if VISUALIZE_CELL_MATCH == True:
            letter.visualizeByOverlay(self.matchFileTemplate % ('overlay', letter.letter))
      # If somehow no matches were found, treat the content same as a black blocked square, and assign it
      # same empty set symbol as used in that scenario.
      self.contentString = bestExemplar.letter + bestExemplar.tiedWith
      return self.contentString


TICKET_SIFT = cv2.xfeatures2d.SIFT_create(
   TICKET_MAX_FEATURES, TICKET_SIFT_OCTAVES, 0, 0, TICKET_SIFT_SIGMA)

class SiftImage: 
   def __init__(self, label, imagePath, dataDir, siftInstance):
      self.label = label
      self.imagePath = imagePath
      self.dataDir = dataDir
      self.siftInstance = siftInstance
      self.imageData = cv2.imread(imagePath, 0)
      self.keypoints = None
      self.descriptors = None
      self.featureCount = -1
      self.featuresReady = False
      if self.imageData is None:
         message = 'Could not read %s' % (self.imagePath)
         raise IOError(message)
      try:
         os.makedirs(dataDir, 0755)
      except:
         noop = 1
         # print('%s already exists' % (dataDir))

   def validateFeatures(self):
      if self.keypoints is None or self.descriptors is None:
         raise Error('Keypoints and descriptors from SIFT must be non-empty arrays, not None.')
      self.featureCount = len(self.keypoints)
      if self.featureCount != len(self.descriptors):
         message = 'fail with <%d> keypoints != <%d> descriptors' % (self.featureCount, len(self.descriptors))
         self.keypoints = None
         self.descriptors = None
         self.featureCount = -1
         self.featuresReady = False
         raise Error(message)
      if self.featureCount <= 0:
         message = 'fail with negative feature count = <%d> features' % (self.featureCount)
         self.keypoints = None
         self.descriptors = None
         self.featureCount = -1
         self.featuresReady = False
         raise Error(message)
      self.featuresReady = True
      return

   def computeFeatures(self):
      if self.featuresReady:
         return
      (self.keypoints, self.descriptors) = self.siftInstance.detectAndCompute(self.imageData, None)
      self.validateFeatures();
      npkp = np.ndarray(shape=(self.featureCount,7), dtype='float')
      for ii in range(0,self.featureCount):
         kpii = self.keypoints[ii]
         npkp[ii] = [kpii.pt[0], kpii.pt[1], kpii.size, kpii.angle, kpii.response, kpii.octave, kpii.class_id]
      npkp.dump(self.dataDir + '/keypoints.dat')
      self.descriptors.dump(self.dataDir + '/descriptors.dat')

   def loadFeatures(self):
      if self.featuresReady:
         return
      try:
         npkp = np.load(self.dataDir + '/keypoints.dat')
         self.keypoints = []
         for ii in range(0, len(npkp)):
            kpii = npkp[ii]
            self.keypoints.append(
               cv2.KeyPoint(kpii[0], kpii[1], kpii[2], kpii[3], kpii[4], int(kpii[5]), int(kpii[6])))
         self.descriptors = np.load(self.dataDir + '/descriptors.dat')
         self.validateFeatures()
      except:
         print("## Failed to load features for %s from cache.  Computing instead..." % (self.label))
         self.computeFeatures()

   def getFeatureCount(self):
      return self.featureCount

   def getKeypoints(self):
      return self.keypoints

   def getDescriptors(self):
      return self.descriptors

   def getLabel(self):
      return self.label

   def getImageData(self):
      return self.imageData

   def getFeatures(self):
      return (self.keypoints, self.descriptors)

   def getAllDetails(self):
      return (self.label, self.imageData, self.keypoints, self.descriptors, self.featureCount)


class KnownExample(SiftImage):
   def __init__(self):
      SiftImage.__init__(self, 'knownExample', 'knownExample/content.tif', 'knownExample', TICKET_SIFT)

      self.cornerCoordinates = np.load(self.dataDir + '/corners.dat')
      gridRegion = np.load(self.dataDir + '/grid.dat')
      for ii in range(0,NUM_CELLS):
         for jj in range(0,NUM_CELLS):
            self.cornerCoordinates[ii][jj] += CELL_CORRECTION
      top = max(gridRegion[1][0][1], gridRegion[2][0][1]) + 1
      bottom = min(gridRegion[0][0][1], gridRegion[3][0][1])
      right = max(gridRegion[2][0][0], gridRegion[3][0][0]) + 1
      left = min(gridRegion[0][0][0], gridRegion[1][0][0])
      # print "%d:%d,%d:%d" % (bottom, top, left, right)
      self.gridRegion = np.ogrid[bottom:top,left:right]

   # TODO Clone collections when returning them for immutability of object.
   def getKnownCornerCoordinates(self):
      # This will return as a no-op if the computate result is already cached.
      self.computeFeatures()
      return self.cornerCoordinates

   # TODO Clone collections when returning them for immutability of object.
   def getKnownGridRegion(self):
      # This will return as a no-op if the computate result is already cached.
      self.computeFeatures()
      return self.gridRegion


KNOWN_EXAMPLE = KnownExample()
KNOWN_EXAMPLE.loadFeatures()


class CrosswordTicket(SiftImage):
   # Read the geometry information about the known sample ticket.  Once we 
   # have located a context hull for the corresponding object in an unknown
   # input, we'll use homography to derive a warping matrix that
   # maps coordinates in the known image's bounded context to the same
   # _relative- location in the uknown image's equivalent bounded context.

   def __init__(self, label, imgFile, dataDir):
      SiftImage.__init__(self, label, imgFile, dataDir, TICKET_SIFT)


class MatchedPair:
   def __init__(self, knownExample, unknownTicket, dataDir):
      self.knownExample = knownExample
      self.unknownTicket = unknownTicket
      self.dataDir = dataDir
      self.M = None
      self.iM = None
      self.matchesMask = None
      self.goodMatches = None
      self.knownPoints = None
      self.unknownPoints = None
      self.warpedImg = None
      self.matchReady = False
      try:
         os.makedirs(dataDir, 0755)
      except:
         noop = 1
         # print('%s already exists' % (dataDir))
         
   def computeCrosswordLocation(self):
      kpE = self.knownExample.getKeypoints()
      desE = self.knownExample.getDescriptors()
      kpT = self.unknownTicket.getKeypoints()
      desT = self.unknownTicket.getDescriptors()
      # Shortcut this routine if we don't have any keypoints to consider from ticket image.
      if kpT is None or desT is None:
         print "## Skipping FLANN since there are no training descriptors!"
         return False
      # Shortcut this routine if we don't have enough descriptors to achieve the required number of matches.
      minMatch = min((TICKET_MIN_MATCH_PERCENT * min(len(kpE), len(kpT))), 20)
      if len(desT) < minMatch or len(desT) < minMatch:
         print "## Skipping FLANN with only %d train descriptors" % (len(desT))
         return False
      print "## Ready to FLANN with %d query and %d train descriptors" % (len(desE), len(desT))
      flann = cv2.FlannBasedMatcher(INDEX_PARAMS, SEARCH_PARAMS)
      # store all the good matches as per Lowe's ratio test.
      self.goodMatches = []
      for m,n in flann.knnMatch(desE,desT,2):
         if m.distance <= (TICKET_MAX_DIST_RATIO * n.distance):
            self.goodMatches.append(m)
      if len(self.goodMatches)>=minMatch:
         label = self.unknownTicket.getLabel()
         print("## Hit on %s with %d out of %d matches" % (label, len(self.goodMatches), minMatch))
         self.knownPoints = np.float32([ kpE[m.queryIdx].pt for m in self.goodMatches ]).reshape(-1,1,2)
         self.unknownPoints = np.float32([ kpT[m.trainIdx].pt for m in self.goodMatches ]).reshape(-1,1,2)
      else:
         print "## Miss on %s with %d out of %d matches" % (label, len(goodMatches), minMatch)
         self.goodMatches = None
         return False
      (self.M, self.matchesMask) = cv2.findHomography(
         self.knownPoints, self.unknownPoints, cv2.RANSAC, 5.0)
      if self.M is None:
         print("## No homography found for derived matching from known example to %s" % (self.unknownTicket.filePath))
         self.knownPoints = None
         self.unknownPoints = None
         self.goodMatches = None
         self.matchesMask = None
         self.matchReady = False
         return False
      # Warp so we can use cell locations using coordinates from the query image to find letters.
      (_, self.iM) = cv2.invert(self.M)
      ticketImg = self.unknownTicket.getImageData()
      (numRows,numCols) = self.knownExample.getImageData().shape
      self.warpedImg = cv2.warpPerspective(ticketImg, self.iM, (numCols, numRows))
      self.dumpCrosswordLocation()
      self.matchReady = True
      return self.matchReady

   def dumpCrosswordLocation(self):
      cv2.imwrite(self.dataDir + '/warped.tif', self.warpedImg)
      self.knownPoints.dump(self.dataDir + '/knownPoints.dat')
      self.unknownPoints.dump(self.dataDir + '/unknownPoints.dat')
      npgm = np.ndarray(shape=(len(self.goodMatches),4), dtype='float')
      for ii in range(0,len(self.goodMatches)):
         gmii = self.goodMatches[ii]
         npgm[ii] = [gmii.queryIdx, gmii.trainIdx, gmii.imgIdx, gmii.distance]
      npgm.dump(self.dataDir + '/goodMatches.dat')
      self.matchesMask.dump(self.dataDir + '/matchesMask.dat')
      self.M.dump(self.dataDir + '/M.dat')
      self.iM.dump(self.dataDir + '/iM.dat')

   def loadCrosswordLocation(self):
      if self.matchReady:
         return
      try:
         self.knownPoints = np.load(self.dataDir + '/knownPoints.dat')
         self.unknownPoints = np.load(self.dataDir + '/unknownPoints.dat')
         npgm = np.load(self.dataDir + '/goodMatches.dat')
         self.goodMatches = []
         for ii in range(0, len(npgm)):
            gmii = npgm[ii]
            self.goodMatches.append(
               cv2.DMatch(int(gmii[0]), int(gmii[1]), int(gmii[2]), gmii[3]))
         self.matchesMask = np.load(self.dataDir + '/matchesMask.dat')
         self.M = np.load(self.dataDir + '/M.dat')
         self.iM = np.load(self.dataDir + '/iM.dat')
         self.warpedImg = cv2.imread(self.dataDir + '/warped.tif', 0)
      except:
          self.knownPoints = None
      if self.knownPoints is None or self.unknownPoints is None or self.goodMatches is None or self.matchesMask is None or self.M is None or self.iM is None or self.warpedImg is None:
         print("## Failed to load match from %s to %s from cache.  Computing instead." % (self.knownExample.label, self.unknownTicket.label))
         self.knownPoints = None
         self.unknownPoints = None
         self.goodMatches = None
         self.M = None
         self.iM = None
         self.matchReady = False
         self.computeCrosswordLocation()
      else:
         self.matchReady = True
      return self.matchReady

   def processCrosswordGrid(self):
      if self.matchReady == False:
         self.loadCrosswordLocation()
         if self.matchReady == False:
            print "## Cannot interpret crossword extract unless its location can be found!"
            return None
      cornerCoordinates = self.knownExample.getKnownCornerCoordinates()
      cellFileTemplate = self.dataDir + '/cell_%dx%d.tif'
      matchLog = file(self.dataDir + '/templateMatchScores.log', 'w')
      xwGrid = np.chararray(shape=(NUM_CELLS,NUM_CELLS), itemsize=20)
      xwGrid.fill('_')
      xwStr = ''
      xwPrint = ''
      for ii in range(0,NUM_CELLS):
         for jj in range(0,NUM_CELLS):
            (bottom,left) = cornerCoordinates[ii][jj]
            (top,right) = cornerCoordinates[ii][jj] + (CELL_SIZE,CELL_SIZE)
            # print "%d:%d,%d:%d" % (bottom, top, left, right)
            cellImg = self.warpedImg[bottom:top,left:right]
            cellLabel = CELL_LABEL_TEMPLATE % (ii, jj, self.unknownTicket.getLabel())
            cellCoordsStr = '%dx%d' % (ii, jj)
            matchFileTemplate = self.dataDir + '/match-%s_' + cellCoordsStr + '_%s.tif'
            cellObject = TicketCell(cellImg, cellLabel, ticketLabel, matchFileTemplate)
            if VISUALIZE_CELL_CLIP == True:
               cellFilePath = cellFileTemplate % (ii,jj)
               cellObject.visualizeCell(cellFilePath)
            xwGrid[ii][jj] = cellObject.identifyContent(ALPHABET.knownLetters, matchLog)
            xwStr += xwGrid[ii][jj]
            xwPrint += xwGrid[ii][jj] + ' '
         xwPrint += "\n"
      print xwPrint
      print xwStr
      output = file(self.dataDir + '/xwGrid.txt', 'w')
      output.write(xwStr)
      output.close()
      matchLog.close()
      xwGrid.dump(self.dataDir + '/xwGrid.dat')
      return xwGrid

   def visualizeCrosswordExtraction(self):
      if self.warpedImg is None:
          print "## Cannot visualize crossword extract until its location has been found!"
          return False
      # Extract a crop of just the target area for debug purposes
      extractedImg = self.warpedImg[KNOWN_EXAMPLE.getKnownGridRegion()]
      cv2.imwrite(self.dataDir + '/warpGridCrop.tif', extractedImg)
      return True

   def visualizeCrosswordLocation(self):
      if self.M is None:
           print "Cannot visualize location until that location has been found!"
           return False
      M = self.M
      kpT = self.unknownTicket.getKeypoints()
      kpE = self.knownExample.getKeypoints()
      knownImg = self.knownExample.getImageData()
      unknownImg = self.unknownTicket.getImageData()
      knownGridRegion = self.knownExample.getKnownGridRegion()
      # Translate a border around the crossword grid in the query image into the coordinate system of the
      # ticket being analyzed, using keyfeature correlation to identity the kernel matrix required to do so.
      unknownRegion = np.int32(cv2.perspectiveTransform(np.float32(self.knownGridRegion), M))
      decoratedImg = unknownImg.copy()
      decoratedImg = cv2.drawKeypoints(decoratedImg, kpT, None, color=(0,128,192)) #, flags=0)
      decoratedImg = cv2.polylines(decoratedImg, [unknownRegion], True, (160,0,0), 5, cv2.LINE_AA)
      cv2.imwrite(self.dataDir + '/warpFullTicket.tif', decoratedImg)
      # Create side-by-side visualization of the perspective transformation's source match support.
      draw_params = dict(matchColor = (0,255,0), # draw matches in green color
                         singlePointColor = (224,0,96), # draw unmatched keyponts in red?
                         matchesMask = self.matchesMask.ravel().tolist(), # draw only inliers
                         flags = 2)
      flannMatchImg = cv2.drawMatches(
         knownImg, kpE, decoratedImg, kpT, self.goodMatches, None, **draw_params)
      cv2.imwrite(self.dataDir + '/flannWarp.tif', flannMatchImg)
      unknownRegion.dump(self.dataDir + '/grid.dat')
      return True


###
# MAIN ROUTINE
###

print "Loading..."

# Read the query image and find its keypoints and their descriptors with ORB

# Iterate over the training images
for ii in range(1,19):
   # Construct next file name and search it
   dataDir = '%s/sample%02d' % (DATA_DIR, ii)
   ticketFile = 'train5/sample%02d.tif' % (ii)
   ticketLabel = 'sample%02d' % (ii)
   print "Ingesting " + ticketFile
   ticketObject = CrosswordTicket(ticketLabel, ticketFile, dataDir)
   ticketObject.loadFeatures()
   print "Matching  " + ticketFile
   matchObject = MatchedPair(KNOWN_EXAMPLE, ticketObject, dataDir)
   if matchObject.loadCrosswordLocation():
      # Save images of the matching and extracted crossword grid
      if VISUALIZE_TICKET_CLIP == True:
         matchObject.visualizeCrosswordExtraction()
      if VISUALIZE_TICKET_MATCH == True:
         matchObject.visualizeCrosswordLocation()
      # Analyze the crossword and interpret its content!
      print "Analyzing  " + ticketFile
      matchObject.processCrosswordGrid() 
      print "** Finished crossword interpretation from %s" % (ticketFile)
   else:
      print "## No feature matches found between query template and %s" % (ticketFile)

