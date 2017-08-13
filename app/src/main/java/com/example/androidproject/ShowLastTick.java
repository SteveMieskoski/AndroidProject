package com.example.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.androidproject.models.Tick;
import com.example.androidproject.models.TickSeries;
import com.example.androidproject.source.DataService;

import org.parceler.Parcels;

public class ShowLastTick extends AppCompatActivity {

    private static final String TAG = "SHOW_LAST_TICK";

    private TextView symbol, open, close, high, low, volume, date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_last_tick);

        symbol = (TextView) findViewById(R.id.ticker);
        date = (TextView) findViewById(R.id.tick_date);
        time = (TextView) findViewById(R.id.tick_time);
        open = (TextView) findViewById(R.id.open);
        close = (TextView) findViewById(R.id.close);
        high = (TextView) findViewById(R.id.high);
        low = (TextView) findViewById(R.id.low);
        volume = (TextView) findViewById(R.id.volume);

        Intent intent = getIntent();

        TickSeries tickSeries = Parcels.unwrap(intent.getParcelableExtra(DataService.TICKER_RESULTS));
        String ticker = intent.getStringExtra(DataService.SYMBOL);
        Log.v(TAG, tickSeries.toString());
        if (tickSeries != null) {
            if (tickSeries.getTickCount() > 0)
                distributeText(ticker, tickSeries.getTick(0));
        }
    }


    private void distributeText(String ticker, Tick tick) {
        Log.v(TAG, tick.toString());
        symbol.setText(ticker);
        date.setText(tick.getDate());
        time.setText(tick.getTime());
        open.setText(tick.getOpen());
        close.setText(tick.getClose());
        high.setText(tick.getHigh());
        low.setText(tick.getLow());
        volume.setText(tick.getVolume());

    }
}
