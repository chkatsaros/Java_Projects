package ce325.hw2;

import java.io.*;
import java.lang.*;
import java.util.*;

public class Histogram {
	
	int []hist = new int[236];
	int numOfPixels;
	//constructors
	
	public Histogram(YUVImage img) {
	
		for (int i=0; i<img.h; i++){
			for (int j=0; j<img.w; j++){
				hist[img.imgTable[i][j].getY()]++;
			}
		}
		numOfPixels = img.w*img.h;
	}
	
	//methods
	
	public String toString() {
		
		String histString = "";
		
		for (int i=0; i<236; i++) {
			histString += Integer.toString(i);
			for (int j=0; j<hist[i]; j++) {
				if (j > 80) break;
				histString += "*";
			}
			histString += "\n";
		}
		
		return (histString);
	}
	
	public void toFile(File file) {
		
		String histText;
		
		try {
			FileWriter fileWriter = new FileWriter(file);
			PrintWriter writer = new PrintWriter(fileWriter);
			writer.print("");
		
			histText = toString();
			writer.print(histText);
			writer.close();
		}
		catch (IOException e) {
			System.out.println("Exception occurred");
		}
	}
	
	public void equalize() {
		
		double []probability = new double[236];
		double []sumProbability = new double[236];
		int maxY = 0;
		
		for (int i=0; i<236; i++){
			if (hist[i] != 0) maxY = i;
			probability[i] = ((float)hist[i])/numOfPixels;
			for (int j=0; j<i; j++) {
				sumProbability[i] += probability[j];
			}
		}
		
		for (int i=0; i<236; i++)
			hist[i] = (int) (sumProbability[i] * maxY);
	}
	
	public short getEqualizedLuminocity(int luminocity) { 
				
		return ((short) hist[luminocity]);
	}
}

