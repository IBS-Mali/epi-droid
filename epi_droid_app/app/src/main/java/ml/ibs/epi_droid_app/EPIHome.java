package ml.ibs.epi_droid_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.prefs.Preferences;


public class EPIHome extends Activity {

    private final static String TAG = Constants.getLogTag("Home");

    private Button registerButton;
    private Button suiviButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate EPIHome");
        setContentView(R.layout.epi_home);

        registerButton = (Button) findViewById(R.id.registerBtn);
        final Activity activity = this;


        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });

        suiviButton = (Button) findViewById(R.id.suiviBtn);
        suiviButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                    getApplicationContext(),
                    Register.class);
                startActivity(intent);
            }
        });
        suiviButton = (Button) findViewById(R.id.suiviBtn);
        suiviButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                    getApplicationContext(),
                    Register.class);
                startActivity(intent);
            }
        });
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
