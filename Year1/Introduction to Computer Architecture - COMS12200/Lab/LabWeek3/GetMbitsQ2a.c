#include "lab-3.q.h"

void rep( uint8_t x ) {
      printf( "%4d_{(10)} = ", x );

        for( int i = ( BITSOF( x ) - 1 ); i >= 0; i-- ) {
                printf( "%d", ( x >> i ) & 1 );
                  }

          printf( "_{(2)}\n" );
}

void getMbits( uint8_t x, uint8_t Pos,uint8_t M){               /*e.g x=15 -- 00001|11|1 Pos=3 M=2  */
    printf( "%4d_%d bits starting from Position %d:",x,M,Pos);  /*Result is   00000 11 0            */
    x=(x>>(Pos-M))&((1<<M)-1);
    rep(x<<(Pos-M));
}

int main( int argc, char* argv[] ) {
    uint8_t t=15;
    getMbits(t,3,2);
    return 0;
}
