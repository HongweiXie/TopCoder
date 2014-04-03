package srm.practice.srm469div2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class TheMoviesLevelThreeDivTwo {

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
		
		public String toString()
		{
			return index+"\t"+v1+"\t"+v2;
		}
	}
	
	private int counter=0;
	public int find(int[] timeJ, int[] timeB)
	{
		Movie mArray[]=new Movie[timeJ.length];
		for(int i=0;i<mArray.length;i++)
		{
			mArray[i]=new Movie(i,timeJ[i],timeB[i]);
			System.out.println(mArray[i]);
		}
		iterate(mArray,0,mArray.length);
		return set.size();
	}
	int iterator=0;
	private void iterate(Movie mArray[],int k, int m)
	{
		if(k==m-1)
		{
			counter+=test(mArray);
			iterator++;
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
				iterate(mArray,k+1,m);
				swap(mArray,k,i);
			}
		}
	}
	Set<String> set=new HashSet<String>();
	private int test(Movie[] mArray) {

		int counter=0;
		
		for(int i=1;i<mArray.length;i++)
		{
			System.out.println("i:"+i);
			boolean flag=true;
			/*Queue<Movie> qJ=new LinkedList<Movie>();
			Queue<Movie> qB=new LinkedList<Movie>();
			for(int j=0;j<i;j++)
			{
				qJ.add(mArray[j]);
			}
			for(int k=i;k<mArray.length;k++)
			{
				qB.add(mArray[k]);
			}
			int time=-1;
			int pollJ=0;
			int pollB=0;
			int pushJ=qJ.size();
			int pushB=qB.size();
			int sumJ=0;
			int sumB=0;
			while(!qJ.isEmpty()||!qB.isEmpty())
			{
				time++;
				
				if(!qJ.isEmpty()&&time>=qJ.peek().v1+sumJ)
				{
					
					Movie m=qJ.poll();
					pollJ++;
					sumJ+=m.v1;
					if(pushB<mArray.length)
					{
						qB.add(m);
						pushB++;
					}
				
				}
				if(!qB.isEmpty()&&time>=qB.peek().v2+sumB)
				{
					Movie m=qB.poll();
					pollB++;
					sumB+=m.v2;
					if(pushJ<mArray.length)
					{
						qJ.add(m);
						pushJ++;
					}
				}
				
				if(qJ.isEmpty()&&pollJ<mArray.length)
					flag=false;
				if(qB.isEmpty()&&pollB<mArray.length)
					flag=false;
				System.out.println(time+"\t"+sumJ+"\t"+sumB+"\t"+pollJ+"\t"+pollB+"\t"+qJ.size()+"\t"+qB.size());
			}
			assert sumB==13&&sumJ==20;*/
			int sum1=mArray[0].v1,sum2=0;
			for(int k=i;k<mArray.length;k++)
			{
				sum2+=mArray[k].v2;
			}
			if(sum1>=sum2)
			{
				flag=false;
			}
			for(int j=1;j<i;j++)
			{
				//System.out.println("sum1 sum2 "+sum1+"\t"+sum2);
				
				sum1+=mArray[j].v1;
				sum2+=mArray[j-1].v2;
				if(sum1>=sum2)
				{
					flag=false;
				}
			}
			//System.out.println("sum1 sum2 "+sum1+"\t"+sum2);
			sum1=0;
			sum2=mArray[i].v2;
			for(int j=0;j<i;j++)
			{
				sum1+=mArray[j].v1;
			}
			if(sum2>=sum1)
			{
				flag=false;
			}
			for(int k=i+1;k<mArray.length;k++)
			{
				
				sum1+=mArray[k-1].v1;
				sum2+=mArray[k].v2;
				if(sum2>=sum1)
				{
					flag=false;
				}
			}
			
			if(flag)
			{
				counter++;
				int results[]=new int[mArray.length];
				String s="";
				for(int j=0;j<mArray.length;j++)
				{
					results[j]=mArray[j].index;
				}
				Arrays.sort(results,0,i);
				Arrays.sort(results,i,results.length);
				for(int j=0;j<i;j++)
				{
					s+=results[j];
				}
				s+="a";
				
				for(int j=i;j<mArray.length;j++)
				{
					//System.out.println("j:"+j);
					s+=results[j];
				}
					
				set.add(s);
			}
			
		}
		return set.size();
	}

	private void swap(Movie[] mArray, int k, int i) {

		Movie temp=mArray[k];
		mArray[k]=mArray[i];
		mArray[i]=temp;
	}
	
	public static void main(String args[])
	{
		//[3a012, 2a013, 13a02, 1a023, 12a03, 23a01, 123a0] [13a02, 12a03, 23a01, 123a0]
		TheMoviesLevelThreeDivTwo ttd=new TheMoviesLevelThreeDivTwo();
		int mJ[]={13, 1, 1, 5};
		int mB[]={5, 1, 6, 1};
		System.out.println(ttd.find(mJ, mB));
		System.out.println(ttd.iterator);
		System.out.println(ttd.set);
	}
}
