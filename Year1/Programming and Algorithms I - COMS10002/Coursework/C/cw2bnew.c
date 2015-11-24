#include <stdio.h>
int calcSheet(int pn){
	return (pn+1)/2;
}

double calcPrice(int pn,double pps, double ppp, int number){
	int sheetNumber=calcSheet(pn);
	if (sheetNumber%16==0){
		return (pn*ppp+(sheetNumber*number*pps)+number*2)*1.2;
	}
	else {
		return (pn*ppp+((sheetNumber+16-(sheetNumber%16))*number*pps)+number*2)*1.2;
	}
}

double calcBW(int pn,int number){
    return calcPrice(pn,0.01,7.00,number);
}

double calcColour(int pn,int number){
    return calcPrice(pn,0.04,28.00,number);
}

int main(void){
    printf("The first total is: %f\n",calcColour(32,1000)+calcBW(40,2000)+calcBW(160,400));
    printf("The second total is: %f\n",calcColour(30,50));
    printf("The third total is: %f\n",calcBW(34,35));
    return 0;
}
