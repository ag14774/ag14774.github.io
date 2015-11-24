/*
 * =====================================================================================
 *
 *       Filename:  ws3_1.c
 *
 *    Description:  Worksheet 3 Extras 1
 *
 *        Version:  1.0
 *        Created:  10/16/2014 08:48:20 PM
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
#define BITS    32
int * int2bin(int X){
    const int MSB=1<<(BITS-1);
    int i;
    int *result=malloc(sizeof(int)*BITS);
    for(i=0;i<=(BITS-1);i++){result[i]=0;}    
    if(X<0){
        result[BITS-1]=1;
        X=X-MSB;
        printf("%d\n",MSB);
    }
    for(i=(BITS-2);i>=0;i--){
        result[i]=(X>>i);
        if (X>>i){X=X-(1<<i);}
    }
    return result;
}

int main(){
    int i;
    int *p=int2bin(43385);
    for(i=(BITS-1);i>=0;i--){
        printf("%d",*(p+i));
    }
    free(p);
    return 0;
}
