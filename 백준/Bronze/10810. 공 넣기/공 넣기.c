#include <stdio.h>
int main(){
    int N, M ;
    scanf("%d %d", &N,&M);
    int i, j, k;
    int num[100];
    for (int m =0; m<N; m++){
        num[m] = 0;
    }
    for (int m =0; m<M; m++){
        scanf("%d %d %d",&i,&j,&k);
        for (int p = i-1; p<=j-1; p++){
            num[p] = k;
        }
    }
    for (int m =0; m<N;m++){
        printf("%d ",num[m]);
    }
}