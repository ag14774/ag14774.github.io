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

typedef struct node{
    char letter;
    struct node *next;
}NODE;

typedef struct{
    NODE *head;
}LIST;

int createAndAdd(LIST *currList,char c){
    NODE *nd=(NODE*)calloc(1,sizeof(NODE));
    if(!nd) return 1;
    nd->letter=c;
    nd->next=currList->head;
    currList->head=nd;
    return 0;
}

void printMyList(LIST *currList){
    NODE *ptr;
    ptr=currList->head;
    while(1){
        printf("%c",ptr->letter);
        if(ptr->next==NULL)break;
        ptr=ptr->next;
    }
}

int main(){
    LIST lst;
    int c=0;
    lst.head=NULL;
    while(c!='.'){
        c=getchar();
        if(createAndAdd(&lst,c)==1){
            printf("Not enough memory!!!\n");
            return 1;
        }
    }
    printMyList(&lst);
    printf("\n");
    return 0;
}
