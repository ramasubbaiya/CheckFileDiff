package com.rama.lazyProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileDiffCheck {
	
	
	
	public static void main(String args[]) throws IOException {
		File file1 = new File("/Path/to/File1");
		File file2 = new File("/Path/to/File2");
		
		// Get the prossedfile
		ArrayList<String> linesOfFile2 = (ArrayList<String>) processFile(file1);
		ArrayList<String> linesOfFile1 = (ArrayList<String>) processFile(file2);
		
		// To find the unique lines
		Set<String> uniqueLines = new HashSet<String>();
		boolean stringFound = false;
		
		// Loop through first file
		for(int i=0; i<linesOfFile1.size(); i++) {
			// Loop through second file
			for(int j=0; j<linesOfFile2.size(); j++) {
				if(linesOfFile1.get(i).equals(linesOfFile2.get(j).trim())) {
					stringFound = true;
					break;
				} else {
					stringFound = false;
				}
				if(j == linesOfFile2.size()-1 && !stringFound) {
					uniqueLines.add(linesOfFile1.get(i));
				}
			}
			
		}
		
		/* If you want to look at the desired output 
		 
		// No. of lines found with my condition 
		System.out.println(uniqueLines.size());
		
		// Display line by line
		for(String uniqueLine : uniqueLines) { 
			System.out.println(uniqueLine);
		}
		
		*/
	}

	private static List<String> processFile(File file) throws IOException {
		// Obtains the input byte from a file
		FileInputStream fis = new FileInputStream(file);
		 
		// Construct BufferedReader from InputStreamReader
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	 
		// Initialize each line
		String line = null;
		
		// Store the conditional lines
		List<String> lines = new ArrayList<String>();
		
		// Read line by line
		while ((line = br.readLine()) != null) {
			// Remove white spaces if needed
			line =line.replaceAll("\\s+","");  // step 1
			
			
			// Example of lines to be stored
			// Eg 1: #commented line - skip this line
			// Eg 2: Where ever you go = I will not follow you
			// From step 1: Whereeveryougo=Iwillnotfollowyou
			// I will need to get this Whereeveryougo stored and Iwillnotfollowyou to be neglected
			
			// skip the line starting with # if needed and check line is not empty
			if(!line.startsWith("#") && !line.isEmpty()) {
				// Find the index of string with "=" sign
				int equalIndex = line.indexOf('=');
				
				// Store the line which starts with index0 and till the equal Index
				line = line.substring(0, equalIndex);
				// Store to lines 
				lines.add(line);
			}
			
		}
		
		br.close();
		
		return lines;
	 
	}
}
