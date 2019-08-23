
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class waitlist
{
    private static class Waitlist
    {
        DoublyNode head;
        DoublyNode tail;
        int size;

        Map<Integer, PriorityQueue<DoublyNode>> cache;

        public Waitlist()
        {
            this.size = 0;

            // two dummy pointer
            this.head = new DoublyNode();
            this.tail = new DoublyNode();

            this.head.next = this.tail;
            this.tail.prev = this.head;

            this.cache = new HashMap<Integer, PriorityQueue<DoublyNode>>();
        }

        public void addToWL( int rank, int size )
        {
            DoublyNode n = new DoublyNode( rank, size );

            this.cache.computeIfAbsent( size, x -> new PriorityQueue<DoublyNode>(
                new Comparator<DoublyNode>()
                {   @Override
                    public int compare( DoublyNode a, DoublyNode b )
                    { return a.rank - b.rank; }
                }
            )).offer( n );
            
            // alwasy add new item to the tail
            n.next = tail;
            n.prev = tail.prev;

            tail.prev.next = n;
            tail.prev = n;

            this.size++;
        }

        public DoublyNode removeFromWL( int size )
        {
            if( !this.cache.containsKey( size ) ) return null;
            if( this.cache.get( size ).isEmpty() ) return null;

            // remove from cache
            DoublyNode pop = this.cache.get( size ).remove();
            // remove from list
            removeNode( pop );
            return pop;
        }

        public void removeNode( DoublyNode n )
        {
            DoublyNode prev = n.prev, next = n.next;

            prev.next = next;
            next.prev = prev;

            n.prev = null;
            n.next = null;
        }

        public int getSize() { return this.size; }

        public void checkWaitList( boolean backward )
        {
            if( backward )
            {
                DoublyNode cur = this.tail.prev;
                while( this.getSize() > 0 && cur != this.head )
                {
                    System.out.println( cur.val );
                    cur = cur.prev;
                }
            }
            else
            {
                DoublyNode cur = this.head.next;
                while( this.getSize() > 0 && cur != this.tail )
                {
                    System.out.println( cur.val );
                    cur = cur.next;
                }
            }
        }
        public void checkCache() { System.out.println( this.cache ); }
    }

    private static class DoublyNode
    {
        DoublyNode prev;
        DoublyNode next;
        int rank;           // the smaller, the higher
        int val;

        public DoublyNode() { this.rank = 0; this.val = -1; default_init(); }
        public DoublyNode( int r, int v ) { this.rank = r; this.val = v; default_init(); }

        private void default_init() { this.prev = null; this.next = null; }
    }

    public static void main( String[] args )
    {
        Waitlist wl = new Waitlist();

        wl.addToWL( 0, 3 );
        wl.addToWL( 1, 4 );
        wl.addToWL( 2, 2 );
        wl.addToWL( 3, 3 );
        wl.addToWL( 3, 1 );

        wl.checkWaitList( false );
        // wl.checkWaitList( true );
        // wl.checkCache();

        DoublyNode pop = wl.removeFromWL( 3 );
        if( pop == null ) System.out.println( "No such group!" );
        else
            System.out.printf( "Rank: %d, Size: %d\n", pop.rank, pop.val );

        pop = wl.removeFromWL( 3 );
        if( pop == null ) System.out.println( "No such group!" );
        else
            System.out.printf( "Rank: %d, Size: %d\n", pop.rank, pop.val );

        wl.checkWaitList( false );
    }
}