/*
 * =====================================================================================
 *
 *       Filename:  ws6.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  11/11/2014 09:22:45 PM
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Mr. Andreas Georgiou (ag14774), a.georgiou.2014@bristol.ac.uk
 *   Organization:  University of Bristol
 *
 * =====================================================================================
 */
#include <stdio.h>
int g(int n, int a, int b){
    int temp;
    switch(n){
    case 0:return a;
    case 1:return b;
    default:{
            return g(n-1,a,b)+g(n-2,a,b);
            }
    }
}

int main(void){
    printf("%d\n",g(300,1,2));
    return 0;
}
