#include <stdio.h>

int main() {
    int N = 0;
    scanf("%d",&N);
    for (int i = 1; i<=N; i++)
    {
        for (int j = 0; j < N-i; j++)
            printf(" ");
        for (int j = 0; j<i; j++)
            printf("*");
        printf("\n");
    }
}