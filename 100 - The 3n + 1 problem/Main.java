import java.io.*;
import java.util.*;

public class Main 
{
	public static int[] cache = new int[1000001];
	public static void main(String[] args) 
	{
		int a, b, cycle;
		Scanner in = new Scanner(new BufferedInputStream(System.in));
		while (in.hasNextInt())
		{
			a = in.nextInt();
			b = in.nextInt();
			cycle = calculate(a, b);
			System.out.printf("%d %d %d\n", a, b, cycle);
		}
	}
	
	private static int calculate(int a, int b)
	{
		int max = 1;
		// Little swap of parameters
		if (a > b)
		{
			int c = a; 
			a = b;
			b = c;
		}
		
		for (int i = a; i <= b && b != 1; i++)
		{
			int x = i;
			int count = 1;
			
			if (cache[i] == 0)
			{
				while (x > 1)
				{
					if (x%2 == 0)
						x /= 2;
					else
						x = 3*x + 1;
					count++;
				}
				cache[i] = count;
			}
			else 
			{
				count = cache[i];
			}

			if (count > max)
				max = count;
		}
		
		return max;
	}
}
