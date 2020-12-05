package Day1ReportRepair;

import common.Adventofile;
import java.util.List;

public class ReportReparatorPart2{

    private static final int GOAL = 2020;

    public static void main(String[] args){
        List<Integer> list = Adventofile.asMappedList("day1.txt", Integer::parseInt);
        for (int i = 0; i < list.size(); i++){
            for (int j = 0; j < list.size(); j++){
                for (int k = 0; k < list.size(); k++){
                    if ((list.get(i) + list.get(j) + list.get(k)) == GOAL){
                        System.out.println(list.get(i) * list.get(j) * list.get(k));
                        return;
                    }
                }
            }   
        }
    }
}