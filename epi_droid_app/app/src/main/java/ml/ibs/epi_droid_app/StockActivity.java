package ml.ibs.epi_droid_app;

import android.os.Bundle;
import android.app.Activity;

public class StockActivity extends CheckedFormActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_activity_stock);
        setContentView(R.layout.activity_stock);
        setupSMSReceiver();
        setupUI();
    }

    private void setupUI() {
    }

}
