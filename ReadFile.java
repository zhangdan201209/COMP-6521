package Project1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String fileName = "D:\\Data\\"+File.separator+"DataA100000_JoinResult";
		@SuppressWarnings("resource")
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		
		for(int i =0; i <100; i++)
			System.out.println(in.readLine());
	
	 int i=0;
	  while(in.readLine()!= null){
		  i++;
	  }
		System.out.println("Line "+ i);  
	}
}
