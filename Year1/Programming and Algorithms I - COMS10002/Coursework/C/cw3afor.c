/*
 * =====================================================================================
 *
 *       Filename:  CW3a.c
 *
 *    Description:  Coursework 3 Part 1
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

double Power(double X,unsigned int Y){
	double r=1.0;
	int i;
	for(i=0;i<Y;i++){
		r=r*X;
	}
	return r;
}

int main(void){
	printf("The sum is: %.2f",1000*Power(1.05,25));
	return 0;
}
