package com.rks.pixelnews.mvp.presenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Coolalien on 12/5/2016.
 */

public abstract class FragmentPresenter extends Fragment {

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
    protected abstract void ui(View view);

    /**
     * fragment instances
     * @return
     */
    protected abstract Fragment instancesfragments();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(setLayout(), container, false);
        ui(view);
        logic();
        return view;
    }
}
