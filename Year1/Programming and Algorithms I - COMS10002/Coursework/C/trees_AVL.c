//Part 4
/* =====================================================================================
         Filename:  trees.c
  
           Author:  Mr. Andreas Georgiou (ag14774), a.georgiou.2014@bristol.ac.uk
     Organization:  University of Bristol
  
  a) Left Left Case
            z                                      y 
           / \                                   /   \
          y   T4      Right Rotate (z)          x      z
         / \          - - - - - - - - ->      /  \    /  \ 
        x   T3                               T1  T2  T3  T4
       / \
     T1   T2
  
  b) Left Right Case
        z                               z                           x
       / \                            /   \                        /  \ 
      y   T4  Left Rotate (y)        x    T4  Right Rotate(z)    y      z
     / \      - - - - - - - - ->    /  \      - - - - - - - ->  / \    / \
   T1   x                          y    T3                    T1  T2 T3  T4
       / \                        / \
     T2   T3                    T1   T2
  
  c) Right Right Case
     z                                y
    /  \                            /   \ 
   T1   y     Left Rotate(z)       z      x
       /  \   - - - - - - - ->    / \    / \
      T2   x                     T1  T2 T3  T4
          / \
        T3  T4
  
  d) Right Left Case
      z                            z                            x
     / \                          / \                          /  \ 
   T1   y   Right Rotate (y)    T1   x      Left Rotate(z)   z      y
       / \  - - - - - - - - ->     /  \   - - - - - - - ->  / \    / \
      x   T4                      T2   y                  T1  T2  T3  T4
     / \                              /  \
   T2   T3                           T3   T4
  
// =====================================================================================*/

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

#define DEBUG 0
#define BALANCE 1

//Linked List node
typedef struct node{
    char *numbers;
    struct node *next;
}NODE;

//Linked List
typedef struct{
    NODE *head;
    NODE *tail;
}LIST;

//Tree node
typedef struct tree{
    char *name;
    LIST phones;
    struct tree *left;
    struct tree *right;
    int height;  //Used for balancing
}TREE;

//Function that returns the height of a node(or -1 for nodes that do not exist). NOTE: "Height" refers to this function while "height" refers to TREE element.
int Height(TREE *t){
    if(t==NULL) return (-1);
    return (t->height);
}

//Rotate oldRoot to the right. New root is oldLeftChild
TREE *rotateRight(TREE *oldRoot){
    TREE *oldLeftChild=oldRoot->left;         //Left child of oldRoot
    TREE *rightLeftChild=oldLeftChild->right; //Right child of the left child of oldRoot
    //Perform rotation
    oldLeftChild->right = oldRoot;
    oldRoot->left = rightLeftChild;
    //Update heights
    (Height(oldRoot->left)>Height(oldRoot->right)) ? (oldRoot->height=Height(oldRoot->left)+1) : (oldRoot->height=Height(oldRoot->right)+1);//The height of oldRoot has to be changed first because it affects the height of oldLeftChild
    (Height(oldLeftChild->left)>Height(oldLeftChild->right)) ? (oldLeftChild->height=Height(oldLeftChild->left)+1) : (oldLeftChild->height=Height(oldLeftChild->right)+1);
    return oldLeftChild;
}

//Rotate oldRoot to the left. New root is oldRightChild
TREE *rotateLeft(TREE *oldRoot){
    TREE *oldRightChild=oldRoot->right;       //Right child of oldRoot
    TREE *leftRightChild=oldRightChild->left; //Left child of the right child of oldRoot
    //Perform rotation
    oldRightChild->left = oldRoot;
    oldRoot->right = leftRightChild;
    //Update heights
    (Height(oldRoot->left)>Height(oldRoot->right)) ? (oldRoot->height=Height(oldRoot->left)+1) : (oldRoot->height=Height(oldRoot->right)+1); //The height of oldRoot has to be changed first because it affects the  height of oldRightChild
    (Height(oldRightChild->left)>Height(oldRightChild->right)) ? (oldRightChild->height=Height(oldRightChild->left)+1) : (oldRightChild->height=Height(oldRightChild->right)+1) ;
    return oldRightChild; //This is the new root. root is changed because it is returned to the insert function between function calls
}

//Function that reads a string of unknown length
char *readLine(FILE *stdptr){
    size_t size=20;   //Set initial size to 20
    register int len=0; 
    int c=0;
    char *ptr;
    ptr=(char*)calloc(size,sizeof(char)); //Allocate memory of initial size
    if(!ptr) return ptr; //NULL if allocation failed
    while((c=fgetc(stdptr))!='\n'&&c!=EOF){  //Read char by char until you find a new line or you reach end of file
        if((c=='.')&&len==0){    //If you encounter a dot AND it's the first character being encountered in this line
            free(ptr);ptr=NULL;  //then free the alocated memory
            return NULL;         //and return NULL
        }
        ptr[len]=c; //Store the character
        len++;      //Increase the length before going to the next character
        if(len==size){  //and check if we've reached our limit
            size*=2;
            ptr=realloc(ptr,size); //double the size to make sure that realloc is not called again in the next loop
            if(!ptr) return ptr;   //if reallocation failed, return NULL
        }
    }
    ptr[len]='\0'; //Terminate string
    for(int i=0;i<=len;i++) { ptr[i]=tolower(ptr[i]); }
    return realloc(ptr,len+1); //Give back to the OS, any unused memory
}

