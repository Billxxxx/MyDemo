package com.arsenal.bill.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CDText {
    /**
     * 繁体中文
     */
    public static List<String> languageList = new ArrayList<String>(Arrays.asList("ML","HK", "TW"));

    /**
     * 简体切换繁体
     *
     * @param s
     * @return
     */
    public static CharSequence J_Change(CharSequence s) {
//        if (s != null && languageList.contains(App.country))
//            try {
//                JChineseConvertor jChineseConvertor = JChineseConvertor.getInstance();
//                s = jChineseConvertor.s2t(s.toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        return s;
    }
}