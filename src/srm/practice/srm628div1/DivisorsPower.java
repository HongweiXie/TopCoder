package srm.practice.srm628div1;

public class DivisorsPower {

	long max=1000000000000000000l;
	
	public long findArgument(long n)
	{
		for(int i=2;i<60;i++)
		{
			long r=getRoot(n,i);
			if(r!=-1)
			{
				long d=countDivisors(r);
				if(d==i)
				{
					return r;
				}
			}
		}
			
		return -1;
	}
	
	long getRoot(long n,long k)
	{
		if(n<k)
			return -1;
		long r=(long) Math.pow(n, 1.0/k);
		
		for(long i=r;i<=r+1;i++)
		{
			long p=1;
			for(int j=0;j<k;j++)
				p*=i;
			if(p==n)
				return i;
		}
		return -1;
	}
	
	long countDivisors(long x)
	{
		long p=1;
		long c=0;
		while(p<=x/p)
		{
			if(x%p==0)
			{
				c++;
				if(x/p!=p)
				{
					c++;
				}
			}
			p++;
		}
		return c;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DivisorsPower dp=new DivisorsPower();
		System.out.println(dp.findArgument(2498388559757689l));
	}

}
