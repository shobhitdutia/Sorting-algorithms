/* 
 * MCQS.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
import java.util.Random;

/**
 * This program sorts a given input
 *
 * @author      Shobhit Dutia
 */

public class MCQS {
	static int noOfPartitions=0;
	static double noOfComparisons=0;
	static boolean loopFlag=true;
	static boolean partitionFlag=true;
   
	/**
	 * The main program.
	 * 
	 * @param    args    input size
	 */

	public static void main(String[] args) {
		MCQS qs=new MCQS();
		int size=Integer.parseInt(args[0]);
		qs.sorted(size);
		qs.random(size);
		qs.sorted25(size);		
		qs.poission(size);
	}
	
	/**
	 * Generates random numbers and sorts them.
	 * 
	 * @param    args    input size
	 */	

	public void random(int size) {
		Random r=new Random();
		int a[]=new int[size];
		for(int i=0;i<a.length;i++) {
			a[i]=r.nextInt();
		}
		double startTime=System.currentTimeMillis();
		this.sort(a, 0, a.length-1);
		double endTime=System.currentTimeMillis();
		System.out.println("Random input:");
		System.out.println("Time to sort " +size+" "+(endTime-startTime));
		System.out.println("No. of partitions for "+size+": "+noOfPartitions);
		System.out.println("No. of comparisons for "+size+": "+noOfComparisons+"\n");
		noOfPartitions=0;
		noOfComparisons=0;
		loopFlag=partitionFlag=true;
	}
	
	/**
	 * Generates sorted numbers and sort them.
	 * 
	 * @param    size    input size
	 */
	
	public void sorted(int size) {
		int a[]=new int[size];
		for(int i=0;i<a.length;i++) {
			a[i]=i;
		}
		double startTime=System.currentTimeMillis();
		this.sort(a, 0, a.length-1);
		double endTime=System.currentTimeMillis();
		System.out.println("Sorted input:");
		System.out.println("Time to sort " +size+" "+(endTime-startTime));
		System.out.println("No. of partitions for "+size+": "+noOfPartitions);
		System.out.println("No. of comparisons for "+size+": "+noOfComparisons+"\n");
		noOfPartitions=0;
		noOfComparisons=0;
		loopFlag=partitionFlag=true;
	}
	
	/**
	 * Generates 25% sorted numbers sort them.
	 * 
	 * @param    size  		input size
	 */	
		
	public void sorted25(int size) {
		Random r=new Random();
		int a[]=new int[size];
		for(int i=0;i<(size*0.25);i++) {
			a[i]=i;
		}
		for(int i=(int) (size*0.25);i<a.length;i++) {
			a[i]=r.nextInt();
		}
		double startTime=System.currentTimeMillis();
		this.sort(a, 0, a.length-1);
		double endTime=System.currentTimeMillis();
		System.out.println("25% Sorted input:");
		System.out.println("Time to sort " +size+" "+(endTime-startTime));
		System.out.println("No. of partitions for "+size+": "+noOfPartitions);
		System.out.println("No. of comparisons for "+size+": "+noOfComparisons+"\n");
		noOfPartitions=0;
		noOfComparisons=0;
		loopFlag=partitionFlag=true;
	}
	/**
	 * Generates poission distriuted numbers sorts them.
	 * 
	 * @param    size    input size
	 */	
		
	public void poission(int size) {
		int lambda=size/2;
		int a[]=new int[size];
		for(int i=0;i<a.length;i++) {
			a[i]=generateRandom(lambda);
		}
		double startTime=System.currentTimeMillis();
		this.sort(a, 0, a.length-1);
		double endTime=System.currentTimeMillis();
		System.out.println("Poission distributed input:");
		System.out.println("Time to sort " +size+" "+(endTime-startTime));
		System.out.println("No. of partitions for "+size+": "+noOfPartitions);
		System.out.println("No. of comparisons for "+size+": "+noOfComparisons+"\n");
		noOfPartitions=0;
		noOfComparisons=0;
		loopFlag=partitionFlag=true;
	}
	
	/**
	 * Sorting the input
	 *
	 * @param    args    array to be sorted
	 * 
	 * @param	 low     start position of the array
	 * 
	 * @param	 high    end position of the array
	 * 
	 */
	
	public void sort(int a[], int low, int high) {
		if(low<high) {
			if(partitionFlag)  //does not increment partitions for the first time
				partitionFlag=false;
			else
				noOfPartitions++;
			int x=a[(low+high)/2], i=low, j=high;
			boolean left_flag=false, right_flag=false, flag=true;
			while(flag) {
				noOfComparisons++; //check already made here
				while(a[i]<x && i!=j) {
					checkNoOfComparisons();  //hence no need to increment no of comparisons here
					if(i!=low) {
						noOfComparisons++;
						if(a[i-1]>a[i]) {
							int temp=a[i-1];
							a[i-1]=a[i];
							a[i]=temp;
							left_flag=true;
						}
					}		
					i++;
				}
				loopFlag=true;
				
				noOfComparisons++; //check already made here
				while(a[j]>=x && j!=i) {
					checkNoOfComparisons();  //hence no need to increment no of comparisons here
					if(j!=high) { 
						noOfComparisons++;
						if(a[j]>a[j+1]){
							int temp=a[j];
							a[j]=a[j+1];
							a[j+1]=temp;
							right_flag=true;
						}
					}	
					j--;
				}
				loopFlag=true;
				if(i!=j) {
					int temp=a[i];
					a[i]=a[j];
					a[j]=temp;
				}
				else {
					noOfComparisons++;
					if(a[j]>=x) {
						noOfComparisons++;
						if(j!=high)
							if(a[j]>a[j+1]) {
								int temp=a[j];
								a[j]=a[j+1];
								a[j+1]=temp;	
								right_flag=true;
						}
					}
					else {
						noOfComparisons++;
						if(a[i-1]>a[i]) {
							int temp=a[i-1];
							a[i-1]=a[i];
							a[i]=temp;	
							left_flag=true;
						}
						/*if(a[i-2]>a[i-1]) {
							int temp=a[i-2];
							a[i-2]=a[i-1];
							a[i-1]=temp;	
						}*/
					}
					flag=false;
				}
			} //end of while flag
			int size=i-low;
			if(size>2) {
				if(left_flag) {
					if(size==3) {
						noOfComparisons++;
						if(a[low]>a[low+1]) {
							int temp=a[low]; 
							a[low]=a[low+1];
							a[low+1]=temp;
						}
					}
					else 
						sort(a, low, i-1);
				}
			}
			size=high-j+1;
			if(size>2) {
				if(right_flag) {
					if(size==3) {
						noOfComparisons++;
						if(a[j+1]>a[j+2]) {
							int temp=a[j+1]; 
							a[j+1]=a[j+2];
							a[j+2]=temp;
						}
					}
					else {
						sort(a, j+1, high);
					}
				}
			}
		}	
	}
	/**
	 * Checks the no of comparisons
	 * 
	 */
		
	private void checkNoOfComparisons() {
		if(loopFlag) 
			loopFlag=false;
		else
			noOfComparisons++;
	}	
	
	/**
	 * Generates poission random numbers.
	 * 
	 * @param    lamda    mean value
	 * 
	 * @return	 k		 poission distributed random number
	 */	
	
	private static int generateRandom(int lambda) {
		int k=0;
		double p=1,L=Math.exp(-(lambda));
		do{
			k=k+1;
			double u=Math.random();
			p=(p*u);
		}
		while(p>L); 
		return k-1;
	}
}
