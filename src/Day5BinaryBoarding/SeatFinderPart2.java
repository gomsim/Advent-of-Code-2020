package Day5BinaryBoarding;

import common.Adventofile;
import java.util.List;

public class SeatFinderPart2 {

    private static final char FRONT = 'F', BACK = 'B', RIGHT = 'R', LEFT = 'L';
    private static final int NUM_ROWS = 128, NUM_COLS = 8; 

    public static void main(String[] args){
        List<String> list = Adventofile.asList("day5.txt");
        boolean[][] overview = getPlaneOverview(list);
        int mySeatID = findMySeatID(overview);
        System.out.println("Your seatID is " + mySeatID);
    }

    private static boolean[][] getPlaneOverview(List<String> list){
        boolean[][] map = new boolean[NUM_COLS][NUM_ROWS];

        for (String line: list){
            int row = findIndex(NUM_ROWS, line.substring(0, 7));
            int col = findIndex(NUM_COLS, line.substring(7));
            map[col][row] = true;
        }                                                                                                          

        return map;
    }

    private static int findIndex(int span, String instruction){
        int jumpDistance = span/2;
        int i = jumpDistance;
        int counter = instruction.length();

        for (char c: instruction.toCharArray()){
            counter--;
            jumpDistance /= 2;
            if (c == FRONT || c == LEFT)
                i -= jumpDistance == 0? 1:jumpDistance;
            else if ((c == BACK || c == RIGHT) && counter > 0)
                i += jumpDistance;
        }

        return i;
    }

    private static int findMySeatID(boolean[][] overview){
        for(int row = 0; row < overview[0].length; row++){
            for(int col = 0; col < overview.length; col++){
                if (emptySeat(col, row, overview)){
                    int thisID = getSeatID(row, col);
                    int pIdRow = getSeatRow(thisID - 1);
                    int pIdCol = getSeatCol(thisID - 1, pIdRow);
                    int nIdRow = getSeatRow(thisID + 1);
                    int nIdCol = getSeatCol(thisID + 1, nIdRow);
                    if (!(emptySeat(pIdCol, pIdRow, overview) || emptySeat(nIdCol, nIdRow, overview)))
                        return thisID;
                }
            }
        }
        return -1;
    }

    private static boolean emptySeat(int col, int row, boolean[][] map){
        return 
        col < 0 || col > map.length || 
        row < 0 || row > map[0].length ||
        !map[col][row];
    }

    private static int getSeatID(int row, int column){
        return row * 8 + column;
    }
    private static int getSeatRow(int id){
        return id / 8;
    }
    private static int getSeatCol(int id, int row){
        return id - (row * 8);
    }    
}
