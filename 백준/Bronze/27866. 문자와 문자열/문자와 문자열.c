#include <stdio.h>

int main(void){
    char s[1000];
    int i = 0;
    
    scanf("%s",s);
    scanf("%d",&i);
    
    printf("%c", s[i-1]);
}