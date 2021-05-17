package indianpoker.indianpoker;

import org.bukkit.Location;

public class Utility {

    public static int getRotateValue(Location l){
        int degrees = (Math.round(l.getYaw()) + 270) % 360;
        if (degrees <= 22) return 4;
        if (degrees <= 45) return 5;
        if (degrees <= 67) return 6;
        if (degrees <= 90) return 7;
        if (degrees <= 112) return 8;
        if (degrees <= 135) return 9;
        if (degrees <= 157) return 10;
        if (degrees <= 180) return 11;
        if (degrees <= 202) return 12;
        if (degrees <= 225) return 13;
        if (degrees <= 247) return 14;
        if (degrees <= 270) return 15;
        if (degrees <= 292) return 0;
        if (degrees <= 315) return 1;
        if (degrees <= 337) return 2;
        if (degrees <= 359) return 3;
        return 0;
    }

}
