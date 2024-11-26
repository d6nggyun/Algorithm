#include <stdio.h>
int main(){
    int N = 0;
    scanf("%d", &N);
    int num[1000000];
    for (int i =0; i<N; i++){
        scanf("%d",&num[i]);
    }
    int min = num[0]; 
    int max = num[0];
    for (int i =0; i<N ; i++){
        if (num[i] < min)
            min = num[i];
        if (num[i] > max)
            max = num[i];
    }
    printf("%d %d", min, max);
}