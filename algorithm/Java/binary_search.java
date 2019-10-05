// ref. https://github.com/yuzhoujr/leetcode/issues/8

public class binary_search
{
    private static int binarySearch( int[] nums, int tar )
    {
        int l = 0, r = nums.length - 1;
        while( l + 1 < r )
        {
            int m = l + (r - l) / 2;
            if( nums[ m ] == tar ) return nums[ m ];
            if( nums[ m ] < tar )   l = m;
            else                    r = m;

            // final case after several loop
            // l will be right next to r
            if( nums[ l ] == tar ) return nums[ l ];
            if( nums[ r ] == tar ) return nums[ r ];
        }
        // not able to find the number
        return -1;
    }

    public static void main( String[] args )
    {
        int[] nums = { 1, 3, 4, 6, 8, 11, 15, 19, 20 };
        int tar = 15;

        System.out.println( binarySearch( nums, tar ) );
    }
}