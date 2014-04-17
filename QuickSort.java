/* 
 * QuickSort.java 
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

public class QuickSort {
	static int noOfPartitions=0;
	static double noOfComparisons=0;
	static boolean loopFlag=true; 
	
	/**
	 * The main program.
	 * 
	 * @param    args    input size
	 */

	public static void main(String[] args) {
		QuickSort qs=new QuickSort();
		int size=Integer.parseInt(args[0]);
		qs.sorted(size);
		qs.random(size);
		qs.sorted25(size);		
		qs.poission(size);
	}
	
	/**
	 * Generates random numbers and sorts them.
	 * 
	 * @param    size    input size
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
		loopFlag=true;
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
		loopFlag=true;
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
		loopFlag=true;
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
		loopFlag=true;
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
	
	/**
	 * Sorting the input
	 *
	 * @param    a    array to be sorted
	 * 
	 * @param    low  start position of the array
	 * 
	 * @param	 high  end position of the array
	 */
		
	public void sort(int a[], int low, int high) {
		if(low<high) {
			//noOfPartitions+=2;
			int pos=partition(a, low, high);
			sort(a, low, pos-1);
			sort(a, pos+1, high);
		}
	}
	
	/**
	 * Partitions the array
	 *
	 * @param    a    array to be sorted
	 * 
	 * @param    low  start position of the array
	 * 
	 * @param	 high  end position of the array
	 */	
	public int partition(int a[], int low, int high) {
		noOfPartitions++;
		int x=a[low], i=low, j=high;
		while(i<j) {
			noOfComparisons++;
			while(a[i]<=x && i<high) {
				if(loopFlag)        //check which doesn't increment partition first time
					loopFlag=false;
				else
					noOfComparisons++;
				i++;
			}
			loopFlag=true;
			noOfComparisons++;
			while(a[j]>=x && j>low) {
				if(loopFlag) 
					loopFlag=false;
				else
					noOfComparisons++;
				j--;
			}
			loopFlag=true;
			if(i<j) {
				int temp=a[i];
				a[i]=a[j];
				a[j]=temp;
			}
		}
		a[low]=a[j];
		a[j]=x;
		return j;
	}
}
