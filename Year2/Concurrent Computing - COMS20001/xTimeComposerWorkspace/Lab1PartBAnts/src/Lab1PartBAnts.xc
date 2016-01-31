/*
 * Lab1PartBAnts.xc
 *
 *  Created on: Oct 2, 2015
 *      Author: ageorgiou
 */

#include <stdio.h>

{int,int,int} ant(unsigned int id, const unsigned char w[3][4],
                  unsigned int x, unsigned int y ) {

    unsigned int food = 0;
    printf("Initializing ant #%u on (%u,%u)\n",id,x,y);

    for(int i=0;i<2;i++){
        uint yOnEast  = (y+1)%4;
        uint xOnSouth = (x+1)%3;

        if(w[xOnSouth][y]>w[x][yOnEast]){
            x = xOnSouth;
        }else{
            y = yOnEast;
        }

        food += w[x][y];

        printf("Ant #%d moved to (%d,%d) with new food count %d\n", id, x, y, food);
    }

    printf("Ant #%d finishes work at position (%d,%d) with food count %d\n", id, x, y, food);

    return {food, x, y};

}

int main(void){
    const unsigned char world[3][4] = {{10,0,1,7},{2,10,0,3},{6,8,7,6}};
    unsigned int food[4];
    unsigned int x[4], y[4];
    unsigned int allFood = 0;
    unsigned int sumX = 0;
    unsigned int sumY = 0;

    par {
        {food[0],x[0],y[0]} = ant(0,world,0,1);
        {food[1],x[1],y[1]} = ant(1,world,1,2);
        {food[2],x[2],y[2]} = ant(2,world,0,2);
        {food[3],x[3],y[3]} = ant(3,world,1,0);
    }

    for(int j=0;j<4;j++){
        allFood += food[j];
        sumX += x[j];
        sumY += y[j];
    }

    printf("All food eaten:%d, Mean X position:%d/10, Mean Y position:%d/10\n", allFood, 10*sumX/4, 10*sumY/4);

    return 0;

}
