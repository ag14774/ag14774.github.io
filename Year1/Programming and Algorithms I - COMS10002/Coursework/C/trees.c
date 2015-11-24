//Part 4
// =====================================================================================
//
//       Filename:  trees.c
//
//    Description:  
//
//        Version:  1.0
//        Created:  11/17/2014 08:24:51 PM
//       Revision:  none
//       Compiler:  gcc
//
//         Author:  Mr. Andreas Georgiou (ag14774), a.georgiou.2014@bristol.ac.uk
//   Organization:  University of Bristol
//
// =====================================================================================

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
int nfree=0;
int nmalloc=0;
typedef struct node{
    char *numbers;
    struct node *next;
}NODE;

typedef struct{
    NODE *head;
    NODE *tail;
}LIST;

typedef struct tree{
    char *name;
    LIST phones;
    struct tree *left;
    struct tree *right;
}TREE;

char *myStrdup(char *s1){
    extern nmalloc;
    char *s2;
    s2=malloc(strlen(s1)+1);
    nmalloc++;
    strcpy(s2,s1);
    s2[strlen(s1)]='\0';
    return s2;
}

void nukeList(LIST *l){
    NODE *tmp;
    extern nfree;
    while((l->head)!=NULL){
        tmp=l->head;
        l->head=tmp->next;
        free(tmp->numbers);tmp->numbers=NULL;tmp->next=NULL;
        nfree++;
        free(tmp);
        nfree++;
        printf("freed something dude\n");
    }
    tmp=NULL;
    l->head=NULL;l->tail=NULL;
}

void nukeTree(TREE *root){
    extern nfree;
    if(root!=NULL){
        nukeTree(root->left);
        nukeTree(root->right);
        nukeList(&root->phones);
        free(root->name);
        nfree++;
        free(root);
        nfree++;
    }
}

char *readLine(FILE *stdptr){
    size_t size=20;
    extern nmalloc;
    extern nfree;
    register int len=0;
    int c=0;
    char *ptr;
    ptr=(char*)calloc(size,sizeof(char));
    nmalloc++;
    if(!ptr) return ptr;
    while((c=fgetc(stdptr))!='\n'&&c!=EOF){
        if((c=='.')&&len==0){
            free(ptr);ptr=NULL;
            nfree++;
            return NULL;
        }
        ptr[len]=c;
        len++;
        if(len==size){
            size*=2;
            ptr=realloc(ptr,size);
            if(!ptr) return ptr;
        }
    }
    ptr[len]='\0';
    return realloc(ptr,len*sizeof(char));
}

int strcmpi(char *s1,char *s2){
    char *tempS1,*tempS2;
    int r;
    extern nfree;
    register int i;
    tempS1=myStrdup(s1);
    tempS2=myStrdup(s2);
    for(i=0;tempS1[i];i++) tempS1[i]=tolower(tempS1[i]);
    for(i=0;tempS2[i];i++) tempS2[i]=tolower(tempS2[i]);
    //printf("TEST: %s\n",tempS1);
    //printf("TEST: %s\n",tempS2);
    r = strcmp(tempS1,tempS2);
    free(tempS1);tempS1=NULL;
    nfree++;
    free(tempS2);tempS2=NULL;
    nfree++;
    return r;
}

void insertPhone(LIST *lst,char *phone){
    NODE *nd;
    extern nfree;
    extern nmalloc;
    if(phone!=NULL&&*phone!='\0'){
        nd=(NODE*)malloc(sizeof(NODE));
        nmalloc++;
        nd->next=NULL;
        if((lst->head==NULL)&&lst->tail==NULL){
            lst->head=nd;
        }else{
            lst->tail->next=nd;
        }
        lst->tail=nd;
        nd->numbers=phone;
    }else{
        free(phone);
        nfree++;
    }
}

TREE *search(TREE *root,char *term){
    if(root==NULL) {
        return NULL;
    } else if(strcmpi(term,root->name)==0){
        return root;
    } else if(strcmpi(term,root->name) <0){
        return search(root->left,term);
    } else {
        return search(root->right,term);
    }
}

TREE *makenode(char *name,char *phone,TREE *l,TREE *r){
    LIST nums;
    extern nmalloc;
    nums.head=NULL;
    nums.tail=NULL;
    insertPhone(&nums,phone);
    TREE *t;
    t = (TREE*)malloc(sizeof(TREE));
    nmalloc++;
    t->left = l;
    t->right = r;
    t->name=name;
    t->phones=nums;
    return t;
}

void printPhones(LIST *temp){
	NODE *nd;
	nd=temp->head;
	while(nd!=NULL){
        printf("%s\n",nd->numbers);
        nd=nd->next;
    }
}

//void printLeaf(TREE *root){
//	printf("%s\n",root->name);
//	printPhones(&(root->phones));
//}

void insert(TREE **root, char *name,char *phone){
    if(*root==NULL){
        *root=makenode(name,phone,NULL,NULL);
    }else if(strcmpi(name,(*root)->name)==0){
        insertPhone(&(*root)->phones,phone);
    }else if(strcmpi(name,(*root)->name)<0){
        insert(&(*root)->left,name,phone);
    }else{
        insert(&(*root)->right,name,phone);
    }
}

void splitNfree(char *s1,char **name, char **phone){
    int i;
    extern nmalloc;
    extern nfree;
	for(i=(strlen(s1)-1);s1[i]!=' '&&i>=0;i--){}
	if(i<0){
        *phone=NULL;
        *name=malloc(strlen(s1)+1);
        nmalloc++;
        printf("MALLOCED ONE THING\n");
        strcpy(*name,s1);
        (*name)[strlen(*name)]='\0';
    }else{
	    *phone=malloc(strlen(s1+i+1)+1);
        nmalloc++;
	    strcpy(*phone,s1+i+1) ; (*phone)[strlen(s1+i+1)]='\0';
	    *name=malloc((i+1)*sizeof(char));
        nmalloc++;
	    strncpy(*name,s1,i) ; (*name)[i]='\0';
        printf("MALLOCED TWO THINGS\n");
    }
	free(s1);s1=NULL;
    nfree++;
}

int main(){
	char *temp=NULL;
	char *name=NULL;
    extern int nfree;
    extern int nmalloc;
	char *phone=NULL;
	TREE *myTree=NULL;
    TREE *result=NULL;
	while((temp=readLine(stdin))!=NULL){
	    splitNfree(temp,&name,&phone);
		insert(&myTree,name,phone);
	}
    getchar();
	while((temp=readLine(stdin))!=NULL){
		result=search(myTree,temp);
        free(temp);temp=NULL;nfree++;
        if(result==NULL){
            printf("NOT FOUND\n");
        }else{
            printPhones(&(result->phones));
        }
	}
    nukeTree(myTree);myTree=NULL;
    printf("MALLOCS: %d,FREES: %d\n",nmalloc,nfree);
    return 0;
}
