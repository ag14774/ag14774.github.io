/*
 * =====================================================================================
 *
 *       Filename:  CW3a.c
 *
 *    Description:  Coursework 3 Part 3 
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

double powerindian(double x,int n){
	double r=1.0;
	if(n==0){
	  return r;
	}
	if(n==1){
	  return x;
	}
	if(n==2){
	  return x*x;
	}
	if(n%2==0){
	  return powerindian(powerindian(x,n/2),2);
	}
	else{
	  return x*powerindian(x,n-1);
	}
}

double Power(double X,int Y){
	if(Y<0){
	    return 1/powerindian(X,abs(Y));
	}
	return powerindian(X,Y);
}

int main(void){
	printf("\tThe sum is: %.2f\n",1000*Power(1.05,25));
	printf("\tThe amount of money you have to put away now, so that you\n\thave 10000 pounds in 12 years is:%f\n",10000*Power(1.04,-12));
	printf("\t1.0000000001 to the power of 1000000000 is:%f\n",Power(1.0000000001,1000000000));
	return 0;
}
