import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class mirror {
	private static ArrayList<ImagePixels> ImagesPixels = new ArrayList<ImagePixels>();

	public static void main(String[] args) {
		BufferedImage actImage = null;
		int imageDataType = -1;

		// Load image
		try {
			actImage = ImageIO.read(new File("img/image.png"));
			imageDataType = 0;
		} catch (IOException e) {
			try {
				actImage = ImageIO.read(new File("img/image.jpg"));
				imageDataType = 1;
			} catch (IOException er) {
				System.out.println("Konnte keine Datei Namens image.png oder image.jpg oder im img Ordner finden!");
				System.exit(0);
			}
		}

		// Save image to 2dint
		int[][] actImagePixel = new int[actImage.getWidth()][actImage.getHeight()];
		for (int i = 0; i < actImagePixel.length; i++) {
			for (int j = 0; j < actImagePixel[i].length; j++) {
				actImagePixel[i][j] = actImage.getRGB(i, j);
			}
		}

		// Input mirror options
		Scanner input = new Scanner(System.in);
		String mirrorOptions = "";
		int[][] MirroredImagePixel = null;
		System.out.println("Soll das Bild horizontal, vertikal, punkt oder diagonal gespiegelt werden? [h][v][p][d]");
		boolean breakWhile = false;
		while (true) {
			mirrorOptions = input.next();
			for (int i = 0; i < mirrorOptions.length(); i++) {
				char mOption = mirrorOptions.toLowerCase().charAt(i);
				if (mOption == "v".charAt(0)) {
					MirroredImagePixel = vMirrorIntImage(actImagePixel);
					breakWhile = true;
				} else if (mOption == "h".charAt(0)) {
					MirroredImagePixel = hMirrorIntImage(actImagePixel);
					breakWhile = true;
				} else if (mOption == "p".charAt(0)) {
					MirroredImagePixel = pMirrorIntImage(actImagePixel);
					breakWhile = true;
				} else if (mOption == "d".charAt(0)) {
					MirroredImagePixel = dMirrorIntImage(actImagePixel);
					breakWhile = true;
				} else {
					breakWhile = false;
					break;
				}
				ImagesPixels.add(new ImagePixels(MirroredImagePixel, mOption));
			}
			if (breakWhile) {
				break;
			} else
				System.out.println("Ungültige Eingabe!");
		}
		input.close();

		// Start output
		if (ImagesPixels.size() > 0) {
			String tmpMirrorType = "";
			for (int i = 0; i < ImagesPixels.size(); i++) {
				char mOption = ImagesPixels.get(i).getmOption();
				// convert 2dInt to image Array
				int[][] actPixels = ImagesPixels.get(i).getPixels();
				BufferedImage MirroredImage = new BufferedImage(actPixels.length, actPixels[0].length, 2);
				for (int l = 0; l < actPixels.length; l++) {
					for (int j = 0; j < actPixels[l].length; j++) {
						MirroredImage.setRGB(l, j, actPixels[l][j]);
					}
				}

				// Write Image
				try {
					BufferedImage bi = MirroredImage;
					File outputfile;
					switch (imageDataType) {
					case -1:
						throw new IOException();
					case 0:
						outputfile = new File("img/" + mOption + "Mirrored.png");
						ImageIO.write(bi, "png", outputfile);
						break;
					case 1:
						outputfile = new File("img/" + mOption + "Mirrored.jpg");
						ImageIO.write(bi, "jpg", outputfile);
						break;
					}
				} catch (IOException e) {
					System.out.println("Bei \"" + mOption + "\" ist etwas schief gelaufen.");
				}

				// Edit Output Text
				if (mOption == "h".charAt(0)) {
					if (tmpMirrorType != "")
						tmpMirrorType = tmpMirrorType + ", horizontal";
					else
						tmpMirrorType = "horizontal";
				} else if (mOption == "v".charAt(0)) {
					if (tmpMirrorType != "")
						tmpMirrorType = tmpMirrorType + ", vertikal";
					else
						tmpMirrorType = "verikal";
				} else if (mOption == "p".charAt(0)) {
					if (tmpMirrorType != "")
						tmpMirrorType = tmpMirrorType + ", punkt";
					else
						tmpMirrorType = "punkt";
				} else if (mOption == "d".charAt(0)) {
					if (tmpMirrorType != "")
						tmpMirrorType = tmpMirrorType + ", diagonal";
					else
						tmpMirrorType = "diagonal";
				}
			}
			System.out.println("Das Bild wurde erfolgreich " + tmpMirrorType + " gespiegelt!");
		} else
			System.out.println("Hierhin solltest du niemals kommen. WTF!");
	}

	// vMirrorIntImage mirrors vertical
	private static int[][] vMirrorIntImage(int[][] actImagePixel) {
		int[][] vMirroredImagePixel = new int[actImagePixel.length][actImagePixel[0].length];
		for (int j = 0; j < vMirroredImagePixel[0].length; j++) {
			for (int i = 0; i < vMirroredImagePixel.length; i++) {
				vMirroredImagePixel[i][j] = actImagePixel[vMirroredImagePixel.length - i - 1][j];
			}
		}
		return vMirroredImagePixel;
	}

	// hMirrorIntImage mirrors horizontal
	private static int[][] hMirrorIntImage(int[][] actImagePixel) {
		int[][] hMirroredImagePixel = new int[actImagePixel.length][actImagePixel[0].length];
		for (int j = 0; j < hMirroredImagePixel[0].length; j++) {
			for (int i = 0; i < hMirroredImagePixel.length; i++) {
				hMirroredImagePixel[i][j] = actImagePixel[i][hMirroredImagePixel[i].length - 1 - j];
			}
		}
		return hMirroredImagePixel;
	}

	// dMirrorIntImage mirrors diagonal
	private static int[][] dMirrorIntImage(int[][] actImagePixel) {
		int[][] dMirroredImagePixel = new int[actImagePixel[0].length][actImagePixel.length];
		for (int j = 0; j < dMirroredImagePixel[0].length; j++) {
			for (int i = 0; i < dMirroredImagePixel.length; i++) {
				dMirroredImagePixel[i][j] = actImagePixel[dMirroredImagePixel[i].length - j
						- 1][dMirroredImagePixel.length - i - 1];
			}
		}
		return dMirroredImagePixel;
	}

	// pMirrorIntImage mirrors diagonal
	private static int[][] pMirrorIntImage(int[][] actImagePixel) {
		int[][] tmpArray = vMirrorIntImage(actImagePixel);
		int[][] pMirroredImagePixel = hMirrorIntImage(tmpArray);
		return pMirroredImagePixel;
	}
}
