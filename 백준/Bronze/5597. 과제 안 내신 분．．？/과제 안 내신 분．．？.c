#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int main() {
    int num[28];
    for (int i = 0; i < 28; i++) {
        scanf("%d", &num[i]);
    }

    int mun[30];
    for (int i = 0; i < 30; i++) {
        mun[i] = i + 1;
    }

    for (int i = 0; i < 28; i++) {
        for (int j = 0; j < 30; j++) {
            if (num[i] == mun[j]) {
                mun[j] = 0;
                break;  // 중복 체크 후 루프 종료
            }
        }
    }

    int res[2];
    int count = 0;  // 찾은 학생 수 카운트
    for (int i = 0; i < 30; i++) {
        if (mun[i] != 0) {
            res[count++] = mun[i];
            if (count == 2) {
                break;  // 두 명의 학생을 찾았으면 종료
            }
        }
    }

    if (res[0] > res[1]) {
        int temp = res[0];
        res[0] = res[1];
        res[1] = temp;
    }
    printf("%d\n", res[0]);
    printf("%d\n", res[1]);

    return 0;
}
