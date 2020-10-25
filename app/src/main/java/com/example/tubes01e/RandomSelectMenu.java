package com.example.tubes01e;

import java.util.List;
import java.util.Random;

public class RandomSelectMenu {

    public static Menu select(List<Menu> menus){
        Random random = new Random();
        Menu menu = menus.get(random.nextInt(menus.size()));
        return menu;
    }
}
