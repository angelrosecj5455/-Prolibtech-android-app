from collections import Counter
list1=['apple','egg','apple','egg','egg','egg']
counts = Counter(list1)
print(counts)
k=counts.keys()
print(k)
v=counts.values()
# print(counts.keys())
# print(counts.values())
# print(type(counts))
for i in range(0,len(k)):
   print(list(k)[i])
   print(list(v)[i])

# Counter({'apple': 3, 'egg': 2, 'banana': 1})