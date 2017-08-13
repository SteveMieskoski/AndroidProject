package com.example.androidproject.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class TickSeries extends ArrayList {

    private ArrayList<Tick> tickSeries;
    private String symbol;

    public TickSeries(String symbol){
        this.symbol = symbol;
    }

    public TickSeries() {
        this.tickSeries = new ArrayList<>();
    }

    public TickSeries(ArrayList<Tick> ticks) {
        this.tickSeries = ticks;
    }

    public TickSeries(ArrayList<Tick> ticks, String symbol) {
        this.tickSeries = ticks;
        this.symbol = symbol;
    }

    public ArrayList<Tick> getTickSeries() {
        return tickSeries;
    }

    public void setTickSeries(ArrayList<Tick> tickSeries) {
        this.tickSeries = tickSeries;
    }

    public void extendTickSeries(ArrayList<Tick> tickSeries){
        this.tickSeries.addAll(tickSeries);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Tick getTick(int i) {
        return this.tickSeries.get(i);
    }

    public int getTickCount(){
        return this.tickSeries.size();
    }



    public void addTick(Tick tick) {
        if (this.tickSeries == null) {
            System.out.println("CANNOT ADD TO NULL");
        } else {
            this.tickSeries.add(tick);
        }
    }

    public ArrayList<String> getDates() {
        ArrayList<String> dateArray = new ArrayList<>();
        for (Tick t : this.tickSeries) {
            dateArray.add(t.getDate());
        }
        return dateArray;
    }

    public ArrayList<String> getTimes() {
        ArrayList<String> timeArray = new ArrayList<>();
        for (Tick t : this.tickSeries) {
            timeArray.add(t.getTime());
        }
        return timeArray;
    }

    public ArrayList<Integer> getIntList(String field) {
        ArrayList<Integer> intArray = new ArrayList<>();
        switch (field) {
            case "Open":
                for (Tick t : this.tickSeries) {
                    intArray.add(Integer.parseInt(t.getOpen()));
                }
                break;
            case "High":
                for (Tick t : this.tickSeries) {
                    intArray.add(Integer.parseInt(t.getHigh()));
                }
                break;
            case "Low":
                for (Tick t : this.tickSeries) {
                    intArray.add(Integer.parseInt(t.getLow()));
                }
                break;
            case "Close":
                for (Tick t : this.tickSeries) {
                    intArray.add(Integer.parseInt(t.getClose()));
                }
                break;
            case "Volume":
                for (Tick t : this.tickSeries) {
                    intArray.add(Integer.parseInt(t.getVolume()));
                }
                break;
            default:
                break;
        }
        return intArray;
    }

    public ArrayList<Float> getFloatList(String field) {
        ArrayList<Float> intArray = new ArrayList<>();
        switch (field) {
            case "Open":
                for (Tick t : this.tickSeries) {
                    intArray.add(Float.parseFloat(t.getOpen()));
                }
                break;
            case "High":
                for (Tick t : this.tickSeries) {
                    intArray.add(Float.parseFloat(t.getHigh()));
                }
                break;
            case "Low":
                for (Tick t : this.tickSeries) {
                    intArray.add(Float.parseFloat(t.getLow()));
                }
                break;
            case "Close":
                for (Tick t : this.tickSeries) {
                    intArray.add(Float.parseFloat(t.getClose()));
                }
                break;
            case "Volume":
                for (Tick t : this.tickSeries) {
                    intArray.add(Float.parseFloat(t.getVolume()));
                }
                break;
            default:
                break;
        }
        return intArray;
    }
}