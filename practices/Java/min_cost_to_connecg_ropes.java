import java.util.*;

class min_cost_to_connecg_ropes
{
    public static void main( String[] args )
    {
        // int[] ropes = new int[]{ 8, 4, 6, 12 };
        // int[] ropes = new int[]{1, 2, 5, 10, 35, 89};
        int[] ropes = new int[]{2, 2, 3, 3};
        System.out.printf( "Min Cost is %d\n", getMinCost( ropes ) );
    }

    private static int getMinCost( int[] ropes )
    {
        // sort the array
        Arrays.sort( ropes );
        // create a min heap
        PriorityQueue<Integer> minheamp = new PriorityQueue<Integer>();
        for( int r: ropes ) minheamp.offer( r );

        System.out.println( minheamp );
        int cost = 0;
        while( minheamp.size() != 1 )
        {
            int a = minheamp.remove(), b = minheamp.remove();
            minheamp.offer( a+b );
            cost += a+b;
        }
        return cost;
    }
}