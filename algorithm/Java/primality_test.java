public class primality_test
{
    // quickly find the prime:
    // excluding 2 and 3, all the primes are either 6x+1 or 6x-1, x is a natural number
    private static Boolean isPrime( int N )
    {
        if( N <= 3 ) return N>1;
        if( N % 6 != 1 && N % 6 != 5 ) return false;
        
        int sqr = (int) Math.sqrt( N );
        for( int i = 5; i <= sqr; i+=6 )
            if( N % i == 0 || N % (i+2) == 0 )
                return false;
        return true;
    }

    public static void main( String[] args )
    {
        System.out.printf( "Num: %d, is prime? %b\n", 6, isPrime( 6 ) );
    }
}