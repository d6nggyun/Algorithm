A, B, C = input().split()
a = int(A)
b = int(B)
c = int(C)
if a == b == c:
    award = 10000 + a*1000
    print(award)
elif a==b or b==c or c==a:
    if a == b:
        award = 1000 + a * 100
    elif b == c:
        award = 1000 + b * 100
    else:
        award = 1000 + c * 100
    print(award)
else:
    if a>b:
        a,b= b,a
        if b > c:
            b,c=c,b
    else:
        if b>c:
            b,c = c,b
    award = c * 100
    print(award)