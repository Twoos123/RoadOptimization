public class Roads {
    public RoadType type;

    public double traffic;

    Roads(String type) {
        if (type == "N") {
            this.type = RoadType.Nothing;
            this.traffic = 0;
        } else {
            this.type = Util.getRoadTypeByLabel(type);
            this.traffic = 1;
        }
    }

    Roads(String type, double traffic) {
        if (type == "N") {
            this.type = RoadType.Nothing;
            this.traffic = 0;
        } else {
            this.type = Util.getRoadTypeByLabel(type);
            this.traffic = traffic;
        }
    }
}
