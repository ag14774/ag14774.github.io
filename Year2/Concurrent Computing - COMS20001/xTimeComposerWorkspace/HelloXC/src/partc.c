/*
 * partc.c
 *
 *  Created on: Oct 2, 2015
 *      Author: ageorgiou
 */

#include <stdio.h>
#include <platform.h>

extern void delay(uint delay);

void hello(int tileNo){
    delay((3-tileNo)*1000);
    printf("Hello from tile#%d.\n",tileNo);
}
