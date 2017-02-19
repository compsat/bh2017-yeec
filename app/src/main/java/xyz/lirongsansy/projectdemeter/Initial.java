package xyz.lirongsansy.projectdemeter;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Initial extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        setTitle("Project Demeter");


        //tv.setText(stringFromJNI());
    }

    public void goToDietPlanner(View view) {
        Intent intent = new Intent(this, dietPlanner.class);

        if (DataHolder.getInstance().isInitialized()) {
            startActivity(intent);
        } else {
            Snackbar.make(view, "New user. Please enter your personal info first.", Snackbar.LENGTH_LONG).show();
        }
    }

    public void goToPersonalInformation(View view) {
        Intent intent = new Intent(this, Information.class);

        startActivity(intent);
    }

    public void goToFoodTracker(View view) {
        Intent intent = new Intent(this, FoodLister.class);

        if (DataHolder.getInstance().isInitialized()) {
            startActivity(intent);
        } else {
            Snackbar.make(view, "New user. Please enter your personal info first.", Snackbar.LENGTH_LONG).show();
        }


    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

}
