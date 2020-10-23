package com.example.tubes01e;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivityPresenter {

    protected MainActivity ui;
    protected MenuListAdapter menuListAdapter;
    protected List<Menu> menus;

    public MainActivityPresenter(MainActivity activity) {
        this.ui  = activity;
        this.menuListAdapter = new MenuListAdapter(activity);
        this.menus = new ArrayList<>();

        for(int i = 10 ; i>0 ; i--){
            this.menus.add(new Menu("Dummy Food "+i, "dummy tag "+i, true, "dummy recipe "+i));
        }
    }

    public void loadData(){
        Log.d("debug", "loading data...");
        this.ui.updateMenuList(this.menus);
    }

    public MenuListAdapter getMenuListAdapter(){
        return this.menuListAdapter;
    }


    interface MenuListView{
        public void updateMenuList(List<Menu> menus);
        //to be added
    }

}
