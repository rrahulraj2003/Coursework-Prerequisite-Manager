
# Deprecated/Useless code

'''
import io

class Node:
    def __init__(self, name, next):
        self.name = name
        self.next = next

def find(list, name):
    for i in range(len(list)):
        if(list[i].name == name):
            return i
    return -1



adjlist = []

# Read File
file = open('adj.in', 'r')
file.readline()
#Lines = file.readlines()

# Verts
with open('adj.in') as f:
    verts = int(f.readline().strip())

# Read file (verts) amount of times
for i in range(verts):
    connection = file.readline().strip().replace(" ", "\n")
    buf = io.StringIO(connection)
    adjlist.append(Node(buf.readline(), None))


print(find(adjlist, "cs323"))

'''