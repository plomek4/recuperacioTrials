package business.Editions;

import Persistance.Fronts.Csv.CsvEditions;
import Persistance.Fronts.Json.JsonEditions;
import Persistance.SelectedPersistance;
import business.Players.Player;
import presentation.Menus.MenuConductor;

import java.io.IOException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

public class EditionManager {
    private List<Edition> editions;
    private SelectedPersistance persistanceFormat;
    private List<PersistedEdition> persistedEditions;
    public EditionManager(SelectedPersistance format) throws IOException {
        if (format.equals(SelectedPersistance.CSV)){
            persistanceFormat = SelectedPersistance.CSV;
            CsvEditions csvEditions = new CsvEditions();
            editions = csvEditions.getEditions();
            this.persistedEditions = csvEditions.getPersistedEditions();
        } else {
            persistanceFormat = SelectedPersistance.JSON;
            JsonEditions jsonEditions = new JsonEditions();
            editions = jsonEditions.getEditions();
            this.persistedEditions = jsonEditions.getPersistedEditions();
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

    public void writeEditions(){
        if (persistanceFormat.equals(SelectedPersistance.CSV)){
            new CsvEditions().writeEditions(editions);
        } else {
            new JsonEditions().writeEditions(editions);
        }
    }

    public List<Edition> getEditions() {
        return editions;
    }

    public boolean isYearUnique(int trialYear) {
        return this.editions.stream().anyMatch(edition -> edition.getYear() == trialYear);
    }

    public Edition getEdition(int year) {
        for (Edition edition : this.editions) {
            if (edition.getYear() == year) {
                return edition;
            }
        }
        return null;
    }

    public boolean isFinalTrial(Edition edition, int trial) {
        return trial < (edition.getTrials().size() - 1);
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

    public Edition getTrialsFromEdition(int selected) {
        Edition e = getEditions().get(selected);
        return e;
    }

    public void saveEdition(Edition edition, List<String> trials, List<Player> players, int nextTrialIndex) {
        PersistedEdition persistedEdition = new PersistedEdition(edition, trials, players, nextTrialIndex);
        persistedEditions.add(persistedEdition);
    }

    public void updatePersistedEditions(){
        if (persistanceFormat.equals(SelectedPersistance.CSV)){
            new CsvEditions().writePersistedEditions(persistedEditions);
        } else {
            new JsonEditions().writePersistedEditions(persistedEditions);
        }
    }

    public PersistedEdition getCurrentSavedEdition() {
        for(PersistedEdition persistedEdition : persistedEditions) {
            if(persistedEdition.getEdition().getYear() == getEditionYear()) {
                return persistedEdition;
            }
        }

        return null;
    }

    public boolean existsPersistedEdition(int year) {
        return this.persistedEditions.stream().anyMatch(game -> game.getEdition().getYear() == year);
    }

    public int getEditionYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public boolean hasMoreTrials(Edition edition) {
        return edition.getTrials().size() < edition.getTrials().size();
    }
}