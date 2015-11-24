/*
 * =====================================================================================
 *
 *       Filename:  test.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  11/22/2014 09:58:31 PM
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Mr. Andreas Georgiou (ag14774), a.georgiou.2014@bristol.ac.uk
 *   Organization:  University of Bristol
 *
 * =====================================================================================
 *
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
int calc(char *s){
    int a=strlen(s);
    return a;
}

int main(){
    char *s=NULL;
    char f[20];
    scanf("%s",s);
    int a=calc(f);
    //s=malloc(strlen(f)+1);
    //strcpy(s,f);
    //int b=calc(s);
    printf("STATIC: %d\n",a);
    //printf("DYNAMIC: %d\n",b);
}
