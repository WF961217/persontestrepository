import java.util.ArrayList;

public class Mars_main {
    public static void main(String[] args) {
        Mars_Rover car = new Mars_Rover("-wide 100,80 -gps 25,10 -dire w -move b -turn l -dist 90");
        String cmd = car.getCmd();
        String[] cmdan = car.splitcmd(cmd);
        ArrayList<Integer> gps = Mars_Rover.move(cmdan);
        Mars_Rover.report(cmdan);
        System.out.println(gps);
    }
}
