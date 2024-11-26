#include <stdio.h>

int main() {
    int T,a,b = 0;
    scanf("%d",&T);
    for (int i =1; i<=T;i++ )
    {
        scanf("%d",&a);
        scanf("%d",&b);
        printf("Case #%d: %d + %d = %d\n",i, a,b,a+b);
    }
}