package io.bhagat.projects.imagecompression;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import io.bhagat.math.linearalgebra.Matrix;
import io.bhagat.math.linearalgebra.Vector;
import io.bhagat.util.SerializableUtil;

public class Compress {

	public static void main(String[] args) throws IOException {
		String filename = "files/bigpic.jpg";
		int k = 311;
		
		BufferedImage image = ImageIO.read(new File(filename));
		int width = image.getWidth();
		int height = image.getHeight();
		double[][] r = new double[width][height];
		double[][] g = new double[width][height];
		double[][] b = new double[width][height];
		for(int i = 0; i < width; i++)
		    for(int j = 0; j < height; j++)
		    {
		        Color color = new Color(image.getRGB(i, j));
		        r[i][j] = color.getRed();
		        g[i][j] = color.getGreen();
		        b[i][j] = color.getBlue();
		    }
		Matrix R = new Matrix(r);
		Matrix G = new Matrix(g);
		Matrix B = new Matrix(b);
		
		System.out.println("Loaded into matricies");
		
		Matrix.OuterProductSVD[] outers = {Matrix.singularValueDecompositionOuter(R, 100), Matrix.singularValueDecompositionOuter(G, 100), Matrix.singularValueDecompositionOuter(B, 100)};
		
		System.out.println("Factored");
		
		for(int i = 0; i < 3; i++)
		{
			double[] singularValues = new double[k];
			Vector[] us = new Vector[k];
			Vector[] vs = new Vector[k];
			
			for(int j = 0; j < k; j++)
			{
				singularValues[j] = outers[i].singularValues[j];
				us[j] = outers[i].us[j];
				vs[j] = outers[i].vs[j];
			}
			outers[i] = new Matrix.OuterProductSVD(singularValues, us, vs);
		}
		
		System.out.println("Truncated");
		
		SerializableUtil.serialize(outers, "311.ser");
		
		System.out.println("Serialized");
		
	}

}
