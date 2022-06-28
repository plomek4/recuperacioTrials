package Persistance.Fronts.Json;

import business.Editions.Edition;
import business.Trials.Trial;
import business.Trials.Types.*;
import com.google.gson.*;
import presentation.Menus.MenuMain;

import java.io.FileWriter;
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
                        case paper_publication -> trialist.add(gson.fromJson(jsonElement, PaperPublication.class));
                        case master_studies -> trialist.add(gson.fromJson(jsonElement, MasterStudies.class));
                        case doctoral_thesis -> trialist.add(gson.fromJson(jsonElement, DoctoralThesis.class));
                        case budget_request -> trialist.add(gson.fromJson(jsonElement, BudgetRequest.class));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                new MenuMain().showMessage("File not found");
            }
        }
        return trialist;
    }

    public void writeTrials(List<Trial> trialList, Types types) {
        String path = "./files/json/trials/" + types.toString().toLowerCase() + ".json";
        try {
            FileWriter fileWriter = new FileWriter(path, false);

            this.gson.toJson(trialList, fileWriter);

            fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
            new MenuMain().showMessage("File not found");
        }
    }

}