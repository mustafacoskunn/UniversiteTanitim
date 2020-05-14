package com.tanitim.universitetanitim.Adapters;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.tanitim.universitetanitim.Models.Universiteler;

import java.util.ArrayList;
import java.util.List;

public class DetayViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList=new ArrayList<>();
    List<String> titleList=new ArrayList<>();
    Universiteler muniversite;


    public DetayViewPagerAdapter(FragmentManager fm) {
        super(fm);


    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment,String titleFragment ){
        fragmentList.add(fragment);
        titleList.add(titleFragment);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