//Inserts a new phone to the specified list
void insertPhone(LIST *lst,char *phone){
    NODE *nd;
    if(phone!=NULL&&*phone!='\0'){
        nd=(NODE*)malloc(sizeof(NODE)); //Allocates memory
        nd->next=NULL;
        if((lst->head==NULL)&&lst->tail==NULL){
            lst->head=nd;  //First element in the list
        }else{
            lst->tail->next=nd;
        }
        lst->tail=nd;
        nd->numbers=phone;
    }else{
        free(phone);
    }
}

//Search for a specific name in the tree and return its address
//Recursive binary search function
TREE *search(TREE *root,char *term){
    #if DEBUG == 1
        if (root!=NULL) printf("Searching:%s\n",root->name);
    #endif
    if(root==NULL) {
        return NULL;
    } else if(strcmp(term,root->name)==0){
        return root;
    } else if(strcmp(term,root->name) <0){
        return search(root->left,term);
    } else {
        return search(root->right,term);
    }
}

//Creates a new tree node and a list.
TREE *makenode(char *name,char *phone,TREE *l,TREE *r){
    //Create the list and pass its pointer to insertPhone
    LIST nums;
    nums.head=NULL;
    nums.tail=NULL;
    insertPhone(&nums,phone);
    //Allocate memory for tree node and set values
    TREE *t;
    t = (TREE*)malloc(sizeof(TREE));
    t->left = l;
    t->right = r;
    t->name=name;
    t->phones=nums;
    t->height=0; //Initially the height is 0
    return t;
}

//Print all nodes in a list
void printPhones(LIST *temp){
    NODE *nd;
    nd=temp->head;
    while(nd!=NULL){
        printf("%s\n",nd->numbers);
        nd=nd->next;
    }
}

void nukeList(NODE *head){
    NODE *temp=head;
    while (temp!=NULL){
        NODE *next=temp->next;
        free(temp->numbers);
        free(temp);
        temp=next;
    }
}

void nukeTree(TREE *root){
    if(root!=NULL){
        nukeTree(root->left);
        nukeTree(root->right);
        free(root->name);
        nukeList((root->phones).head);
        free(root);
    }
}

//Insert a new leaf in the tree. Each time a new leaf is inserted, the balance of the tree is checked
//There are 4 different cases in which a tree is unbalanced
//Because the function is recursive, each function call returns the (modified) root to the previous function call. The result is that we have pointers to all ancestor nodes of the inserted node.
//Tree is balanced while climbing up the tree.
TREE* insert(TREE *root, char *name,char *phone){
    if(root==NULL){
        root = makenode(name,phone,NULL,NULL);
    }else if(strcmp(name,root->name)==0){
        free(name);
        insertPhone(&(root->phones),phone);
    }else if(strcmp(name,root->name)<0){
        root->left = insert(root->left,name,phone);
        #if BALANCE == 1
        (Height(root->left)>Height(root->right)) ? (root->height=Height(root->left)+1) : (root->height=Height(root->right)+1) ; //Choose the maximum height of the two subtrees and add one to get the height of current tree.
        //****Balancing****
        if( (Height(root->left)-Height(root->right)) >1){ //If there's a difference in heights, greater than 1 => left subtree is bigger than right one.
            if(strcmp(name,root->left->name)>0) {        //Choose between case (a) or case (b). Case (b) requires an additional left rotation of the left child node
                root->left=rotateLeft(root->left);
            }
            return rotateRight(root);
        }
        //*****************
        #endif
    }else{
        root->right = insert(root->right,name,phone);
        #if BALANCE == 1
        (Height(root->left)>Height(root->right)) ? (root->height=Height(root->left)+1) : (root->height=Height(root->right)+1) ; //Choose the maximum height of the two subtrees and add one to get the height of current tree.
        //****Balancing****
        if( (Height(root->right)-Height(root->left)) >1){ //If there's a difference in heights, greater than 1 => right subtree is bigger than left one.
            if(strcmp(name,root->right->name)<0) {       //Choose between case (c) or case (d). Case (d) requires an additional right rotation of the right child node
                root->right=rotateRight(root->right);
            }
            return rotateLeft(root);
        }
        //*****************
        #endif
    }
    return root;
}

int main(){
    //Initialise variables.
    char *temp=NULL;
    char *name=NULL;
    char *phone=NULL;
    TREE *myTree=NULL;
    TREE *result=NULL;
    //Build tree
    while((temp=readLine(stdin))!=NULL){
        register int i;
        for(i=strlen(temp)-1;temp[i]!=' '&&i>0;i--) {}
        if(i>0){
            name=(char *)malloc(i+1);
            phone=(char *)malloc(strlen(temp)-i);
            sscanf(temp,"%s %s",name,phone);
            myTree=insert(myTree,name,phone);
        }
        free(temp);temp=NULL;
    }
    getchar();
    //Search
    while((temp=readLine(stdin))!=NULL){
        result=search(myTree,temp);
        free(temp);temp=NULL;
        if(result==NULL){
            printf("NOT FOUND\n");
        }else{
            printPhones(&(result->phones));
        }
    }
    nukeTree(myTree);
    return 0;
}
