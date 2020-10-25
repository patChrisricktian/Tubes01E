package com.example.tubes01e;

import android.widget.ListView;

public interface FragmentListener {
    void changePage(FragmentType type);
    public boolean addMenu(String name, String description, String tag, boolean hasRecipe, String recipe);
    public boolean editMenu(String id, String name, String description, String tag, boolean hasRecipe, String recipe);
    public void setMenuListAdapter(ListView list);
    void closeApplication();
}
