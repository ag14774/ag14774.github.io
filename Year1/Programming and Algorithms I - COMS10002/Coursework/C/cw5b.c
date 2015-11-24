/*
 * =====================================================================================
 *
 *       Filename:  cw5b.c
 *
 *    Description:  Coursework 5 Part B
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

box combine(box A,box B){
    box result;
    (A.bl.x<B.bl.x) ? (result.bl.x=A.bl.x) : (result.bl.x=B.bl.x);
    (A.bl.y<B.bl.y) ? (result.bl.y=A.bl.y) : (result.bl.y=B.bl.y);
    (A.tr.x>B.tr.x) ? (result.tr.x=A.tr.x) : (result.tr.x=B.tr.x);
    (A.tr.y>B.tr.y) ? (result.tr.y=A.tr.y) : (result.tr.y=B.tr.y);
    return result;
}

void printBound(box bound){
    printf("(%d,%d) (%d,%d)\n",bound.bl.x,bound.bl.y,bound.tr.x,bound.tr.y);
}

int main(){
    int x1,y1,x2,y2;
    unsigned int r1,r2;
    printf("Enter the properties of the two circles in the format x1 y1 r1 x2 y2 r2:\n");
    scanf("%d %d %u %d %d %u",&x1,&y1,&r1,&x2,&y2,&r2);
    circle A=createCircle(x1,y1,r1);
    circle B=createCircle(x2,y2,r2);
    box boxA=createBox(A);
    box boxB=createBox(B);
    box boxAB=combine(boxA,boxB);
    printBound(boxAB);
    return 0;
}
