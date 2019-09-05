import java.util.*;

class treasure_island_2
{
    static int m, n;
    public static void main( String[] args )
    {
        char[][] grid = {
            {'S', 'O', 'O', 'S', 'S'},
            {'D', 'O', 'D', 'O', 'D'},
            {'O', 'O', 'O', 'O', 'X'},
            {'X', 'D', 'D', 'O', 'O'},
            {'X', 'D', 'D', 'D', 'O'}
        };
        m = grid.length;
        n = grid[ 0 ].length;
        System.out.printf( "Min Steps: %d\n", bfs( grid ) );
    }

    static int[][] dirs = new int[][]{ {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

    // multi-source bfs
    private static Queue<Point> collectStart( char[][] grid )
    {
        Queue<Point> q = new LinkedList<Point>();
        for( int i = 0; i < m; i++ )
            for( int j = 0; j < n; j++ )
            {
                if( grid[ i ][ j ] == 'S' ) q.offer( new Point( i, j ) );
            }
        return q;
    }

    private static int bfs( char[][] grid )
    {
        Queue<Point> q = collectStart( grid );

        int steps = 0;
        while( !q.isEmpty() )
        {
            // finish this level
            for( int s = q.size(); s > 0; s-- )
            {
                Point cur = q.remove();
                if( grid[ cur.x ][ cur.y ] == 'X' ) return steps;
                // marked as visited
                grid[ cur.x ][ cur.y ] = 'D';

                for( int[] dir: dirs )
                {
                    int x = cur.x + dir[ 0 ], y = cur.y + dir[ 1 ];
                    if( x < 0 || x >= grid.length || y < 0 || y >= grid[ 0 ].length || grid[ x ][ y ] == 'D' ) continue;

                    q.offer( new Point( x, y ) );
                }
            }
            steps++;
        }
        // not found
        return -1;
    }

    private static class Point
    {
        int x, y;
        public Point( int x, int y ) { this.x = x; this.y = y; }
    }
}