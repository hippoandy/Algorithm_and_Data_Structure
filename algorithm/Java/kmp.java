// ref. https://xjay.net/201905/kmp-algorithm-note/

public class kmp
{
    // time complexity: O( m + n )
    // space complexity: O( n )
    private static void kmpSearch( String text, String pattern )
    {
        int m = text.length(), n = pattern.length();
        int[] prefix = prefixTable( pattern, n );

        int i = 0, j = 0;
        while( i < m )
        {
            // found!
            if( j == n-1 && text.charAt( i ) == pattern.charAt( j ) )
            {
                System.out.printf( "Found a match at idx %d\n", i - j );
                // keep searching!
                j = prefix[ j ];
            }

            // a match, go ahead for next char for both the string
            if( text.charAt( i ) == pattern.charAt( j ) )
            {
                i++; j++;
            }
            else
            {
                // // shift the pattern
                // j = prefix[ j ];
                // // if the current cell has value -1, means not able to find a match, increase i and j by 1
                // if( j == -1 )
                // {
                //     i++; j++;
                // }
                if( j > 0 ) j = prefix[ j-1 ];
                else        i++;
            }
        }
    }

    private static int[] prefixTable( String pattern, int n )
    {
        int[] prefix = new int[ n ];
        // build up the prefix table
        int i = 1, j = 0;
        while( i < n )
        {   // match!
            if( pattern.charAt( i ) == pattern.charAt( j ) )
                // j first increased by 1, then assign to prefix[ i ], finally i increased by 1
                prefix[ i++ ] = ++j;
            // not a match
            else
            {
                // make sure the index will not underflow
                if( j > 0 ) j = prefix[ j-1 ];
                // assign j's value to prefix[ i ], then i increased by 1
                else        prefix[ i++ ] = j;
            }
        }
        // for( i = n-1; i >= 1; i-- ) prefix[ i ] = prefix[ i-1 ];
        // prefix[ 0 ] = -1;
        return prefix;
    }

    public static void main( String[] args )
    {
        String text = "AABAACAADAABAABA", pattern = "AABA";
        kmpSearch( text, pattern );
    }
}