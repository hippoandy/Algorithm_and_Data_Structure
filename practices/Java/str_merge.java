
class str_merge
{
    public static void main( String[] args )
    {
        String str1 = "abdcd", str2 = "dcd";

        String res = kmp_merge( str1, str2 );
        System.out.println( res );
    }

    public static String kmp_merge( String str1, String str2 )
    {
        int m = str1.length(), n = str2.length();
        if( m == 0 || n == 0 ) return str1 + str2;

        // make sure str2 is shorter than str1
        if( m < n ) return kmp_merge( str2, str1 );
        
        int[] prefix = prefix_table( str2, n );
        int i = 0, j = 0;
        while( i < m )
        {
            if( str1.charAt( i ) == str2.charAt( j ) ) { i++; j++; }
            else
            {
                j = prefix[ j ];
                if( j == -1 ) { i++; j++; }
            }
        }
        return str1 + str2.substring( j );
    }

    public static int[] prefix_table( String text, int n )
    {
        int[] prefix = new int[ n ];
        int i = 1, j = 0;
        while( i < n )
        {
            if( text.charAt( i ) == text.charAt( j ) )
                prefix[ i++ ] = ++j;
            else
            {
                if( j > 0 ) j = prefix[ j-1 ];
                else
                    prefix[ i++ ] = j;
            }
        }
        // shift the value
        for( i = n-1; i >= 1; i-- ) prefix[ i ] = prefix[ i-1 ];
        prefix[ 0 ] = -1;
        return prefix;
    }
}