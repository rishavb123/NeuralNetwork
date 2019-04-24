package io.bhagat.projects.imagecompression;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import io.bhagat.math.linearalgebra.Matrix;
import io.bhagat.projects.iris.SerializableUtil;

public class Decompress {

	public static void main(String[] args) {
		try {
			Matrix.OuterProductSVD[] outers = SerializableUtil.deserialize("311.ser");
			Matrix temp = Matrix.multiply(outers[0].us[0].toMatrixColumn(), outers[0].vs[0].toMatrixRow());
			Matrix R = new Matrix(temp.getRows(), temp.getColumns());
			Matrix G = new Matrix(temp.getRows(), temp.getColumns());
			Matrix B = new Matrix(temp.getRows(), temp.getColumns());
			for(int i = 0; i < outers[0].singularValues.length; i++)
			{
				R.add(Matrix.multiply(outers[0].us[i].toMatrixColumn(), outers[0].vs[i].toMatrixRow()).multiply(outers[0].singularValues[i]));
				G.add(Matrix.multiply(outers[1].us[i].toMatrixColumn(), outers[1].vs[i].toMatrixRow()).multiply(outers[1].singularValues[i]));
				B.add(Matrix.multiply(outers[2].us[i].toMatrixColumn(), outers[2].vs[i].toMatrixRow()).multiply(outers[2].singularValues[i]));
			}
			BufferedImage image = new BufferedImage(temp.getRows(), temp.getColumns(), BufferedImage.TYPE_INT_RGB);
			for(int i = 0; i < temp.getRows(); i++)
				for(int j = 0; j < temp.getColumns(); j++)
				{
					if(R.get(i, j) > 255)
						R.set(i, j, 255);
					if(G.get(i, j) > 255)
						G.set(i, j, 255);
					if(B.get(i, j) > 255)
						B.set(i, j, 255);
					if(R.get(i, j) < 0)
						R.set(i, j, 0);
					if(G.get(i, j) < 0)
						G.set(i, j, 0);
					if(B.get(i, j) < 0)
						B.set(i, j, 0);
					image.setRGB(i, j, new Color((int) R.get(i, j), (int) G.get(i, j), (int)B.get(i, j)).getRGB());
				}
			ImageIO.write(image, "jpg", new File("files/311.jpg"));
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

}
