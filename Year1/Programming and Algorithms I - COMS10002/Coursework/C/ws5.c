/*
 * =====================================================================================
 *
 *       Filename:  ws5.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  30/10/14 16:18:02
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Mr. Andreas Georgiou (ag14774), a.georgiou.2014@bristol.ac.uk
 *   Organization:  University of Bristol
 *
 * =====================================================================================
 */

#include <stdio.h>

int f(int n){
    if(n==0)return 0;
    if(n==1)return 1;
    if(n>=2)return f(n-1)+f(n-2);
}

int main(){
    printf("%d",4);
}
