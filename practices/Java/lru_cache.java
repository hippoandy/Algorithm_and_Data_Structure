import java.util.*;

class lru_cache
{
    public static class LRUCache
    {
        private int capacity;
        private int size;

        DoublyNode head;
        DoublyNode tail;

        Map<Integer, DoublyNode> cache;

        public LRUCache( int capacity )
        {
            this.capacity = capacity;
            this.size = 0;

            // 2 dummy pointer
            this.head = new DoublyNode();
            this.tail = new DoublyNode();

            head.next = tail;
            tail.pre = head;

            this.cache = new HashMap<Integer, DoublyNode>();
        }

        public void put( int k, int v )
        {
            if( !this.cache.containsKey( k ) )
            {
                DoublyNode n = new DoublyNode( k, v );
                // add the node to the cache
                cache.put( k, n );
                // add the node to the list
                addNextToHead( n );

                if( ++this.size > this.capacity )
                {
                    // delete oldest one
                    DoublyNode pop = deleteTail();
                    this.cache.remove( pop.key );
                    this.size--;
                }
            }
            else
            {
                // update value of the existing node
                DoublyNode cur = cache.get( k );
                cur.val = v;
                // increase its priority
                increasePriority( cur );
            }
        }

        public int get( int k )
        {
            if( !this.cache.containsKey( k ) ) return -1;
            DoublyNode tar = cache.get ( k );
            // since it is been accessed, increase its priority
            increasePriority( tar );
            return tar.val;
        }

        private DoublyNode deleteTail()
        {
            DoublyNode pop = tail.pre;
            removeNode( pop );
            return pop;
        }

        private void addNextToHead( DoublyNode n )
        {
            n.pre = head;
            n.next = head.next;

            head.next.pre = n;
            head.next = n;
        }

        private void removeNode( DoublyNode n )
        {
            DoublyNode pre = n.pre, next = n.next;

            pre.next = next;
            next.pre = pre;

            n.next = null;
            n.pre = null;
        }

        private void increasePriority( DoublyNode n )
        {
            removeNode( n );
            addNextToHead( n );
        }

        private class DoublyNode
        {
            DoublyNode pre;
            DoublyNode next;
            int key;
            int val;
            public DoublyNode() { init_pointer(); }
            public DoublyNode( int k, int v )
            {
                this.key = k;
                this.val = v;
                init_pointer();
            }
            private void init_pointer()
            {
                this.pre = null;
                this.next = null;
            }
        }
    }

    public static void main( String[] args )
    {
        LRUCache cache = new LRUCache( 2 /* capacity */ );

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println( cache.get(1) );       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println( cache.get(2) );       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println( cache.get(1) );       // returns -1 (not found)
        System.out.println( cache.get(3) );       // returns 3
        System.out.println( cache.get(4) );       // returns 4
        System.out.println( cache.get(3) );       // returns 3
        System.out.println( cache.get(3) );       // returns 3
    }
}