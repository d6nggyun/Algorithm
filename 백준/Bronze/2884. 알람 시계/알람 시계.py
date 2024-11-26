H, M = input().split()
h = int(H)
m = int(M)
if m - 45 < 0:
    if h == 0:
        h = 23
        m = m + 60 - 45
        print(h, m)
    else:
        h = h - 1
        m = m + 60 - 45
        print(h, m)
else:
    m = m - 45
    print(h, m)