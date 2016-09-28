#!/usr/local/bin/python

from xwvision02 import CrosswordTicket, MatchedPair, KNOWN_EXAMPLE

VISUALIZE_TICKET_CLIP = False
VISUALIZE_TICKET_MATCH = True

###
# MAIN ROUTINE
###

print "Loading..."

# Read the query image and find its keypoints and their descriptors with ORB

# Iterate over the training images
for ii in range(1,19):
   # Construct next file name and search it
   dataDir = 'data28/sample%02d' % (ii)
   ticketFile = 'testInputs/sample%02d.tif' % (ii)
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

