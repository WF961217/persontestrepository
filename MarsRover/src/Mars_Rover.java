import java.util.ArrayList;
import java.util.Scanner;

public class Mars_Rover {
    private String cmd;
    private String cmdre;
    private ArrayList<Integer> GPS;

    public String getCmdre() {
        return cmdre;
    }

    public void setCmdre(String cmdre) {
        this.cmdre = cmdre;
    }

    public ArrayList<Integer> getGPS() {
        return GPS;
    }

    public void setGPS(ArrayList<Integer> GPS) {
        this.GPS = GPS;
    }

    public Mars_Rover(String cmd) {
        this.cmd = cmd;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public static String[] splitcmd(String s) {
        String[] b = s.split(" ");
        Scanner in = new Scanner(System.in);
        return b;
    }

    public static ArrayList<Integer> getwide(String[] m) {
        ArrayList<Integer> result = new ArrayList<>();
        String re_roll = null;
        for (int i = 0; i < m.length; i++) {
            if (m[i].equals("-wide")) {
                re_roll = m[i + 1];
                String[] a = re_roll.split(",");
                for (int i1 = 0; i1 < a.length; i1++) {
                    result.add(Integer.valueOf(a[i1]));
                }
            }
        }
        return result;
    }

    public static ArrayList<Integer> getgps(String[] m) {
        ArrayList<Integer> result = new ArrayList<>();
        String re_roll = null;
        for (int i = 0; i < m.length; i++) {
            if (m[i].equals("-gps")) {
                re_roll = m[i + 1];
                String[] a = re_roll.split(",");
                for (int i1 = 0; i1 < a.length; i1++) {
                    result.add(Integer.valueOf(a[i1]));
                }
            }
        }
        return result;
    }

    public static String getdire(String[] m) {
        String dir = null;
        for (int i = 0; i < m.length; i++) {
            if (m[i].equals("-dire")) {
                dir = m[i + 1];
            }
        }
        return dir;
    }

    public static String getmove(String[] m) {
        String dir = null;
        for (int i = 0; i < m.length; i++) {
            if (m[i].equals("-move")) {
                dir = m[i + 1];
            }
        }
        return dir;
    }

    public static String getturn(String[] m) {
        String dir = null;
        for (int i = 0; i < m.length; i++) {
            if (m[i].equals("-turn")) {
                dir = m[i + 1];
            }
        }
        return dir;
    }

    public static String[][] creatmap(ArrayList<Integer> list) {
        int X = list.get(0);
        int Y = list.get(1);
        String[][] map = new String[X][Y];
        return map;
    }

    public static int getdist(String[] m) {
        String portinfo = null;
        int portnum = -1;
        for (int i = 0; i < m.length; i++) {
            if (m[i].equals("-dist")) {
                portinfo = m[i + 1];
                portnum = Integer.parseInt(portinfo);
            }
        }
        return portnum;
    }

    public static String turn(String[] cmdre) {
        ArrayList<String> direarray = new ArrayList<String>() {{
            add("n");
            add("w");
            add("s");
            add("d");
        }};
        int num = direarray.indexOf(getdire(cmdre));
        String nextdir;
        String curdire = getturn(cmdre);
        if (curdire.equals("l")) {
            int numchange = (num + 5) % 4;
            nextdir = direarray.get(numchange);
        } else if (curdire.equals("r")) {
            int numchange = (num + 3) % 4;
            nextdir = direarray.get(numchange);
        } else {
            nextdir = direarray.get(num);
        }
        return nextdir;
    }


    public static ArrayList<Integer> move(String[] cmdre) {
        ArrayList<Integer> curgps = getgps(cmdre);
        String forwardback = getmove(cmdre);
        String direct = turn(cmdre);
        int dis = getdist(cmdre);
        if (forwardback.equals("f")) {
            if (direct.equals("n")) {
                curgps.set(1, curgps.get(1) + dis);
            } else if (direct.equals("s")) {
                curgps.set(1, curgps.get(1) - dis);
            } else if (direct.equals("w")) {
                curgps.set(0, curgps.get(0) - dis);
            } else if (direct.equals("e")) {
                curgps.set(0, curgps.get(0) + dis);
            }
        } else if (forwardback.equals("b")) {
            if (direct.equals("n")) {
                curgps.set(1, curgps.get(1) - dis);
            } else if (direct.equals("s")) {
                curgps.set(1, curgps.get(1) + dis);
            } else if (direct.equals("w")) {
                curgps.set(0, curgps.get(0) + dis);
            } else if (direct.equals("e")) {
                curgps.set(0, curgps.get(0) - dis);
            }
        }
        curgps = overwide(curgps,cmdre);
        return curgps;
    }


    public static void report(String[] cmdre) {
        ArrayList<Integer> GPS = move(cmdre);
        String dierect = turn(cmdre);
        System.out.println("GPS:"+GPS);
        System.out.println("dierect:"+dierect);
    }


    public static ArrayList<Integer> overwide(ArrayList<Integer> gpscal,String[] cmdre) {
        ArrayList<Integer> widerange = getwide(cmdre);
        int X = widerange.get(0);
        int Y = widerange.get(1);
        if(gpscal.get(0)>X||gpscal.get(0)<0){
            gpscal.set(0,(gpscal.get(0)+X)%X);
        }else if (gpscal.get(1)>Y||gpscal.get(1)<0){
            gpscal.set(1,(gpscal.get(1)+Y)%Y);
        }
        return gpscal;
    }
}
