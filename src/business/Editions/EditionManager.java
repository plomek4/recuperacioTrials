package business.Editions;

import Persistance.Fronts.Csv.CsvEditions;
import Persistance.Fronts.Json.JsonEditions;
import Persistance.SelectedPersistance;
import business.Trials.Trial;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

public class EditionManager {
    private List<Edition> editions;

    public EditionManager(SelectedPersistance format) {
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
}
