package io.bhagat.projects.imagecompression;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import io.bhagat.ai.unsupervised.KMeans;

public class KMeansCompression {

	public static void main(String[] args) throws IOException {
		String filename = "files/awesome-colors.jpg";
		int k = 25;
		int iterations = 100;
		boolean showOriginal = false;
		
		KMeans model = new KMeans(k, iterations);
		
		BufferedImage image = ImageIO.read(new File(filename));
		BufferedImage image2 = ImageIO.read(new File(filename));
		int width = image.getWidth();
		int height = image.getHeight();
		System.out.println(width + " " + height + " " + width * height);
		int count = 0;
		double[][] inputs = new double[width * height][3];
		for(int i = 0; i < width; i++) {
		    for(int j = 0; j < height; j++)
		    {
		        Color color = new Color(image.getRGB(i, j));
		        inputs[j * width + i] = new double[]{ color.getRed(), color.getGreen(), color.getBlue() };
		    	count++;
		    }
		}
		System.out.println(count);
			
		
		System.out.println("training");
		model.train(inputs);
		System.out.println("Done training");
		
		for(int i = 0; i < width; i++) {
		    for(int j = 0; j < height; j++)
		    {
		    	double[] cluster = model.predict(inputs[j * width + i]);
		        image.setRGB(i, j, new Color((int) cluster[0], (int) cluster[1], (int) cluster[2]).getRGB());
		    }
		}
		System.out.println("Done Predicting");
		
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(new JLabel(new ImageIcon(image)));
		if(showOriginal)
			frame.getContentPane().add(new JLabel(new ImageIcon(image2)));
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
