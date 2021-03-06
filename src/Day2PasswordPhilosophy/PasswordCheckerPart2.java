package Day2PasswordPhilosophy;

import common.Adventofile;
import java.util.List;


public class PasswordCheckerPart2{

    public static void main(String[] args){
        List<String> list = Adventofile.asList("day2.txt");
        System.out.println(numValidPasswords(list) + " of " + list.size() + " passwords were correct!");
    }

    private static int numValidPasswords(List<String> list){
        final int MIN = 0, MAX = 1, CHAR = 0, NUMS_TOK = 0, CHAR_TOK = 1, PSWRD_TOK = 2;
        int counter = 0;

        for (String line: list){
            String[] tokens = line.split(" ");
            String[] numToks = tokens[NUMS_TOK].split("-");
            int minChar = Integer.parseInt(numToks[MIN]);
            int maxChar = Integer.parseInt(numToks[MAX]);
            char ruleChar = tokens[CHAR_TOK].charAt(CHAR);
            String password = tokens[PSWRD_TOK];

            if (valid(password, ruleChar, minChar, maxChar))
                counter++;
        }
        return counter;
    }

    private static boolean valid(String password, char ruleChar, int first, int second){
        return password.charAt(first - 1) == ruleChar ^ password.charAt(second - 1) == ruleChar;
    }
}