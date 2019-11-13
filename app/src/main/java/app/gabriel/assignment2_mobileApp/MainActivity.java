//Gabriel Lupu c15712195 DT354/year4
package app.gabriel.assignment2_mobileApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.gabriel.assignment2_mobileApp.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }
}
