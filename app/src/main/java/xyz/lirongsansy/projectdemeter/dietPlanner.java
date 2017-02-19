package xyz.lirongsansy.projectdemeter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class dietPlanner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_planner);

        setTitle("Meals for the day");


    }

    public void onSalmon(View view) {
        Intent intent = new Intent(this, Salmon.class);

        startActivity(intent);
    }

    public void onChicken(View view) {
        Intent intent = new Intent(this, Chicken.class);

        startActivity(intent);
    }
}
