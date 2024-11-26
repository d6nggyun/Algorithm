#include <stdio.h>

int main(){
    int t = 0;
    int r = 0;
    int length = 0;
    int ResultLen = 0;
    char s[20];
    char p[160];
    
    scanf("%d", &t);
    
    for (int i=0; i<t; i++){
        scanf("%d", &r);
        scanf("%s", &s);
        
        length = strlen(s);
        ResultLen = length * r;
        
        for (int j=0; j< length; j++){
            for (int m=0; m<r; m++){
                p[(j*r)+m] = s[j];
            }
        }
        
        p[ResultLen] = '\0';
        printf("%s\n", p);
    }
    
    return 0;
}