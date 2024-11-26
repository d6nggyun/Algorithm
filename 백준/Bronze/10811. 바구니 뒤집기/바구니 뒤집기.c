#include <stdio.h>

int main(){
    int n=0; int m=0;
    int a=0; int b=0;
    int trans = 0; int temp = 0;
    scanf("%d %d", &n, &m);
    int basket[n];
    for (int i = 0; i < n; i++) {
        basket[i] = i+1;
    }
    
    for (int i =0; i<m; i++){
        scanf("%d %d", &a, &b);
        trans = (b-a+1) / 2;
        for (int k=0; k<trans; k++){
            temp = basket[a-1+k];
            basket[a-1+k] = basket[b-1-k];
            basket[b-1-k] = temp;
        }
    }
    for (int i =0; i<n; i++){
        printf("%d ", basket[i]);
    }
}