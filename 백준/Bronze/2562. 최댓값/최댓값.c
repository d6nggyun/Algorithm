#include <stdio.h>
int main(){
    int num[9];
    for (int i = 0; i<9; i++)
        scanf("%d",&num[i]);
    int max = num[0];
    int try = 0;
    for (int i =0; i<9; i++){
        if (num[i] > max){
            max = num[i];
            try = i;
        }
    }
    printf("%d\n", max);
    printf("%d",try+1);
}