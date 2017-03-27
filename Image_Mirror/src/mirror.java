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
			actImage = ImageIO.read(new File("img/golang.png"));
		} catch (IOException e) {
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
				.println("Soll das bild horizontal oder vertikal gespiegelt werden? h / v");
		while (true) {
			mirrorOption = input.next();
			if (mirrorOption.equalsIgnoreCase("v")) {
				MirroredImagePixel = vMirrorIntImage(actImagePixel);
				break;
			} else if (mirrorOption.equalsIgnoreCase("h")) {
				MirroredImagePixel = hMirrorIntImage(actImagePixel);
				break;
			} else
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
				|| mirrorOption.equalsIgnoreCase("v")) {
			try {// Write Image
				BufferedImage bi = MirroredImage;
				File outputfile = new File("img/" + mirrorOption
						+ "mirrored.png");
				ImageIO.write(bi, "png", outputfile);
			} catch (IOException e) {
			}
			System.out.println("Das Bild wurde erfolgreich "
					+ (mirrorOption.equalsIgnoreCase("h") ? "horizontal"
							: "vertikal") + " gespiegelt!");
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
}
