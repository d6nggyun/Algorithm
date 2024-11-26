#include <stdio.h>

int main(){
    char s[100];
    int in[26];
    
    for (int i=0; i<26; i++){
        in[i] = -1;
    }
    
    scanf("%s",&s);
    int length = strlen(s);
    
    for (int i = 0; i<length; i++){
        for (int j=0; j<26;j++){
            if (j + 97 == (int)s[i]){
                if (in[j] == -1){
                    in[j] = i;
                }
            }
        }
    }
    
    for (int i=0; i<26; i++){
        printf("%d ", in[i]);
    }
    
    return 0;
}