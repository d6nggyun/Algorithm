#include <stdio.h>

int main(){
    int test = 0;
    char s[100];
    
    scanf("%d",&test);
    
    for (int i =0; i<test; i++){
        scanf("%s",s);
        printf("%c",s[0]);
        printf("%c\n",s[strlen(s)-1]);
    }
}