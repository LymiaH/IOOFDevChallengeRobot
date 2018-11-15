package lymiah.ioofrobot;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A rudimentary implementation of the IOOF Robot
 */
public class Quick {

    private static final Map<String, Integer> LOOKUP_FACING_ID;
    static {
        HashMap<String, Integer> temp = new HashMap<>();
        temp.put("N", 0);
        temp.put("E", 1);
        temp.put("S", 2);
        temp.put("W", 3);
        LOOKUP_FACING_ID = Collections.unmodifiableMap(temp);
    }

    private static final Map<Integer, String> LOOKUP_FACING_NAME;
    static {
        HashMap<Integer, String> temp = new HashMap<>();
        temp.put(0, "NORTH");
        temp.put(1, "EAST");
        temp.put(2, "SOUTH");
        temp.put(3, "WEST");
        LOOKUP_FACING_NAME = Collections.unmodifiableMap(temp);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int W = 5;
        int H = 5;
        int x = 0;
        int y = 0;
        int facing = 0;
        boolean placed = false;

        while(in.hasNextLine()) {
            String line = in.nextLine();
            String cmd = line;
            String params = "";
            int spaceIndex = line.indexOf(' ');
            if (spaceIndex >= 0) {
                cmd = line.substring(0, spaceIndex);
                params = line.substring(spaceIndex + 1);
            }
            cmd =  cmd.toUpperCase();
            if (!placed && !cmd.equals("PLACE")){
                System.err.println("Ignoring Command: " + line);
                continue;
            }

            int nx = x;
            int ny = y;
            int nfacing = facing;

            switch(cmd) {
                case "PLACE": {
                    placed = true;
                    String[] split = params.split(",");
                    boolean success = true;
                    try {
                        nx = Integer.parseInt(split[0]);
                        ny = Integer.parseInt(split[1]);
                    } catch(NumberFormatException ne) {
                        success = false;
                    }
                    if (!success || split.length != 3) {
                        System.err.println("PLACE expected X,Y,NORTH/SOUTH/EAST/WEST but got: " + params);
                        break;
                    }
                    nfacing = LOOKUP_FACING_ID.get(split[2].substring(0, 1).toUpperCase());
                } break;
                case "MOVE": {
                    switch(facing) {
                        case 0: { // North
                            ny++;
                        } break;
                        case 1: { // East
                            nx++;
                        } break;
                        case 2: { // South
                            ny--;
                        } break;
                        case 3: { // West
                            nx--;
                        } break;
                    }
                } break;
                case "LEFT": {
                    nfacing = (facing - 1 + 4) % 4;
                } break;
                case "RIGHT": {
                    nfacing = (facing + 1) % 4;
                } break;
                case "REPORT": {
                    System.out.println(String.format("%d,%d,%s", x, y, LOOKUP_FACING_NAME.get(facing)));
                } break;
                default: {
                    System.err.println("Unknown Command: " + line);
                }
            }

            // Check if new position is valid
            if(0 <= nx && nx < W && 0 <= ny && ny < H) {
                x = nx;
                y = ny;
                facing = nfacing;
            } else {
                System.err.println(String.format("Invalid Movement to %d,%d caused by: %s", nx, ny, line));
            }
        }
    }
}
