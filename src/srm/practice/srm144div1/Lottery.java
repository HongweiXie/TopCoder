package srm.practice.srm144div1;

import java.util.Arrays;
/**
 * srm144div1
 * Problem Statement
    
In most states, gamblers can choose from a wide variety of different lottery games. The rules of a lottery are defined by two integers (choices and blanks) and two boolean variables (sorted and unique). choices represents the highest valid number that you may use on your lottery ticket. (All integers between 1 and choices, inclusive, are valid and can appear on your ticket.) blanks represents the number of spots on your ticket where numbers can be written.
The sorted and unique variables indicate restrictions on the tickets you can create. If sorted is set to true, then the numbers on your ticket must be written in non-descending order. If sorted is set to false, then the numbers may be written in any order. Likewise, if unique is set to true, then each number you write on your ticket must be distinct. If unique is set to false, then repeats are allowed.
Here are some example lottery tickets, where choices = 15 and blanks = 4:
{3, 7, 12, 14} -- this ticket is unconditionally valid.
{13, 4, 1, 9} -- because the numbers are not in nondescending order, this ticket is valid only if sorted = false.
{8, 8, 8, 15} -- because there are repeated numbers, this ticket is valid only if unique = false.
{11, 6, 2, 6} -- this ticket is valid only if sorted = false and unique = false.
Given a list of lotteries and their corresponding rules, return a list of lottery names sorted by how easy they are to win. The probability that you will win a lottery is equal to (1 / (number of valid lottery tickets for that game)). The easiest lottery to win should appear at the front of the list. Ties should be broken alphabetically (see example 1).
 * 
 * @author xhw
 *
 */
public class Lottery {

	class Rule implements Comparable<Rule>
	{
		String name;
		int choices;
		int blank;
		boolean sorted;
		boolean unique;
		long value;
		public Rule(String rule)
		{
			String r[]=rule.split(":");
			name=r[0].trim();
			String vStr[]=r[1].trim().split(" ");
			System.out.println(r[1]);
			//System.out.println(vStr[0]);
			choices=Integer.parseInt(vStr[0].trim());
			blank=Integer.parseInt(vStr[1].trim());
			if(vStr[2].equals("T"))
				sorted=true;
			else
				sorted=false;
			if(vStr[3].equals("T"))
				unique=true;
			else
				unique=false;
		}
		@Override
		public int compareTo(Rule r) {

			if(value>r.value)
				return 1;
			else if(value<r.value)
				return -1;
			else
			{
				return name.compareTo(r.name);
			}
		}
	}
	public String[] sortByOdds(String[] rules)
	{
		Rule[] ruleObjs=new Rule[rules.length];
		String[] results=new String[rules.length];
		for(int i=0;i<rules.length;i++)
		{
			ruleObjs[i]=new Rule(rules[i]);
		}
		computeProbability(ruleObjs);
		Arrays.sort(ruleObjs);
		for(int i=0;i<rules.length;i++)
		{
			results[i]=ruleObjs[i].name;
		}
		return results;
	}
	
	private void computeProbability(Rule[] ruleObjs) {

		for(Rule rule:ruleObjs)
		{
			if(rule.sorted&&rule.unique)
				rule.value=combination(rule.choices, rule.blank);
			else if(rule.sorted&&!rule.unique)
			{
				rule.value=combinationNoUnique(rule.choices,rule.blank);
			}
			else if(!rule.sorted&&rule.unique)
			{
				rule.value=factorial(rule.choices,rule.blank);
			}
			else
			{
				rule.value=pow(rule.choices,rule.blank);
			}
			System.out.println(rule.value);
		}
		
	}

	private long factorial(int n, int size)
	{
		long sum=1;
		for(int i=0;i<size;i++)
		{
			sum*=(n-i);
		}
		return sum;
	}
	
	private long pow(int n, int size)
	{
		long sum=1;
		for(int i=0;i<size;i++)
		{
			sum*=n;
		}
		return sum;
	}
	
	private long combination(int n, int m)
	{
		return factorial(n,m)/factorial(m,m);
	}
	long table[][]=new long[100][10];
	private long combinationNoUnique(int n, int m)
	{
		long sum=0;
		for(int i=0;i<n;i++)
			Arrays.fill(table[i],-1 );
		for(int i=1;i<=n;i++)
		{
			sum+=f(m-1,i);
		}
		return sum;
	}
	
	
	
	private long f(int m,int n)
	{
		if(m==2)
			return n*(n+1)/2;
		if(m==1)
			return n;
		if(m==0)
			return 1;
		if(table[n-1][m]!=-1)
			return table[n-1][m];
		//if(n<m)
		//	return 0;
		long sum=0;
		for(int i=1;i<=n;i++)
		{
			sum+=f(m-1,i);
		}
		table[n-1][m]=sum;
		return sum;
	}
	
	/*public static void main(String args[])
	{
		Lottery lot=new Lottery();
		//long temp[]=lot.combinationNoUnique(4, 4);186087894300
		System.out.println(lot.combinationNoUnique(93, 8));
	}*/

}


