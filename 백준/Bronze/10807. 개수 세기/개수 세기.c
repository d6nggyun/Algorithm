#include <stdio.h>
int main(){
    int N = 0;
    scanf("%d",&N);
    int num[100];
    for (int i = 0; i < N; i++){
        scanf("%d",&num[i]);
    }
    int v = 0;
    scanf("%d",&v);
    int z = 0;
    for (int i = 0; i<N; i++){
        if (v == num[i]){
            z++;
        }
    }
    printf("%d",z);
    
    
}