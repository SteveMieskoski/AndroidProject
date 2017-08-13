package com.example.androidproject.source;

import android.util.Log;

import com.example.androidproject.models.Tick;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.androidproject.source.DataConstants.ApiKey;

public class DataRequest {

    private static final String TAG = "DATA_REQUEST";


    private static final String Base_Url = "https://www.alphavantage.co";


    public DataRequest(){};

    public ArrayList<Tick> requestData(String ticker){
        ProcessJson processJson = new ProcessJson();
        Log.v(TAG, "TICKER: " + ticker);
       String jsonResponse = connectToUrl(ticker);
        Log.v(TAG, "JSON Response");
        Log.v(TAG, jsonResponse);
       ArrayList<Tick> processedResponse = processJson.processJson(jsonResponse);
      //  Log.v(TAG, processedResponse.toString());
        return processedResponse;
    }


    private String buildUrl(String ticker) {
        if (ticker == null || ticker == "") {
            ticker = "MSFT";
        }

        return Base_Url + "/query?function=TIME_SERIES_INTRADAY&outputsize=compact&symbol=" + ticker + "&interval=1min&apikey=" + ApiKey;
    }


    private String connectToUrl(String ticker) {
        try {
            String requestResponse = "";
            if (ticker == null) {
                return requestResponse;
            }

            String url = buildUrl(ticker);

            Log.v(TAG, "URL STRING" + url);
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();

            return response.body().string();


        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}
