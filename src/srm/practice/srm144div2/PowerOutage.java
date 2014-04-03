package srm.practice.srm144div2;
/**
 * You work for an electric company, and the power goes out in a rather large apartment complex with a lot of irate tenants. You isolate the problem to a network of sewers underneath the complex with a step-up transformer at every junction in the maze of ducts. Before the power can be restored, every transformer must be checked for proper operation and fixed if necessary. To make things worse, the sewer ducts are arranged as a tree with the root of the tree at the entrance to the network of sewers. This means that in order to get from one transformer to the next, there will be a lot of backtracking through the long and claustrophobic ducts because there are no shortcuts between junctions. Furthermore, it's a Sunday; you only have one available technician on duty to search the sewer network for the bad transformers. Your supervisor wants to know how quickly you can get the power back on; he's so impatient that he wants the power back on the moment the technician okays the last transformer, without even waiting for the technician to exit the sewers first.
You will be given three int[]'s: fromJunction, toJunction, and ductLength that represents each sewer duct. Duct i starts at junction (fromJunction[i]) and leads to junction (toJunction[i]). ductlength[i] represents the amount of minutes it takes for the technician to traverse the duct connecting fromJunction[i] and toJunction[i]. Consider the amount of time it takes for your technician to check/repair the transformer to be instantaneous. Your technician will start at junction 0 which is the root of the sewer system. Your goal is to calculate the minimum number of minutes it will take for your technician to check all of the transformers. You will return an int that represents this minimum number of minutes.
 * @author xhw
 *
 */
public class PowerOutage {

	class Edge
	{
		int from;
		int to;
		int length;
		
		public Edge(int from, int to, int length) {
			super();
			this.from = from;
			this.to = to;
			this.length = length;
		}
		
		
	}
	public int estimateTimeOut(int[] fromJunction, int[] toJunction, int[] ductLength)
	{
		Edge edges[]=new Edge[fromJunction.length];
		for(int i=0;i<fromJunction.length;i++)
		{
			edges[i]=new Edge(fromJunction[i],toJunction[i],ductLength[i]);
			
		}
		int max=0;
		for(int i=0;i<edges.length;i++)
		{
			if(edges[i].from==0)
			{
				int length=estimateMaxLength(i,edges);
				if(length>max)
					max=length;
			}
		}
		
		int sum=0;
		for(int i=0;i<ductLength.length;i++)
		{
			sum+=ductLength[i];
		}
		return sum*2-max;
	}
	private int estimateMaxLength(int index, Edge[] edges) {

		int toJunction=edges[index].to;
		
		int max=0;
		for(int i=0;i<edges.length;i++)
		{
			if(edges[i].from==toJunction)
			{
				int len=estimateMaxLength(i,edges);
				if(len>max)
					max=len;
			}
		}
		
		return max+edges[index].length;
	}
}
