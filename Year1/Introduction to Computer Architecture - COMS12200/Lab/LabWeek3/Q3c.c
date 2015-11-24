/*
 * =====================================================================================
 *
 *       Filename:  Q3c.c
 *
 *    Description:  Returns x%(2^n)
 *
 *        Version:  1.0
 *        Created:  10/18/2014 03:41:13 PM
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Mr. Andreas Georgiou (ag14774), a.georgiou.2014@bristol.ac.uk
 *   Organization:  University of Bristol
 *
 * =====================================================================================
 */

#include <stdio.h>
#include <stdint.h>

uint8_t mod(uint8_t x, int n){
    return x&((1<<n)-1);
}

int main(void){
    printf("%d",mod(10,2));
    return 0;
}
