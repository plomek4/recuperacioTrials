package Persistance.Fronts.Csv;

import business.Editions.Edition;
import business.Editions.PersistedEdition;
import business.Players.Player;
import presentation.Menus.MenuMain;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class CsvEditions {

    public List<Edition> getEditions() {
        List<Edition> editionList = new LinkedList<>();

        String path = "./files/csv/editions/editions.csv";

        try {
            String c = new String(Files.readAllBytes(Path.of(path)));
            editionList = getList(c);
        }catch (Exception e){
            new MenuMain().showMessage("File not found");
        }

        return editionList;
    }

    private List<Edition> getList(String c) {
        List<Edition> editions = new LinkedList<>();

        for (String s : c.split(System.lineSeparator())){
            if (!s.isEmpty()){
                int year = 0, playersNum = 0, trialsNum = 0, i=0;
                List<String> trials = new LinkedList<>();

                for (String camp : s.split(",")) {
                    if (i < 3){
                        switch (i){
                            case 0 -> year = Integer.parseInt(camp);
                            case 1 -> playersNum = Integer.parseInt(camp);
                            case 2 -> trialsNum = Integer.parseInt(camp);
                        }
                    }else {
                        trials.add(camp);
                    }
                    i++;
                }
                Edition e = new Edition(year, playersNum, trialsNum, trials);
                editions.add(e);
            }
        }
        return editions;
    }


    public void writeEditions(List<Edition> editions) {
        StringBuilder subject = new StringBuilder();

        for (Edition e : editions) {
            subject.append(this.csvConverter(e)).append(System.lineSeparator());
        }
        try {
            FileWriter fileWriter = new FileWriter("./files/csv/editions/editions.csv");
            fileWriter.write(subject.toString());
            fileWriter.close();

            new MenuMain().showMessage("File updated");
        }catch (Exception e){
            new MenuMain().showMessage("File not found");
        }
    }

    private String csvConverter(Edition edition){
        StringBuilder editionCsv = new StringBuilder();

        editionCsv.append(edition.getYear());
        editionCsv.append(",").append(edition.getPlayersQuantity());
        editionCsv.append(",").append(edition.getTrials().size());

        for (String trial : edition.getTrials()){
            editionCsv.append(",").append(trial);
        }

        return editionCsv.toString();
    }





    public List<PersistedEdition> getPersistedEditions(){
        List<PersistedEdition> persistedEditionList = new LinkedList<>();

        String path = "./files/csv/editions/persistedEditions.csv";

        try {
            String c = new String(Files.readAllBytes(Path.of(path)));
            int i = 1,j = 0,players = 0;

            Edition e = null;
            List<Player> playersList = new LinkedList<>();

            for (String row : c.split(System.lineSeparator())){
                if (!row.isEmpty()){
                    if (i<2){
                        e = getList(row).get(0);
                    }else{
                        if (players < e.getPlayersQuantity()) {
                            String name = "";
                            String role = "";
                            int campNum = 0, investigationPoints = 0;

                            for (String camp : row.split(",")){
                                switch (campNum) {
                                    case 0 -> name = camp;
                                    case 1 -> investigationPoints = Integer.parseInt(camp);
                                    case 2 -> role = camp;
                                }
                                campNum++;
                            }
                            switch (role) {
                                case "Engineer" -> playersList.add(new Player(name, investigationPoints, "Engineer"));
                                case "Master" -> playersList.add(new Player(name, investigationPoints, "Master"));
                                case "Doctor" -> playersList.add(new Player(name, investigationPoints, "Doctor"));
                            }
                            players++;
                        }else {
                            j = Integer.parseInt(row);
                            i=0;
                            persistedEditionList.add(new PersistedEdition(e, e.getTrials(), playersList, j));

                        }
                    }
                    i++;
                }
            }

        }catch (Exception e){
            new MenuMain().showMessage("File not found");
        }

        return persistedEditionList;
    }



    private String persistedEditionsCsvConvert(PersistedEdition edition){
        StringBuilder persistedEditionCsv = new StringBuilder();

        persistedEditionCsv.append(csvConverter(edition.getEdition()));

        for (Player player : edition.getPlayers()) {
            persistedEditionCsv.append(",").append(player.getName()).append(",").append(player.getInvestigationPoints()).append(",").append(player.getRole());
        }


        persistedEditionCsv.append(edition.getNextTrialIndex());

        return persistedEditionCsv.toString();
    }



    public void writePersistedEditions(List<PersistedEdition> persistedEditions) {
        StringBuilder subject = new StringBuilder();

        for (PersistedEdition e : persistedEditions) {
            subject.append(this.persistedEditionsCsvConvert(e)).append(System.lineSeparator());
        }
        try {
            FileWriter fileWriter = new FileWriter("./files/csv/editions/persistedEditions.csv");
            fileWriter.write(subject.toString());
            fileWriter.close();

            new MenuMain().showMessage("File updated");
        }catch (Exception e){
            new MenuMain().showMessage("File not found");
        }

    }
}