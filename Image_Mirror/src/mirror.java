import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class mirror {

	public static void main(String[] args) {
		BufferedImage actImage = null;

		// Load image
		try { 
			actImage = ImageIO.read(new File("img/image.png"));
		} catch (IOException e) {
			try { 
				actImage = ImageIO.read(new File("img/image.jpg"));
			} catch (IOException er) {
				try { 
					actImage = ImageIO.read(new File("img/image.jpeg"));
				} catch (IOException err) {
					System.out.println("Konnte keine Datei Namens image.png, image.jpg oder image.jpeg im img Ordner finden!");
					System.exit(0);
				}
			}
		}

		int[][] actImagePixel = new int[actImage.getWidth()][actImage
				.getHeight()];// Save image to 2dint
		for (int i = 0; i < actImagePixel.length; i++) {
			for (int j = 0; j < actImagePixel[i].length; j++) {
				actImagePixel[i][j] = actImage.getRGB(i, j);
			}
		}

		Scanner input = new Scanner(System.in);
		String mirrorOption = "";
		int[][] MirroredImagePixel = null;
		System.out
				.println("Soll das Bild horizontal, vertikal, punkt oder diagonal gespiegelt werden? h / v / p / d");
		while (true) {
			mirrorOption = input.next();
			if (mirrorOption.equalsIgnoreCase("v")) {
				MirroredImagePixel = vMirrorIntImage(actImagePixel);
				break;
			} else if (mirrorOption.equalsIgnoreCase("h")) {
				MirroredImagePixel = hMirrorIntImage(actImagePixel);
				break;
			} else if (mirrorOption.equalsIgnoreCase("p")) {
				int[][] tmpArray=vMirrorIntImage(actImagePixel);
				MirroredImagePixel = hMirrorIntImage(tmpArray);
				break;
			} else if(mirrorOption.equalsIgnoreCase("d")){
				MirroredImagePixel = dMirrorIntImage(actImagePixel);
				break;
			}else
				System.out.println("Ungültige Eingabe!");
		}
		input.close();

		BufferedImage MirroredImage = new BufferedImage(
				MirroredImagePixel.length, MirroredImagePixel[0].length, 2);// convert
																			// 2dInt
																			// to
																			// image
		for (int i = 0; i < MirroredImagePixel.length; i++) {
			for (int j = 0; j < MirroredImagePixel[i].length; j++) {
				MirroredImage.setRGB(i, j, MirroredImagePixel[i][j]);
			}
		}
		
		if (mirrorOption.equalsIgnoreCase("h")
				|| mirrorOption.equalsIgnoreCase("v")||mirrorOption.equalsIgnoreCase("p")||mirrorOption.equalsIgnoreCase("d")) {
			try {// Write Image
				BufferedImage bi = MirroredImage;
				File outputfile = new File("img/" + mirrorOption
						+ "mirrored.png");
				ImageIO.write(bi, "png", outputfile);
			} catch (IOException e) {
			}
			String tmpMirrorType="";
			if(mirrorOption.equalsIgnoreCase("h"))
				tmpMirrorType="horizontal";
			else if(mirrorOption.equalsIgnoreCase("v"))
				tmpMirrorType="verikal";
			else if(mirrorOption.equalsIgnoreCase("p"))
				tmpMirrorType="punkt";
			else if(mirrorOption.equalsIgnoreCase("d"))
				tmpMirrorType="diagonal";
			System.out.println("Das Bild wurde erfolgreich "
					+ tmpMirrorType + " gespiegelt!");
		} else
			System.out.println("Hierhin solltest du niemals kommen. WTF!");
	}

	// vMirrorIntImage mirrors vertical
	private static int[][] vMirrorIntImage(int[][] actImagePixel) {
		int[][] vMirroredImagePixel = new int[actImagePixel.length][actImagePixel[0].length];
		for (int j = 0; j < vMirroredImagePixel.length; j++) {
			for (int i = 0; i < vMirroredImagePixel[j].length; i++) {
				vMirroredImagePixel[i][j] = actImagePixel[vMirroredImagePixel[j].length
						- i - 1][j];
			}
		}
		return vMirroredImagePixel;
	}

	// hMirrorIntImage mirrors horizontal
	private static int[][] hMirrorIntImage(int[][] actImagePixel) {
		int[][] hMirroredImagePixel = new int[actImagePixel.length][actImagePixel[0].length];
		for (int j = 0; j < hMirroredImagePixel.length; j++) {
			for (int i = 0; i < hMirroredImagePixel[j].length; i++) {
				hMirroredImagePixel[i][j] = actImagePixel[i][hMirroredImagePixel.length
						- j - 1];
			}
		}
		return hMirroredImagePixel;
	}
	
	// dMirrorIntImage mirrors diagonal
		private static int[][] dMirrorIntImage(int[][] actImagePixel) {
			int[][] hMirroredImagePixel = new int[actImagePixel[0].length][actImagePixel.length];
			for (int j = 0; j < hMirroredImagePixel.length; j++) {
				for (int i = 0; i < hMirroredImagePixel[j].length; i++) {
					hMirroredImagePixel[i][j] = actImagePixel[hMirroredImagePixel.length
					                  						- j - 1][hMirroredImagePixel.length
							- i - 1];
				}
			}
			return hMirroredImagePixel;
		}
}
