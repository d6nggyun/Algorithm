#include <stdio.h>

int main() {
    int T,A,B = 0;
    scanf("%d",&T);
    for (int i =0; i<T; i++)
    {
        scanf("%d",&A);
        scanf("%d",&B);
        printf("%d\n", A+B);
    }
}