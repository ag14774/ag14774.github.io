/*
 * =====================================================================================
 *
 *       Filename:  cw4a.c
 *
 *    Description:  SOUNDEX
 *
 *        Version:  1.0
 *        Created:  21/10/14 14:01:27
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Mr. Andreas Georgiou (ag14774), a.georgiou.2014@bristol.ac.uk
 *   Organization:  University of Bristol
 *
 * =====================================================================================
 */

#include <stdio.h>
#include <string.h>
#define ARRAYSIZE 32
#define SOUNXLEN  4

typedef unsigned short int USINT;

void upperCase(char *surname){
    while (*surname!='\0'){
        if(*surname<='z'&&*surname>='a'){*surname-=32;}
        surname++;
    }
}

/*void rmvDouble2(char *surname){
    upperCase(surname);
    char *dest;
    dest=surname;
    while (*surname!='\0'){
        *dest=*surname;
        if(*surname!=*(surname+1)){
            dest++;
        }
        surname++;
    }
    *dest='\0';
}*/

void rmvDouble(char *surname,size_t arsize){
    upperCase(surname);
    char *pointToFirst=surname;
    char last;
    while (*surname!='\0'){
        last=*surname;
        surname++;
        if(*surname==last){
            *surname='\0';
            strncat(surname,surname+1,arsize-strlen(pointToFirst)-1);
            surname--;
        }
    }
}

int rplSpecific(char *string,const char *pattern,char rplWith,size_t buff){
/* This function replaces every occurence in "string" of any of the characters that are part of "pattern"
 * with the character "rplWith". If rplWith is set to '\0', instead of replacing, it removes every occurence. */
    char *ptr=strpbrk(string+1,pattern);
    if (!ptr){
        return 0;
    }else{
        *ptr=rplWith;
        if(rplWith=='\0'){  strncat(ptr,ptr+1,buff-strlen(string)-1);  }
        return rplSpecific(string,pattern,rplWith,buff);
    }
}

void rmvVowel(char *string,size_t buff){
    const char VOW[]="AEIOUWYH";
    rplSpecific(string,VOW,'\0',buff);
}


int addZeros(char *string,USINT len,size_t buff){
    int i=0;
    if ((strlen(string)<len)&&(strlen(string)<buff-1)){
        i=1;
        strncat(string,"0",1);
        return addZeros(string,len,buff);
    }
    return i; /* Return 1 if the function added at least one zero. 0 otherwise. */  
}

void strEncodeSoundX(char *string,USINT len,size_t buff){
    const char CAT1[]="BPFV";
    const char CAT2[]="CSKGJQXZ";
    const char CAT3[]="DT";
    const char CAT4[]="L";
    const char CAT5[]="MN";
    const char CAT6[]="R";
    if(addZeros(string,len,buff)==0) *(string+len)='\0';
    rplSpecific(string,CAT1,'1',buff);
    rplSpecific(string,CAT2,'2',buff);
    rplSpecific(string,CAT3,'3',buff);
    rplSpecific(string,CAT4,'4',buff);
    rplSpecific(string,CAT5,'5',buff);
    rplSpecific(string,CAT6,'6',buff);
}

int main(void){
    char name[ARRAYSIZE];
    scanf("%s",name);
    rmvDouble(name,ARRAYSIZE);
    rmvVowel(name,ARRAYSIZE);
    strEncodeSoundX(name,SOUNXLEN,ARRAYSIZE);
    printf("%s\n",name);
}
