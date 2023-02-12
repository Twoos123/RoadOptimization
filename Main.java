import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.lang.reflect.InaccessibleObjectException;

public class Main {
    
    private static final String SEPARATOR = ",";
    
    private static final String SPACE = " ";

    private static final String SECTIONER = "###";

    private static Roads roadMap[][];

    private static Weather weatherMap[][];

    private static int numRows;
    
    private static int numCollums;

	
    private static int x_startingPoint, y_startingPoint;
    private static int [][] parcelsList;
    private static int parselCounter = 0;

	private static Integer[][] intRoadMap;

    public static void main(String[] args) {
        try {
            statiate(args[0]);
            weatherMap = new Weather[numRows][numCollums];
            roadMap = new Roads[numRows][numCollums];
			intRoadMap = new Integer[numRows][numCollums];
            populate(args[0]);

			parcelCounter();

			populateParcelList();

			populateIntArray();

			startCord();
			
			File myObj = new File("output.txt");
			myObj.createNewFile();

			List<Integer> path = new ArrayList<>();

			path.add(10);
			path.add(1);
			path.add(10);
			path.add(1);

			for (int i = 0; i < parcelsList.length; i++) {
				if (i == 0) {

				} else if (i == parcelsList.length - 1) {

				} else {

				}
				//path = Labirinth.solve();
			}

			int time = calculateTime(path);
			double fuel = calculateFuel(path);

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

			myWriter.write("\n" + SECTIONER + "\n\n");
			
			for (int i = 0; i < path.size(); i++) {
				if (i < path.size()) {
					myWriter.write(Integer.toString(path.get(i)));
					myWriter.write(", ");
				} else {
					myWriter.write(Integer.toString(path.get(i)));
				}
			}

			myWriter.write("\n" + SECTIONER + "\n\n");
			
			myWriter.write(Integer.toString(time));
			myWriter.write(", ");
			myWriter.write(Double.toString(fuel));

      		myWriter.close();

        } catch(Exception a) {
            a.printStackTrace();
        }
    }

	private static void populateIntArray() {
		for (int i = 0; i < roadMap.length; i++) {
			for (int j = 0; j < roadMap[i].length; j++) {
				intRoadMap[i][j] = Util.getRoadNumberFromLabel(roadMap[i][j].type);
			}
		}
	}

	private static int calculateTime(List<Integer> path) {
		int out = 1;
		for (int i = 0; i < path.size(); i++) {
			out += out * path.get(i);
		}
		return out;
	}

	private static double calculateFuel(List<Integer> path) {
		int time = calculateTime(path);

		return (time / 3600) * 74.65;
	}

    private static void parcelCounter(){
        for (int i = 0; i<roadMap.length; i++){
            for (int j = 0; j<roadMap[i].length; j++){
                if (roadMap[i][j].type == RoadType.Parcel){
                    parselCounter++;
                }
            }
        }
        parcelsList = new int[parselCounter][2];
    }


    private static void populateParcelList() {
        int x = 0;
        for (int i = 0; i<roadMap.length; i++){
            for (int j = 0; j<roadMap[i].length; j++){
                if (roadMap[i][j].type == RoadType.Parcel){
                    parcelsList[x][0] = i;
                    parcelsList[x][1] = j;
                }
        	}
    	}
    }

    public static void startCord() {
        for (int i = 0; i < roadMap.length; i++){
            for (int j = 0; j< roadMap[i].length; j++){
                if (roadMap[i][j].type == RoadType.Central)
                    x_startingPoint = i;
                    y_startingPoint = j;
            }
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
