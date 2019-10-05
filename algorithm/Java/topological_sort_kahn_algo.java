// ref. https://www.geeksforgeeks.org/topological-sorting-indegree-based-solution/
// ref. https://www.geeksforgeeks.org/detect-cycle-in-a-directed-graph-using-bfs/

import java.util.*;

class topological_sort_kahn_algo
{
    private static void topologicalSort( Graph graph, int n )
    {
        // count the indegree for each node
        int[] indegree = new int[ n ];
        for( int i = 0; i < n; i++ )
            for( int nei: graph.neighbors[ i ] )
            {
                indegree[ nei ]++;
            }
        
        Queue<Integer> q = new LinkedList<Integer>();
        // get all the node with indegree 0
        for( int i = 0; i < n; i++ )
            if( indegree[ i ] == 0 )
                q.offer( i );
        
        int cnt = 0;
        List<Integer> res = new ArrayList<Integer>();
        while( !q.isEmpty() )
        {
            int cur = q.remove();
            res.add( cur );
            for( int nei: graph.neighbors[ cur ] )
            {
                if( --indegree[ nei ] == 0 )
                    q.offer( nei );
            }
            cnt++;
        }

        if( cnt != n )
            System.out.println( "Cycle detected!!" );
        else
            System.out.println( res );
    }

    public static void main( String[] args )
    {
        // build the edge
        int n = 6;
        Graph graph = new Graph( n );
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        topologicalSort( graph, n );
    }

    /* Class Graph */
    private static class Graph
    {
        int numOfNodes;
        List<Integer>[] neighbors;

        public Graph( int n )
        {
            this.numOfNodes = n;
            this.neighbors = new List[ this.numOfNodes ];
            // build the adjacent list
            for( int i = 0; i < n; i++ )
                this.neighbors[ i ] = new ArrayList<Integer>();
        }

        public void addEdge( int u, int v ) { this.neighbors[ u ].add( v ); }
    }
}