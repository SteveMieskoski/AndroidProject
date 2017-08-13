package com.example.androidproject.models;

import org.parceler.Parcel;

@Parcel
public class Tick {

    private String date;
    private String time;
    private String open;
    private String high;
    private String low;
    private String close;
    private String volume;

    public Tick(){}

    public Tick(String date, String time, String open, String high, String low, String close, String volume){
        this.date = date;
        this.time = time;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOpen() {
        return open;
    }

    public Float getOpenFloat() {
        return Float.parseFloat(open);
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public Float getHighFloat(){
        return Float.parseFloat(high);
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public Float getLowFloat(){
        return Float.parseFloat(low);
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getClose() {
        return close;
    }

    public Float getCloseFloat(){
        return Float.parseFloat(close);
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getVolume() {
        return volume;
    }

    public Float getVolumeFloat(){
        return Float.parseFloat(volume);
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Override
    public String toString(){
        return "Date: " + getDate() + " Time: " + getTime() + " Open: " + getOpen() + " Close: " + getClose() + " High: " + getHigh() + " Low: " + getLow();
    }

}
