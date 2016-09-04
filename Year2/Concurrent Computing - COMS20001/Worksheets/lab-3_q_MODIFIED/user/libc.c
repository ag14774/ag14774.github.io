#include "libc.h"

void yield() {
  asm volatile( "mov r0, #0 \n"
                "svc #0     \n"
              :
              :
              : "r0" );
}

int write( int fd, void* x, size_t n ) {
  int r;

  asm volatile( "mov r0, #1 \n"
                "mov r1, %1 \n"
                "mov r2, %2 \n"
                "mov r3, %3 \n"
                "svc #0     \n"
                "mov %0, r0 \n" 
              : "=r" (r) 
              : "r" (fd), "r" (x), "r" (n) 
              : "r0", "r1", "r2", "r3" );

  return r;
}
