import java.io.*;
import java.util.*;

public class Main 
{
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
		
		for (int i = b; i >= a && a != 1; i--)
		{
			int x = i;
			int count = 1;
			while (x > 1)
			{
				if (x%2 == 0)
					x /= 2;
				else
					x = 3*x + 1;

				count++;
			}
			if (count > max)
				max = count;
		}
		
		return max;
	}
}
