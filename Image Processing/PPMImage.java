package ce325.hw2;
import java.io.*;
import java.util.*;

public class PPMImage extends RGBImage {
	
	//constructors
	
	public PPMImage (java.io.File file) {
		
		super(0, 0, 0);
		File myFile = file;
		
		try {
			if (!myFile.exists())
				throw new FileNotFoundException();
			Scanner input = new Scanner(new FileReader(myFile)); 
			     
			if (input.hasNext()) {        
				String myMagicNum = input.next();
				if (!myMagicNum.equals("P3")) throw new UnsupportedFileFormatException();
			}
			if (input.hasNext()) w = Integer.parseInt(input.next());
			if (input.hasNext()) h = Integer.parseInt(input.next());
			if (input.hasNext()) {
				c = Integer.parseInt(input.next());
				if (c > 255) {								
					c = 255;
				}
			}
			imgTable = new RGBPixel[h][w];
			
			for (int i=0; i<h; i++) {
				for (int j=0; j<w; j++) {
					imgTable[i][j] = new RGBPixel((short) 0, (short) 0, (short) 0);
				}
			}
						
			for (int i=0; i<h; i++ ) {
				for (int j=0; j<w; j++) {
					if (input.hasNext()) {
						short r = Short.parseShort(input.next());
						imgTable[i][j].setRed(r);
					}
					if (input.hasNext()) {
						short g = Short.parseShort(input.next());
						imgTable[i][j].setGreen(g);
					}
					if (input.hasNext()) {
						short b = Short.parseShort(input.next());
						imgTable[i][j].setBlue(b);
					}
				}	
			}
				
		}
		catch(UnsupportedFileFormatException e) {
			System.out.println("Unsupported file!");
		}
		catch (FileNotFoundException e) {
			System.out.println("No such file!");
		}
	}
	
	public PPMImage (RGBImage img) {
		
		super(img);
	}
	
	
	public PPMImage (YUVImage img) {
		
		super(img);
	}
	
	//methods
	
	public String toString() {
		
		String imgString = "P3 ";
		
		imgString += Integer.toString(w) + " " + Integer.toString(h) + " " + Integer.toString(c) + " ";
		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {
				imgString += Short.toString(imgTable[i][j].getRed()) + " " + Short.toString(imgTable[i][j].getGreen()) + " " + Short.toString(imgTable[i][j].getBlue()) + " ";
			}
		}
		
		return (imgString); 
	}
	
	public void toFile(java.io.File file) {
		
		String ppmText;
		
		try {
			FileWriter fileWriter = new FileWriter(file);
			PrintWriter writer = new PrintWriter(fileWriter);
			writer.print("");
		
			ppmText = toString();
			writer.print(ppmText);
			writer.close();
		}
		catch (IOException e) {
			System.out.println("Exception occurred");
		}
		
	}
}

