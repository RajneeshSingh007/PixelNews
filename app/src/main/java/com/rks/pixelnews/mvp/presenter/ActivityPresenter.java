package com.rks.pixelnews.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Coolalien on 12/5/2016.
 */

public abstract class ActivityPresenter extends AppCompatActivity  {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        logic();
        ui();
    }

    /**
     * setLayout
     * @return
     */
    protected abstract int setLayout();

    /**
     * Logic
     */
    protected abstract void logic();


    /**
     * Ui
     */
    protected abstract void ui();


    /**
     * fragment instance
     * @return
     */
    protected abstract Fragment instancefragments();

    /**
     * fragment id for container
     * @return
     */
    protected abstract int fragmentid();

    /**
     * get fragment and id from instancefragments and fragmentid;
     * load fragment;
     * @param fragment
     * @param id
     */
    public void loaderFragment(Fragment fragment, int id){
        getSupportFragmentManager().beginTransaction().replace(id,fragment).addToBackStack(null).commit();
    }

}
