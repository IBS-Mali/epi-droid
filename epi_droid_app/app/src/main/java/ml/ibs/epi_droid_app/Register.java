package ml.ibs.epi_droid_app;

import android.os.Bundle;
import android.util.Log;



public class Register extends CheckedFormActivity {
    private final static String TAG = Constants.getLogTag("Register");



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Register");
        setContentView(R.layout.register);

        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI Register");

    }

    protected void setupInvalidInputChecks() {}

    protected boolean ensureDataCoherence(){return true;}

    public String buildSMSText() {return "";}
}