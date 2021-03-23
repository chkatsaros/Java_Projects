package ce325.hw2;

public class RGBImage implements Image {
	
	public int w;
	public int h;
	public int c;
	RGBPixel[][] imgTable;
	
	//constructors
		
	public RGBImage (int width, int height, int colordepth) {
		
		c = colordepth;
		w = width;
		h = height;
		imgTable = new RGBPixel[h][w];
		
		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {
				imgTable[i][j] = new RGBPixel((short) 0, (short) 0, (short) 0);
			}
		}
	}
	
	public RGBImage (RGBImage copyImg) {
		
		w = copyImg.w;
		h = copyImg.h;
		c = copyImg.c;
		imgTable = new RGBPixel[h][w];
		
		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {
				imgTable[i][j] = copyImg.imgTable[i][j];
			}
		}
	}
	
	public RGBImage (YUVImage YUVImg) {
		
		w = YUVImg.w;
		h = YUVImg.h;
		c = 255;
		imgTable = new RGBPixel[h][w];
		
		for (int i=0; i<YUVImg.h; i++) {
			for (int j=0; j<YUVImg.w; j++) {
				imgTable[i][j] = new RGBPixel((short) 0, (short) 0, (short) 0);
			}
		}
		
		for (int i=0; i<YUVImg.h; i++) {
			for (int j=0; j<YUVImg.w; j++) {
				short C = (short) (YUVImg.imgTable[i][j].getY() - 16);
				short D = (short) (YUVImg.imgTable[i][j].getU() - 128);
				short E = (short) (YUVImg.imgTable[i][j].getV() - 128);
				imgTable[i][j].setRed(clip((short) ((((C * 298 + E * 409 + 128)) >> 8))));
				imgTable[i][j].setGreen(clip((short) ((((C * 298 - D * 100 + E * 208 + 128)) >> 8))));
				imgTable[i][j].setBlue(clip((short) ((((C * 298 + D * 516 + 128)) >> 8))));
			}
		}
	}
	
	//methods
	
	public void grayscale () {
		
		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {
				double gray = imgTable[i][j].getRed() * 0.3 + imgTable[i][j].getGreen() * 0.59 + imgTable[i][j].getBlue() * 0.11;
				imgTable[i][j].setRed((short) gray);
				imgTable[i][j].setGreen((short) gray);
				imgTable[i][j].setBlue((short) gray);
			}
		}
	}
	
	public void doublesize() {
		
		h = h * 2;
		w = w * 2;
		
		RGBPixel [][] doubleImgTable = new RGBPixel[h][w];
		
		for (int i=0; i<h/2; i++) {
			for (int j=0; j<w/2; j++) {
				doubleImgTable[2*i][2*j] = imgTable[i][j];
				doubleImgTable[2*i+1][2*j] = imgTable[i][j];
				doubleImgTable[2*i][2*j+1] = imgTable[i][j];
				doubleImgTable[2*i+1][2*j+1] = imgTable[i][j];
			}
		}
		
		imgTable = doubleImgTable;
	}
	
	public void halfsize() {
		
		h = h / 2;
		w = w / 2;
		
		RGBPixel [][] halfImgTable = new RGBPixel[h][w];
		
		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {
				halfImgTable[i][j] = new RGBPixel((short) 0, (short) 0, (short) 0);
			}
		}
	
		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {
				halfImgTable[i][j].setRed((short)((imgTable[2*i][2*j].getRed() + imgTable[2*i+1][2*j].getRed() + imgTable[2*i][2*j+1].getRed() + imgTable[2*i+1][2*j+1].getRed())/4));
				halfImgTable[i][j].setGreen((short)((imgTable[2*i][2*j].getGreen() + imgTable[2*i+1][2*j].getGreen() + imgTable[2*i][2*j+1].getGreen() + imgTable[2*i+1][2*j+1].getGreen())/4));
				halfImgTable[i][j].setBlue((short)((imgTable[2*i][2*j].getBlue() + imgTable[2*i+1][2*j].getBlue() + imgTable[2*i][2*j+1].getBlue() + imgTable[2*i+1][2*j+1].getBlue())/4));
			}
		}
		
		imgTable = halfImgTable;
	}
	
	public void rotateClockwise() {
		
		RGBPixel [][] rotateImgTable = new RGBPixel[w][h];
					
		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {
				rotateImgTable[j][i] = imgTable[h-1-i][j];
			}
		}
		
		int tmp = h;
		h = w;
		w = tmp;
		imgTable = rotateImgTable;
	}
	
	public short clip(short x) {
		
		if (x < (short) 0) x = 0;
		if (x > (short) 255) x = 255;
		
		return x;
	}
}

