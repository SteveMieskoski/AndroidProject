package com.example.androidproject.source;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import com.example.androidproject.models.Tick;
import com.example.androidproject.models.TickSeries;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class DataService extends IntentService {

    private static final String TAG = "DATA_SERVICE";
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.example.androidproject.action.FOO";
    private static final String ACTION_BAZ = "com.example.androidproject.action.BAZ";
    public static final String ACTION_DATA = "com.example.androidproject.action.DATA";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.androidproject.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.androidproject.extra.PARAM2";
    public static final String LOOKUP_TICKER = "com.example.androidproject.extra.LOOKUP_TICKER";

    public static final String RECEIVER = "Receiver";
    public static final String SYMBOL = "symbol";
    public static final String TICKER_RESULTS = "tickerResults";
    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;

    private ResultReceiver receiver;

    public DataService() {
        super(DataService.class.getName());
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if(ACTION_DATA.equals(action)){
                receiver = intent.getParcelableExtra(RECEIVER);
                receiver.send(STATUS_RUNNING, Bundle.EMPTY);
                String ticker = intent.getStringExtra(LOOKUP_TICKER);
                TickSeries tickSeries = new TickSeries(ticker);
                DataRequest dataRequest = new DataRequest();
                ArrayList<Tick> result = dataRequest.requestData(ticker);
                tickSeries.setTickSeries(result);
                Log.v(TAG, tickSeries.toString());
                returnResults(tickSeries);
            }
        }
    }

    private void returnResults(TickSeries results) {
      if(results != null){
          Bundle bundle = new Bundle();
          bundle.putString(SYMBOL, results.getSymbol());
          bundle.putParcelable(TICKER_RESULTS, Parcels.wrap(results));
          receiver.send(STATUS_FINISHED, bundle);
      } else {
          receiver.send(STATUS_ERROR, Bundle.EMPTY);
      }

    }

}
