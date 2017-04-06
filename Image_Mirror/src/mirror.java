import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class mirror {
	private static boolean mHorizontal = false;
	private static boolean mVertical = false;
	private static boolean mPunctual = false;
	private static boolean mDiagonal = false;

	public static void main(String[] args) {
		BufferedImage actImage = null;

		// Load image
		try {
			actImage = ImageIO.read(new File("img/image.png"));
		} catch (IOException e) {
			try {
				actImage = ImageIO.read(new File("img/image.jpg"));
			} catch (IOException er) {
					System.out
							.println("Konnte keine Datei Namens image.png oder image.jpg oder im img Ordner finden!");
					System.exit(0);
				}
			}
		

		// Save image to 2dint
		int[][] actImagePixel = new int[actImage.getWidth()][actImage
				.getHeight()];
		for (int i = 0; i < actImagePixel.length; i++) {
			for (int j = 0; j < actImagePixel[i].length; j++) {
				actImagePixel[i][j] = actImage.getRGB(i, j);
			}
		}

		Scanner input = new Scanner(System.in);
		String mirrorOption = "";
		int[][] MirroredImagePixel = null;
		System.out
				.println("Soll das Bild horizontal, vertikal, punkt oder diagonal gespiegelt werden? [h][v][p][d]");
		boolean breakWhile = false;
		while (true) {
			mirrorOption = input.next();
			for (int i = 0; i < mirrorOption.length(); i++) {
				if (String.valueOf((mirrorOption.charAt(i))).equalsIgnoreCase(
						"v")) {
					MirroredImagePixel = vMirrorIntImage(actImagePixel);
					mVertical = true;
					breakWhile = true;
				} else if (String.valueOf((mirrorOption.charAt(i)))
						.equalsIgnoreCase("h")) {
					MirroredImagePixel = hMirrorIntImage(actImagePixel);
					mHorizontal = true;
					breakWhile = true;
				} else if (String.valueOf((mirrorOption.charAt(i)))
						.equalsIgnoreCase("p")) {
					MirroredImagePixel = pMirrorIntImage(actImagePixel);
					mPunctual = true;
					breakWhile = true;
				} else if (String.valueOf((mirrorOption.charAt(i)))
						.equalsIgnoreCase("d")) {
					MirroredImagePixel = dMirrorIntImage(actImagePixel);
					mDiagonal = true;
					breakWhile = true;
				} else {
					breakWhile = false;
					break;
				}
			}
			if (breakWhile)
				break;
			else
				System.out.println("Ungültige Eingabe!");
		}
		input.close();

		// convert 2dInt to image
		BufferedImage MirroredImage = new BufferedImage(
				MirroredImagePixel.length, MirroredImagePixel[0].length, 2);
		for (int i = 0; i < MirroredImagePixel.length; i++) {
			for (int j = 0; j < MirroredImagePixel[i].length; j++) {
				MirroredImage.setRGB(i, j, MirroredImagePixel[i][j]);
			}
		}

		if (mVertical || mHorizontal || mPunctual || mDiagonal) {
			String tmpMirrorType = "";
			// Write Image
			for (int i = 0; i < mirrorOption.length(); i++) {
				try {
					BufferedImage bi = MirroredImage;
					File outputfile = new File("img/" + mirrorOption.charAt(i)
							+ "mirrored.png");
					ImageIO.write(bi, "png", outputfile);
				} catch (IOException e) {
				}
				if (mHorizontal) {
					if (tmpMirrorType != "")
						tmpMirrorType = tmpMirrorType + ", horizontal";
					else
						tmpMirrorType = "horizontal";
					mHorizontal = false;
				} else if (mVertical) {
					if (tmpMirrorType != "")
						tmpMirrorType = tmpMirrorType + ", vertikal";
					else
						tmpMirrorType = "verikal";
					mVertical = false;
				} else if (mPunctual) {
					if (tmpMirrorType != "")
						tmpMirrorType = tmpMirrorType + ", punkt";
					else
						tmpMirrorType = "punkt";
					mPunctual = false;
				} else if (mDiagonal) {
					if (tmpMirrorType != "")
						tmpMirrorType = tmpMirrorType + ", diagonal";
					else
						tmpMirrorType = "diagonal";
					mDiagonal = false;
				}
			}
			System.out.println("Das Bild wurde erfolgreich " + tmpMirrorType
					+ " gespiegelt!");
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
						- j - 1][hMirroredImagePixel.length - i - 1];
			}
		}
		return hMirroredImagePixel;
	}
	
	// pMirrorIntImage mirrors diagonal
		private static int[][] pMirrorIntImage(int[][] actImagePixel) {
			int[][] tmpArray = vMirrorIntImage(actImagePixel);
			int[][] pMirroredImagePixel = hMirrorIntImage(tmpArray);
			return pMirroredImagePixel;
		}
}
