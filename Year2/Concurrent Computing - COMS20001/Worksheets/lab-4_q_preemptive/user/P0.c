#include "P0.h"

void P0() {
  char* x = "hello world, I'm P0\n";

  while( 1 ) {
    //printF("hello world, I'm P0\n");
    write( 0, x, 20 );// yield();
  }
}

void (*entry_P0)() = &P0;