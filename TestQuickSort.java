package Project1;

import java.util.Random;

public class TestQuickSort {

	/**
	 * @param args
	 */
	String [] inStr;
	public TestQuickSort(){
		inStr = new String[100];
		Random r = new Random();
		for(int i=99; i>0;i--){
			
			inStr[i]= Integer.toString(r.nextInt(90)+10);
			inStr[i] = Integer.toString(i);
			System.out.print(inStr[i]+" ");
		}
		System.out.println();
		quickSort(0,9);
		for(int i=0; i<=99;i++){
		System.out.print(inStr[i]+" ");
		}
		
	}
public void quickSort(int begin, int end){
		
		if(end<=begin) return;
		int i = partition(begin,end);
		//testRound++;
		quickSort(begin,i-1);
		quickSort(i+1,end);		
		
	}
	
	private int randomPartition(int begin, int end){
		Random r = new Random();
		int pivotPosition = r.nextInt(end-begin)+begin;
		String tmp;
		tmp = inStr[pivotPosition];
		inStr[pivotPosition] = inStr[end];
		inStr[end] = tmp;
		return  partition( begin, end);
		
	}
	
	private int partition( int begin, int end)
	{
		int i=begin-1;
		int j=end;
		String tmp;
		while(true)
		{
			//A[end] is pivot
			/*
			while(i<end && compareStr(A[++i],A[end])==-1)   //A[++i]<A[end]
				;
			while(j>begin && compareStr(A[end],A[--j])==-1)  //A[end]<A[--j]
				;
				*/
			if(i<end)
			{
				do {
					i++;
				}while(compareStr(inStr[i],inStr[end])==-1&&i<end);
				
			}
			
			if(j>begin)
			{
				do {
					j--;
				}while(compareStr(inStr[end],inStr[j])==-1&&j>begin);
				
			}
			
			if(i>=j) break;
			//exchange A[i] and A[j]
			//swap(A[i],A[j]);
			
			tmp = inStr[i];
			inStr[i] = inStr[j];
			inStr[j] = tmp;
			
		}
		//exchange A[i] and A[end]
		//swap(A[i],A[end]);
		
		tmp = inStr[i];
		inStr[i] = inStr[end];
		inStr[end] = tmp;
		
		return i;
	}
	
	
	private int compareStr(String str1,String str2)
	{
		if(str1==null || str2==null)
			return 2;
		
		//two files are sorted on EmpID
		//System.out.println(str1);
		int s1=Integer.valueOf(str1.substring(0,2));
		int s2=Integer.valueOf(str2.substring(0,2));
		if(s1<s2)
			return -1;
		else if(s1>s2)
			return 1;
		else
			return 0;
					
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestQuickSort test1 = new TestQuickSort();
		
	}

}
