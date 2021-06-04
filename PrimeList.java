
/**
 * A prime number is a number that cannot be factored into smaller primes. This concept allows us to go much more efficiently create a list of the first n primes. \
 * Basically just a sieve of eratosthenes.
 *
 * Nate Bradley
 * 1.0
 */
public class PrimeList
{
    public static void main(String args[])
    {
        boolean flag;
        int[] PrimeList = new int[100];
        PrimeList[0] = 2;
        int PrimeNums = 1;
        int index = 3;
        
        while(PrimeNums < 100)
        {
            flag = true;
            for(int y = 0; y < PrimeNums; y++)
            {
                if(index % PrimeList[y] == 0)
                {
                    //if it finds a prime factor, stop looking
                    flag = false;
                    break;
                } 
            }
            if(flag)
            {
                PrimeList[PrimeNums] = index;
                PrimeNums++;
                System.out.println(index + " is prime");
            }
            index += 2;
        }
    }
}
