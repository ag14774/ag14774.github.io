/*
 * =====================================================================================
 *
 *       Filename:  cw5a.c
 *
 *    Description:  Coursework 5 Part A
 *
 *        Version:  1.0
 *        Created:  30/10/14 13:31:53
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Mr. Andreas Georgiou (ag14774), a.georgiou.2014@bristol.ac.uk
 *   Organization:  University of Bristol
 *
 * =====================================================================================
 */

#include <stdio.h>

struct coords{
    int x;
    int y;
};

typedef struct{
    struct coords centre;
    unsigned int r;
}circle;

typedef struct{
    struct coords bl;
    struct coords tr;
}box;

circle createCircle(int cX, int cY, unsigned int R){
    circle output;
    output.centre.x=cX;
    output.centre.y=cY;
    output.r=R;
    return output;
}

box createBox(circle circ){
    box bound;
    bound.bl.x=circ.centre.x-circ.r;
    bound.bl.y=circ.centre.y-circ.r;
    bound.tr.x=circ.centre.x+circ.r;
    bound.tr.y=circ.centre.y+circ.r;
    return bound;
}

void printBound(box bound){
    printf("(%d,%d) (%d,%d)\n",bound.bl.x,bound.bl.y,bound.tr.x,bound.tr.y);
}

int main(){
    const int X=1;
    const int Y=2;
    const unsigned int R=3;
    circle myCircle=createCircle(X,Y,R);
    box myBox=createBox(myCircle);
    printBound(myBox);
    return 0;
}
