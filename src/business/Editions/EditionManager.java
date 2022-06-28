package business.Editions;

import Persistance.Fronts.Csv.CsvEditions;
import Persistance.Fronts.Json.JsonEditions;
import Persistance.SelectedPersistance;
import business.Trials.Trial;
import presentation.Menus.MenuComposer;
import presentation.Menus.MenuConductor;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

public class EditionManager {
    private List<Edition> editions;
    private List<AvailableEditions> availableEditions;


    public EditionManager(SelectedPersistance format) throws IOException {
        if (format.equals(SelectedPersistance.CSV)){
            CsvEditions csvEditions = new CsvEditions();
            editions = csvEditions.getEditions();

        } else {
            JsonEditions jsonEditions = new JsonEditions();
            editions = jsonEditions.getEditions();
        }
        this.setEditions();


    }
    private void setEditions() {
        this.editions.sort(Comparator.comparing(Edition::getYear));
    }

    public void addEdition(Edition edition) {
        this.editions.add(edition);
        setEditions();
    }

    public List<Edition> getEditions() {
        return editions;
    }

    public boolean isYearUnique(int trialYear) {
        return this.editions.stream().anyMatch(edition -> edition.getYear() == trialYear);
    }



    public boolean areAvailableEditions(int year) {
        return this.availableEditions.stream().anyMatch(i -> i.getEdition().getYear() == year);
    }

    public AvailableEditions getAvailabeEditions(){
        for(AvailableEditions e : this.availableEditions) {
            if(e.getEdition().getYear() == new MenuConductor().getYear()){
                return e;
            }
        }
        return null;
    }


    public boolean areEditions(int year) {
        return this.editions.stream().anyMatch(i -> i.getYear() == year);
    }

    public Edition getYearEdition(){
        for (Edition e : editions){
            if (e.getYear() == new MenuConductor().getYear()){
                return e;
            }
        }
        return null;
    }

    public void deleteEdition(int s) {
        editions.remove(s);
        setEditions();
    }
}