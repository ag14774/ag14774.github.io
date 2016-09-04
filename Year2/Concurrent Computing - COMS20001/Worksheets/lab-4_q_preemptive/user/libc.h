#ifndef __LIBC_H
#define __LIBC_H

#include <stddef.h>
#include <stdint.h>
#include <string.h>
#include <stdarg.h>

// cooperatively yield control of processor, i.e., invoke the scheduler
void yield();

// write n bytes from x to the file descriptor fd
int write( int fd, void* x, size_t n );

char* itoa(int i, char b[]);

int print(uint8_t isInteger, ...);

int printF(const char* f,...);

#endif
