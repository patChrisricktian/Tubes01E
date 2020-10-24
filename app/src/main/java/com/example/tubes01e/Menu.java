package com.example.tubes01e;

public class Menu {
    protected String id;
    protected String name;
    protected String description;
    protected String tag;
    protected boolean hasRecipe;
    protected String recipe;

    public Menu(String id,String name, String description, String tag, boolean hasRecipe, String recipe){
        this.id = id;
        this.name = name;
        this.description = description;
        this.tag = tag;
        this.hasRecipe = hasRecipe;

        if(hasRecipe){
            this.recipe = recipe;
        }else{
            this.recipe = "";
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
    public String getId(){
        return this.id;
    }

    public String getRecipe() {
        return recipe;
    }
    public String getDescription(){return this.description;}

    public String toString(){
        return "[ id: "+this.id+", name: "+this.name+" ]";
    }
}
