import sys, itertools

# This function performs a string comparison, allowing don't-care 
# characters (which in this cases are question marks).  Basically,
# it iterates through each i-th pair of corresponding characters: 
# if one or other is a don't-care we ignore them and carry on, 
# otherwise if there is a mismatch we return false. 

def match( x, y ) :
  if ( len( x ) != len( y ) ) :
    return False

  for i in range( 0, len( x ) ) :
    if ( ( x[ i ] == '?' ) or ( y[ i ] == '?' ) ) :
      continue
    if (   x[ i ] !=            y[ i ]          ) :
      return False

  return True

# Read a line specifying the number of input and output bits.

l = sys.stdin.readline()

n = int( l.strip().split( " " )[ 0 ] ) # number of address (or  input) bits
m = int( l.strip().split( " " )[ 1 ] ) # number of value   (or output) bits

# T is a dictionary (mapping addresses to values) of specified 
# entries constructed from lines on stdin; A is a dictionary of 
# all possible addresses.

T = dict() ; A = [ "".join( t ) for t in itertools.product( "01", repeat = n ) ]

# Read each remaining line, and update T accordingly.

for l in sys.stdin.readlines() :
  # Each line has three fields: a label (which is ignored), an
  # address and a value.

  t = "".join( l.strip().split( " " )[ 1 : ] )

  a = t[ 0 : n + 0 ]
  x = t[ n : n + m ]

  # Try to match all possible addresses against the one read, 
  # and for each match (keeping in mind don't-cares can mean
  # more than one) map it to the value.

  for k in A :
    if ( match( k, a ) ) :
      T[ int( k, 2 ) ] = int( x.replace( '?', '0' ), 2 )

# For completness, map any unspecified addresses that remain
# to a zero value.

for k in [ int( t, 2 ) for t in A ] :
  if ( not k in T ) :
    T[ k ] = 0

# Finally, print out the Logisim-compatible ROM content; this
# is basically just a header followed by each value (sorted by
# address).

print "v2.0 raw"

for k in sorted( T.keys() ) :
  print "%02X" % ( T[ k ] )
