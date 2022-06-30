package Persistance.Fronts.Json;

import business.Editions.Edition;
import business.Editions.PersistedEdition;
import com.google.gson.*;
import presentation.Menus.MenuMain;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;


import java.awt.*;

public class JsonEditions {
    private Gson gson;

    public JsonEditions() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Function that reads the "./files/json/editions/editions.json" file and saves it to a list
     * @return the list
     */
    public List<Edition> getEditions() {
        List<Edition> editionList = new LinkedList<>();

            String path = "./files/json/editions/editions.json";

            try {
                String c = new String(Files.readAllBytes(Path.of(path)));
                JsonArray jsonArray = JsonParser.parseString(c).getAsJsonArray();

                for (JsonElement jsonElement : jsonArray) {
                    editionList.add(gson.fromJson(jsonElement, Edition.class));
                }
            } catch (Exception e) {
                e.printStackTrace();
                new MenuMain().showMessage("File not found");
            }

        return editionList;
    }

    /**
     * Function that writes an edition list to the json file
     * @param editionList the edition list
     */
    public void writeEditions(List<Edition> editionList) {
        String path = "./files/json/editions/editions.json";
        try {
            FileWriter fileWriter = new FileWriter(path, false);

            this.gson.toJson(editionList, fileWriter);

            fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
            new MenuMain().showMessage("File not found");
        }
    }

    /**
     * Function that reads the "./files/json/editions/persistedEditions.json" file and saves it to a list
     * @return the list
     */
    public List<PersistedEdition> getPersistedEditions() {
        List<PersistedEdition> persistedEditions = new LinkedList<>();

        String path = "./files/json/editions/persistedEditions.json";

        try {
            String c = new String(Files.readAllBytes(Path.of(path)));
            JsonArray jsonArray = JsonParser.parseString(c).getAsJsonArray();

            for (JsonElement jsonElement : jsonArray) {
                persistedEditions.add(gson.fromJson(jsonElement, PersistedEdition.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            new MenuMain().showMessage("File not found");
        }

        return persistedEditions;
    }

    /**
     * Function that writes a persisted edition list to the json file
     * @param persistedEditions the persisted edition list
     */
    public void writePersistedEditions(List<PersistedEdition> persistedEditions) {
        String path = "./files/json/editions/persistedEditions.json";
        try {
            FileWriter fileWriter = new FileWriter(path, false);

            this.gson.toJson(persistedEditions, fileWriter);

            fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
            new MenuMain().showMessage("File not found");
        }
    }

}
