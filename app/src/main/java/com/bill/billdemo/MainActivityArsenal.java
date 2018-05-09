package com.bill.billdemo;

import android.os.Bundle;

import com.arsenal.bill.ArsenalBaseActivity;

public class MainActivityArsenal extends ArsenalBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected boolean enableSwipeBack() {
        return false;
    }
}
