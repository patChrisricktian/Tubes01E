package com.example.tubes01e;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class MenuListAdapter extends BaseAdapter {
    private MainActivity activity;
    private List<Menu> menuList;
    public MenuListAdapter(MainActivity activity){
        this.activity = activity;
        this.menuList = new ArrayList<>();
    }

    public void setMenuList(List<Menu> list){
        this.menuList.clear();
        for(Menu menu : list){
            this.menuList.add(menu);
        }
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.menuList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.menuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(this.activity).inflate(R.layout.menu_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

            viewHolder.foodImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle args = new Bundle();
                    Menu menu = menuList.get(position);
                    args.putString("id", menu.getId());
                    args.putString("name", menu.getName());
                    args.putString("description", menu.getDescription());
                    args.putString("tags", menu.getTag());
                    args.putBoolean("hasRecipe", menu.hasRecipe());
                    args.putString("recipe", menu.getRecipe());

                    activity.fragments.remove(activity.menuDetailFragment);
                    activity.menuDetailFragment = MenuDetailFragment.newInstance(args);
                    activity.fragments.add(activity.menuDetailFragment);
                    activity.changePage(FragmentType.FRAGMENT_MENU_DETAIL);
                }
            });

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(this.menuList.size() > 0){
            viewHolder.updateView((Menu) this.menuList.get(position));
        }

        return convertView;
    }

    protected class ViewHolder{
        protected TextView tvFoodName;
        protected ImageView foodImage;
        protected int position;

        public ViewHolder(View view){
            this.tvFoodName = view.findViewById(R.id.tv_food_name);
            this.foodImage = view.findViewById(R.id.iv_food);
        }

        public void updateView(Menu menu){
            //To be implemented
            this.tvFoodName.setText(menu.getName());
        }
    }
}
