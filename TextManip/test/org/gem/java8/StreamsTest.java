	package org.gem.java8;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;

public class StreamsTest {

	@Test
	public void test() throws IOException {
		 try(FileInputStream fis = new
		 FileInputStream("./TextFiles/VBCC-Directory.txt"))
		 {
			 System.out.println("Total file size to read (in bytes) : "
						+ fis.available());
			 
			 int b =0;
			 
			 while (( b= fis.read())!=-1){
				 	System.out.print((char)b);
			 	}
			 }
		 catch(IOException ioe){
			 	System.err.println(ioe.getMessage());			 
		 }
		
	}

}
