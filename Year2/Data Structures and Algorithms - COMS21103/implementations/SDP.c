//Solitary Drinking Problem
//WORK IN PROGRESS

#include <stdio.h>

int floor_new(float a){
    return (int)a;
}

int round_new(float a){
    return (int)(a+0.5);
}

int B(int n){
//    printf("calling %d",n);
    if(n==0)
        return 0;
    if(n==1)
        return 1;
    if(n==2)
        return 1;
    if(n==3)
        return 2;
    return B(round_new(n/2.0f)) + B(floor_new(n/2.0f)-1);
}

int main(){
    for(int i=0;i<15;i++){
        int res = B(i);
        printf("B(%d) = %d\n",i,res);
    }
    return 0;
}
