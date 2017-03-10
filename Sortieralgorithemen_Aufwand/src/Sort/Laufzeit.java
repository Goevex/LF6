package Sort;

public class Laufzeit {
	public static long getDuration(long startTime, long endTime) {
		return endTime - startTime;
	}

	public static long getAverageDuration(long startTime[], long endTime[]) {
		long averageDuration = 0;
		try {
			for (int i = 0; i < endTime.length; i++) {
				averageDuration = averageDuration + (endTime[i] - startTime[i]);
			}
		} catch (Exception e) {
			System.out.println("Es ist ein Fehler aufgetreten " + e.toString());
		}
		return averageDuration / (long) endTime.length;
	}

	public static String calcAufwandsklasse(int[] n, long[] duration) {
		String aufwandsklasse = "Bei der Berechnung der Aufwandsklasse ist ein Fehler aufgetreten.";
		if (n.length == duration.length) {
			aufwandsklasse = "Aufwandsklasse konnte nicht ermittelt werden.";
			boolean[][] weighArray = new boolean[5][n.length - 1];
			for (int i = 1; i < n.length; i++) {
				double tmpN = (double) n[i];
				double tmpNPrior = (double) n[i - 1];
				double tmpDuration = (double) duration[i];
				double tmpDurationPrior = (double) duration[i - 1];
				weighArray[0][i - 1] = (tmpN == tmpNPrior && tmpDuration == tmpDuration);// konstant
				weighArray[1][i - 1] = (tmpN / tmpDuration == tmpNPrior
						/ tmpDuration); // proportional
				weighArray[2][i - 1] = (Math.pow(tmpN, 2) / tmpDuration == Math
						.pow(tmpNPrior, 2) / tmpDuration);// duration
															// proportional zu
															// n^2
				weighArray[3][i - 1] = ((double) (int) (tmpN / 2) / tmpDuration == (double) (int) (tmpNPrior / 2)
						/ tmpDurationPrior);// duration proportional zur anzahl,
											// in der sich n durch 2 teilen
											// lässt
				weighArray[4][i - 1] = (tmpN * Math.log(tmpN) / tmpDuration == tmpNPrior
						* Math.log(tmpNPrior) / tmpDurationPrior);// duration
																	// ist
				// proprtional zu n
				// * log(2)
			}
			for (int j = 0; j <= 4; j++) {
				int tmpCountTrue = 0;
				int tmpCountFalse = 0;
				for (int i = 0; i < weighArray.length; i++) {
					if (weighArray[j][i] == true)
						tmpCountTrue++;
					else if (weighArray[j][i] == false)
						tmpCountFalse++;
				}
				if (tmpCountTrue > tmpCountFalse)
					if (aufwandsklasse
							.equals("Bei der Berechnung der Aufwandsklasse ist ein Fehler aufgetreten.")
							|| aufwandsklasse
									.equals("Aufwandsklasse konnte nicht ermittelt werden.")) {
						aufwandsklasse = "";
						switch (j) {
						case 0:
							aufwandsklasse = "O(1)";
							break;
						case 1:
							aufwandsklasse = "O(N)";
							break;
						case 2:
							aufwandsklasse = "O(N^2)";
							break;
						case 3:
							aufwandsklasse = "O(log2(N))";
							break;
						case 4:
							aufwandsklasse = "O(N*log2(N))";
							break;
						}
					} else {
						switch (j) {
						case 0:
							aufwandsklasse = aufwandsklasse + " ODER O(1)";
							break;
						case 1:
							aufwandsklasse = aufwandsklasse + " ODER O(N)";
							break;
						case 2:
							aufwandsklasse = aufwandsklasse + " ODER O(N^2)";
							break;
						case 3:
							aufwandsklasse = aufwandsklasse
									+ " ODER O(log2(N))";
							break;
						case 4:
							aufwandsklasse = "ODER O(N*log2(N))";
							break;
						}
					}
			}
		}
		return aufwandsklasse;
	}
}
