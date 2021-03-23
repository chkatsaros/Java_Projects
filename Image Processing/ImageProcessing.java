package ce325.hw2;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.filechooser.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.util.*;

public class ImageProcessing {
	
	static String inType = "";
	static String outType = "";
	static PPMImage inImgPPM;
	static YUVImage inImgYUV;
	static PPMImage outImgPPM;
	static YUVImage outImgYUV;
	
	public static void main (String[] args){
		
		JFrame jf = new JFrame();
		jf.setTitle("Image Processing");
		jf.setSize(500, 500);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		JMenuBar jmb = new JMenuBar();
		jf.setJMenuBar(jmb);
		
		JMenu file = new JMenu("File");
		jmb.add(file);
		
		JMenu action = new JMenu("Actions");
		jmb.add(action);
				
		JMenuItem open = new JMenuItem(new AbstractAction("Open...") {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setAcceptAllFileFilterUsed(false);	
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.ppm", "ppm");
				jfc.setFileFilter(filter);
				jfc.addChoosableFileFilter(new FileNameExtensionFilter("*.yuv", "yuv"));
				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					if (selectedFile.getName().split("\\.")[1].equals("ppm")) {
						inType = "ppm";
						inImgPPM = new PPMImage(selectedFile);
						inImgYUV = new YUVImage(inImgPPM);
						addImg(inImgPPM, jf);
					}
					else {
						inType = "yuv";
						inImgYUV = new YUVImage(selectedFile);
						inImgPPM = new PPMImage(inImgYUV);
						addImg(inImgPPM, jf);
					}
				}
			}	
		});		
		file.add(open);	
		
		JMenuItem save = new JMenuItem(new AbstractAction("Save As...") {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setAcceptAllFileFilterUsed(false);	
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.ppm", "ppm");
				jfc.setFileFilter(filter);
				jfc.addChoosableFileFilter(new FileNameExtensionFilter("*.yuv", "yuv"));
				int returnValue = jfc.showSaveDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					if (!inType.equals("")) {
						File selectedFile = jfc.getSelectedFile();
						if (selectedFile.getName().split("\\.")[1].equals("ppm")) {
							outType = "ppm";
							if (!inType.equals("")){
								if (inType.equals(outType)) {
									inImgPPM.toFile(selectedFile);
								}
								else {
									outImgPPM = new PPMImage(inImgYUV);
									outImgPPM.toFile(selectedFile);
								}
							}
						}
						else {
							outType = "yuv";
							if (!inType.equals("")){
								if (inType.equals(outType)) {
									inImgYUV.toFile(selectedFile);
								}
								else {
									outImgYUV = new YUVImage(inImgPPM);
									outImgYUV.toFile(selectedFile);
								}
							}
					}
					}					
				}	
			}
		});
		file.add(save);	
		
		JMenuItem gray = new JMenuItem(new AbstractAction("Grayscale") {
			public void actionPerformed(ActionEvent e) {
				if (!inType.equals("")){
					inImgPPM.grayscale();
					inImgYUV = new YUVImage(inImgPPM);
					addImg(inImgPPM, jf);
				}
			}
		});
		action.add(gray);
		
		JMenuItem inc = new JMenuItem(new AbstractAction("Increase Size") {
			public void actionPerformed(ActionEvent e) {
				if (!inType.equals("")){
					inImgPPM.doublesize();
					inImgYUV = new YUVImage(inImgPPM);
					addImg(inImgPPM, jf);
				}	
			}
		});
		action.add(inc);
		
		JMenuItem dec = new JMenuItem(new AbstractAction("Decrease Size") {
			public void actionPerformed(ActionEvent e) {
				if (!inType.equals("")){
					inImgPPM.halfsize();
					inImgYUV = new YUVImage(inImgPPM);
					addImg(inImgPPM, jf);
				}
			}
		});
		action.add(dec);
		
		JMenuItem rot = new JMenuItem(new AbstractAction("Rotate Clockwise") {
			public void actionPerformed(ActionEvent e) {
				if (!inType.equals("")){
					inImgPPM.rotateClockwise();
					inImgYUV = new YUVImage(inImgPPM);
					addImg(inImgPPM, jf);
				}
			}
		});
		action.add(rot);
		
		JMenuItem eq = new JMenuItem(new AbstractAction("Equalize Histogram") {
			public void actionPerformed(ActionEvent e) {
				if (!inType.equals("")){
					inImgYUV.hist = new Histogram(inImgYUV);
					inImgYUV.equalize();
					inImgPPM = new PPMImage(inImgYUV);
					addImg(inImgPPM, jf);
				}
			}
		});
		action.add(eq);
		
		JMenuItem st = new JMenuItem(new AbstractAction("Stacking Algorithm") {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				jfc.setAcceptAllFileFilterUsed(false);
				
				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedDir = jfc.getSelectedFile();
					PPMImageStacker stacker = new PPMImageStacker(selectedDir);
					stacker.stack();
					PPMImage stImg = new PPMImage(stacker.getStackedImage());
					addImg(stImg, jf);
				}
			}
		});
		action.add(st);
	}
		
	public static void addImg(PPMImage img, JFrame jf) {
		
		BufferedImage bi = new BufferedImage(img.w, img.h, BufferedImage.TYPE_INT_RGB);
		for(int i = 0; i < img.h; i++){
			for(int j = 0; j < img.w ; j++){
				bi.setRGB(j, i, img.imgTable[i][j].getRgb());
			}
		}
		jf.getContentPane().removeAll();
		JLabel lbl = new JLabel();
		lbl.setIcon(new ImageIcon(bi));
		jf.getContentPane().add(lbl,BorderLayout.CENTER);
		jf.pack();
	}	
}

