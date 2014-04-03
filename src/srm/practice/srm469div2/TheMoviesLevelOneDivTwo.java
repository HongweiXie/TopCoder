package srm.practice.srm469div2;

import java.util.Arrays;

public class TheMoviesLevelOneDivTwo {

	public int find(int n, int m, int[] row, int[] seat)
	{
		assert row.length==seat.length;
		int array[][]=new int[n][m];
		for(int i=0;i<n;i++)
			Arrays.fill(array[i], 0);
		for(int i=0;i<row.length;i++)
		{
			array[row[i]-1][seat[i]-1]=1;
		}
		
		int sum=0;
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<m-1;j++)
			{
				if(array[i][j]==0&&array[i][j+1]==0)
				{
					sum++;
				}
			}
		}
		return sum;
		
	}
	
	
	/*public static void main(String args[])
	{
		TheMoviesLevelOneDivTwo tot=new TheMoviesLevelOneDivTwo();
		int row[]={1, 9, 6, 10, 6, 7, 9, 3, 9, 2};
		int seat[]={7, 7, 3, 3, 7, 1, 5, 1, 6, 2};
		System.out.println(tot.find(10, 8, row, seat));
	}*/
}
