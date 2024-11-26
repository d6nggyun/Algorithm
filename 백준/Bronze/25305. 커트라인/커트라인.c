#include <stdio.h>

int main(){
    int n =0;
    int k =0;
    int temp =0;
    int cutline = 0;
    int grade[1000];
    
    scanf("%d %d", &n, &k);
    for (int i=0; i<n; i++){
        scanf("%d", &grade[i]);
    }
    
    for (int i=0; i<n; i++){
        for (int j=0; j<n-1;j++){
            if (grade[j] > grade[j+1]){
                temp = grade[j];
                grade[j] = grade[j+1];
                grade[j+1] = temp;
            }
        }
    }
    
    for (int i=0; i<k; i++){
        cutline = grade[n-1-i];
    }
    
    printf("%d",cutline);
}