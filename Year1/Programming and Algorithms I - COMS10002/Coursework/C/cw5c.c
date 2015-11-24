/*
 * =====================================================================================
 *
 *       Filename:  cw5c.c
 *
 *    Description:  Coursework 5 Part C
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
#include <stdlib.h>
#include <string.h>

/* This will probably accept any kind of input, uppercase and lowercase
 * It will detect syntax errors such as wrong letters, incomplete shapes etc 
 * and notify the user. It removes extra spaces. It will also detect the case of
 * not having enough memory to store all the data. If there are no bugs I didn't notice
 * then it will probably check way more stuff than the exercise requires.
 * I think this could also be done using scanf() 4 times if it's a line and 3 times
 * if it's a circle.                                                                      */

struct coords{
    int x;
    int y;
};

typedef struct{
    int x;
    int y;
    unsigned int r;
}circle;

typedef struct{
    struct coords A;
    struct coords B;
}line;

typedef struct{
    union{
        circle C;
        line L;
    }type;
    char tag;
}Shape;

typedef struct{
    struct coords bl;
    struct coords tr;
}box;

char *saveString(size_t n,FILE* stdptr,int *spacePassed){/* Creates an array in the heap of size n, but if it needs more space than n, it allocates 8 more bytes checking each time if it needs again more than that. */
    int len=0;
    char *data;
    int ch;
    int spaceCount=0;/* Next 'C' or 'L' will occur in spaceCount spaces. */
    int prevChar=0;
    *spacePassed=0;/* Counts spaces and returns numbers to be stored by reference */
    data=(char*)malloc(n*sizeof(char));
    if(!data) return data;/* Not enough space */
    while((ch=fgetc(stdptr))!='\n' && ch!=EOF){
        if((ch!='0'&&ch!='1'&&ch!='2'&&ch!='3'&&ch!='4'&&ch!='5'&&ch!='6'&&ch!='7'&&ch!='8'&&ch!='9'&&ch!=' '&&ch!='-'&&spaceCount!=*spacePassed)||
           (ch!='c'&&ch!='C'&&ch!='l'&&ch!='L'&&spaceCount==*spacePassed&&ch!=' ')){/* This checks if letters and numbers are where they should be and whether there are any invalid characters */

                printf("\tWrong syntax!\n");
                return (char*)NULL;
        }
        if(!(prevChar==' '&&ch==' ')){
            if(ch==' ') *spacePassed=*spacePassed+1;
            if(ch=='c'||ch=='C') spaceCount+=4;
            if(ch=='l'||ch=='L') spaceCount+=5;
            data[len]=ch;
            len++;
        }
        prevChar=ch;
        if(len==n){
            data=(char*)realloc(data,n+=8);
            if(!data) return data;
        }
    }
    data[len]='\0';
    *spacePassed=*spacePassed+1;/* The number of numbers stored (including letters) is "Number of Spaces + 1" */
    if(!(ch=='\n'&&prevChar==' ')){
        if(spaceCount!=*spacePassed) return (char*)NULL;/* For the syntax to be valid, the number of spaces predicted should be correct. */
    }
    return (char*)realloc(data,sizeof(char)*len);
}

int *convertIntArray(char *str,int n/*Int Array size */){
    char *ptr;
    int *nums;
    nums=(int*)calloc(n,sizeof(int));
    if(!nums) return nums;
    int i=0;
    ptr=strtok(str," ");
    while(ptr!=NULL&&i<n){
        if(*ptr=='c'||*ptr=='C'||*ptr=='l'||*ptr=='L'){
            nums[i++]=(int)*ptr;
        }else{
            nums[i++]=(int)strtol(ptr,(char**)NULL,10);
        }
        ptr=strtok(NULL," ");
    }
    free(str);
    return nums;
}



circle createCircle(int cX, int cY, unsigned int R){
    circle output;
    output.x=cX;
    output.y=cY;
    output.r=R;
    return output;
}

box createBox(Shape S){
    box bound;
    switch(S.tag){
     case 'C':case 'c':
                        bound.bl.x=S.type.C.x-S.type.C.r;
                        bound.bl.y=S.type.C.y-S.type.C.r;
                        bound.tr.x=S.type.C.x+S.type.C.r;
                        bound.tr.y=S.type.C.y+S.type.C.r;
                        break;
     case 'L':case 'l':
                        if(S.type.L.A.x<S.type.L.B.x){
                            bound.bl.x=S.type.L.A.x;
                            bound.tr.x=S.type.L.B.x;
                        }else{
                            bound.bl.x=S.type.L.B.x;
                            bound.tr.x=S.type.L.A.x;
                        }
                        if(S.type.L.A.y<S.type.L.B.y){
                            bound.bl.y=S.type.L.A.y;
                            bound.tr.y=S.type.L.B.y;
                        }else{
                            bound.bl.y=S.type.L.B.y;
                            bound.tr.y=S.type.L.A.y;
                        }
                        break;
    }
    return bound;
}

box combine(box A,box B){
    box result;
    (A.bl.x<B.bl.x) ? (result.bl.x=A.bl.x) : (result.bl.x=B.bl.x);
    (A.bl.y<B.bl.y) ? (result.bl.y=A.bl.y) : (result.bl.y=B.bl.y);
    (A.tr.x>B.tr.x) ? (result.tr.x=A.tr.x) : (result.tr.x=B.tr.x);
    (A.tr.y>B.tr.y) ? (result.tr.y=A.tr.y) : (result.tr.y=B.tr.y);
    if((A.bl.x==A.bl.y)&&(A.bl.y==A.tr.x)&&(A.tr.x==A.tr.y)){
        result=B;
    }
    if((B.bl.x==B.bl.y)&&(B.bl.y==B.tr.x)&&(B.tr.x==B.tr.y)){
        result=A;
    }
    return result;
}

box combineArray(int *data,int n){
    int i;
    int jump;
    Shape S;
    S.tag='C';
    S.type.C.x=0;
    S.type.C.y=0;
    S.type.C.r=0;
    box final=createBox(S);
    for(i=0;i<n;i+=jump){
        S.tag=(char)data[i];
        if(S.tag=='c'||S.tag=='C'){
            jump=4;
            S.type.C.x=data[i+1];
            S.type.C.y=data[i+2];
            S.type.C.r=data[i+3];
        }else{
            jump=5;
            S.type.L.A.x=data[i+1];
            S.type.L.A.y=data[i+2];
            S.type.L.B.x=data[i+3];
            S.type.L.B.y=data[i+4];
        }
        final=combine(createBox(S),final);
    }
    free(data);
    return final;
}

void printBound(box bound){
    printf("(%d,%d) (%d,%d)\n",bound.bl.x,bound.bl.y,bound.tr.x,bound.tr.y);
}

int main(){
    char *string;
    int size;
    int *nums;
    box result;
    printf("Enter data:\n");
    string=saveString(17,stdin,&size);
    if(string==NULL){
        printf("\tError! Exiting Program!\n");
        return 1;
    }
    nums=convertIntArray(string,size);
    if(nums==NULL){
        printf("\tError! Not enough memory!\n\tExiting Program!\n");
        return 1;
    }
    result=combineArray(nums,size);
    printBound(result);
    return 0;
}
