import java.io.*;
import java.util.*;

public class Main 
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(new BufferedInputStream(System.in));
		Main aMain = new Main();
		try
		{
			aMain.run(in);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.out.println();
		}
	}
	
	private void run(Scanner in)
	{
		int n, m;
		String line;
		char[][] data, result;
		byte count = 0;
		
		while (in.hasNext())
		{
			n = in.nextInt();
			m = in.nextInt();
			if (n == m && n == 0)
			{
				System.exit(0);
			}
			else 
			{
				in.nextLine();
				// Initialization of each loop
				data = new char[n][m];
				result = new char[n][m];
				count++;
				
				// Filling initial data
				for (int i=0; i < n; i++)
				{
					line = in.nextLine();
					for (int j=0; j < m; j++)
					{
						data[i][j] = line.charAt(j);
					}
				}
				
				// Processing data
				result = sweep(data, n, m);
				
				// Printing the result
				System.out.println("Field #" + count + ":");
				for (int i=0; i < n; i++)
				{
					for (int j=0; j < m; j++)
					{
						System.out.print(result[i][j]);
					}
					System.out.println();
				}
				System.out.println();
			}
		}
	}
	
	private char[][] sweep(char[][] data, int n, int m)
	{
		char[][] res = data;
		byte mines;
		for (int i=0; i < n; i++)
		{
			for (int j=0; j < m; j++)
			{
				if (data[i][j] == '.')
				{
					mines = 0;
					if ((i-1 >= 0) && (data[i-1][j] == '*'))
						mines++;
					if ((i-1 >= 0) && (j+1 <= m-1) && (data[i-1][j+1] == '*'))
						mines++;
					if ((j+1 <= m-1) && (data[i][j+1] == '*'))
						mines++;
					if ((i+1 <= n-1) && (j+1 <= m-1) && (data[i+1][j+1] == '*'))
						mines++;
					if ((i+1 <= n-1) && (data[i+1][j] == '*'))
						mines++;
					if ((i+1 <= n-1) && (j-1 >= 0) && (data[i+1][j-1] == '*'))
						mines++;
					if ((j-1 >= 0) && (data[i][j-1] == '*'))
						mines++;
					if ((i-1 >= 0) && (j-1 >= 0) && (data[i-1][j-1] == '*'))
						mines++;
					
					res[i][j] = (char)(mines+48);
				}
			}
		}
		return res;
	}
}
