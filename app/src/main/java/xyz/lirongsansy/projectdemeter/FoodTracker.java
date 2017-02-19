package xyz.lirongsansy.projectdemeter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class FoodTracker extends AppCompatActivity {

    public void addFood(String namae, int count, int expiry) {
        FoodItem newList[] = new FoodItem[DataHolder.getInstance().numFood + 1];
        FoodItem newFood = new FoodItem();

        for (int i = 0; i < DataHolder.getInstance().numFood; i++) {
            newList[i] = DataHolder.getInstance().getFoods()[i];
        }

        newFood.name = namae;
        newFood.count = count;
        newFood.expiry = expiry;
        newList[DataHolder.getInstance().numFood] = newFood;

        DataHolder.getInstance().setFoods(newList);
    }
    
    boolean addVisible = false;

    public void update(RelativeLayout layout) {
        for (int i = 0; i < DataHolder.getInstance().numFood; i++) {
            CardView bg = new CardView(this);
            bg.setCardElevation(2);
            bg.setPadding(8,8,8,8);
            RelativeLayout.LayoutParams bgparams= new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.MATCH_PARENT);
            bg.setLayoutParams(bgparams);
            layout.addView(bg);

            System.out.println("updated!");

            FrameLayout frame = new FrameLayout(this);
            frame.setPadding(8,8,8,8);
            RelativeLayout.LayoutParams frameparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            frame.setLayoutParams(frameparams);
            layout.addView(frame);

        }

        setContentView(layout);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_tracker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final EditText foodName = (EditText)findViewById(R.id.foodName);
        final EditText foodAmount = (EditText)findViewById(R.id.amount);
        final EditText foodExpiry = (EditText)findViewById(R.id.expiry);
        final TextInputLayout foodNameTIL = (TextInputLayout)findViewById(R.id.foodNameTIL);
        final TextInputLayout foodAmountTIL = (TextInputLayout)findViewById(R.id.amountTIL);
        final TextInputLayout foodExpiryTIL = (TextInputLayout)findViewById(R.id.expiryTIL);
        Button addFoodButton = (Button)findViewById(R.id.addFoodButton);
        final RelativeLayout layout = (RelativeLayout)findViewById(R.id.relalay);

        final CardView addCard = (CardView)findViewById(R.id.addCard);
        
        if (DataHolder.getInstance().numFood == 0) {
            addVisible = true;
            addCard.setVisibility(View.VISIBLE);
        } else {
            update(layout);
        }

        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean pwede = true;

                if (foodName.getText().toString().equals("")) {
                    foodNameTIL.setErrorEnabled(true);
                    foodNameTIL.setError("This field is required.");
                    pwede = false;
                } else {
                    foodNameTIL.setErrorEnabled(false);
                }
                if (foodAmount.getText().toString().equals("")) {
                    foodAmountTIL.setErrorEnabled(true);
                    foodAmountTIL.setError("This field is required.");
                    pwede = false;
                } else {
                    foodAmountTIL.setErrorEnabled(false);
                }
                if (foodExpiry.getText().toString().equals("")) {
                    foodExpiryTIL.setErrorEnabled(true);
                    foodExpiryTIL.setError("This field is required.");
                    pwede = false;
                } else {
                    foodExpiryTIL.setErrorEnabled(false);
                }

                if(pwede) {
                    addFood(foodName.getText().toString(), Integer.parseInt(foodAmount.getText().toString()), Integer.parseInt(foodExpiry.getText().toString()));
                    foodName.setText("");
                    foodAmount.setText("");
                    foodExpiry.setText("");
                    addVisible = false;
                    addCard.setVisibility(View.GONE);
                    update(layout);
                    DataHolder.getInstance().numFood++;
                }
            }
        });
        

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addVisible = true;
                addCard.setVisibility(View.VISIBLE);

            }
        });
    }

}
