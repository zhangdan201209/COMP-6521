package Project1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

public class SortBasedPhase1 {

	/**
	 * @param args
	 */
	String fileName;
	int tulperPerTime; 
	int tulperLongth;
	int readNumber;
	PrintStream out ;
	BufferedReader in;
	String[] inStr;
	int testRound=0;
	
	public SortBasedPhase1 ( BufferedReader in, String fileName, int tulperPerTime, int tulperLongth, int round) throws FileNotFoundException{
	
		this.fileName = fileName;
		this.tulperPerTime = tulperPerTime; 
		this.tulperLongth =  tulperLongth;
		
		File file=new File(fileName);
    	FileOutputStream output = new FileOutputStream(file+"_"+Integer.toString(round));
		out =  new  PrintStream(output);
        this.in  = in;
        inStr =new String[tulperPerTime];
       
	}
	
	public int phase1() throws IOException{
		int flag =0;
		flag = this.read();
			
		//this.print();
		if (flag == -1)
			return -1;
		this.quickSort(0,readNumber-1);
		this.write();
		
		return 0;
	}
	public int read() throws IOException{
		int i=0;
		
			
			for(i=0; i<tulperPerTime; i++){
				
				inStr[i] = in.readLine();
				if(inStr[i]==null){
					readNumber = i;
					return -1;
				}
					
			}
			readNumber = tulperPerTime;
			return 0;
		

		
	}
	
	public void quickSort(int begin, int end){
		
		if(end<=begin) return;
		int i = randomPartition(begin,end);
		testRound++;
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
		int s1=Integer.valueOf(str1.substring(0,7));
		int s2=Integer.valueOf(str2.substring(0,7));
		if(s1<s2)
			return -1;
		else if(s1>s2)
			return 1;
		else
			return 0;
					
	}
	
	
	public void write(){
		
		for(int i=0; i<readNumber; i++){
			out.println(inStr[i]);	
		}
		
		
	}
	
	public void print(){
		
		for(int i=0; i<readNumber; i++){
			System.out.println(inStr[i]);	
		}
		
		System.out.println("_______________________________________");	
	}
	
	
	public static void main(String[] args) throws IOException {
		
		// TODO Auto-generated method stub
		
		int tulperPerTime = 100000; // Related to Memory
		int tulperLongth= 100;
		int readRound = 10;  // readRound*tulperPertime*tuplerLongth = File Size
		BufferedReader in;
		String fileName = "D:\\Data\\"+File.separator+"DataAA"+Integer.toString(readRound*tulperPerTime);
		int i =0;
		int result =0;
		in = new BufferedReader(new FileReader(fileName));
		System.out.println("Phase1: ");
		do{
			
			SortBasedPhase1 test1 = new SortBasedPhase1(in, fileName, tulperPerTime, tulperLongth, i);
			result = test1.phase1();
			i++;
		}while(result!=-1);
		 
		System.out.println("Finish");	

	}

}
