package srm.practice.srm145div1;

public class VendingMachine {

	class Column
	{
		int index;
		int prices[];
		int sum=0;
		public Column(int index,int size) {
			super();
			this.index = index;
			prices=new int[size];
		}
		
		public int sum()
		{
			sum=0;
			for(int i=0;i<prices.length;i++)
				sum+=prices[i];
			return sum;
		}
		
	}
	class Purchase
	{
		int row;
		int column;
		int time;
		public Purchase(String str) {
			super();
			String t[]=str.split(":");
			time=Integer.parseInt(t[1]);
			String rc[]=t[0].split(",");
			row=Integer.parseInt(rc[0]);
			column=Integer.parseInt(rc[1]);
					
		}
		
		public String toString()
		{
			return row+","+column+":"+time;
		}
		
	}
	public int motorUse(String[] prices, String[] purchases)
	{
		
		String strs[]=prices[0].split(" ");
		Column[] cols=new Column[strs.length];
		for(int i=0;i<strs.length;i++)
		{
			cols[i]=new Column(i,prices.length);
		}
		for(int i=0;i<strs.length;i++)
		{
			for(int j=0;j<prices.length;j++)
			{
				strs=prices[j].split(" ");
				cols[i].prices[j]=Integer.parseInt(strs[i]);
			}
		}
		
		int curIndex=0;
		int time=0;
		int preTime=0;
		Purchase purs[]=new Purchase[purchases.length];
		for(int i=0;i<purchases.length;i++)
		{
			purs[i]=new Purchase(purchases[i]);
		}
		
		int nextIndex=getMaxIndex(cols);
		int roll=calTime(curIndex,nextIndex,cols.length);
		time+=roll;
		curIndex=nextIndex;
		
		for(int i=0;i<purs.length;i++)
		{
			System.out.println(purs[i]);
			if(purs[i].time-preTime>=5)
			{
				nextIndex=getMaxIndex(cols);
				roll=calTime(curIndex,nextIndex,cols.length);
				time+=roll;
				curIndex=nextIndex;
				
			}
			roll=purchase(purs[i],curIndex,cols);
			if(roll==-1)
				return -1;
			time+=roll;
			curIndex=purs[i].column;
			preTime=purs[i].time;
			System.out.println(time+"\t"+curIndex);
			
		}
		nextIndex=getMaxIndex(cols);
		time+=calTime(curIndex,nextIndex,cols.length);
		curIndex=nextIndex;
		return time;
	}
	private int calTime(int curIndex, int nextIndex,int length) {

		int rollC=nextIndex-curIndex;
		int rollCC=0;
		if(rollC>0)
		{
			rollCC=length-rollC;
		}
		else
		{
			rollCC=rollC+length;
			rollC=-rollC;
			
		}
		int roll=rollC<rollCC ? rollC:rollCC;
		return roll;
	}
	
	private int getMaxIndex(Column[] cols) {

		int max=0;
		int index=0;
		for(int i=0;i<cols.length;i++)
		{
			cols[i].sum();
			if(cols[i].sum>max)
			{
				max=cols[i].sum;
				index=i;
			}
		}
		return index;
	}
	private int purchase(Purchase purchase,int curIndex,Column[] cols) {
		try {
			if(cols[purchase.column].prices[purchase.row]==0)
				return -1;
		} catch (Exception e) {
			
			e.printStackTrace();
			return -1;
		}
		int rollC=purchase.column-curIndex;
		int rollCC=0;
		if(rollC>0)
		{
			rollCC=cols.length+curIndex-purchase.column;
		}
		else
		{
			rollCC=rollC+cols.length;
			rollC=-rollC;
			
		}
		
		int roll=rollC<rollCC ? rollC:rollCC;
		cols[purchase.column].prices[purchase.row]=0;
		return roll;
	}
	
	/*public static void main(String args[])
	{
		VendingMachine vm=new VendingMachine();
		String prices[]={"100 200 300",
		 "600 500 400"};
		String purchases[]={"0,0:0", "1,1:10", "1,2:20",
				 "0,1:21", "1,0:22", "0,2:35"};
		System.out.println(vm.motorUse(prices, purchases));
	}*/
	
}
