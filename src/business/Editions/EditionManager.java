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

    /**
     * Setter of the Editions
     */
    private void setEditions() {
        this.editions.sort(Comparator.comparing(Edition::getYear));
    }

    /**
     * Function that adds an edition to the edition list
     * @param edition a single edition
     */
    public void addEdition(Edition edition) {
        this.editions.add(edition);
        setEditions();
    }

    /**
     * Function that calls the persistance to write the editions
     */
    public void writeEditions(){
        if (persistanceFormat.equals(SelectedPersistance.CSV)){
            new CsvEditions().writeEditions(editions);
        } else {
            new JsonEditions().writeEditions(editions);
        }
    }

    /**
     * Getter of the edition list
     * @return the list of editions
     */
    public List<Edition> getEditions() {
        return editions;
    }

    /**
     * Function that checks if the edition's year is unique
     * @param trialYear a year
     */
    public boolean isYearUnique(int trialYear) {
        return this.editions.stream().anyMatch(edition -> edition.getYear() == trialYear);
    }

    /**
     * Function that get the edition
     * @param year wanted edition's year
     * @return an edition
     */
    public Edition getEdition(int year) {
        for (Edition edition : this.editions) {
            if (edition.getYear() == year) {
                return edition;
            }
        }
        return null;
    }

    /**
     * Function that checks if the edition's trial is the last one
     * @param edition the edition to check
     * @param trial the trial in question
     * @return the trial
     */
    public boolean isFinalTrial(Edition edition, int trial) {
        return trial < (edition.getTrials().size() - 1);
    }

    /**
     * Function that deletes and edition from the edition list
     * @param s the index
     */
    public void deleteEdition(int s) {
        editions.remove(s);
        setEditions();
    }


    public Edition getTrialsFromEdition(int selected) {
        return getEditions().get(selected);
    }

    /**
     * Function that saves int the persistance an edition to continue the execution after
     * @param edition the edition to save
     * @param trials the trials of the edition
     * @param players the edition's players
     * @param nextTrialIndex the index that determines wich trial is next
     */
    public void saveEdition(Edition edition, List<String> trials, List<Player> players, int nextTrialIndex) {
        PersistedEdition persistedEdition = new PersistedEdition(edition, trials, players, nextTrialIndex);
        persistedEditions.add(persistedEdition);
    }

    /**
     * Function that calls the persistance to write those saved editions
     */
    public void updatePersistedEditions(){
        if (persistanceFormat.equals(SelectedPersistance.CSV)){
            new CsvEditions().writePersistedEditions(persistedEditions);
        } else {
            new JsonEditions().writePersistedEditions(persistedEditions);
        }
    }

    /**
     * Function that gets the already started current edition to continue the execution after
     * @return the edition
     */
    public PersistedEdition getCurrentSavedEdition() {
        for(PersistedEdition persistedEdition : persistedEditions) {
            if(persistedEdition.getEdition().getYear() == getEditionYear()) {
                return persistedEdition;
            }
        }
        return null;
    }

    /**
     * Function used to check if there are existent saved editions
     * @param year
     * @return
     */
    public boolean existsPersistedEdition(int year) {
        return this.persistedEditions.stream().anyMatch(game -> game.getEdition().getYear() == year);
    }

    /**
     * Getter for the actual year using library: Calendar
     * @return the year
     */
    public int getEditionYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
}