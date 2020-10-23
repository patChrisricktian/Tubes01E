package com.example.tubes01e;

public class Menu {
    protected String name;
    protected String tag;
    protected boolean hasRecipe;
    protected String recipe;

    public Menu(String name, String tag, boolean hasRecipe, String recipe){
        this.name = name;
        this.tag = tag;
        this.hasRecipe = hasRecipe;

        if(hasRecipe){
            this.recipe = recipe;
        }
    }

    public String getName(){
        return this.name;
    }

    public String getTag(){
        return this.tag;
    }

    public boolean hasRecipe(){
        return this.hasRecipe;
    }

    public String getRecipe() {
        return recipe;
    }
}
