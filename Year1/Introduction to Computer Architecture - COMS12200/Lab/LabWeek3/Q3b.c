/*
 * =====================================================================================
 *
 *       Filename:  Q3b.c
 *
 *    Description:  Returns the negative of a number
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

int8_t neg(int8_t x){
    return (~(x)+1);
}

int main(void){
    printf("%d",neg(10));
    return 0;
}
