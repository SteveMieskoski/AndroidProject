package com.example.androidproject.source;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class DataReceiver extends ResultReceiver {

    private Receiver mReceiver;


    public DataReceiver(Handler handler){
        super(handler);
    }

    public void setReceiver(Receiver receiver){
        mReceiver = receiver;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if(mReceiver != null){
            mReceiver.onReciveResult(resultCode, resultData);
        }
    }

    public interface Receiver{
        public void onReciveResult(int resultCode, Bundle bundle);
    }

}
