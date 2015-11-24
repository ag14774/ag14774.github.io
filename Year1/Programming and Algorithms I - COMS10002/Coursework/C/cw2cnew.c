#include <stdio.h>
int calcSheet(int pn){
	return (pn+1)/2;
}

double calcPrice(int pn,double pps, double ppp, int number,int multi){
	int sheetNumber=calcSheet(pn);
	if (sheetNumber%multi==0){
		return (pn*ppp+(sheetNumber*number*pps)+number*2)*1.2;
	}
	else {
		return (pn*ppp+((sheetNumber+multi-(sheetNumber%multi))*number*pps)+number*2)*1.2;
	}
}

double calcBW(int pn,int number,int multi){
    return calcPrice(pn,0.01,7.00,number,multi);
}

double calcColour(int pn,int number,int multi){
    return calcPrice(pn,0.04,28.00,number,multi);
}

int main(void){
	printf("The first total is: %f\n",calcColour(32,1000,16)+calcBW(40,2000,16)+calcBW(160,400,16));
    printf("The second total is: %f\n",calcColour(30,50,16));
    printf("The third total is: %f\n",calcBW(34,35,16));
    printf("The fourth total is: %f\n",calcBW(34,35,8));
    printf("The fifth total is: %f\n",calcBW(34,100,6));
    return 0;
}
