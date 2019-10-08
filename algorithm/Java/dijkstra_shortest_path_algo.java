import java.util.*;

// Dijkstra's Algorithm
// Given a graph and a source vertex in graph, find shortest paths from source to all vertices in the given graph.

class dijkstra_shortest_path_algo
{
    private static int[] dijkstra( Graph g, int src )
    {
        // 1. build and init the distance array
        int[] dist = new int[ g.getNumOfNodes() ];
        for ( int i = 0; i < dist.length; i++ ) dist[ i ] = Integer.MAX_VALUE;
        // the distance to the source is 0
        dist[ src ] = 0;

        // 2. build and init a priority queue
        PriorityQueue<Node> pq = new PriorityQueue<Node>( (a, b) -> a.w - b.w );
        // add starting point to the queue
        pq.offer( new Node( src, 0 ) );

        // 3. find the distance
        Set<Integer> visited = new HashSet<Integer>();
        // while not all the node been visited
        while( visited.size() != g.getNumOfNodes() )
        {
            int cur = pq.remove().k;
            visited.add( cur );

            // process all the neighbors
            for( int i = 0; i < g.neighbors[ cur ].size(); i++ )
            {
                Node nei = g.neighbors[ cur ].get( i );
                if( !visited.contains( nei.k ) )
                {
                    int alt = dist[ cur ] + nei.w;

                    // update the dist of the neighbor
                    if( alt < dist[ nei.k ] )
                        dist[ nei.k ] = alt;
                    
                    pq.offer( new Node( nei.k, dist[ nei.k ] ) );
                }
            }
        }
        return dist;
    }

    public static void main( String[] args )
    {
        // build the edge
        int n = 5;
        Graph graph = new Graph( n );
        graph.addEdge( 0, new Node( 1, 9 ) );
        graph.addEdge( 0, new Node( 2, 6 ) );
        graph.addEdge( 0, new Node( 3, 5 ) );
        graph.addEdge( 0, new Node( 4, 3 ) );
        graph.addEdge( 2, new Node( 1, 2 ) );
        graph.addEdge( 2, new Node( 3, 4 ) );
        int src = 0;

        System.out.println( "Shortest paths from " + src + " to all other nodes are: " + Arrays.toString( dijkstra( graph, src ) ) );
    }

    /* Class Graph */
    private static class Graph
    {
        int numOfNodes;
        List<Node>[] neighbors;

        public Graph( int n )
        {
            this.numOfNodes = n;
            this.neighbors = new List[ this.numOfNodes ];
            // build the adjacent list
            for( int i = 0; i < n; i++ )
                this.neighbors[ i ] = new ArrayList<Node>();
        }

        public void addEdge( int u, Node v ) { this.neighbors[ u ].add( v ); }

        public int getNumOfNodes() { return this.numOfNodes; }
    }

    private static class Node
    {
        int k, w;
        public Node( int k, int w ) { this.k = k; this.w = w; }
    }
}