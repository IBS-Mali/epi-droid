package ml.ibs.epi_droid_app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class EPIAbout extends Activity {

    private final static String TAG = Constants.getLogTag("About");


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("A Propos");
        setContentView(R.layout.about);

        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI About");
    }
}
