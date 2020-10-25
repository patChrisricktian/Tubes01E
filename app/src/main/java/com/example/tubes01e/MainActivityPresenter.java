package com.example.tubes01e;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivityPresenter {

    protected MainActivity ui;
    protected MenuListAdapter menuListAdapter;
    protected List<Menu> menus;
    protected MenuStorage menuStorageHandler;
    protected MenuPreferences menuPreferences;

    public MainActivityPresenter(MainActivity activity) {
        this.ui = activity;
        this.menuListAdapter = new MenuListAdapter(activity);
        this.menus = new ArrayList<>();
        this.menuStorageHandler = new MenuStorage(activity);

        this.menuPreferences = new MenuPreferences(activity);
    }

    public void loadData() {
        Log.d("debug", "loading data...");
        this.menuStorageHandler.writeInitialMenuToInternalStorage(this.menuPreferences);
        this.menus = menuStorageHandler.getAllMenu();
        Log.d("debug", ""+this.menuPreferences.isLoaded());
        this.ui.updateMenuList(this.menus);
    }

    public boolean addMenu(String name, String description, String tag, boolean hasRecipe, String recipe) {
        boolean isSuccess = this.menuStorageHandler.writeWithIntegrityCheck(name, description, tag, hasRecipe, recipe);
        if (isSuccess) {
            this.menus = this.menuStorageHandler.getAllMenu();
            this.ui.updateMenuList(menus);
        }
        return isSuccess;
    }

    public boolean editMenu(String id, String name, String description, String tag, boolean hasRecipe, String recipe) {
        boolean isSuccess = this.menuStorageHandler.editWithIntegrityCheck(id, name, description, tag, hasRecipe, recipe);
        if (isSuccess) {
            this.menus = this.menuStorageHandler.getAllMenu();
            this.ui.updateMenuList(menus);
        }
        return isSuccess;
    }

    public boolean deleteMenu(String id) {
        boolean isSuccess = this.menuStorageHandler.deleteMenu(id);
        if (isSuccess) {
            this.menus = this.menuStorageHandler.getAllMenu();
            this.ui.updateMenuList(menus);
        }
        return isSuccess;
    }

    public Menu randomSelectMenu() {
        return RandomSelectMenu.select(this.menus);
    }

    public MenuListAdapter getMenuListAdapter() {
        return this.menuListAdapter;
    }


    interface MenuListView {
        public void updateMenuList(List<Menu> menus);
        //to be added
    }

}
