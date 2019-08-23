import java.lang.Character.Subset;
import java.util.*;

class longest_substring
{
    private static void lenOfLongestSubstrWithoutDup( String s )
    {
        if( s == null || s.length() == 0 ) System.out.print( "Invalid input string" );
        int n = s.length();

        Set<Character> set = new HashSet<Character>();
        int i = 0, j = 0, ans = 0;
        while( i < n && j < n )
        {
            if( !set.contains( s.charAt( j ) ) )
            {
                set.add( s.charAt( j ) );
                j++;
                ans = (j-i > ans) ? j-i : ans;
            }
            else
            {
                // move i pointer
                set.remove( s.charAt( i ) );
                i++;
            }
        }
        System.out.printf( "Len of Longest substring without dup is %d\n", ans );
    }

    private static void setOfSubstrsWithoutDup_n2( String s )
    {
        if( s == null || s.length() == 0 ) System.out.print( "Invalid input string" );
        int n = s.length();

        Set<String> substrs = new HashSet<String>();

        for( int i = 0; i < n; i++ )
            for( int j = 0; j < (n-i); j++ )
                substrs.add( s.substring( j, i + j + 1 ) );
        
        List<String> ans = new ArrayList<String>( substrs );

        Collections.sort( ans, new Comparator<String>()
        {   @Override
            public int compare( String a, String b )
            { return a.compareTo( b ); }
        });
        System.out.println( ans );
    }

    public static void main( String[] args )
    {
        String testcase = "aba";
        lenOfLongestSubstrWithoutDup( testcase );
        setOfSubstrsWithoutDup_n2( testcase );
    }
}