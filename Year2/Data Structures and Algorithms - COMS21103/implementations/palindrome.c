#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int max(int a, int b){
    return a>b?a:b;
}

int pal(int l, int r, char *s, int **memoize){
//    if(l==strlen(s) || r==-1)
//        return 0;
    if(memoize[l][r]!=-1)
        return memoize[l][r];
    if(l==r)
        return memoize[l][r]=1;
    if(l-r>0)
        return memoize[l][r]=0;
    if(s[l]==s[r])
        return memoize[l][r]=pal(l+1,r-1,s,memoize)+2;
    return memoize[l][r]=max(pal(l+1,r,s,memoize),pal(l,r-1,s,memoize));
}

void printArray(int **memoize,char *string){
    int len = strlen(string);
    for(int i=-1;i<len;i++){
        for(int j=-1;j<len;j++){
            if(i==-1 && j==-1)
                printf("%3c",' ');
            else if(i==-1)
                printf("%3c",string[j]);
            else if(j==-1)
                printf("%3c",string[i]);
            else if(memoize[i][j]==-1)
                printf("%3c",'_');
            else
                printf("%3d",memoize[i][j]);
        }
        printf("\n");
    }
}

int main(){
    char string[100]="AAA";
    int len = strlen(string);
    int **memoize;
    memoize = malloc(sizeof(int*) * len);
    for(int i=0;i<len;i++){
        memoize[i]=malloc(sizeof(int) * len);
        for(int j=0;j<len;j++)
            memoize[i][j]=-1;
    }
    int res = pal(0,len-1,string,memoize);
    printf("result is %d\n",res);
    float per = ((float)res) / len * 100;
    printf("Your name is %.2f%% symmetrical\n",per);
    printArray(memoize,string);
    return 0;
}
