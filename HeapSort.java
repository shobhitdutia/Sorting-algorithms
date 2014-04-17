/* 
 * HeapSort.java 
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

public class HeapSort {
	static int heapSize;
	static double noOfComparisons=0;
   
	/**
	 * The main program.
	 * 
	 * @param    args    input size
	 */

	public static void main(String[] args) {
		HeapSort hs=new HeapSort();
		int size=Integer.parseInt(args[0]);
		hs.sorted(size);
		hs.random(size);
		hs.sorted25(size);
		hs.poission(size);
	}
	
	/**
	 * Generates random numbers and sorts them.
	 * 
	 * @param    args    input size
	 */	
	
	public void random(int size) {
		heapSize=size;
		Random r=new Random();
		int a[]=new int[size];
		for(int i=0;i<a.length;i++) {
			a[i]=r.nextInt();
		}
		double startTime=System.currentTimeMillis();
		this.sort(a);
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
		heapSize=size;
		int a[]=new int[size];
		for(int i=0;i<a.length;i++) {
			a[i]=i;
		}
		double startTime=System.currentTimeMillis();
		this.sort(a);
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
		heapSize=size;
		Random r=new Random();
		int a[]=new int[size];
		for(int i=0;i<(size*0.25);i++) {
			a[i]=i;
		}
		for(int i=(int) (size*0.25);i<a.length;i++) {
			a[i]=r.nextInt();
		}
		double startTime=System.currentTimeMillis();
		this.sort(a);
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
		heapSize=size;
		int lambda=size/2;
		int a[]=new int[size];
		for(int i=0;i<a.length;i++) {
			a[i]=generateRandom(lambda);
		}
		double startTime=System.currentTimeMillis();
		this.sort(a);
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
	 * @param    args    array to be sorted
	 * 
	 */
	
	public void sort(int a[]) {
		//build heap
		for(int i=(a.length)/2-1;i>=0;i--) {
			heapify(a, i);
		}
		//extractMax
		while(heapSize>1) {
			extractMax(a);
		}
	}
	
	/**
	 * Heapify
	 *
	 * @param    args    array
	 * 
	 * @param    args    node from which it is heapified
	 */
	
	public void heapify(int a[], int i) {
		int left=i*2+1, right=i*2+2, largest=i;
		if(left>heapSize-1&&right>heapSize-1) {
			return;
		}
		if(left<=heapSize-1) {
			noOfComparisons++;
			if(a[left]>a[i])
				largest = left;	
		}
		if(right<=heapSize-1) {
			noOfComparisons++;
			if(a[right]>a[largest]) 
				largest = right;	
		}
		
		if(largest!=i) {
			int temp=a[i];
			a[i]=a[largest];
			a[largest]=temp;
			heapify(a, largest);
		}
	}
	
	/**
	 * Extracting max element
	 *
	 * @param    args    array
	 * 
	 */
	
	public void extractMax(int a[]) {
		int temp=a[0];
		a[0]=a[heapSize-1];
		a[heapSize-1]=temp;
		heapSize=heapSize-1;
		heapify(a, 0);
	}
}