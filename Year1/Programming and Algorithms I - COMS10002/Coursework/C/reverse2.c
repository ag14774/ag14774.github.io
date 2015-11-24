/*
 * =====================================================================================
 *
 *       Filename:  cw6a.c
 *
 *    Description:  Coursework 5 Part A
 *
 *        Version:  1.0
 *        Created:  04/11/14 14:05:49
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

typedef struct node{
    char *line;
    struct node *next;
}NODE;

typedef struct{
    NODE *head;
}LIST;

int createAndAdd(LIST *currList,char *ptr){
    NODE *nd=(NODE*)calloc(1,sizeof(NODE));
    if(!nd) return 1;
    if(strncmp(ptr,"\0",1)==0) return 0;
    nd->line=ptr;
    nd->next=currList->head;
    currList->head=nd;
    return 0;
}

void printMyList(LIST *currList){
    NODE *ptr;
    ptr=currList->head;
    while(1){
        printf("%s\n",ptr->line);
        if(ptr->next==NULL)break;
        ptr=ptr->next;
    }
}

char *readLine(size_t size,FILE *stdptr,unsigned short int *reason){/* It returns a pointer to the line and stores "1" in "reason" if it found a line with "." */
    int len=0;
    int c=0;
    *reason=0;
    char *ptr;
    ptr=(char*)calloc(size,sizeof(char));
    if(!ptr) return ptr;
    while((c=fgetc(stdptr))!='\n'&&c!=EOF){
        if(c=='.'){
            *reason=1;
            ptr[len]='\0';
            len++;
            break;
        }else{
            ptr[len]=c;
        }
        len++;
        if(len==size){
            ptr=realloc(ptr,size+=8);
            if (!ptr) return ptr;
        }
    }
    ptr[len]='\0';
    return (char*)realloc(ptr,len*sizeof(char));
}

int main(){
    LIST lst;
    unsigned short int reason=0;
    char *string;
    lst.head=NULL;
    while(reason!=1){
        if((string=readLine(16,stdin,&reason))==NULL){
            printf("Not enough memory!!!\n");
            return 1;
        }
        if(createAndAdd(&lst,string)==1){
            printf("Not enough memory2!!!\n");
            return 1;
        }
    }
    printMyList(&lst);
    return 0;
}
