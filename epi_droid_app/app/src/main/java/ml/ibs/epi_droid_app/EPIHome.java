package ml.ibs.epi_droid_app;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

//import java.util.prefs.Preferences;


public class EPIHome extends CheckedFormActivity {

    private final static String TAG = Constants.getLogTag("Home");

    private Button registerButton;
    private Button suiviButton;
    private Button stockButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate EPIHome");
        setContentView(R.layout.epi_home);

        registerButton = findViewById(R.id.registerBtn);
        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        suiviButton = findViewById(R.id.suiviBtn);
        suiviButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                    getApplicationContext(),
                    SuiviActivity.class);
                startActivity(intent);
            }
        });

        stockButton = findViewById(R.id.stockBtn);
        stockButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                    getApplicationContext(),
                    StockActivity.class);
                startActivity(intent);
            }
        });

        setupSMSReceiver();

        final Activity activity = this;

        int permissionSendSMSCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);
        int permissionStorageCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionSendSMSCheck < 0 || permissionStorageCheck < 0) {

            AlertDialog.Builder prefCheckBuilder = new AlertDialog.Builder(this);
            prefCheckBuilder.setCancelable(false);
            prefCheckBuilder.setTitle(
                    getString(R.string.pnso_get_permission_title));
            prefCheckBuilder.setMessage(
                    getString(R.string.pnso_get_permission_body));
            prefCheckBuilder.setIcon(R.mipmap.ic_launcher);
            prefCheckBuilder.setPositiveButton(R.string.go_to_application_settings,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // close the dialog (auto)
                            finish();
                            // go to package

                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                        }
                    });
            AlertDialog prefCheckDialog = prefCheckBuilder.create();
            prefCheckDialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.epi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_settings) {
            Intent intent = new Intent(this, Preferences.class);
            startActivityForResult(intent, 1);
        }
        if (id == R.id.about) {
            Intent intent = new Intent(this, EPIAbout.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
