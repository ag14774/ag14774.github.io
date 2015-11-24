#include "lab-3.q.h"

void rep( int8_t x ) {
      printf( "%4d_{(10)} = ", x );

        for( int i = ( BITSOF( x ) - 1 ); i >= 0; i-- ) {
                printf( "%d", ( x >> i ) & 1 );
                  }

          printf( "_{(2)}\n" );
}


int main( int argc, char* argv[] ) {
    int8_t t;
    /* for (t=-128;t<=127;t++){ *   <--The commented code will end up in an endless loop because it overflows.
     *   rep(t);                *      When it gets to 127, it prints its binary representation and by adding 1
     * }                        */  /* it gets back to -128.                                                  */
    for (t=-128;t<=126;t++){
        rep(t);
    }
    rep(t);
    return 0;
}
