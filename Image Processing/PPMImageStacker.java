package ce325.hw2;
import java.io.*;
import java.lang.*;
import java.util.*;

public class PPMImageStacker {
	
	List <PPMImage> ppmImageList = new ArrayList<PPMImage>();;
	PPMImage stImage;
	
	//constructors
	
	public  PPMImageStacker(File dir) {
		
		File folder = new File(dir.getPath());
		
		if (!(folder.exists())) {
			System.out.println("[ERROR] Directory " + dir.getName() + " does not exist!​");
		}
		else {
			if (!(folder.isDirectory())) {
				System.out.println("[ERROR] " + dir.getName() + " is not a directory!");
			}
		}
		
		for (File file : folder.listFiles()) {
			if (file.isFile()) {
				PPMImage nodeImg = new PPMImage(file);
				ppmImageList.add(nodeImg);
			}
		}
		
	}
	
	//methods
	
	public void stack() {
				
		stImage = new PPMImage(ppmImageList.get(0));
				
		int[][] sumRgb = new int[ppmImageList.get(0).h][ppmImageList.get(0).w];
				
		for (int x=0; x<ppmImageList.size(); x++) {
			for (int i=0; i<ppmImageList.get(0).h; i++) {
				for (int j=0; j<ppmImageList.get(0).w; j++) {
					sumRgb[i][j] += ppmImageList.get(x).imgTable[i][j].getRgb();
				}
			}	
		}
		
		for (int i=0; i<ppmImageList.get(0).h; i++) {
			for (int j=0; j<ppmImageList.get(0).w; j++) {
				stImage.imgTable[i][j].setRgb(sumRgb[i][j]/ppmImageList.size());
			}
		}
	}
	
	public PPMImage getStackedImage() {
		
		return(stImage);
	}
}

