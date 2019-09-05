import java.util.*;

class find_bridges_in_graph
{
    static int times = 0;

    public static void main( String[] args )
    {
        // int[][] edges = new int[][]{ {1, 2}, {1, 3}, {2, 3}, {2, 4}, {2, 5}, {4, 6}, {5, 6} };
        int[][] edges = {{1, 2}, {1, 3}, {3, 4}, {1, 4}, {4, 5}};

        for( int[] edge: findBridge( edges ) ) System.out.printf( "[%d, %d]\n", edge[ 0 ], edge[ 1 ] );
    }

    private static void bridgeUntil( List<int[]> res, Map<Integer, List<Integer>> graph, int u, boolean[] visited, int[] disc, int[] low, int[] parent )
    {
        visited[ u ] = true;
        disc[ u ] = low[ u ] = ++times;

        List<Integer> neighbors = graph.get( u );
        // remove the edges!!
        graph.remove( u );

        for( int v: neighbors )
        {
            if( !visited[ v ] && graph.containsKey( v ) )
            {
                parent[ v ] = u;
                bridgeUntil( res, graph, v, visited, disc, low, parent );

                // Check if the subtree rooted with v has a
                // connection to one of the ancestors of u
                low[ u ] = Math.min( low[ u ], low[ v ] );

                // If the lowest vertex reachable from subtree 
                // under v is below u in DFS tree, then u-v is a bridge 
                if( low[ v ] > disc[ u ] ) res.add( new int[]{ u, v } );
            }
            // Update low value of u for parent function calls. 
            else if( v != parent[ u ] ) low[ u ] = Math.min( low[ u ], disc[ v ] ); 
        }
    }

    private static List<int[]> findBridge( int[][] edges )
    {
        Map<Integer, List<Integer>> graph = buildGraph( edges );
        int n = graph.size();

        boolean[] visited = new boolean[ n+1 ];
        int[] disc = new int[ n+1 ];    // stores discovery times of visited vertices
        /*
         * low[u] = min(disc[u], disc[w]) 
         * where w is an ancestor of u and there is a back edge from 
         * some descendant of u to w.
         */
        int[] low = new int[ n+1 ];
        int[] parent = new int[ n+1 ];  // stores parent vertices in DFS tree

        // init array
        for( int i = 1; i <= n; i++ ) parent[ i ] = -1;

        List<int[]> res = new ArrayList<int[]>();

        // for( int i = 1; i <= n; i++ )
        //     if( !visited[ i ] ) bridgeUntil( res, graph, i, visited, disc, low, parent );

        bridgeUntil( res, graph, 1, visited, disc, low, parent );
        return res;
    }

    private static Map<Integer, List<Integer>> buildGraph( int[][] edges )
    {
        Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
        for( int[] edge: edges )
        {
            int u = edge[ 0 ], v = edge[ 1 ];
            // undirected graph
            graph.computeIfAbsent( u, x -> new ArrayList<Integer>() ).add( v );
            graph.computeIfAbsent( v, x -> new ArrayList<Integer>() ).add( u );
        }
        return graph;
    }
}