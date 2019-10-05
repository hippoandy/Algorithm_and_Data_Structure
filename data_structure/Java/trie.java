
import java.lang.*;
import java.util.*;

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

        public String getWord()
        { return this.word; }
    }

    public static void main( String[] args )
    {
        test1();
        test2();
    }

    public static void test2()
    {
        /* test case ******************** */
        String[] dict = { "burger king", "kdk dnsd burgers", "sad burger's", "walburgers", "burger lover", "burger and brew" };
        String search = "bur";
        int size = 27;
        Map<String, List<String>> partToName = new HashMap<String, List<String>>();
        /* ******************** test case */

        TrieNode root = new TrieNode( size );

        for( String w: dict )
        {
            // split the name by space and store in trie
            String[] split = w.split( "\\s+" );
            for( String p: split )
            {
                // rest the trie pointer here!
                TrieNode cur = root;
                for( char c: p.toCharArray() )
                {
                    int idx = (c == '\'') ? 26: c - 'a';
                    if( !cur.contains( idx ) ) cur.put( idx, new TrieNode( size ) );
                    cur = cur.get( idx );
                }
                // after a word finished, set the end
                cur.setEnd( p );

                // build the mapping
                partToName.computeIfAbsent( p, x -> new ArrayList<String>() ).add( w );
            }
        }
        
        List<String> candi = autoComplete( root, search );
        HashSet<String> autocomplete = new HashSet<String>();
        for( String s: candi ) autocomplete.addAll( partToName.get( s ) );
        // print the result list
        System.out.println( autocomplete );
    }
    private static List<String> autoComplete( TrieNode root, String prefix )
    {
        List<String> candi = new ArrayList<String>();
        TrieNode cur = root;
        for( char c: prefix.toCharArray() )
        {
            int idx = (c == '\'') ? 26: c - 'a';
            if( !cur.contains( idx ) ) return candi;
            cur = cur.get( idx );
        }
        // if the prefix is valid, search for words longer than prefix
        traverse( prefix, cur, candi);
        return candi;
    }
    private static void traverse( String search, TrieNode cur, List<String> candi )
    {
        // found a partial word, add to candi list
        if( cur.isEnd() ) candi.add( cur.getWord() );
        for( int i = 'a'; i <= 'z'; i++ )
        {
            int idx = i - 'a';
            if( cur.contains( idx ) )
                traverse( search + i, cur.get( idx ), candi );
        }
        // for character '\''
        if( cur.contains( 26 ) )
            traverse( search + '\'', cur.get( 26 ), candi );
    }

    public static void test1()
    {
        /* test case ******************** */
        String[] dict = { "leetcode", "leet", "lintcode", "code", "yelp", "youtube" };
        int size = 26;
        /* ******************** test case */

        TrieNode root = new TrieNode( size );
        // build the trie
        for( String w: dict )
        {
            TrieNode cur = root;
            for( char c: w.toCharArray() )
            {
                int idx = c - 'a';
                if( !cur.contains( idx ) ) cur.put( idx, new TrieNode( size ) );
                cur = cur.get( idx );
            }
            // set end while the current word complete
            cur.setEnd( w );
        }
        for( String w: dict )
            if( search( root, w ) )
                System.out.printf( "Found %s!\n", w );
    }

    /** Returns if the word is in the trie. */
    // Time: O(n), Space: O(1)
    public static boolean search( TrieNode root, String word )
    {
        TrieNode n = root;
        for( int i = 0; i < word.length(); i++ )
        {
            int cur = word.charAt( i ) - 'a';
            if( n.contains( cur ) ) n = n.get( cur ); // keep following the path
            else return false;
        }
        // word is exists iff the word is in the trie and it is not a prefix of another word
        return n != null && n.isEnd();
    }
}