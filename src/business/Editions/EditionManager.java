package business.Editions;

import Persistance.SelectedPersistance;
import business.Editions.Edition;
import business.Trials.Trial;

import java.util.Calendar;
import java.util.List;

public class EditionManager {
    private List<Edition> editions;

    public EditionManager(SelectedPersistance format) {
        if (format.equals(SelectedPersistance.CSV)){

        }else{

        }
    }

    public int getCurrentEditionYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public List<Edition> getEditions() {
        return editions;
    }
}
