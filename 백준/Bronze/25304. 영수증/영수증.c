#include <stdio.h>

int main() {
    int X,N,a,b,sum = 0;
    scanf("%d",&X);
    scanf("%d",&N);
    for (int i = 0; i<N;i++)
    {
        scanf("%d",&a);
        scanf("%d",&b);
        sum += a * b;
    }
    if (sum == X)
        printf("Yes");
    else
        printf("No");
}