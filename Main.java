import java.io.File;
import java.util.Scanner;

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
            statiate(args[1]);
            weatherMap = new Weather[numRows][numCollums];
            roadMap = new Roads[numRows][numCollums];
            populate(args[1]);

			for (int i = 0; i < weatherMap.length; i++) {
				for (int j = 0; j < weatherMap[i].length; j++) {
					System.out.print(Util.getWeatherLabelByType(weatherMap[i][j]));
				}
			}
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
						for (int j = 0; j < str.length() - 1; j++) {
                            Roads road = new Roads(Character.toString(str.charAt(j)));
							roadMap[i][j] = road;
						}
						i++;
					}
				}
			} else {
				if (str.length() > 0) {
					for (int j = 0; j < str.length() - 1; j++) {
						weatherMap[i][j] = Util.getWeatherTypeByLabel(Character.toString(str.charAt(j)));
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
