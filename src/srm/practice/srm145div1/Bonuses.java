package srm.practice.srm145div1;

import java.util.Arrays;

public class Bonuses {

	class Employee implements Comparable<Employee>
	{
		int index;
		int points;
		int value;
		
		public Employee(int index, int points) {
			super();
			this.index = index;
			this.points = points;
		}

		@Override
		public int compareTo(Employee o) {

			if(this.points>o.points)
				return -1;
			else if(this.points<o.points)
				return 1;
			return 0;
		}
	}
	
	public int[] getDivision(int[] points)
	{
		Employee ems[]=new Employee[points.length];
		int sum=0;
		for(int i=0;i<points.length;i++)
		{
			ems[i]=new Employee(i,points[i]);
			sum+=points[i];
			//System.out.println(ems[i].points);
		}
		int temp=0;
		for(int i=0;i<points.length;i++)
		{
			ems[i].value=(int) ((ems[i].points*1.0/sum)*100);
			System.out.println(ems[i].value);
			temp+=ems[i].value;
		}
		int extra=100-temp;
		Arrays.sort(ems);
		for(int i=0;i<extra;i++)
		{
			ems[i%points.length].value++;
		}
		
		int[] results=new int[points.length];
		for(int i=0;i<points.length;i++)
		{
			for(int j=0;j<points.length;j++)
			{
				if(i==ems[j].index)
				{
					results[i]=ems[j].value;
				}
			}
		}
		return results;
	}
	
	public static void main(String args[])
	{
		Bonuses b=new Bonuses();
		int[] points={485, 324, 263, 143, 470, 292, 304, 188, 100, 254, 296,
				 255, 360, 231, 311, 275,  93, 463, 115, 366, 197, 470};
		int[] results=b.getDivision(points);
		for(int i=0;i<results.length;i++)
		{
			System.out.print(results[i]+"\t");
		}
	}
}
