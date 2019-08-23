
import java.util.*;

public class top_message
{
    public static void findTop3( List<Msg> input )
    {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for( Msg m: input )
            map.computeIfAbsent( m.sender, x -> new ArrayList<String>() ).add( m.receiver );
        System.out.println( map );
        // customize the comparator
        PriorityQueue<Pair> maxheap = new PriorityQueue<Pair>(
            new Comparator<Pair>() {
                @Override
                public int compare( Pair a, Pair b )
                {
                    if( a.amount == b.amount ) return a.key.compareTo( b.key );
                    return b.amount - a.amount;
                }
        });
        // add to max-heap
        for( String k: map.keySet() )
            maxheap.offer( new Pair( k, map.get( k ).size() ) );

        int c = 0;
        while( !maxheap.isEmpty() && c < 3 )
        {
            System.out.println( maxheap.remove().key );
            c++;
        }
    }
    public static void findTop3_nodup( List<Msg> input )
    {
        Map<String, Set<String>> map = new HashMap<String, Set<String>>();
        for( Msg m: input )
            map.computeIfAbsent( m.sender, x -> new HashSet<String>() ).add( m.receiver );
        System.out.println( map );
        // customize the comparator
        PriorityQueue<Pair> maxheap = new PriorityQueue<Pair>(
            new Comparator<Pair>() {
                @Override
                public int compare( Pair a, Pair b )
                {
                    if( a.amount == b.amount ) return a.key.compareTo( b.key );
                    return b.amount - a.amount;
                }
        });
        // add to max-heap
        for( String k: map.keySet() )
            maxheap.offer( new Pair( k, map.get( k ).size() ) );

        int c = 0;
        while( !maxheap.isEmpty() && c < 3 )
        {
            System.out.println( maxheap.remove().key );
            c++;
        }
    }
    private static class Pair
    {
        int amount;
        String key;
        public Pair( String k, int v )
        {
            this.amount = v; this.key = k;
        }
    }

    public static void main( String[] args )
    {
        findTop3( build_input() );
        findTop3_nodup( build_input() );
    }
    private static List<Msg> build_input()
    {
        List<Msg> input = new ArrayList<Msg>();
        input.add( new Msg( "a", "b", "a@gmail.com" ) );
        input.add( new Msg( "a", "b", "a@gmail.com" ) );
        input.add( new Msg( "b", "c", "b@gmail.com" ) );
        input.add( new Msg( "a", "c", "a@gmail.com" ) );
        input.add( new Msg( "b", "d", "b@gmail.com" ) );
        input.add( new Msg( "d", "a", "d@gmail.com" ) );
        input.add( new Msg( "b", "a", "b@gmail.com" ) );
        input.add( new Msg( "c", "a", "c@gmail.com" ) );
        input.add( new Msg( "d", "a", "d@gmail.com" ) );
        input.add( new Msg( "a", "c", "a@gmail.com" ) );

        return input;
    }
    private static class Msg
    {
        String sender;
        String receiver;
        String email;
        public Msg( String src, String dest, String acc )
        {
            this.sender = src; this.receiver = dest; this.email = acc;
        }
    }
}