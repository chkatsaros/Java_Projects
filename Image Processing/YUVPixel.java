package ce325.hw2;

public class YUVPixel {
	
	private int yuv;
	
	public YUVPixel (short Y, short U, short V) {
		
		yuvCalc(Y, U, V);
	}
	
	public YUVPixel (YUVPixel pixel) {}
	
	public YUVPixel (RGBPixel pixel) {}
	
	public void setY (short Y) {
		
		short U = (short)((yuv >> 8) & 0xFF);
		short V = (short)(yuv & 0xFF);
				
		yuvCalc(Y, U, V);
	}
	public void setU (short U) { 
		
		short Y = (short)((yuv >> 16) & 0xFF);
		short V = (short)(yuv & 0xFF);
				
		yuvCalc(Y, U, V);
	}
	public void setV (short V) {
		
		short Y = (short)((yuv >> 16) & 0xFF);
		short U = (short)((yuv >> 8) & 0xFF);
		
		yuvCalc(Y, U, V);
	}
	public short getY () { return ((short)((yuv >> 16) & 0xFF)); }
	public short getU () { return ((short)((yuv >> 8) & 0xFF)); }
	public short getV () {return ((short)(yuv & 0xFF)); }
	public void yuvCalc (short Y, short U, short V) {
		
		yuv = Y;
		yuv = (yuv << 8) + U;
		yuv = (yuv << 8) + V;
	}
	public void setYuv (int newYuv) { yuv = newYuv; }
	public int getYuv () { return yuv; }
}

