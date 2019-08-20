
import java.lang.*;

public class trie
{
    private static class TrieNode
    {
        TrieNode[] next;
        int size;
        boolean isEnd;
        String word;

        public TrieNode( int size )
        {
            this.next = new TrieNode[ size ];
            this.size = size;
            this.isEnd = false;
            this.word = "";
        }

        public boolean contains( int idx )
        {
            if( idx < 0 || idx > this.size ) throw new IllegalArgumentException( "Invalid index entered for funct TrieNode.contains()!" );
            if( this.next[ idx ] != null ) return true;
            return false;
        }

        public void put( int idx, TrieNode n )
        {
            if( idx < 0 || idx > this.size ) throw new IllegalArgumentException( "Invalid index entered for funct TrieNode.put()!" );
            this.next[ idx ] = n;
        }

        public TrieNode get( int idx )
        {
            if( idx < 0 || idx > this.size ) throw new IllegalArgumentException( "Invalid index entered for funct TrieNode.get()!" );
            return this.next[ idx ];
        }

        public void setEnd( String word )
        {
            this.isEnd = true;
            this.word = word;
        }

        public boolean isEnd()
        { return this.isEnd; }
    }

    /** Returns if the word is in the trie. */
    // Time: O(n), Space: O(1)
    public static boolean search( TrieNode root, String word )
    {
        TrieNode n = searchPrefix( root, word );
        // word is exists iff the word is in the trie and it is not a prefix of another word
        return n != null && n.isEnd();
    }
    private static TrieNode searchPrefix( TrieNode root, String word )
    {
        TrieNode n = root;
        for( int i = 0; i < word.length(); i++ )
        {
            int cur = word.charAt( i ) - 'a';
            if( n.contains( cur ) ) n = n.get( cur ); // keep following the path
            else return null;
        }
        return n;
    }

    public static void main( String[] args )
    {
        /* test case ******************** */
        String[] dict = { "leetcode", "leet", "lintcode", "code", "yelp", "youtube" };
        String to_search = "leet";
        int size = 26;
        /* ******************** test case */
        TrieNode root = new TrieNode( size );
        for( String w: dict )
        {
            TrieNode cur = root;
            for( char c: w.toCharArray() )
            {
                int idx = c - 'a';
                if( !cur.contains( idx ) ) cur.put( idx, new TrieNode( size ) );
                cur = cur.get( idx );
            }
            // after a word finished, set the end
            cur.setEnd( w );
        }
        for( String w: dict )
        {
            if( search( root, w ) )
                System.out.printf( "Found %s!\n", w );
        }
    }
}