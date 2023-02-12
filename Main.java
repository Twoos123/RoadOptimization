import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;

public class Main {
    
    private static final String SEPARATOR = ",";
    
    private static final String SPACE = " ";

    private static final String SECTIONER = "###";

    private static Roads roadMap[][];

    private static Weather weatherMap[][];

    private static int numRows;
    
    private static int numCollums;

    public static void main(String[] args) {
        try {
            statiate(args[0]);
            weatherMap = new Weather[numRows][numCollums];
            roadMap = new Roads[numRows][numCollums];
            populate(args[0]);

			for (int i = 0; i < weatherMap.length; i++) {
				for (int j = 0; j < weatherMap[i].length; j++) {
					System.out.print(Util.getWeatherLabelByType(weatherMap[i][j]));
				}
				System.out.println();
			}
			for (int i = 0; i < roadMap.length; i++) {
				for (int j = 0; j < roadMap[i].length; j++) {
					System.out.print(Util.getRoadLabelByType(roadMap[i][j].type));
				}
				System.out.println();
			}

			File myObj = new File("output.txt");
			myObj.createNewFile();

			FileWriter myWriter = new FileWriter("filename.txt");

			for (int i = 0; i < roadMap.length; i++) {
				for (int j = 0; j < roadMap[i].length; j++) {
					if (j < roadMap[i].length - 1) {
						myWriter.write(Util.getRoadLabelByType(roadMap[i][j].type));
						myWriter.write(", ");
					} else {
						myWriter.write(Util.getRoadLabelByType(roadMap[i][j].type));
					}
				}
				myWriter.write("\n");
			}

			myWriter.write("\n" + SECTIONER + "\n\n");

			for (int i = 0; i < weatherMap.length; i++) {
				for (int j = 0; j < weatherMap[i].length; j++) {
					if (j < weatherMap[i].length - 1) {
						myWriter.write(Util.getWeatherLabelByType(weatherMap[i][j]));
						myWriter.write(", ");
					} else {
						myWriter.write(Util.getWeatherLabelByType(weatherMap[i][j]));
					}
				}
				myWriter.write("\n");
			}
      		myWriter.close();

        } catch(Exception a) {
            System.out.println(a);
        }
    }

    private static void populate(String file) throws Exception {
        Scanner scanner = new Scanner(new File(file));

		int i = 0;
		boolean before = true;
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			str = str.replace(SPACE, "");
			str = str.replace(SEPARATOR, "");
			if (before) {
				if (str.length() > 0) {
					if (str.charAt(0) == '#') {
						before = false;
						i = 0;
					}
					else {
						for (int j = 0; j < str.length(); j++) {
							Roads road = new Roads(str.substring(j, j + 1));
							roadMap[i][j] = road;
						}
						i++;
					}
				}
			} else {
				if (str.length() > 0) {
					for (int j = 0; j < str.length(); j++) {
						weatherMap[i][j] = Util.getWeatherTypeByLabel(str.substring(j, j + 1));
					}
					i++;
				}
			}
		}
    }
    
    private static void statiate(String file) throws Exception {
        Scanner scanner = new Scanner(new File(file));

		boolean before = true;
		numRows = 0;
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			str = str.replace(SPACE, "");
			str = str.replace(SEPARATOR, "");
			if (str.length() > 0) {
				if (str.charAt(0) == '#') {
					before = false;
				}
				else {
					if (before) {
						numCollums = str.length();
						numRows++;
					}
				}
			}
		}

		scanner.close();
    }
}
