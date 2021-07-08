package com.meli.socialmeli.service;

import java.util.Comparator;

public class StringComparator implements Comparator<String> {

    private String order;

    public StringComparator(String order) {
        this.order = order;
    }

    @Override
    public int compare(String o1, String o2) {
        if ("name_asc".equals(order)) {
            return o1.compareToIgnoreCase(o2);
        } else if ("name_desc".equals(order)) {
            return o2.compareToIgnoreCase(o1);
        }
        return 0;
    }
}
