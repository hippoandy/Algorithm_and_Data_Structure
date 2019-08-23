
class longest_common_prefix
{
    public static String longestCommonPrefix(String[] strs)
    {
        if( strs == null || strs.length == 0 ) return "";
        
        String prefix = strs[ 0 ];
        for( int i = 1; i < strs.length; i++ )
            while( strs[ i ].indexOf( prefix ) != 0 )
            {
                prefix = prefix.substring( 0, prefix.length()-1 );
                if( prefix.length() == 0 ) return "";
            }
        return prefix;
    }

    public static String longestCommonPrefix_KMP(String[] strs)
    {
        if( strs == null || strs.length == 0 ) return "";
        
        String prefix = strs[ 0 ];
        for( int i = 1; i < strs.length; i++ )
            while( prefix.length() != 0 && kmp_search( strs[ i ], prefix ) != 0 )
            {
                prefix = prefix.substring( 0, prefix.length()-1 );
                if( prefix.length() == 0 ) return "";
            }
        return prefix;
    }

    private static int kmp_search( String str1, String str2 )
    {
        int m = str1.length(), n = str2.length();

        if( m == 0 && n == 0 ) return 0;

        int[] prefix = prefix_table( str2, n );

        int i = 0, j = 0;
        while( i < m && j < n )
        {
            if( j == n-1 && str1.charAt( i ) == str2.charAt( j ) ) return i-j;

            if( str1.charAt( i ) == str2.charAt( j ) )
            { i++; j++; }
            else
            {
                // j = prefix[ j ];
                // if( j < 0 ){ i++; j++; }
                if( j > 0 ) j = prefix[ j-1 ];
                else
                    i++;
            }
        }
        // not found
        return -1;     
    }

    private static int[] prefix_table( String pattern, int n )
    {
        if( n <= 0 ) return null;

        int[] prefix = new int[ n ];
        prefix[ 0 ] = 0;
        int i = 1, j = 0;
        while( i < n )
        {
            if( pattern.charAt( i ) == pattern.charAt( j ) )
                prefix[ i++ ] = ++j;
            else
            {
                if( j > 0 ) j = prefix[ j-1 ];
                else
                    prefix[ i++ ] = j;
            }
        }
        // for( i = n-1; i >= 1; i-- ) prefix[ i ] = prefix[ i-1 ];
        // prefix[ 0 ] = -1;
        return prefix;
    }

    public static void main( String[] args )
    {
        String[] strs = { "leets","leetcode", "leet", "leeds" };
        // String[] strs = { "", "leet", "leeds" };
        // String res = longestCommonPrefix( strs );
        String res = longestCommonPrefix_KMP( strs );
        if( res.isEmpty() )
            System.out.println( "No common prefix!" );
        else
            System.out.printf( "Longest common prefix (LCP) is \"%s\"\n", res );
    }
}