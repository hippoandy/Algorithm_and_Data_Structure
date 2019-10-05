import java.util.*;

public class topological_sort
{
    // dfs-style topo sort
    private static void topoSortUntil( Graph graph, int cur, boolean[] visited, Stack<Integer> stack )
    {
        // marked as visited
        visited[ cur ] = true;
        
        // visit the neighbor
        for( int nei: graph.neighbors[ cur ] )
            if( !visited[ nei ] )
                topoSortUntil( graph, nei, visited, stack );
        // push into stack if out-degree is 0!
        stack.push( cur );
    }

    private static void topologicalSort( Graph graph, int n )
    {
        Stack<Integer> stack = new Stack<Integer>();

        boolean[] visited = new boolean[ n ];

        for( int i = 0; i < n; i++ )
            if( !visited[ i ] )
                topoSortUntil( graph, i, visited, stack );
        
        // the result
        while( !stack.isEmpty() ) System.out.println( stack.pop() + " " );
    }

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
}