package Persistance.Fronts.Csv;

import business.Trials.Trial;
import business.Trials.Types.*;
import presentation.Menus.MenuMain;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

                                //TODO : leer trials + pasarlas a CSV + escribirlas
public class CsvTrials {
    public List<Trial> getTrials() {
        List<Trial> trialist = new LinkedList<>();

        for (Types t : Types.values()){
            String path = "./files/csv/trials/" + t.toString().toLowerCase() + ".csv";

            try{
                String c = new String(Files.readAllBytes(Path.of(path)));

                for (String row : c.split(System.lineSeparator())){
                    if (!row.isEmpty()){
                        switch (t){
                            case paper_publication -> {
                                int i = 1, acceptation = 0, revision = 0, refuse = 0;;
                                String trialName = "", journalName = "", quartile = "";
                                for (String camp : row.split(",")) {
                                    switch (i) {
                                        case 1 -> trialName = camp;
                                        case 2 -> journalName = camp;
                                        case 3 -> quartile = camp;
                                        case 4 -> acceptation = Integer.parseInt(camp);
                                        case 5 -> revision = Integer.parseInt(camp);
                                        case 6 -> refuse = Integer.parseInt(camp);
                                        default -> {}
                                    }
                                    i++;
                                }
                                trialist.add(new PaperPublication(trialName, journalName, quartile, acceptation, revision, refuse));
                            }
                            case master_studies -> {
                                String trialName = "", masterName = "";
                                int i = 1, ects = 0, probability = 0;

                                for (String camp : row.split(",")) {
                                    switch (i) {
                                        case 1 -> trialName = camp;
                                        case 2 -> masterName = camp;
                                        case 3 -> ects = Integer.parseInt(camp);
                                        case 4 -> probability = Integer.parseInt(camp);
                                        default -> {}
                                    }
                                    i++;
                                }
                                trialist.add(new MasterStudies(trialName, masterName, ects, probability));

                            }
                            case doctoral_thesis -> {
                                int i = 1, defenseDifficulty = 0;
                                String trialName = "", studyField = "";

                                for (String camp : row.split(",")) {
                                    switch (i) {
                                        case 1 -> trialName = camp;
                                        case 2 -> studyField = camp;
                                        case 3 -> defenseDifficulty = Integer.parseInt(camp);
                                        default -> {}
                                    }
                                    i++;
                                }
                                trialist.add(new DoctoralThesis(trialName, studyField, defenseDifficulty));
                            }
                            case budget_request -> {
                                int budget = 0, i = 1;
                                String trialName = "", entityName = "";

                                for (String camp : row.split(",")) {
                                    switch (i) {
                                        case 1 -> trialName = camp;
                                        case 2 -> entityName = camp;
                                        case 3 -> budget = Integer.parseInt(camp);
                                        default -> {}
                                    }
                                    i++;
                                }
                                trialist.add(new BudgetRequest(trialName, entityName, budget));

                            }
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                new MenuMain().showMessage("File not found");
            }
        }
        return trialist;
    }

    private String csvConverter(Trial trial, Types types){
        StringBuilder trialCsv = new StringBuilder();
        trialCsv.append(trial.getName());

        switch (types){
            case paper_publication -> {
                trialCsv.append(",").append(((PaperPublication) trial).getJournalName());
                trialCsv.append(",").append(((PaperPublication) trial).getQuartile());
                trialCsv.append(",").append(((PaperPublication) trial).getAcceptationProbability());
                trialCsv.append(",").append(((PaperPublication) trial).getRevisionProbability());
                trialCsv.append(",").append(((PaperPublication) trial).getRefuseProbability());
            }
            case master_studies -> {
                trialCsv.append(",").append(((MasterStudies) trial).getMasterName());
                trialCsv.append(",").append(((MasterStudies) trial).getCreditsQuantity());
                trialCsv.append(",").append(((MasterStudies) trial).getApproveCreditProbability());
            }
            case doctoral_thesis -> {
                trialCsv.append(",").append(((DoctoralThesis) trial).getStudyField());
                trialCsv.append(",").append(((DoctoralThesis) trial).getDefenseDifficulty());
            }
            case budget_request -> {
                trialCsv.append(",").append(((BudgetRequest) trial).getEntityName());
                trialCsv.append(",").append(((BudgetRequest) trial).getBudgetQuantity());
            }
        }
        return trialCsv.toString();
    }

    public void writeTrials(List<Trial> trials, Types types) {
        StringBuilder subject = new StringBuilder();

        for(Trial t : trials) {
            subject.append(this.csvConverter(t, types)).append(System.lineSeparator());
        }

        try {
            FileWriter fileWriter = new FileWriter("./files/csv/trials/" + types.toString().toLowerCase() + ".csv");

            fileWriter.write(subject.toString());
            fileWriter.close();

            new MenuMain().showMessage("File updated");

        }catch (Exception e){
            new MenuMain().showMessage("File not found");
        }
    }
}