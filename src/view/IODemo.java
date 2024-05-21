package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IODemo {
    public static void main(String[] args) throws IOException {
        String inputfile = "src/view/input.txt";
        Path inputpath = Paths.get(inputfile);
        List<String> lines = new ArrayList<>();


        for(String l:lines){
            System.out.println(l);
        }
    }
}
