package com.example.tubes01e;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivityPresenter {

    protected MainActivity ui;
    protected MenuListAdapter menuListAdapter;
    protected List<Menu> menus;
    protected MenuStorage menuStorageHandler;

    public MainActivityPresenter(MainActivity activity) {
        this.ui = activity;
        this.menuListAdapter = new MenuListAdapter(activity);
        this.menus = new ArrayList<>();
        this.menuStorageHandler = new MenuStorage(activity);
        this.menus = menuStorageHandler.getAllMenu();
    }

    public void loadData() {
        Log.d("debug", "loading data...");
            this.ui.updateMenuList(this.menus);
    }

    public boolean addMenu(String name, String description, String tag, boolean hasRecipe, String recipe){
        boolean isSuccess = this.menuStorageHandler.writeWithIntegrityCheck(name, description, tag, hasRecipe, recipe);
        if(isSuccess){
            this.menus = this.menuStorageHandler.getAllMenu();
            this.ui.updateMenuList(menus);
        }
        return isSuccess;
    }

    public boolean editMenu(String id, String name, String description, String tag, boolean hasRecipe, String recipe){
        boolean isSuccess = this.menuStorageHandler.editWithIntegrityCheck(id, name, description, tag, hasRecipe, recipe);
        if(isSuccess){
            this.menus = this.menuStorageHandler.getAllMenu();
            this.ui.updateMenuList(menus);
        }
        return isSuccess;
    }

    public MenuListAdapter getMenuListAdapter() {
        return this.menuListAdapter;
    }


    interface MenuListView {
        public void updateMenuList(List<Menu> menus);
        //to be added
    }

}
