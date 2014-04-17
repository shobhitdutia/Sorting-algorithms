/* 
 * MergeSort.java 
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

public class MergeSort {
	static double noOfComparisons=0;
	   
	/**
	 * The main program.
	 * 
	 * @param    args    input size
	 */
	
	public static void main(String[] args) {
		MergeSort ms=new MergeSort();
		int size=Integer.parseInt(args[0]);
		ms.sorted(size);
		ms.random(size);
		ms.sorted25(size);		
		ms.poission(size);
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
		System.out.println("No. of comparisons for "+size+": "+noOfComparisons+"\n");
		noOfComparisons=0;
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
		System.out.println("No. of comparisons for "+size+": "+noOfComparisons+"\n");
		noOfComparisons=0;
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
		System.out.println("No. of comparisons for "+size+": "+noOfComparisons+"\n");
		noOfComparisons=0;
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
		System.out.println("No. of comparisons for "+size+": "+noOfComparisons+"\n");
		noOfComparisons=0;
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
		if(low<high){
			int mid = (low+high)/2;
			sort(a, low, mid);
			sort(a, mid+1, high);
			merge(a, low, mid, high);
		}
		else 
			return;
	}
	/**
	 * Merging sorted array
	 *
	 * @param    a    array to be sorted
	 * 
	 * @param    mid  middle position of the array
	 * 
	 * @param    low  start position of the array
	 * 
	 * @param	 high  end position of the array
	 */	
	public void merge(int a[], int low, int mid, int high) {
		int c[]=new int[(high-low)+1];
		int k=0;
		int i=low, j=mid+1;
		while(i<=mid && j<=high) {
			noOfComparisons++;
			if(a[i]<a[j]) {
				c[k++]=a[i++];
			}
			else {
				c[k++]=a[j++];
			}
		}
		while(i<=mid) {
			c[k++]=a[i++];
		}
		while(j<=high) {
			c[k++]=a[j++];
		}
		
		k=0;
		for(int index=low;index<=high;index++) {
			a[index]=c[k++];
		}
	}
}
