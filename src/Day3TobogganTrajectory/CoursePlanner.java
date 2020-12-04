package Day3TobogganTrajectory;

import common.Adventofile;

public class CoursePlanner {
    
    private static final char TREE = '#';

    public static void main(String[] args){
        char[][] map = Adventofile.asCharMatrix("day3.txt");
        
        long numTrees = 
        checkDirection(1, 1, map) *
        checkDirection(3, 1, map) *
        checkDirection(5, 1, map) *
        checkDirection(7, 1, map) *
        checkDirection(1, 2, map);

        System.out.println("You will encounter " + numTrees + " trees on your way down.");
    }

    /**
     * Doesn't check the starting position
     */
    private static long checkDirection(int dirX, int dirY, char[][] map){
        int x = 0, y = 0;
        int numTrees = 0;

        do{
            x += dirX;
            y += dirY;
            if (get(x, y, map) == TREE)
                numTrees++;
        }while(y + dirY < map[0].length);

        return numTrees;
    }

    private static char get(int x, int y, char[][] map){
        return map[x%map.length][y];
    }
}
