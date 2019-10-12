// ref. https://www.geeksforgeeks.org/tarjan-algorithm-find-strongly-connected-components/
// ref. https://blog.csdn.net/QiHang_QiHang/article/details/78300054
// ref. http://www.cfzhao.com/2018/11/03/%E6%88%91%E7%90%86%E8%A7%A3%E7%9A%84tarjan%E7%AE%97%E6%B3%95/

import java.util.*;

class tarjan_scc_algo
{
    // dfs helper funct
    private static void sccUntil( Graph g, List<List<Integer>> scc, int u, int times, int[] disc, int[] low, Stack<Integer> stack, boolean[] stackMember )
    {
        // times records the num. of steps been discovered
        disc[ u ] = low[ u ] = ++times;

        stack.push( u );
        stackMember[ u ] = true;

        // go through all the neighbors
        for( int i = 0; i < g.neighbors[ u ].size(); i++ )
        {
            int v = g.neighbors[ u ].get( i );

            // if the nei has not been visited (discovered) yet
            if( disc[ v ] == -1 )
            {
                sccUntil( g, scc, v, times, disc, low, stack, stackMember );
                // the earliest visit of the current node
                low[ u ] = Math.min( low[ u ], low[ v ] );
            }
            else if( stackMember[ v ] )
                // the earliest visit of the current node
                low[ u ] = Math.min( low[ u ], disc[ v ] );
        }

        // head node found
        if( low[ u ] == disc[ u ] )
        {
            List<Integer> group = new ArrayList<Integer>();
            while( !stack.isEmpty() )
            {
                int w = stack.pop();
                stackMember[ w ] = false;
                group.add( w );

                if( w == u ) break;
            }
            scc.add( group );
        }
    }

    // tarjan's algorithm using DFS
    private static List<List<Integer>> tarjan( Graph g )
    {
        // stores discovery times (steps) of visited vertices
        int[] disc = new int[ g.getNumOfNodes() ];
        // earliest visited vertex (the vertex with minimum 
        // discovery time) that can be reached from subtree 
        // rooted with current vertex
        int[] low = new int[ g.getNumOfNodes() ];

        boolean[] stackMember = new boolean[ g.getNumOfNodes() ];

        // stores possible scc
        Stack<Integer> stack = new Stack<Integer>();

        for( int i = 0; i < g.getNumOfNodes(); i++ )
        {
            disc[ i ] = -1;
            low[ i ] = -1;

            stackMember[ i ] = false;
        }

        List<List<Integer>> scc = new ArrayList<List<Integer>>();
        // start to find the strongly connected components using dfs helper funct
        for( int i = 0; i < g.getNumOfNodes(); i++ )
            if( disc[ i ] == -1 )
                sccUntil( g, scc, i, 0, disc, low, stack, stackMember );
        return scc;
    }

    public static void main( String[] args )
    {
        Graph g = new Graph( 5 );
        g.addEdge( 1, 0 );
        g.addEdge( 0, 2 );
        g.addEdge( 2, 1 );
        g.addEdge( 0, 3 );
        g.addEdge( 3, 4 );

        System.out.println( tarjan( g ) );
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

        public int getNumOfNodes() { return this.numOfNodes; }
    }
}