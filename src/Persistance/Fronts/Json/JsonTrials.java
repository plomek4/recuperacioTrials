package Persistance.Fronts.Json;

import business.Trials.Trial;
import business.Trials.Types.*;
import com.google.gson.*;
import presentation.Menus.MenuMain;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class JsonTrials {
    private Gson gson;

    public JsonTrials() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public List<Trial> getTrials() {
        List<Trial> trialist = new LinkedList<>();

        for (Types types : Types.values()){
            String path = "./files/json/trials/" + types.toString().toLowerCase() + ".json";

            try {
                String c = new String(Files.readAllBytes(Path.of(path)));
                JsonArray jsonArray = JsonParser.parseString(c).getAsJsonArray();

                for (JsonElement jsonElement : jsonArray) {
                    switch (types) {
                        case PAPER_PUBLICATION -> trialist.add(gson.fromJson(jsonElement, PaperPublication.class));
                        case MASTER_STUDIES -> trialist.add(gson.fromJson(jsonElement, MasterStudies.class));
                        case DOCTORAL_THESIS_DEFENSE -> trialist.add(gson.fromJson(jsonElement, DoctoralThesisDefense.class));
                        case BUDGET_REQUEST -> trialist.add(gson.fromJson(jsonElement, BudgetRequest.class));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                new MenuMain().showMessage("File not found");
            }
        }

        return trialist;
    }
}
