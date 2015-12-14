function prepareObject(value) {
  var bits, isNegative = (value < 0);

  if (isNegative) {
    // For negative X, we use the two's complement of X.
    // The two's complement of X is one plus a binary inversion of
    // -1 * X
    //
    // We cannot get the twos complement represntation X from
    // X.toString(2) when X is negative, but we can still get something
    // usable with the following trick...
    //
    // Let X be negative...
    // BIN(X)          == INV(BIN(-1 * X)) + 1
    // BIN(X) - 1      == INV(BIN(-1 * X))
    // INV(BIN(X) - 1) == BIN(-1 * X)
    //
    // Let T = (-1 * X) - 1.  The closer X is to 0, the smaller T
    // gets.  The smallest negative X is -1, for which T is 0, so
    // T is non-negative for all X which are negative.  That means
    // we can get the binary representation of all T from toString(2).
    //
    // It can be demonstrated that the binary representation of T is
    // the binary inversion of the true binary representation of X,
    // at least for all bits that are not attributable for overflow.
    //
    // This means that if we can account for the overflow of both
    // negative and non-negative scenarios, we can use the binary
    // representation of -X - 1 to account for the low-order bits.
    //
    // Where X is negative
    // MASK(BIN(T), NBITS(X)) == MASK(BIN(X), NBITS(X))
    bits = ((-1 * value) - 1).toString(2);
  } else {
    bits = value.toString(2);
  }

  return {
    value: value,
    negative: isNegative,
    bits: bits,
    length: bits.length
  };
}

function isEqualTo(bitOne, bitTwo) {
  return bitOne === bitTwo;
}

function isNotEqualTo(bitOne, bitTwo) {
  return bitOne !== bitTwo;
}


function ham(numOne, numTwo) {
  // TODO: validate input types and non-nullness

  // Analyze the values to extract isNegative, bits, and length.
  // For negative values, this function will return the inverse
  // of the actual binary sequence for the negative number.
  // This inversion means that there will be 0-padding instead of
  // 1-padding and the non-padded bits will be the opposite of
  // what twos complement representation would have called for.
  var objOne = prepareObject(numOne),
      objTwo = prepareObject(numTwo),
      ii = 0, jj = 0, hamming = 0;

  // Make sure that if either string is longer, it is objTwo.
  if (objOne.length > objTwo.length) {
    var swap = objOne;
    objOne = objTwo;
    objTwo = swap;
  }

  console.log('######################### NEW SCENARIO: (' + numOne + ', ' + numTwo + ') ###################');
  console.log( 'objOne: ' + JSON.stringify(objOne));
  console.log( 'objTwo: ' + JSON.stringify(objTwo));

  // Analyze three distinct regions, and presume a 32 bit value.
  // Let longBit be the length of the longer bit string, and
  // shortBit be the length of the shorter bit string.

  // == Region One ==
  // There are 32 - longBit bits that are all padding values
  // for both strings.  If both were negative or both were not
  // negative, the hamming distance for this region is 0,
  // otherwise it is (32 - longBit)
  if (objOne.negative !== objTwo.negative) {
    hamming = 32 - objTwo.length;
  }
  console.log('** region one =>', hamming);
  console.log();

  // == Region Two ==
  // There are longBit - shortBit bits in a region where the
  // shorter bit string is still only providing pad values, but
  // the longer string's contribution becomes value-dependent.
  //
  // The effect of either value being negative depends upon its
  // role as either shorter or longer than its peer.
  // -- If the longer value is negative, its bits through this
  //    region are the inverse of those of its bits property
  //    due to the use of BITS(-X - 1) (an incomplete yet
  //    sufficient inversion of X's twos compliment representation)
  // -- If the shorter value is negative, the padding value to
  //    use when testing is 1, otherwise it is 0.
  var padLen   = objTwo.length - objOne.length,
      padValue = objOne.negative ? '1' : '0',
      compFn   = objTwo.negative ?  isNotEqualTo :  isEqualTo,
      compStr  = objTwo.negative ? 'isNotEqualTo': 'isEqualTo';

  while (ii<padLen) {
    var isAlligned = compFn(objTwo.bits.charAt(ii), padValue);
    if (isAlligned == false) {
      hamming = hamming+1;
    }

    console.log(
      'padValOne: ' + padValue +
      '; indexTwo: ' + ii +
      '; valueTwo: ' + objTwo.bits.charAt(ii++) +
      '; compFn: ' + compStr +
      '; isAlligned: ' + isAlligned +
      '; newDistance: ' + hamming
    );
  }
  console.log();
  console.log('** region two =>', hamming);
  console.log();

  // The third regions are where bits are contributed by both strings.
  // If both values were positive or both were negative, then inequal
  // values increase the hamming distance.  Otherwise, equal values
  // increase the hamming distance.
  if (objOne.negative === objTwo.negative) {
    compFn = isEqualTo;
    compStr = 'isEqualTo';
  } else {
    compFn = isNotEqualTo;
    compStr = 'isNotEqualTo';
  }

  while(ii<objTwo.length) {
    var isAlligned = compFn(
      objOne.bits.charAt(jj), objTwo.bits.charAt(ii)
    );
    if (isAlligned === false) {
      hamming = hamming + 1;
    }

    console.log(
      'shortIndex: ' + jj +
      '; shortValue: ' + objOne.bits.charAt(jj++) +
      '; longIndex: ' + ii +
      '; longValue: ' + objTwo.bits.charAt(ii++) +
      '; compFn: ' + compStr +
      '; isAlligned: ' + isAlligned +
      '; newDistance: ' + hamming
    );
  }
  console.log();
  console.log('** region three and result => ', hamming);
  console.log();
  console.log();
  console.log();

  return hamming;
}

var Chance = require('chance');
var c = new Chance();
var mt = c.mersenne_twister();
var ii, jj;
mt.init_genrand();

function delta() {
  return 1 + mt.genrand_int32() % 64;
}
function value() {
  return mt.genrand_int31() * (2 * (0.5 - (mt.genrand_int31()%2)));
}

ii = 300;
while( ii-- > 0 ) {
  ham(value(), value());
}
process.exit(0);

for( ii=-303 + delta(); ii<255; ii = ii + delta()) {
  for( jj=-303 + delta(); jj<255; jj = jj + delta()) {
    ham(ii, jj);
  }
}
process.exit(0);



ham(5,3);
ham(0, -7);
ham(-7, 0);
ham(7, -7);
ham(-24, 7);
ham(24, 7);
ham(31, 7);
ham(-31, -7);
ham(-31, 7);
ham(-7, 31);
ham(24, 31);
ham(-31, 24);
ham(24, -31);
ham(-31, 31);
ham(24, -24);
ham(-24, 0);
ham(0, -31);





