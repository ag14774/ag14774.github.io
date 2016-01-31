/*
 * Lab2QueenAnts.xc
 *
 *  Created on: Oct 9, 2015
 *      Author: ageorgiou
 */


#include <stdio.h>
#include <platform.h>

void topqueen(chanend sq[n], unsigned n){
    unsigned maxchannelid = 0;
    unsigned maxfertility = 0;
    unsigned count = 0;

    printf("Initializing top queen\n");

    while (1){
        select {
        case sq[int j] :> int fertility:
            if(fertility>=maxfertility){
                maxchannelid = j;
                maxfertility = fertility;
            }
            count++;
            if(count==n){
                sq[maxchannelid] <: 0;
                for(int i=0;i<n;i++){
                    if(i!=maxchannelid)
                        sq[i] <: 1;
                }
            }
            break;
        }
    }
}

void superqueen(chanend q[n], unsigned n, chanend ?sq){
    unsigned int maxchannelid = 0;
    unsigned int maxfertility = 0;
    unsigned int count = 0;
    unsigned int overall = 0;

    printf("Initializing super queen\n");

    while (1){
        select {
        case q[int j] :> int fertility:
            if (fertility>=maxfertility){
                maxchannelid = j;
                maxfertility = fertility;
            }
            count++;
            if(count==n){
                int cmd = 0;
                if(!isnull(sq)){
                    sq <: maxfertility;
                    sq :> cmd;
                }
                printf("Overall harvest from super queen %d\n",overall);
                if(!cmd){
                    q[maxchannelid] <: 0;
                    overall += maxfertility;
                }
                else
                    q[maxchannelid] <: 1;
                for(int i=0;i<n;i++){
                    if(i!=maxchannelid)
                        q[i] <: 1;
                    }
                count = 0;
            }
            break;
        case sq :> int cmd:
            printf("Overall harvest from super queen %d\n",overall);
            if(!cmd){
                q[maxchannelid] <: 0;
                overall += maxfertility;
            }
            else
                q[maxchannelid] <: 1;
            for(int i=0;i<n;i++){
                if(i!=maxchannelid)
                    q[i] <: 1;
            }
            count = 0;
            break;
        }
    }
}

void queen(chanend c[n], unsigned n, chanend q,const unsigned char w[3][4], unsigned int x, unsigned int y){
    unsigned int overall = 0;
    unsigned int maxchannelid = 0;
    unsigned int maxfertility = 0;
    unsigned int count = 0;

    printf("Initializing queen on (%u,%u)\n",x,y);

    while (1){
        select {
        case c[int j] :> int fertility:
            if (fertility>=maxfertility){
                maxchannelid = j;
                maxfertility = fertility;
            }
            count++;
            if(count==n)
                q <: maxfertility;
            break;
        case q :> int cmd:
            printf("Overall harvest %d\n",overall);
            if(!cmd){
                c[maxchannelid] <: 0;
                overall += maxfertility;
            }
            else
                c[maxchannelid] <: 1;
            for(int i=0;i<n;i++){
                if(i!=maxchannelid)
                    c[i] <: 1;
            }
            count = 0;
            break;
        }
    }
}

void ant(chanend c, unsigned int id, const unsigned char w[3][4],
                  unsigned int x, unsigned int y ) {

    printf("Initializing ant #%u on (%u,%u)\n",id,x,y);

    while(1){
        int cmd;
        int current = w[x][y];
        c <: current;
        c :> cmd;

        if(cmd){
            printf("Ant #%d moving\n",id);
            for(int i=0;i<2;i++){
                uint yOnEast  = (y+1)%4;
                uint xOnSouth = (x+1)%3;
                if(w[xOnSouth][y]>w[x][yOnEast])
                    x = xOnSouth;
                else
                    y = yOnEast;
            }
        }else{
            printf("Ant #%d harvesting food\n",id);
        }
    }
}

int main(void){
    const unsigned char world[3][4] = {{10,0,1,7},{2,10,0,3},{6,8,7,6}};
    chan c11[2];
    chan c21[2];
    chan c12[2];
    chan c22[2];
    chan q1[2];
    chan q2[2];
    chan sq[2];

    par {

        superqueen(sq,2,null);

        superqueen(q2,2,sq[1]);
        superqueen(q1,2,sq[0]);

        queen(c11,2,q1[0],world,1,1);
        queen(c12,2,q2[0],world,1,1);
        queen(c21,2,q1[1],world,1,1);
        queen(c22,2,q2[1],world,1,1);

        ant(c11[0],0,world,0,1);
        ant(c11[1],1,world,1,0);
        ant(c21[0],2,world,0,1);
        ant(c21[1],3,world,1,0);

        ant(c12[0],4,world,0,1);
        ant(c12[1],5,world,1,0);
        ant(c22[0],6,world,0,1);
        ant(c22[1],7,world,1,0);
    }

    return 0;

}
