package com.adfin.numobile.model;
// Created by prakasa on 02/01/17.

import java.util.ArrayList;
import java.util.List;

public class CDataDetail {
    private List<DataWarga> DataWarga = new ArrayList<>();
    private List<DataSDM> DataSDM = new ArrayList<>();

    public List<DataWarga> getDataWarga() {
        return DataWarga;
    }

    public List<DataSDM> getDataSDM() {
        return DataSDM;
    }
}
