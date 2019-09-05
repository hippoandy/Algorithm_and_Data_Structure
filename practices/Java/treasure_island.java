import java.util.*;

class treasure_island
{
    public static void main( String[] args )
    {
        char[][] grid = {{'O', 'O', 'O', 'O'},
                         {'D', 'O', 'D', 'O'},
                         {'O', 'O', 'O', 'O'},
                         {'X', 'D', 'D', 'O'}};
        System.out.printf( "Min Steps: %d\n", bfs( grid ) );
    }

    static int[][] dirs = new int[][]{ {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

    private static int bfs( char[][] grid )
    {
        Queue<Point> q = new LinkedList<Point>();
        // always start at the top-left corner
        q.offer( new Point( 0, 0 ) );
        // marked as visited
        grid[ 0 ][ 0 ] = 'D';
        int steps = 1;
        while( !q.isEmpty() )
        {
            // finish this level
            for( int s = q.size(); s > 0; s-- )
            {
                Point cur = q.remove();
                for( int[] dir: dirs )
                {
                    int x = cur.x + dir[ 0 ], y = cur.y + dir[ 1 ];
                    if( x < 0 || x >= grid.length || y < 0 || y >= grid[ 0 ].length || grid[ x ][ y ] == 'D' ) continue;

                    if( grid[ x ][ y ] == 'X' ) return steps;
                    
                    q.offer( new Point( x, y ) );
                    grid[ x ][ y ] = 'D';
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