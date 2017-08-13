package com.example.androidproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.androidproject.models.TickSeries;
import com.example.androidproject.source.DataReceiver;
import com.example.androidproject.source.DataService;

import org.parceler.Parcels;

public class MainActivity extends AppCompatActivity implements DataReceiver.Receiver {

    private static final String TAG = "MAIN_ACTIVITY";

    private ProgressBar progressBar;
    private TextView searchError;
    private EditText editText;
    private Button tickerSearchBtn;
    private DataReceiver mReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.ticker_search_txt);
        tickerSearchBtn = (Button) findViewById(R.id.ticker_search_btn);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        searchError = (TextView) findViewById(R.id.search_error);
        searchError.setVisibility(View.INVISIBLE);
        mReceiver = new DataReceiver(new Handler());
        mReceiver.setReceiver(this);


        tickerSearchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tickerSearchClick(view);
            }
        });

    }

    public void tickerSearchClick(View view){
      //  Intent intent = new Intent(Intent.ACTION_SYNC, null, this, DataService.class);
        Intent intent = new Intent(DataService.ACTION_DATA, null, this, DataService.class);
        intent.putExtra(DataService.RECEIVER, mReceiver);
        String ticker = editText.getText().toString();
        intent.putExtra(DataService.LOOKUP_TICKER, ticker);
        startService(intent);
    }


    @Override
    public void onReciveResult(int resultCode, Bundle bundle) {
        switch(resultCode){
            case DataService.STATUS_RUNNING:
                searchError.setVisibility(View.INVISIBLE);
                editText.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                break;
            case DataService.STATUS_FINISHED:
                progressBar.setVisibility(View.INVISIBLE);
                editText.setVisibility(View.VISIBLE);
                TickSeries tickSeries = Parcels.unwrap(bundle.getParcelable(DataService.TICKER_RESULTS));
                String symbol = bundle.getString(DataService.SYMBOL);
                Intent intent = new Intent(this, ShowLastTick.class);
                intent.putExtra(DataService.TICKER_RESULTS, Parcels.wrap(tickSeries));
                intent.putExtra(DataService.SYMBOL, symbol);
                startActivity(intent);
                break;
            case DataService.STATUS_ERROR:
                progressBar.setVisibility(View.INVISIBLE);
                editText.setVisibility(View.VISIBLE);
                searchError.setVisibility(View.VISIBLE);
                break;
        }
    }


}
