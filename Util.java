public final class Util {

    public static String getRoadLabelByType(RoadType in) {
        String out = null;

        if (in == RoadType.Road) {
            out = "R";
        } else if (in == RoadType.Central) {
            out = "C";
        } else if (in == RoadType.Nothing) {
            out = "N";
        } else if (in == RoadType.Parcel) {
            out = "P";
        }

        return out;
    }

    public static RoadType getRoadTypeByLabel(String in) {
        RoadType out = null;

        if (in == "R") {
            out = RoadType.Road;
        } else if (in == "C") {
            out = RoadType.Central;
        } else if (in == "N") {
            out = RoadType.Nothing;
        } else if (in == "P") {
            out = RoadType.Parcel;
        }

        return out;
    }
    
    public static String getWeatherLabelByType(Weather in) {
        String out = null;

        if (in == Weather.Clear) {
            out = "C";
        } else if (in == Weather.Ice) {
            out = "I";
        } else if (in == Weather.Rainy) {
            out = "R";
        } else if (in == Weather.Snow) {
            out = "S";
        }

        return out;
    }

    public static Weather getWeatherTypeByLabel(String in) {
        Weather out = null;

        if (in == "C") {
            out = Weather.Clear;
        } else if (in == "I") {
            out = Weather.Ice;
        } else if (in == "R") {
            out = Weather.Rainy;
        } else if (in == "s") {
            out = Weather.Snow;
        }

        return out;
    }

    public static double getWeatherWeight(Weather in) {
        double out = 0;

        if (in == Weather.Clear) {
            out = 0.25;
        } else if (in == Weather.Ice) {
            out = 1.0;
        } else if (in == Weather.Rainy) {
            out = 0.5;
        } else if (in == Weather.Snow) {
            out = 0.75;
        }

        return out;
    }
}
