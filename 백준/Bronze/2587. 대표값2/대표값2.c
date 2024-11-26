#include <stdio.h>

int main(){
    int number[5];
    int average = 0;
    int mid = 0;
    int temp = 0;
    
    for (int i =0; i<5; i++){
        scanf("%d",&number[i]);
    }
    
    for (int i =0; i<5; i++){
        for (int j=0; j<4; j++){
            if (number[j] > number[j+1]){
                temp = number[j];
                number[j] = number[j+1];
                number[j+1] = temp;
            }
        }
    }
    
    for (int i =0; i<5; i++){
        average = average + number[i];
    }
    average = average / 5;
    mid = number[2];
    
    printf("%d\n", average);
    printf("%d\n", mid);
}