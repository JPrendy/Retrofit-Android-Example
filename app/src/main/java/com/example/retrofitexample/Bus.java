package com.example.retrofitexample;

import java.util.ArrayList;

public class Bus {

    //What we are doing here is getting the field results that values contain a json array, which we store in the class BusResult
    private ArrayList<BusResult> results;

    public ArrayList<BusResult> getResults(){
        return results;
    }
}
