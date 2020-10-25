package com.example.tubes01e;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.tubes01e.databinding.ActivityMainBinding;
import com.example.tubes01e.databinding.FragmentMenuListBinding;
import com.example.tubes01e.databinding.FragmentRandomSelectMenuBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends AppCompatActivity implements FragmentListener, MainActivityPresenter.MenuListView {
    private DrawerLayout mainDrawer;
    private MainFragment mainFragment;
    private RandomSearchFragment randomSearchFragment;
    private MenuListFragment menuListFragment;
    private SettingFragment settingFragment;
    private AddMenuFragment addMenuFragment;
    protected MenuDetailFragment menuDetailFragment;
    protected MenuEditFragment menuEditFragment;
    protected RandomSelectMenuFragment randomSelectMenuFragment;
    protected ArrayList<Fragment> fragments;

    private Toolbar toolbar;
    private FragmentManager fragmentManager;
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        this.fragments = new ArrayList<>();

        this.mainFragment = new MainFragment();
        this.fragments.add(mainFragment);

        this.randomSearchFragment = new RandomSearchFragment();
        this.fragments.add(randomSearchFragment);

        this.menuListFragment = new MenuListFragment();
        this.fragments.add(this.menuListFragment);

        this.settingFragment = new SettingFragment();
        this.fragments.add(this.settingFragment);

        this.addMenuFragment = new AddMenuFragment();
        this.fragments.add(this.addMenuFragment);

        this.menuDetailFragment = new MenuDetailFragment();
        this.fragments.add(this.menuDetailFragment);

        this.menuEditFragment  = new MenuEditFragment();
        this.fragments.add(this.menuEditFragment);

        this.randomSelectMenuFragment = new RandomSelectMenuFragment();
        this.fragments.add(this.randomSelectMenuFragment);

        this.presenter = new MainActivityPresenter(this);

        this.mainDrawer = binding.drawerLayout;
        this.toolbar = binding.toolbar;
        this.setSupportActionBar(toolbar);
        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, mainDrawer, toolbar, R.string.open_drawer, R.string.close_drawer);
        this.mainDrawer.addDrawerListener(abdt);
        abdt.syncState();

        this.fragmentManager = this.getSupportFragmentManager();

        setContentView(binding.getRoot());

        this.presenter.loadData();
        this.changePage(FragmentType.FRAGMENT_HOME);
    }

    public MainActivityPresenter getPresenter() {
        return this.presenter;
    }

    @Override
    public void changePage(FragmentType page) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.slide_out);

        Fragment selectedFragment;

        switch (page) {
            case FRAGMENT_HOME:
                selectedFragment = this.mainFragment;
                break;
            case FRAGMENT_RANDOM_SEARCH:
                selectedFragment = this.randomSearchFragment;
                break;
            case FRAGMENT_SETTING:
                selectedFragment = this.settingFragment;
                break;
            case FRAGMENT_MENU_LIST:
                selectedFragment = this.menuListFragment;
                break;
            case FRAGMENT_ADD_MENU:
                selectedFragment = this.addMenuFragment;
                break;
            case FRAGMENT_MENU_DETAIL:
                selectedFragment = this.menuDetailFragment;
                break;
            case FRAGMENT_EDIT_MENU:
                selectedFragment = this.menuEditFragment;
                break;
            case FRAGMENT_RANDOM_SELECT:
                selectedFragment = this.randomSelectMenuFragment;
                break;
            default:
                selectedFragment = this.mainFragment;
        }

        Log.d("debug", "fr.getclass.getName:" + selectedFragment.getClass().getName());

        if (selectedFragment.isAdded()) {
            ft.show(selectedFragment);
        } else {
            ft.add(R.id.fragment_container, selectedFragment);
        }

        Iterator<Fragment> iterator = this.fragments.iterator();
        while (iterator.hasNext()) {
            Fragment fragment = iterator.next();
//            Log.d("debug", "loop getclass.getName:"+fragment.getClass().getName());
//            Log.d("debug", "loop compare getclass.getName:"+ (fragment.getClass().getSimpleName().equals(selectedFragment.getClass().getSimpleName())+"\n"));

            if (fragment.getClass().getName().equals(selectedFragment.getClass().getName()))
                continue;
            if (fragment.isAdded()) {
                ft.hide(fragment);
            }
        }

        ft.commit();

        //ref: https://www.11zon.com/zon/android/onclick-event-in-navigation-drawer.php
        this.mainDrawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void closeApplication() {
        this.moveTaskToBack(true);
        this.finish();
    }

    public void updateMenuList(List<Menu> menus) {
        this.presenter.getMenuListAdapter().setMenuList(menus);
    }
    public boolean addMenu(String name, String description, String tag, boolean hasRecipe, String recipe){
        return this.presenter.addMenu( name,  description, tag,  hasRecipe,  recipe);
    }

    public boolean editMenu(String id, String name, String description, String tag, boolean hasRecipe, String recipe){
        return this.presenter.editMenu( id, name,  description, tag,  hasRecipe,  recipe);
    }

    public boolean deleteMenu(String id){
        return this.presenter.deleteMenu(id);
    }

    public Menu randomSelectMenu(){
        return this.presenter.randomSelectMenu();
    }

    public void setMenuListAdapter(ListView listView){
        listView.setAdapter(this.presenter.getMenuListAdapter());
    }

    public void destroyFragment(Fragment fragment){
        this.fragments.remove(fragment);
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }
}