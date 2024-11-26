H, M = input().split()
s = int(input())
h = int(H)
m = int(M)
a = m+s
if a < 60:
    print(h, a)
else:
    while a >= 60:
        h = h + 1
        a = a - 60
        if h == 24:
            h = 0
    print(h, a)
    
        
    
    