#include <stdio.h>

int main(){
    int n = 0; 
    float gra = 0;
    float max = 0;
    float sum = 0;
    
    scanf("%d", &n);
    float grade[n];
    for (int i =0; i<n; i++){
        scanf("%f", &gra);
        grade[i] = gra;
    }
    
    for (int i =0; i<n; i++){
        if (grade[i] > max)
            max = grade[i];
    }
    
    for (int i =0; i<n; i++){
        grade[i] = grade[i] / max * 100;
        sum = sum + grade[i];
    }
    printf("%f", sum / n);
}