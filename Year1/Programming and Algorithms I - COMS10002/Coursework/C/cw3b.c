/*
 * =====================================================================================
 *
 *       Filename:  CW3a.c
 *
 *    Description:  Coursework 3 Part 2
 *
 *        Version:  1.0
 *        Created:  10/14/2014 02:21:36 PM
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Mr. Andreas Georgiou (ag14774), a.georgiou.2014@bristol.ac.uk
 *   Organization:  University of Bristol
 *
 * =====================================================================================
 */

#include <stdio.h>
#include <math.h>

double Power(double X,int Y){
	int i = 0;
	double r=1.0;
	while(i<abs(Y)){
	    r=r*X;
	    i++;
	}
	if(Y<0){
	    return 1/r;
	}
	return r;
}

int main(void){
	printf("\tThe sum is: %.2f\n",1000*Power(1.05,25));
	printf("\tThe amount of money you have to put away now, so that you\n\thave 10000 pounds in 12 years is:%f\n",10000*Power(1.04,-12));
	return 0;
}
