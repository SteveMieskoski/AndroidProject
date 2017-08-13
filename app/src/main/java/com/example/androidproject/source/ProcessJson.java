package com.example.androidproject.source;

import android.text.TextUtils;
import android.util.Log;

import com.example.androidproject.models.Tick;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Set;

public class ProcessJson {

    private static final String TAG = "PROCESS_JSON";

    private String timeSeriesKey;

    public ProcessJson(){};

    public ProcessJson(String timeSeriesKey){
        this.timeSeriesKey = timeSeriesKey;
    }

    public ArrayList<Tick> processJson(String jsonData){
            if(TextUtils.isEmpty(jsonData)){
                return null;
            }
            Log.v(TAG, jsonData);
            if(timeSeriesKey == null){
                timeSeriesKey = "Time Series (1min)";
            }
            try {

                JsonObject jsonObject = (JsonObject) new JsonParser().parse(jsonData);
                JsonObject timeSeriesContent = jsonObject.getAsJsonObject(timeSeriesKey);

                ArrayList<Tick> results = new ArrayList<>();
                Set<String> jsonKeys = timeSeriesContent.keySet();

                for(String entry: jsonKeys){
                    JsonObject tickContent = (JsonObject) timeSeriesContent.get(entry);
                    String [] dayTime = entry.split(" ");

                    String date = dayTime[0];
                    String time = dayTime[1];
                    String open = tickContent.get("1. open").getAsString();
                    String high = tickContent.get("2. high").getAsString();
                    String low = tickContent.get("3. low").getAsString();
                    String close = tickContent.get("4. close").getAsString();
                    String volume = tickContent.get("5. volume").getAsString();
                    Tick tick = new Tick(date, time, open, high, low, close, volume);
                    results.add(tick);
                }
                return results;

            } catch( Exception e){
                Log.e(TAG, e.getMessage());
                return null;
            }

    }

    public String getTimeSeriesKey() {
        return timeSeriesKey;
    }

    public void setTimeSeriesKey(String timeSeriesKey) {
        this.timeSeriesKey = timeSeriesKey;
    }


}
