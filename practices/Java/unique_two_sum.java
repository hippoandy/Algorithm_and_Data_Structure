import java.util.*;

class unique_two_sum
{
    public static void main( String[] args )
    {
        int[] nums = {1, 1, 2, 45, 46, 46};
        int target = 47;

        Set<List<Integer>> set = new HashSet<List<Integer>>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for( int i = 0; i < nums.length; i++ )
        {
            int counterpart = target - nums[ i ];
            if( map.containsKey( counterpart ) )
            {
                int p = map.get( counterpart );
                set.add( Arrays.asList(
                    (nums[ p ] < nums[ i ] ? nums[ p ] : nums[ i ]),
                    (nums[ i ] < nums[ p ] ? nums[ p ] : nums[ i ]) )
                );
            }
            map.put( nums[ i ], i );
        }

        System.out.println( set.size() );
    }
}