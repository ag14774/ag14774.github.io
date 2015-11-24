#include <stdio.h>

double calcPrice(int pn,double pps, double ppp, int number){
    return (pn*ppp+((pn+1)/2*number*pps)+number*2)*1.2;
}

double calcBW(int pn,int number){
    return calcPrice(pn,0.01,7.00,number);
}

double calcColour(int pn,int number){
    return calcPrice(pn,0.04,28.00,number);
}

int main(void){
    printf("The total is: %f\n",calcColour(32,1000)+calcBW(40,2000)+calcBW(160,400));
    return 0;
}
