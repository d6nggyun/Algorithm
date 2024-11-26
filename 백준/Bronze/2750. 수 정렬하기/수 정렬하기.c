#include <stdio.h>

int main(){
    int number = 0;
    scanf("%d", &number);
    
    int up[1000];
    for (int i =0; i<number; i++){
        scanf("%d", &up[i]);
    }
    
    int temp = 0;
    for (int j =0; j<number; j++){
        for (int i = 0; i<number - 1; i++){
            if (up[i] > up[i+1]){
                temp = up[i];
                up[i] = up[i+1];
                up[i+1] = temp;
            }
        }
    }
    
    for (int i =0; i<number; i++){
        printf("%d\n",up[i]);
    }
}