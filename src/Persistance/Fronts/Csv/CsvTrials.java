package Persistance.Fronts.Csv;

import business.Trials.Trial;
import business.Trials.Types.Types;
import presentation.Menus.MenuMain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class CsvTrials {
    public List<Trial> getTrials() {
        List<Trial> trialist = new LinkedList<>();

        for (Types t : Types.values()){
            String path = "./files/csv/trials/\" + types.toString().toLowerCase() + \".csv";

            try{
                String c = new String(Files.readAllBytes(Path.of(path)));


            } catch (IOException e) {
                e.printStackTrace();
                new MenuMain().showMessage("File not found");
            }
        }
        return trialist;
    }

    public void writeTrials(List<Trial> typeOfTrials, Types types) {

    }
}