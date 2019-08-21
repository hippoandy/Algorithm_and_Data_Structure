
import java.util.*;

public class union_find
{
    private static class DSU
    {
        int[] parent;   // record the parent of each node
        // ranking of the node is optional, depends on the situation
        int[] rank;     // the priority for a node to be a root (the bigger the higher)
        int components; // record how many connected components in the graph

        public DSU( int size )
        {
            this.components = 0;
            // init the parent array
            this.parent = new int[ size ];
            // init the rank array
            this.rank = new int[ size ];
            // at first, all the node has parent as itself
            for( int i = 0; i < size; i++ )
            {
                this.parent[ i ] = i;
                this.components++;
            }
            // fill out the rank array;
            Arrays.fill( this.rank, 1 );
        }

        // check if the two nodes share the same parent
        public boolean equivalent( int x, int y )
        {
            return find( x ) == find( y );
        }

        // find the parent of the connected component the index belongs to
        public int find( int x )
        {
            while( x != this.parent[ x ] )
            {
                // path compression
                this.parent[ x ] = this.parent[ this.parent[ x ] ];
                x = this.parent[ x ];
            }
            return x;
        }

        // get the parent idx of y and assign to x as its parent
        public void unify( int x, int y )
        {
            int root_x = find( x ), root_y = find( y );
            if( root_x != root_y )
            {
                if( this.rank[ root_x ] > this.rank[ root_y ] )
                    this.parent[ root_y ] = root_x;
                else if( this.rank[ root_x ] < this.rank[ root_y ] )
                    this.parent[ root_x ] = root_y;
                // while having the same priority, default to make x the parent of y
                else
                {
                    this.parent[ root_y ] = root_x;
                    // increase the priority
                    this.rank[ root_x ]++;
                }
                // reduce the amount of connected components
                this.components--;
            }
        }

        // get the number of connected components
        public int getCount()
        { return this.components; }
    }

    public static void main( String[] args )
    {
        /* test case ******************** */
        int num_of_nodes = 4;
        int[][] edges = {
            {0, 1},
            {1, 2},
            {3, 1},
        };
        /* ******************** test case */

        // init the Disjoint-set/Union-find Forest (DSU)
        DSU dsu = new DSU( num_of_nodes );

        // application: find cycle in undirected graph
        for( int[] e: edges )
        {
            int x = e[ 0 ], y = e[ 1 ];
            if( dsu.equivalent( x, y ) )
                System.out.println( "Found Cycle!" );
            dsu.unify( x, y);
        }
        System.out.println( dsu.getCount() );
    }
}