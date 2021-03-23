package ce325.hw2;
import java.io.*;
import java.util.*;

public class YUVImage {
	
	int w, h;
	YUVPixel[][] imgTable;
	Histogram hist; 
	
	public YUVImage(int width, int height) {
		
		w = width;
		h = height;
		imgTable = new YUVPixel[h][w];
		
		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {
				imgTable[i][j] = new YUVPixel((short) 16, (short) 128, (short) 128);
			}
		} 
	}
	
	public YUVImage(YUVImage copyImg) {
		
		imgTable = new YUVPixel[copyImg.h][copyImg.w];
		
		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {
				imgTable[i][j] = new YUVPixel((short) 16, (short) 128, (short) 128);
			}
		}
		
		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {
				imgTable[i][j].setY(copyImg.imgTable[i][j].getY());
				imgTable[i][j].setU(copyImg.imgTable[i][j].getU());
				imgTable[i][j].setV(copyImg.imgTable[i][j].getV());
			}
		}
	}
	
	public YUVImage(RGBImage RGBImg) {
		
		h = RGBImg.h;
		w = RGBImg.w;
		imgTable = new YUVPixel[h][w];
		
		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {
				imgTable[i][j] = new YUVPixel((short) (((((RGBImg.imgTable[i][j].getRed() * 66 + RGBImg.imgTable[i][j].getGreen() * 129 + RGBImg.imgTable[i][j].getBlue() * 25 + 128) >> 8) + 16))),
											  (short) ((((RGBImg.imgTable[i][j].getRed() * (-38) - RGBImg.imgTable[i][j].getGreen() * 74 + RGBImg.imgTable[i][j].getBlue() * 112 + 128) >> 8) + 128)),
											  (short) ((((RGBImg.imgTable[i][j].getRed() * 112 - RGBImg.imgTable[i][j].getGreen() * 94 - RGBImg.imgTable[i][j].getBlue() * 18 + 128) >> 8) + 128)));
			}
		}
				
		/*for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {
				imgTable[i][j].setY((short) ((((RGBImg.imgTable[i][j].getRed() * 66 + RGBImg.imgTable[i][j].getGreen() * 129 + RGBImg.imgTable[i][j].getBlue() * 25 + 128)) >> 8 + 16)));
				imgTable[i][j].setU((short) ((((RGBImg.imgTable[i][j].getRed() * (-38) - RGBImg.imgTable[i][j].getGreen() * 74 + RGBImg.imgTable[i][j].getBlue() * 112 + 128)) >> 8 + 128)));
				imgTable[i][j].setV((short) ((((RGBImg.imgTable[i][j].getRed() * 112 - RGBImg.imgTable[i][j].getGreen() * 94 - RGBImg.imgTable[i][j].getBlue() * 18 + 128)) >> 8 + 128)));
			}
		}*/
	}
	
	public YUVImage(File file) {
		
		File myFile = file;
		
		try {
			if (!myFile.exists())
				throw new FileNotFoundException();
			Scanner input = new Scanner(new FileReader(myFile)); 
			     
			if (input.hasNext()) {        
				String myMagicNum = input.next();
				if (!myMagicNum.equals("YUV3")) throw new UnsupportedFileFormatException();
			}
			if (input.hasNext()) w = Integer.parseInt(input.next());
			if (input.hasNext()) h = Integer.parseInt(input.next());
			
			imgTable = new YUVPixel[h][w];
			
			for (int i=0; i<h; i++) {
				for (int j=0; j<w; j++) {
					imgTable[i][j] = new YUVPixel((short) 16, (short) 128, (short) 128);
				}
			}
						
			for (int i=0; i<h; i++ ) {
				for (int j=0; j<w; j++) {
					if (input.hasNext()) {
						short Y = Short.parseShort(input.next());
						imgTable[i][j].setY(Y);
					}
					if (input.hasNext()) {
						short U = Short.parseShort(input.next());
						imgTable[i][j].setU(U);
					}
					if (input.hasNext()) {
						short V = Short.parseShort(input.next());
						imgTable[i][j].setV(V);
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
	
	public String toString() {
		
		String imgString = "YUV3 ";
		
		imgString += Integer.toString(w) + " " + Integer.toString(h) + " ";
		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {
				imgString += Short.toString(imgTable[i][j].getY()) + " " + Short.toString(imgTable[i][j].getU()) + " " + Short.toString(imgTable[i][j].getV()) + " ";
			}
		}
		
		return (imgString);
	}
	
	public void toFile(File file) {
		
		String yuvText;
		
		try {
			FileWriter fileWriter = new FileWriter(file);
			PrintWriter writer = new PrintWriter(fileWriter);
			writer.print("");
		
			yuvText = toString();
			writer.print(yuvText);
			writer.close();
		}
		catch (IOException e) {
			System.out.println("Exception occurred");
		}
	}
	
	public void equalize() {
		
		hist.equalize();
		
		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {
				imgTable[i][j].setY(hist.getEqualizedLuminocity(imgTable[i][j].getY()));
			}
		}
	}
}

