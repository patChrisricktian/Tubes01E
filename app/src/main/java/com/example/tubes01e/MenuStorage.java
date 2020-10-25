package com.example.tubes01e;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//Reference:
// https://medium.com/@nayantala259/android-how-to-read-and-write-parse-data-from-json-file-226f821e957a
// https://stackoverflow.com/questions/3024002/how-to-create-a-folder-in-java
// https://howtodoinjava.com/java/io/how-to-check-if-file-exists-in-java/
// https://stackoverflow.com/questions/189094/how-to-scan-a-folder-in-java
public class MenuStorage {
    private final static String FOOD_DIR = "//food";
    private static int FOOD_ID_COUNTER = 1;

    private Context context;

    public MenuStorage(Context context) {
        this.context = context;

    }


    public boolean writeMenu(Menu menu, int counter) {
        boolean isSuccess = false;
        if (counter < 2) {
            File parentDir = new File(this.context.getFilesDir() + FOOD_DIR);
            try {

                if (parentDir.isDirectory()) {
                    File fileToBeWrittenInto = new File(parentDir + "//" + menu.getId() + ".json");
                    JSONObject object = new JSONObject();
                    object.put("id", menu.getId());
                    object.put("name", menu.getName());
                    object.put("description", menu.getDescription());
                    object.put("tags", menu.getTag());
                    object.put("hasRecipe", String.format("%s", menu.hasRecipe()));
                    object.put("recipe", menu.getRecipe());

                    String jsonString = object.toString();

                    FileWriter fw = new FileWriter(fileToBeWrittenInto);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(jsonString);
                    bw.close();
                    isSuccess = true;
                } else {

                    Log.d("debug", "Directory not exist");
                    Log.d("debug", "Making directory:" + parentDir.getAbsolutePath() + " directory.");
                    if (parentDir.mkdir()) {
                        isSuccess = writeMenu(menu, counter);
                    } else {
                        Log.d("debug", "Failed to make directory: " + parentDir.getAbsolutePath());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    public boolean writeMenu(File file, Menu menu, int counter) {
        boolean isSuccess = false;
        if (counter < 2) {
            File fileToBeWrittenInto = file;
            File parentDir = new File(fileToBeWrittenInto.getParent());
            try {
                if (parentDir.isDirectory()) {
                    JSONObject object = new JSONObject();
                    object.put("id", menu.getId());
                    object.put("name", menu.getName());
                    object.put("description", menu.getDescription());
                    object.put("tags", menu.getTag());
                    object.put("hasRecipe", String.format("%s", menu.hasRecipe()));
                    object.put("recipe", menu.getRecipe());

                    String jsonString = object.toString();

                    FileWriter fw = new FileWriter(fileToBeWrittenInto);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(jsonString);
                    bw.close();
                    isSuccess = true;
                } else {

                    Log.d("debug", "Directory not exist");
                    Log.d("debug", "Making directory:" + parentDir.getAbsolutePath() + " directory.");
                    if (parentDir.mkdir()) {
                        isSuccess = writeMenu(menu, counter);
                    } else {
                        Log.d("debug", "Failed to make directory: " + parentDir.getAbsolutePath());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    public Menu getMenu(String id) {
        File parentDir = new File(this.context.getFilesDir() + FOOD_DIR);

        if (parentDir.isDirectory()) {
            File fileToBeReadFrom = new File(parentDir, id + ".json");
            try {
                if (fileToBeReadFrom.exists()) {
                    FileReader fileReader = new FileReader(fileToBeReadFrom);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    StringBuilder builder = new StringBuilder();
                    String line = bufferedReader.readLine();

                    while (line != null) {
                        builder.append(line).append("\n");
                        line = bufferedReader.readLine();
                    }
                    bufferedReader.close();

                    String response = builder.toString();
                    JSONObject jsonObject = new JSONObject(response);

                    String menuID = jsonObject.get("id").toString();
                    String nama = jsonObject.get("name").toString();
                    String description = jsonObject.get("description").toString();
                    String tags = jsonObject.get("tags").toString();
                    String hasRecipeStr = jsonObject.get("hasRecipe").toString();
                    String recipe = jsonObject.get("recipe").toString();

                    boolean hasRecipe = false;
                    if (hasRecipeStr.equals("true")) {
                        hasRecipe = true;
                    }

                    Menu menu = new Menu(menuID, nama, description, tags, hasRecipe, recipe);
                    return menu;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Menu getMenu(File file) {
        try {
            if (file.exists()) {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringBuilder builder = new StringBuilder();
                String line = bufferedReader.readLine();

                while (line != null) {
                    builder.append(line).append("\n");
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();

                String response = builder.toString();
                JSONObject jsonObject = new JSONObject(response);

                String menuID = jsonObject.get("id").toString();
                String nama = jsonObject.get("name").toString();
                String description = jsonObject.get("description").toString();
                String tags = jsonObject.get("tags").toString();
                String hasRecipeStr = jsonObject.get("hasRecipe").toString();
                String recipe = jsonObject.get("recipe").toString();

                boolean hasRecipe = false;
                if (hasRecipeStr.equals("true")) {
                    hasRecipe = true;
                }

                Menu menu = new Menu(menuID, nama, description, tags, hasRecipe, recipe);
                return menu;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Menu> getAllMenu() {
        ArrayList<Menu> list = new ArrayList<>();
        ;
        File parentDir = new File(this.context.getFilesDir() + FOOD_DIR);

        if (parentDir.isDirectory()) {

            File[] children = parentDir.listFiles();

            if (children != null) {
                for (File childFile : children) {
                    if (!childFile.isDirectory()) {
                        Menu menu = getMenu(childFile);
                        if (menu != null) {
                            Log.d("debug", menu.toString());
                        } else {
                            Log.d("debug", "null");
                        }
                        list.add(menu);
                    }
                }
            }
        } else {
            Log.d("debug", "No menu exist.");
        }
        return list;
    }

    public boolean writeWithIntegrityCheck(String name, String description, String tag, boolean hasRecipe, String recipe) {
        boolean isGood = false;
        Date currentTime = Calendar.getInstance().getTime();
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        if (name.length() > 0 && description.length() > 0 && tag.length() > 0) {
            boolean recipeReqPassed = true;
            Log.d("debug", "recipe length:" + recipe.length() + ", recipe: " + recipe);

            String id = "food-" + FOOD_ID_COUNTER + "-" + month + "-" + year + "-" + c.get(Calendar.HOUR) + "-" + c.get(Calendar.MINUTE);
            FOOD_ID_COUNTER = FOOD_ID_COUNTER + 1;
            Menu menu = new Menu(id, name, description, tag, hasRecipe, recipe);
            isGood = this.writeMenu(menu, 0);
        }

        return isGood;
    }

    public boolean editWithIntegrityCheck(String id, String name, String description, String tag, boolean hasRecipe, String recipe) {
        boolean isGood = false;
        File contextFilesDir = this.context.getFilesDir();
        File file = new File(contextFilesDir, "//" + FOOD_DIR + "//" + id + ".json");
        if (name.length() > 0 && description.length() > 0 && tag.length() > 0) {
            boolean recipeReqPassed = true;
            Log.d("debug", "recipe length:" + recipe.length() + ", recipe: " + recipe);
            Menu menu = new Menu(id, name, description, tag, hasRecipe, recipe);
            isGood = this.writeMenu(file, menu, 0);
        }
        return isGood;
    }

    public boolean deleteMenu(String id) {
        boolean isSuccess = false;
        File parentDir = new File(this.context.getFilesDir() + FOOD_DIR);
        try {
            if (parentDir.isDirectory()) {
                File fileToBeDeleted = new File(parentDir + "//" + id + ".json");
                if (fileToBeDeleted.exists()) {
                    if (fileToBeDeleted.delete()) {
                        isSuccess = true;
                    }
                }
            } else {
                Log.d("debug", "Directory not exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    public void writeInitialMenuToInternalStorage(MenuPreferences menuPreferences) {
        boolean isLoaded = menuPreferences.isLoaded();
        Log.d("debug", String.format("%s", isLoaded));
        if (!isLoaded) {
            try {
                String[] lists = context.getAssets().list("menuJSON");
                for (String fileName : lists) {
                    String json = "";
                    Log.v("names", fileName);

                    InputStream is = context.getAssets().open("menuJSON/"+fileName);
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    json = new String(buffer, "UTF-8");

                    JSONObject jsonObject = new JSONObject(json);

                    String nama = jsonObject.get("name").toString();
                    String description = jsonObject.get("description").toString();
                    String tags = jsonObject.get("tags").toString();
                    String hasRecipeStr = jsonObject.get("hasRecipe").toString();
                    String recipe = jsonObject.get("recipe").toString();

                    boolean hasRecipe = false;
                    if (hasRecipeStr.equals("true")) {
                        hasRecipe = true;
                    }
                    writeWithIntegrityCheck(nama, description, tags, hasRecipe, recipe);
                }

                menuPreferences.loadInitialMenu();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void loadJSONFromAsset(File file) {

        if (file != null) {
            try {
                if (file.exists()) {
                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    StringBuilder builder = new StringBuilder();
                    String line = bufferedReader.readLine();

                    while (line != null) {
                        builder.append(line).append("\n");
                        line = bufferedReader.readLine();
                    }
                    bufferedReader.close();

                    String response = builder.toString();
                    JSONObject jsonObject = new JSONObject(response);

                    String nama = jsonObject.get("name").toString();
                    String description = jsonObject.get("description").toString();
                    String tags = jsonObject.get("tags").toString();
                    String hasRecipeStr = jsonObject.get("hasRecipe").toString();
                    String recipe = jsonObject.get("recipe").toString();

                    boolean hasRecipe = false;
                    if (hasRecipeStr.equals("true")) {
                        hasRecipe = true;
                    }
                    writeWithIntegrityCheck(nama, description, tags, hasRecipe, recipe);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

