#include <stdio.h>

int main(){
    int x = 0;
    scanf("%d",&x);
    for (int i=1; i<10; i++)
    {
        printf("%d * %d = %d\n",x,i,x*i);
    }
}