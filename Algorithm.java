import java.io.File;
import java.util.Scanner;

public class Algorithm {
    
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
                            Util.getRoadTypeByLabel(Character.toString(str.charAt(j)));

						}
						i++;
					}
				}
			} else {

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
