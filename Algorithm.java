import java.io.File;
import java.util.Scanner;

public class Algorithm {
    
    private static final String SEPARATOR = ",";
    
    private static final String SPACE = " ";

    private static final String SECTIONER = "###";

    public static void main(String[] args) {
        try {
            populate(args[1]);
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
                            
						}
						i++;
					}
				}
			} else {

			}
		}
    }
}
