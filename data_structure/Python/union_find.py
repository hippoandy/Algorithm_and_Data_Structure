"""
Union Find

This class is designed for LeetCode 200. Number of Islands.
But the idea behind is the same, can be applied for other 
union find problems after slight modification.

"""

class UnionFind:
        # Initalization root array to every element's index,
        # meaning that every element is the root of itself.
        def __init__(self, grid):
            m = len(grid)
            n = len(grid[0])
            self.root = [None] * (m*n)
            self.count = 0
            for i in range(m):
                for j in range(n):
                    # Entrance condition, modify here to 
                    # apply for other questions
                    if grid[i][j] == '1':
                        id = i * n + j
                        self.root[id] = id
                        self.count += 1
        
        # Function to set and return the root
        def find(self, id):
            if self.root[id] == id:
                return id
            self.root[id] = self.find(self.root[id])
            return self.root[id]
        
        # Set the root of id1 to the root of id2
        def union(self, id1, id2):
            find1 = self.find(id1)
            find2 = self.find(id2)
            if find1 != find2:
                self.root[find2] = find1
                # After union two blocks, count minus 1
                self.count -= 1
