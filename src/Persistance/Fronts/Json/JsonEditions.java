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
