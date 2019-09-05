import java.util.*;

class optimal_utilization
{
    public static void main( String[] args )
    {
        int[][] a = new int[][]{ {1, 3}, {2, 5}, {3, 7}, {4, 10} };
        int[][] b = new int[][]{ {1, 2}, {2, 3}, {3, 4}, {4, 5} };
        int target = 10;

        List<int[]> res = findPair( a, b, target );
        for( int[] p: res ) System.out.printf( "{%d, %d}\n", p[ 0 ], p[ 1 ]);
    }

    private static List<int[]> findPair( int[][] a, int[][] b, int tar )
    {
        // sort by value
        Arrays.sort( a, (int[] x, int[] y) -> x[ 1 ] - y[ 1 ] );
        Arrays.sort( b, (int[] x, int[] y) -> x[ 1 ] - y[ 1 ] );

        Map<Integer, List<int[]>> map = new HashMap<Integer, List<int[]>>();

        int max = -1;
        int m = a.length, n = b.length, i = 0, j = n-1;
        while( i < m && j >= 0 )
        {
            int sum = a[ i ][ 1 ] + b[ j ][ 1 ];
            if( sum > tar )     // sum too big
            {
                j--;
                continue;
            }
            if( sum >= max )
            {
                map.putIfAbsent( sum, new ArrayList<int[]>() );
                int idx = j;
                // check the duplicate
                while( idx >= 0 && b[ idx ][ 1 ] == b[ j ][ 1 ] )
                {
                    map.get( sum ).add( new int[]{ a[ i ][ 0 ], b[ idx ][ 0 ] } );
                    idx--;
                }
                max = sum;
            }
            i++;
        }
        return map.get( max );
    }
}