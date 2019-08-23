
import java.lang.*;
import java.util.*;

public class min_queue
{
    private static class MinQueue
    {
        Queue<Integer> q;
        int min;

        public MinQueue()
        {
            this.q = new LinkedList<Integer>();
            this.min = Integer.MAX_VALUE;
        }

        // time complexity: O( 1 )
        public void enqueue( int x )
        {
            this.q.offer( x );
            this.min = (x < this.min) ? x : this.min;
        }

        // time complexity: average case O( 1 ), worse case O( n )
        public int dequeue()
        {
            int pop = this.q.remove();
            if( pop == findMin() )
            {
                this.min = Integer.MAX_VALUE;
                for( int i: q ) min = (i < min) ? i: min;
            }
            return pop;
        }

        public int findMin()
        {
            return this.min;
        }

        public boolean isEmpty()
        { return this.q.isEmpty(); }
    }

    private static class MinQueue_amortized
    {
        Queue<Integer> q;
        Deque<Integer> deque;

        public MinQueue_amortized()
        {
            this.q = new LinkedList<Integer>();
            this.deque = new ArrayDeque<Integer>();
        }

        // time complexity: amortized O(1)
        public void enqueue( int x )
        {
            // push element to queue;
            this.q.offer( x );
            // remove all the previous element that is larger than the current input value
            while( !this.deque.isEmpty() && this.deque.peekLast() > x )
                this.deque.removeLast();
            this.deque.offer( x );
        }

        // time complexity: amortized O(1)
        public int dequeue()
        {
            int pop = this.q.remove();
            if( pop == this.deque.peek() )
                this.deque.remove();
            return pop;
        }

        public int findMin()
        {
            if( this.deque.isEmpty() ) throw new NullPointerException( "Queue is empty!" );
            return this.deque.peek();
        }

        public boolean isEmpty()
        { return this.q.isEmpty(); }
    }

    public static void main( String[] args )
    {
        // MinQueue q = new MinQueue();
        MinQueue_amortized q = new MinQueue_amortized();
        q.enqueue( 2 );
        q.enqueue( 5 );
        System.out.println( q.findMin() );    // should equal 2
        System.out.println( q.dequeue() );    // return 2
        System.out.println( q.findMin() );    // should equal 5
        q.enqueue( 3 );
        System.out.println( q.findMin() );    // should equal 5
        q.enqueue( 1 );
        System.out.println( q.findMin() );    // should equal 5
        System.out.println( q.dequeue() );
        q.enqueue( 3 );
        System.out.println( q.dequeue() );
        System.out.println( q.findMin() );
        System.out.println( q.dequeue() );
        System.out.println( q.findMin() );  // should equal 3
        System.out.println( q.dequeue() );
        q.enqueue( 1 );
        q.enqueue( 1 );
        System.out.println( q.findMin() );
        System.out.println( q.dequeue() );
        System.out.println( q.findMin() );
        System.out.println( q.dequeue() );

        System.out.println( "Content of queue:" );
        while( !q.isEmpty() )
            System.out.println( q.dequeue() );
    }
}