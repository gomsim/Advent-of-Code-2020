package common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Adventofile{

    BufferedReader reader;
    String nextLine;

    public Adventofile(String file){
        try{
            reader = new BufferedReader(new FileReader("input/" + file));
            nextLine = reader.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public String readLine(){
        String line = "";
        try{
            line = nextLine;
            nextLine = reader.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        return line;
    }
    public boolean hasNext(){
        return nextLine != null;
    }
    public void close(){
        try{
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static List<String> asList(String file){
        Adventofile adventofile = new Adventofile(file);
        List<String> list = new ArrayList<>();

        while(adventofile.hasNext()){
            list.add(adventofile.readLine());
        }
        adventofile.close();

        return list;
    }
    
    public static <E> List<E> asMappedList(String file, Function<String,E> map){
        List<E> list = new ArrayList<>();

        for(String s: asList(file)){
            list.add(map.apply(s));
        }

        return list;
    }

    public static char[][] asCharMatrix(String file){
        List<String> list = asList(file);
        
        char[][] matrix = new char[list.get(0).length()][list.size()];
        for (int y = 0; y < list.size(); y++){
            for (int x = 0; x < list.get(0).length(); x++){
                matrix[x][y] = list.get(y).charAt(x);
            }   
        }

        return matrix;
    }
}