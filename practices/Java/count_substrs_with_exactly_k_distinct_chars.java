
import java.util.*;

class count_substrs_with_exactly_k_distinct_chars
{
    public static void main( String[] args )
    {
        String s = "pqpqs";
        int k = 2;

        List<String> ans = new ArrayList<String>();
        
        for( int i = 0; i < s.length(); i++ )
        {
            char c = s.charAt( i );
            String tmp = "" + c;
            Set<Character> set = new HashSet<Character>();
            set.add( c );

            for( int j = i+1; j < s.length(); j++ )
            {
                char nc = s.charAt( j );
                set.add( nc );
                tmp += nc;
                if( tmp.length() >= k && set.size() == k ) ans.add( tmp );
            }
        }
        System.out.println( ans );
    }
}