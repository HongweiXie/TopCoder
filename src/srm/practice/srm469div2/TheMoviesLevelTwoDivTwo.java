package srm.practice.srm469div2;

public class TheMoviesLevelTwoDivTwo {

	class Movie
	{
		int index=0;
		int v1=0;
		int v2=0;
		public Movie(int index, int v1, int v2) {
			super();
			this.index = index;
			this.v1 = v1;
			this.v2 = v2;
		}
	}
	private int totalTime=0;
	private int scaryInit=74;
	public int[] find(int[] length, int[] scary)
	{
		Movie mArray[]=new Movie[length.length];
		int[] results=new int[mArray.length];
		for(int i=0;i<mArray.length;i++)
		{
			mArray[i]=new Movie(i,length[i],scary[i]);
		}
		iterate(mArray,results,0,mArray.length);
		return results;
	}
	
	private void iterate(Movie mArray[], int [] results,int k, int m)
	{
		if(k==m-1)
		{
			int t=calcu(mArray);
			
			if(t>totalTime)
			{
				System.out.println(t);
				totalTime=t;
				for(int i=0;i<results.length;i++)
				{
					results[i]=mArray[i].index;
				}
			}
			else if(t==totalTime)
			{
				System.out.println(t);
				int sum1=0,sum2=0;
				for(int i=0;i<results.length;i++)
				{
				
						sum1=sum1*10+(results[i]);
						sum2=sum2*10+(mArray[i].index);
					
				}
				if(sum1>sum2)
				{
					for(int i=0;i<results.length;i++)
					{
						results[i]=mArray[i].index;
					}
				}
			}
			return;
		}
		else
		{
			for(int i=k;i<m;i++)
			{
				swap(mArray,k,i);
				for(int j=0;j<mArray.length;j++)
				{
					System.out.print(mArray[j].index+"\t");
				}
				System.out.println();
				iterate(mArray,results,k+1,m);
				swap(mArray,k,i);
			}
		}
	}

	private int calcu(Movie[] mArray) {

		int scaryVal=scaryInit;
		int t=0;
		for(int i=0;i<mArray.length;i++)
		{
			for(int j=0;j<mArray[i].v1;j++)
			{
				if(j!=mArray[i].v2)
				{
					scaryVal--;
					t++;
				}
				else
				{
					scaryVal--;
					t++;
					scaryVal+=47;
				}
			
				if(scaryVal<=-0.5)
					return i+1;
			}
			
			
		}
		return mArray.length+1;
	}

	private void swap(Movie[] mArray, int k, int i) {

		Movie temp=mArray[k];
		mArray[k]=mArray[i];
		mArray[i]=temp;
	}
	
	/*public static void main(String args[])
	{
	//{{13, 1, 1, 5}, {5, 1, 6, 1}}{{187, 18, 192, 144}, {67, 10, 161, 52}}{{2, 149, 118, 114}, {1, 1, 64, 69}}
		TheMoviesLevelTwoDivTwo tot=new TheMoviesLevelTwoDivTwo();
		//int length[]={100, 100, 100, 100};
		//int scary[]={77, 76, 75, 74};
		//int results[]=tot.find(length,scary);
		int length[]={187, 18, 192, 144};
		int scary[]={67, 10, 161, 52};
		int results[]=tot.find(length,scary);
		for(int i=0;i<results.length;i++)
		System.out.print(results[i]+"\t");
	}*/
}
