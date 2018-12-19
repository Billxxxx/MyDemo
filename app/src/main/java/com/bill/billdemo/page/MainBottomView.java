package com.bill.billdemo.page;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arsenal.bill.activity.IClickRefresh;
import com.bill.billdemo.R;

public class MainBottomView extends RelativeLayout {
    View cover;
    boolean selected = false;
    public ImageView imageView;
    public View readPoint;
    public TextView textView;
    private int unSelectIconID, selectedIconID, stringID, selectedTextColorID, unSelectTextColorID;
    int index;
    public Fragment fragment;

    public MainBottomView(Context context) {
        super(context);
        initView();
    }

    public MainBottomView(Context context, int stringID, int index, int unSelectIcon, int selectedIcon, int selectedTextColorID, int unSelectTextColorID, Fragment fragment) {
        super(context);
        this.stringID = stringID;
        this.index = index;
        this.unSelectIconID = unSelectIcon;
        this.selectedIconID = selectedIcon;
        this.selectedTextColorID = selectedTextColorID;
        this.unSelectTextColorID = unSelectTextColorID;
        this.fragment = fragment;
        initView();
    }

    public MainBottomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MainBottomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public MainBottomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_tab_indicator, this);

        imageView = (ImageView) findViewById(R.id.tab_iv_image);
        textView = (TextView) findViewById(R.id.tab_tv_text);
        readPoint = findViewById(R.id.read_point);
        cover = findViewById(R.id.cover);
        cover.setVisibility(INVISIBLE);
        readPoint.setVisibility(View.GONE);
        findViewById(R.id.cover).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected && fragment instanceof IClickRefresh)
                    ((IClickRefresh) fragment).click();
            }
        });
        textView.setText(stringID);
        if (index == 0)
            imageView.setImageResource(selectedTextColorID);
        else
            imageView.setImageResource(unSelectIconID);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        this.selected = selected;
        if (selected) {
            imageView.setImageResource(selectedIconID);
            cover.setVisibility(VISIBLE);
            textView.setTextColor(getResources().getColor(selectedTextColorID));
        } else {
            imageView.setImageResource(unSelectIconID);
            cover.setVisibility(INVISIBLE);
            textView.setTextColor(getResources().getColor(unSelectTextColorID));
        }
    }
}
