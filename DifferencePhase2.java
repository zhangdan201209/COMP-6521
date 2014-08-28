package Project1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class DifferencePhase2   {
	
	SortBasePhase2 J1;
	SortBasePhase2 J2;
	PrintStream out ;
	 int sublistNumber1 ;
	 int sublistNumber2;
	
	public  DifferencePhase2 (String fileName1, String fileName2, int sublistNumber1 , int sublistNumber2) throws IOException{
			
		
		J1 = new SortBasePhase2(fileName1, sublistNumber1);
		J2 = new SortBasePhase2(fileName2, sublistNumber2);
		File file=new File(fileName1);
		FileOutputStream output = new FileOutputStream(file+"_DifferenceResult");
		out =  new  PrintStream(output);
		this.sublistNumber1 = sublistNumber1;
		this.sublistNumber2 = sublistNumber2;
		//System.out.println(sublistNumber1+" "+J1.differenceInit( ));
		//System.out.println(sublistNumber2+" "+J2.differenceInit());
		
	}
   
	public void phase2() throws IOException{
		int flag ;
		J1.differenceInit();
		J2.differenceInit();
		int count =0;
		
		
		/*
		 while(J1.duplicatedCount[J1.minPosition]!=0){
			 out.println(J1.inStr[J1.minPosition]);
			 System.out.println("Write J1"+J1.inStr[J1.minPosition]);
			 J1.duplicatedCount[J1.minPosition]--;
		 }
		 if(J1.differenctReadOne(J1.minPosition) == -1){
		    	J1.readableList--;
		    }
		 for(int i=0; i<J1.readableList; i++)
				System.out.println(J1.duplicatedCount[i]+" ");
		*/
		/*
		for(int i=0; i<J1.readableList; i++)
			System.out.println(J1.inStr[i]);
		for(int i=0; i<J1.readableList; i++)
			System.out.println(J1.duplicatedCount[i]+" ");
		System.out.println("____________");
		for(int i=0; i<J2.readableList; i++)
			System.out.println(J2.inStr[i]);
		for(int i=0; i<J2.readableList; i++)
			System.out.println(J2.duplicatedCount[i]+" ");
		//System.out.println(J2.readableList);
		*/
		
		do{ 
			count++;
			J1.minPosition = J1.findMin();
			J2.minPosition = J2.findMin();
			//System.out.println(J1.inStr[J1.minPosition]+"        "+J1.readableList);
			//System.out.println(J2.inStr[J2.minPosition]+"        "+J2.readableList);
			flag = J1.compareStr(J1.inStr[J1.minPosition],J2.inStr[J2.minPosition]);
			//System.out.println(flag);
			switch(flag){
			
			case -1: {
				 while(J1.duplicatedCount[J1.minPosition]!=0){
					 out.println(J1.inStr[J1.minPosition]);
					 //System.out.println("Write J1"+J1.inStr[J1.minPosition]);
					 J1.duplicatedCount[J1.minPosition]--;
				 }
				 
				 
				 if(J1.differenctReadOne(J1.minPosition) == -1){
				    	J1.readableList--;
				    	J1.inStr[J1.minPosition] = null;
				    }
				 break;
			}
				
			case 0:{
				    J1.duplicatedCount[J1.minPosition] =0;
				    J2.duplicatedCount[J2.minPosition] =0;
					if(J1.differenctReadOne(J1.minPosition) == -1){
						J1.readableList--;
			    	}
					if(J2.differenctReadOne(J2.minPosition) == -1){
				    	J2.readableList--;
				    }
				   break;
				
			}
				 
			case 1: {
				 while(J2.duplicatedCount[J2.minPosition]!=0){
				 out.println(J2.inStr[J2.minPosition]);
				 //System.out.println("Write J2"+J1.inStr[J2.minPosition]);
				 J2.duplicatedCount[J2.minPosition]--;
				 }
				 if(J2.differenctReadOne(J2.minPosition) == -1){
				    	J2.readableList--;
				    	J2.inStr[J2.minPosition] = null;
				    }
				 break;
			}
				
			default:
				break;
			}
		   // System.out.println(J1.readableList + " "+J2.readableList +" Count "+count+"Flag"+flag);
		}while(J2.readableList > 1 &&J1.readableList> 1);
		
		System.out.println("--------------");
		/*
		 System.out.println(J1.readableList + " "+J2.readableList);
		 for(int i=0; i<sublistNumber1; i++)
				System.out.println(J1.duplicatedCount[i]+" ");
		 for(int i=0; i<sublistNumber2; i++)
				System.out.println(J2.duplicatedCount[i]+" ");
		 
		 */
		if(J1.readableList !=1){
			
			
			
			do{
				J1.minPosition = J1.findMin();				
				out.println(J1.inStr[J1.minPosition]);	
			    if(J1.readOne(J1.minPosition) == -1){
			    	J1.readableList--;
			    }
			    //System.out.println(readableList);
			}while(J1.readableList>1);
		}
		if(J2.readableList !=1){
			
			do{
				J2.minPosition = J2.findMin();
				out.println(J2.inStr[J2.minPosition]);	
			    if(J2.readOne(J2.minPosition) == -1){
			    	J2.readableList--;
			    }
			    //System.out.println(readableList);
			}while(J2.readableList>1);
			
		}
		
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		long a=System.currentTimeMillis();
		
		// TODO Auto-generated method stub
		int tulperPerTime = 20000; // Related to Memory
		int tulperLongth= 100;
		int readRound = 10;  // readRound*tulperPertime*tuplerLongth = File Size
		BufferedReader in1, in2;
		String fileName1 = "D:\\Data\\"+File.separator+"DataA500000";
		String fileName2 = "D:\\Data\\"+File.separator+"DataAA500000";
		
		in1 = new BufferedReader(new FileReader(fileName1));
		in2 = new BufferedReader(new FileReader(fileName2));
		System.out.println("Phase1: R1 ");
		int i1 =0;
		int i2 =0;
		int result1 =0;
		int result2 =0;
		do{
			
			SortBasedPhase1 test1 = new SortBasedPhase1(in1, fileName1, tulperPerTime, tulperLongth, i1);
			result1 = test1.phase1();
			System.out.println(i1);
			i1++;
		}while(result1!=-1);
		System.out.println("Phase1: R2 ");
		
		do{
			
			SortBasedPhase1 test1 = new SortBasedPhase1(in2, fileName2, tulperPerTime, tulperLongth, i2);
			result2 = test1.phase1();
			System.out.println(i2);
			i2++;
		}while(result2!=-1);
		
		
		in1.close();
		in2.close();
		
		DifferencePhase2  test2 = new  DifferencePhase2 (fileName1,fileName2,i1, i2);
		test2.phase2();
		System.out.println("Used: "+(System.currentTimeMillis()-a)/1000f+" s ");
	}

	

}
