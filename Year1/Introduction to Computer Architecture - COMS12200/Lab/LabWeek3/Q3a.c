/*
 * =====================================================================================
 *
 *       Filename:  Q3a.c
 *
 *    Description:  Returns 0 for positive and 1 for negative
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

int sign(int8_t x){
    return (x>>7)&1;
}

int main(void){
    printf("%d",sign(-10));
    return 0;
}
