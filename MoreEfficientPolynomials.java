import java.util.*;
/**
 * the fastest way to evaluate any polynomial is by using numbers already gathered.
   for example, x^16 is the same as ((((x^2)^2)^2)^2
   by storing new values in memory, we can lower the number of multiplications:
   known values: [x]
   x*x = x^2 -> store
   known values: [x,x^2]
   x^2 * x^2 = x^4 -> store
   known values: [x,x^2,x^4]
   x^4 * x^4 = x^8 -> store
   known values: [x,x^2,x^4,x^8]
   x^8 * x^8 = x^16
   this has calculated x^16 in only 4 multiplications! 
   This concept can be applied to non powers of two, but an algorithm for generating optimal solutions without brute forcing them is currently unknown.
   the purpose of this program is to generate sub-optimal solutions that are still fast. Although the algorithm used is well known, and not the fastest there is, I discovered it on my own.
 *
 * Nate Bradley
 * 1.0
 */
public class MoreEfficientPolynomials
{
    public static void main(String args[])
    {
        //instantiate reader 
        Scanner reader = new Scanner(System.in);
        System.out.print("What number would you like to be the base?: ");
        double base = reader.nextDouble();
        System.out.print("Type an integer power that you would like to have a mult. chain for: ");
        int power = reader.nextInt();
        //create a copy of power in memory
        int powCopy = power;
        //convert power into an array of binary digits. use bools to make the process easier to do since bools are literally only 1 or 0 by definition. 
        //Does waste memory for all the 0 bits after the most significant bit
        boolean[] bitArray = new boolean[32];
        //use a byte instead of an int to save 3 bytes of memory. Start at index -1 since that way index will end on the largest  
        byte index = 0;
        while(powCopy > 0)
        {
            //goes through each bit and checks to see if it is active or not.
            if((powCopy & 1) == 1)               
                bitArray[index] = true;
            else
                bitArray[index] = false;
            powCopy = powCopy >> 1;
            index++;
        }
        
        //here's where all the magic happens. Basically, I repeatedly square the number until I reach the most significant bit, at which point I use my bit Array to multiply the rest in. 
        double[] knownNums = new double[index];
        knownNums[0] = base;
        for(int x = 1; x < index; x++)
        {
            knownNums[x] = knownNums[x-1] * knownNums[x-1];
        }
        //we now know x^(most sig bit)
        double answer = knownNums[index-1];
        for(int x = 0; x < index - 1; x++)
        {
            if(bitArray[x])
            {
                answer *= knownNums[x];
            }
        }
        System.out.println(answer);
    }
}
