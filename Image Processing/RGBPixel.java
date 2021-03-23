package ce325.hw2;
import java.io.*;
import java.util.*;

public class RGBPixel {
	
	private int rgb;
	
	//constructors
	
	public RGBPixel (short red, short green, short blue) {
		
		rgbCalc(red, green, blue);
	}
	
	public RGBPixel (RGBPixel pixel) {
		
		RGBPixel cpPixel = new RGBPixel(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
	}
	
	//public RGBPixel (YUVPixel pixel) {}
	
	//methods
	
	public void setRed (short red) {
		
		short green = (short)((rgb >> 8) & 0xFF);
		short blue = (short)(rgb & 0xFF);
				
		rgbCalc(red, green, blue);
	}
	public void setGreen (short green) { 
		
		short red = (short)((rgb >> 16) & 0xFF);
		short blue = (short)(rgb & 0xFF);
				
		rgbCalc(red, green, blue);
	}
	public void setBlue (short blue) {
		
		short red = (short)((rgb >> 16) & 0xFF);
		short green = (short)((rgb >> 8) & 0xFF);
		
		rgbCalc(red, green, blue);
	}
	public short getRed () { return ((short)((rgb >> 16) & 0xFF)); }
	public short getGreen () { return ((short)((rgb >> 8) & 0xFF)); }
	public short getBlue () {return ((short)(rgb & 0xFF)); }
	public void rgbCalc (short red, short green, short blue) {
		
		rgb = red;
		rgb = (rgb << 8) + green;
		rgb = (rgb << 8) + blue;
	}
	public void setRgb (int newRgb) { rgb = newRgb; }
	public int getRgb () { return rgb; }
	
}

