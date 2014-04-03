package srm.practice.srm144div1;

import java.util.HashSet;
import java.util.Set;

public class PenLift {

	class Node implements Comparable<Node>
	{
		int x;
		int y;
		Set<Node> adjacentNodes;

		public Node(int x,int y)
		{
			this.x=x;
			this.y=y;
			adjacentNodes=new HashSet<Node>();
		}

		@Override
		public int compareTo(Node n) {
			if(this.adjacentNodes.size()<n.adjacentNodes.size())
				return 1;
			else if(this.adjacentNodes.size()>n.adjacentNodes.size())
				return -1;
			return 0;
		}

		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Node)
			{
				Node n=(Node)obj;
				if(n.x==x&&n.y==y)
					return true;
			}
			return false;
		}
		
		
	}
	
	class Segment
	{
		int x1;
		int y1;
		int x2;
		int y2;
		public Segment(int x1,int y1,int x2,int y2)
		{
			this.x1=x1;
			this.x2=x2;
			this.y1=y1;
			this.y2=y2;
		}
		public Segment(int value[])
		{
			if(value[0]==value[2])
			{
				if(value[1]<=value[3])
				{
					this.x1=value[0];
					this.y1=value[1];
					this.x2=value[2];
					this.y2=value[3];
				}
				else
				{
					this.x1=value[2];
					this.y1=value[3];
					this.x2=value[0];
					this.y2=value[1];
				}
			}
			else if(value[1]==value[3])
			{
				if(value[0]<=value[2])
				{
					this.x1=value[0];
					this.y1=value[1];
					this.x2=value[2];
					this.y2=value[3];
				}
				else
				{
					this.x1=value[2];
					this.y1=value[3];
					this.x2=value[0];
					this.y2=value[1];
				}
			}
			
		}
	}
	
	private Node nodes[];
	private int size=0;
	
	public int numTimes(String[] segments, int n)
	{
		Node nodes[]=parseSegments(segments);
		return 0;
	}

	private Node[] parseSegments(String[] segments) {

		nodes=new Node[segments.length*2];
		Segment segArray[]=new Segment[segments.length];
		for(int i=0;i<segments.length;i++)
		{
			String vStrs[]=segments[i].split(" ");
			int value[]=new int[vStrs.length];
			for(int j=0;j<value.length;j++)
			{
				value[j]=Integer.parseInt(vStrs[j]);
			}
			segArray[i]=new Segment(value);
			generateGrap(segArray);
			/*Node node1=generateNodeInstance(new Node(value[0],value[1]));
			Node node2=generateNodeInstance(new Node(value[2],value[3]));*/
			//generateEdges(node1,node2);
		}
		//generateEdges();
		return nodes;
	}

	private void generateGrap(Segment[] segArray) {

		for(int i=0;i<segArray.length;i++)
		{
			for(int j=i;j<segArray.length;j++)
			{
				generateEdges(segArray[i],segArray[j]);
			}
		}
	}

	private void generateEdges(Segment seg1,Segment seg2) {

		Node node1a=generateNodeInstance(new Node(seg1.x1,seg1.y1));
		Node node1b=generateNodeInstance(new Node(seg1.x2,seg2.y2));
		Node node2a=generateNodeInstance(new Node(seg2.x1,seg2.y1));
		Node node2b=generateNodeInstance(new Node(seg2.x2,seg2.y2));
		
		Node n=overlap(node1a,node1b,node2a,node2b);
		
	}

	private Node overlap(Node node1a, Node node1b, Node node2a, Node node2b) {

		//seg1是横线
		if(node1a.y==node1b.y)
		{
			//seg2 是横线
			if(node2a.y==node2a.y)
			{
				if(node2a.x>=node1a.x&&node2a.x<=node1b.x)
				{
					return node2a;
				}
				else if(node2b.x>=node1a.x&&node2a.x<=node1b.x)
				{
					return node2b;
				}
				else
				{
					
				}
			}
		}
		return null;
	}

	private Node generateNodeInstance(Node node) {

		for(int i=0;i<size;i++)
		{
			if(nodes[i].equals(node))
				return nodes[i];
		}
		nodes[size++]=node;
		return node;
	}
}
