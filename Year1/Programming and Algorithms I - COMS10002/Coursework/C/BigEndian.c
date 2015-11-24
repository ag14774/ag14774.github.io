/*
 * =====================================================================================
 *
 *       Filename:  test.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  05/11/14 11:34:52
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Mr. Andreas Georgiou (ag14774), a.georgiou.2014@bristol.ac.uk
 *   Organization:  University of Bristol
 *
 * =====================================================================================
 */
#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
int main(void){
    unsigned int *ptr;
    ptr=(unsigned int*)malloc(2*sizeof(unsigned int));
    *ptr=3;
    *(ptr+1)=5;
    printf("%d  %p\n", *ptr,ptr);
    printf("%d  %p\n",*(ptr+1),ptr+1);
    uint8_t *temp=NULL;
    temp=(uint8_t*)ptr+2;
    ptr=temp;
    printf("%d   %p\n",*ptr,ptr);
    return 0;
}
