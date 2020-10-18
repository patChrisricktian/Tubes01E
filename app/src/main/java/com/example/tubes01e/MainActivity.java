package com.example.tubes01e;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.tubes01e.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements FragmentListener {
    private DrawerLayout mainDrawer;
    private MainFragment mainFragment;
    private RandomSearchFragment randomSearchFragment;
    private MenuListFragment menuListFragment;
    private SettingFragment settingFragment;
    private Toolbar toolbar;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

        this.mainFragment = new MainFragment();
        this.randomSearchFragment = new RandomSearchFragment();
        this.menuListFragment = new MenuListFragment();
        this.settingFragment = new SettingFragment();


        this.mainDrawer = binding.drawerLayout;

        this.toolbar = binding.toolbar;
        this.setSupportActionBar(toolbar);

        this.fragmentManager = this.getSupportFragmentManager();

        ActionBarDrawerToggle abdt  = new ActionBarDrawerToggle(this, mainDrawer, toolbar, R.string.open_drawer, R.string.close_drawer);
        this.mainDrawer.addDrawerListener(abdt);
        abdt.syncState();
        setContentView(binding.getRoot());

        this.changePage(1);
    }

    @Override
    public void changePage(int page) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.slide_out);
        if( page == 1){
            if(this.mainFragment.isAdded()){
                ft.show(this.mainFragment);
            }else{
                ft.add(R.id.fragment_container, this.mainFragment);
            }

            if(this.randomSearchFragment.isAdded()){
                ft.hide(this.randomSearchFragment);
            }

            if(this.menuListFragment.isAdded()){
                ft.hide(this.menuListFragment);
            }

            if(this.settingFragment.isAdded()){
                ft.hide(this.settingFragment);
            }

        }else if( page == 2){
            if(this.randomSearchFragment.isAdded()){
                ft.show(this.randomSearchFragment);
            }else{
                ft.add(R.id.fragment_container, this.randomSearchFragment);
            }

            if(this.mainFragment.isAdded()){
                ft.hide(this.mainFragment);
            }

            if(this.menuListFragment.isAdded()){
                ft.hide(this.menuListFragment);
            }

            if(this.settingFragment.isAdded()){
                ft.hide(this.settingFragment);
            }
        }else if( page == 3){
            if(this.menuListFragment.isAdded()){
                ft.show(this.menuListFragment);
            }else{
                ft.add(R.id.fragment_container, this.menuListFragment);
            }

            if(this.mainFragment.isAdded()){
                ft.hide(this.mainFragment);
            }

            if(this.randomSearchFragment.isAdded()){
                ft.hide(this.randomSearchFragment);
            }

            if(this.settingFragment.isAdded()){
                ft.hide(this.settingFragment);
            }
        }else if( page == 4){
            if(this.settingFragment.isAdded()){
                ft.show(this.settingFragment);
            }else{
                ft.add(R.id.fragment_container, this.settingFragment);
            }

            if(this.mainFragment.isAdded()){
                ft.hide(this.mainFragment);
            }

            if(this.menuListFragment.isAdded()){
                ft.hide(this.menuListFragment);
            }

            if(this.randomSearchFragment.isAdded()){
                ft.hide(this.randomSearchFragment);
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
}