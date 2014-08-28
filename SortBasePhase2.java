package Project1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;


public class SortBasePhase2 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	String fileName;
	int sublistNumber;
	int readNumber;
	BufferedReader[] in;
	PrintStream out ;
	String[] inStr;
	int[] sublistPostion; 
	int minPosition;
	int readableList;
	int[] duplicatedCount;
	
	public SortBasePhase2(String fileName, int sublistNumber) throws FileNotFoundException{
		this.fileName = fileName;
		this.sublistNumber = sublistNumber;
		this.sublistPostion = new int[sublistNumber];
		File file=new File(fileName);
		inStr =new String[sublistNumber];
		in =new  BufferedReader[sublistNumber];
		readableList = sublistNumber;
		for(int i=0; i<sublistNumber; i++){
			in[i] = new BufferedReader(new FileReader(fileName+"_"+Integer.toString(i)));
			sublistPostion[i] = 1;
		}
		FileOutputStream output = new FileOutputStream(file+"_OUTPUT");
		out =  new  PrintStream(output);
		
	}
	
	public void init() throws IOException{
		
		int i=0;
		try{
			for(i=0; i<sublistNumber; i++){
				inStr[i] = in[i].readLine();
				
			}readNumber = sublistNumber;
			
		}catch( Exception e){ readNumber = i; }

		
	}
	
	public int  differenceInit( ) throws IOException{
		int i=0;
		int duplicatedPosition;
		String temp;
		int count =0;
		duplicatedCount = new int[sublistNumber];
		//System.out.println("sublistNumber  "+sublistNumber);
		
			for(i=0; i<sublistNumber; i++){
				//System.out.println("i  "+i+"sublistNumber"+sublistNumber);
				temp  = in[i].readLine();
				
				
				if(temp == null)
				{
					readNumber = i;
					return 1;
				}
				System.out.println("Str  "+temp );
				//System.out.println(inStr[i]);
				duplicatedPosition = checkExist(temp);
				//System.out.println("duplicatedPosition!! "+duplicatedPosition);
				if(duplicatedPosition != -1){
				
				duplicatedCount[duplicatedPosition]++;
				i--;
				}
				else{
					inStr[i] =new String(temp);
					duplicatedCount[i]=1;
				}
				
					
			count ++;	
			
			}
			readNumber = sublistNumber;
			
		
		return count;
	}
	
	public int checkExist(String temp){

		int i;
		for(i=0; i<sublistNumber; i++)
			if(compareStr(temp,inStr[i]) == 0)
				return i;
		return -1;
		
		
	}

	public int  findMin(){
        
		int minPosition=0 ;
	
		//Random  random = new Random();
		while(sublistPostion[minPosition] ==0){
			minPosition++;
			
		}
			
		for(int i=1; i<sublistNumber; i++){
			if (compareStr(inStr[i], inStr[minPosition]) == -1)
				minPosition =i;
		}
		
		return minPosition;
		
		
	}

	public int readOne(int i) throws IOException{
		inStr[i] = in[i].readLine();
		if(inStr[i] == null){
			sublistPostion[i] =0;
			return -1; // Sublist Over;
		}
			
		return 0;
		
	}
	// Return 0 Success; Return -1 Null;
	public int differenctReadOne (int i) throws IOException { 
		String temp;
		int duplicatedPosition ;
		do{
			temp = in[i].readLine();
			if(temp == null){
				sublistPostion[i] =0;
				return -1;
			}
			
			duplicatedPosition = checkExist(temp);
			
			if(duplicatedPosition != -1){
				
				duplicatedCount[duplicatedPosition]++;
				
				}
			else{
				inStr[i] =new String(temp);
				duplicatedCount[i]++;
			}
				
			
		}while (duplicatedPosition!=-1);
		return 0;		
	
	}
	
	public void writeOne(int i){
		
		out.println(inStr[i]);	
	}
	
	public void phase2() throws IOException{
		
		
		init();
		do{
			minPosition = findMin();
			writeOne(minPosition);
		    if(readOne(minPosition) == -1){
		    	readableList--;
		    }
		    //System.out.println(readableList);
		}while(readableList>0);
		
		
	}
	
	int compareStr(String str1,String str2)
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
	
	public static void main(String[] args) throws IOException {
		
		long a=System.currentTimeMillis();
		
		// TODO Auto-generated method stub
		int tulperPerTime = 20000; // Related to Memory
		int tulperLongth= 100;
		int readRound = 50;  // readRound*tulperPertime*tuplerLongth = File Size
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
		readRound = i;
		System.out.println(readRound);
		in.close();
		SortBasePhase2 test2 = new SortBasePhase2(fileName,readRound);
		test2.phase2();
		System.out.println("Used: "+(System.currentTimeMillis()-a)/1000f+" s ");
	}

}
