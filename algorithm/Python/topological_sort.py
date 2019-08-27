class Node:
    def __init__(self, val):
        self.val = val
        self.neighbors = []

def helper(node, visited, res):
    if node in visited:
        return 
    visited.add(node)
    for nei in node.neighbors:
        if nei not in visited:
            helper(nei, visited, res)
    # Add to the top the stack
    res.insert(0, node)

def topological_sort(nodes):
    visited = set()
    res = []
    for n in nodes:
        if n not in visited:
            helper(n, visited, res)
    return [n.val for n in res]


"""
        1 
      /   \
     2  4  \
    / /   \ \
   3 ------- 5

topological order:
    [1, 2, 3, 4, 5] 
"""

n1 = Node(1)
n2 = Node(2)
n3 = Node(3)
n4 = Node(4)
n5 = Node(5)
n1.neighbors += [n1, n5]
n2.neighbors += [n3]
n3.neighbors += [n4, n5]
n4.neighbors += [n5]
nodes = [n2,n1,n3,n5,n4]

print(topological_sort(nodes))
