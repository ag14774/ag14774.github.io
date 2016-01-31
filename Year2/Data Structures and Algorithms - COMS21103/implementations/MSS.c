#include <stdio.h>

int inline max(int a, int b){
    return a>b?a:b;
}

int MSS(int *numbers, size_t len){
    int i;
    if(len==0)
        return 0;
    int max_here = numbers[0];
    int max_total = numbers[0];
    for(i=1;i<len;i++){
        max_here = max(max_here+numbers[i],numbers[i]);
        max_total = max(max_total,max_here);
    }
    return max_total;
}

int main(){
    int numbers[]={1,10,-5,3,-1,2};
    int res = MSS(numbers, sizeof numbers / sizeof *numbers);
    printf("Maximum subarray sum is %d\n",res);
    return 0;
}
