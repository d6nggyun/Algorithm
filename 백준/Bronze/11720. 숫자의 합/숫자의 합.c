#include <stdio.h>

int main(){
    int n = 0;
    int sum = 0;
    char sen[100];
    
    scanf("%d",&n);
    scanf("%s", &sen);
    
    for (int i =0; i<n; i++){
        sum = sum + sen[i] - '0';
    }
    
    printf("%d",sum);
    
    return 0;
}