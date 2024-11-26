#include <stdio.h>
int main(){
    int N, M;
    scanf("%d %d", &N,&M);    
    int num[100];
    for (int i = 0; i< N ; i++){
        num[i] = i+1;
    }
    int i, j;
    int temp;
    for (int m =0; m<M; m++){
        scanf("%d %d", &i,&j);
        temp = num[i-1];
        num[i-1] = num[j-1];
        num[j-1] = temp;
    }
    for (int i = 0; i<N; i++){
        printf("%d ", num[i]);
    }
}