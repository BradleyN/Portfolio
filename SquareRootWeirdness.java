import java.util.*;
/**
 * A weird square root algorithm I made.
 *
 * Nate Bradley
 * 1.0
 */
public class SquareRootWeirdness
{
    public static void main(String args[])
    {
        Scanner reader = new Scanner(System.in);
        Random randy = new Random();
        int[] ints = new int[1000000];
        for(int x = 0; x < ints.length; x++)
        {
            ints[x] = randy.nextInt(2147483647);
        }
       
        double sum = 0;
        for(int n = 0; n < ints.length; n++)
        {
            //System.out.println(100 * error(ints[n]));
            //"Trial " + n + ": error for " + ints[n] + "= " + 
            sum += error(ints[n]);
        }
        
        System.out.println("Average % error: " + (sum * 100)/ints.length );
        
        int worstEst = maxError(ints);
        System.out.println("maximum % error was on " + ints[worstEst] + ", with an error of " + 100 * error(ints[worstEst]));
        //System.out.println("scuffedSqrt(" + ints[worstEst] + ") = " +  scuffedSqrt(ints[worstEst]));
    }
    
    public static int maxError(int[] arr)
    {
        int maxIndex = 0;
        for(int x = 0; x < arr.length; x++)
        {
            if(error(arr[x]) > error(arr[maxIndex]))
            {
                maxIndex = x;
                //System.out.println("new largest error found, " + arr[maxIndex] + ", with an error of " + 100 * error(arr[maxIndex]));
            }
        }
        return maxIndex;
    }
    
    public static double error(int x)
    {
        return Math.abs(Math.sqrt(x) - scuffedSqrt(x)) / Math.sqrt(x);
    }
    
    public static int scuffedSqrt(int x)
    {
        int bigBit = mostSigBit(x);
        bigBit = (bigBit >> 1) + (bigBit & 1);  
        int est = (((x >> bigBit) + (1 << bigBit)) >> 1);
        return est;
    }
    
    public static int mostSigBit(int x)
    {
        int index = 16;
        int guess = 16;
        while(index > 0)
        {
            if(x >> guess > 0)
            {
                index = index >> 1; 
                guess = guess | index;
            }
            else
            {
                guess = (guess & ~index) | (index >> 1);
                index = index >> 1;
            }
        }
        return guess;
    }
}