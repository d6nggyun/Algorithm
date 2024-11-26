#include <stdio.h>

int main(){
    int num[10] = {0};
    int remain[10] = {0};
    int exist[10];
    for (int i = 0; i < 10; i++) {
        exist[i] = -1;
    }
    int in = 0;
    int number = 0;
    int index = 0;
    
    for (int i =0; i<10; i++){
        scanf("%d", &num[i]);
        remain[i] = num[i] % 42;
    }
    for (int i =0; i<10; i ++){
        in = 0;
        for (int k =0; k<i; k++){
            if (remain[i] == exist[k]){
                in = 1;
            }        
        }
        if (in == 0){
            number++;
            exist[index++] = remain[i];
        }
    }
    printf("%d",number);
}