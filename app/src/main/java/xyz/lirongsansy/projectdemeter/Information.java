package xyz.lirongsansy.projectdemeter;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Information extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    static {
        System.loadLibrary("native-lib");
    }

    double getBMR(double height, double weight, int age, int gender) {
        double bmr, modh, modw, modAge;
        if(gender == 0){
            modw = 13.397 * weight;
            modh = 4.799 * height;
            modAge = 5.677 * age;
            bmr = (88.362 + modh + modw - modAge);
            return bmr;
        }
        else{
            modw = 9.247 * weight;
            modh = 3.098 * height;
            modAge = 4.330 * age;
            bmr = (447.593 + modw + modh - modAge);
        }
        return bmr;
    }

    boolean selected = false;
    boolean ailmentsToggle = false;
    int wew;

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        parent.getItemAtPosition(pos);
        wew = pos;
        selected = true;
    }

    public void onNothingSelected(AdapterView<?> parent) {
        selected = false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        setTitle("Personal Information");



        boolean pwede = false;

        final TextInputLayout nameTIL = (TextInputLayout)findViewById(R.id.nameTIL);
        final EditText name = (EditText)findViewById(R.id.name);
        final TextInputLayout ageTIL = (TextInputLayout)findViewById(R.id.ageTIL);
        final EditText age = (EditText)findViewById(R.id.age);
        final TextView TDEE = (TextView)findViewById(R.id.TDEE);
        final TextInputLayout heightTIL = (TextInputLayout)findViewById(R.id.heightTIL);
        final EditText height = (EditText)findViewById(R.id.height);
        final TextInputLayout weightTIL = (TextInputLayout)findViewById(R.id.weightTIL);
        final EditText weight = (EditText)findViewById(R.id.weight);
        final CardView ailmentsCard = (CardView)findViewById(R.id.ailments);
        final CheckBox Nuts = (CheckBox)findViewById(R.id.nuts);
        final CheckBox Dairy = (CheckBox)findViewById(R.id.dairy);
        final CheckBox SeaFood = (CheckBox)findViewById(R.id.seafood);
        final CheckBox Spice = (CheckBox)findViewById(R.id.spice);
        SeekBar seekBar = (SeekBar)findViewById(R.id.activitylvl);
        final TextView activityDescription = (TextView)findViewById(R.id.activity_description);
        Button next = (Button)findViewById(R.id.nextBasic);
        Button advanced = (Button)findViewById(R.id.advancedBasic);


        Spinner gender =(Spinner)findViewById(R.id.gender_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.genderspinner,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);
        gender.setOnItemSelectedListener(this);

        if (DataHolder.getInstance().isInitialized()) {
            int progress = (int)(DataHolder.getInstance().getActivityModifier()/0.01 - 100);
            name.setText(DataHolder.getInstance().getNamae());
            age.setText(Integer.toString(DataHolder.getInstance().getAge()));
            height.setText(Double.toString(DataHolder.getInstance().getHeight()));
            weight.setText(Double.toString(DataHolder.getInstance().getWeight()));
            seekBar.setProgress(progress);
            if (progress >= 0 && progress < 10) {
                activityDescription.setText(DataHolder.getInstance().getActivityModifier() + ": Little or no Exercise.");
            } else if (progress >= 10 && progress < 20) {
                activityDescription.setText(DataHolder.getInstance().getActivityModifier() + ": Light Exercise / sports 1 - 3 days/week.");
            } else if (progress >= 20 && progress < 35) {
                activityDescription.setText(DataHolder.getInstance().getActivityModifier() + ": Moderate Exercise / sports 3 - 5 days/week.");
            } else if (progress >= 35 && progress < 45) {
                activityDescription.setText(DataHolder.getInstance().getActivityModifier() + ": Hard Exercise / sports 6 - 7 days/week.");
            } else if (progress >= 45 && progress <= 100) {
                activityDescription.setText(DataHolder.getInstance().getActivityModifier() + ": Very Hard / sports 6 - 7 days/week.");
            }
            gender.setVerticalScrollbarPosition(DataHolder.getInstance().getGender());
            TDEE.setText("Total Daily Energy Expenditure: " + DataHolder.getInstance().getTDEE() + " calories.");
            Nuts.setChecked(DataHolder.getInstance().getAllergies()[0]);
            Dairy.setChecked(DataHolder.getInstance().getAllergies()[1]);
            SeaFood.setChecked(DataHolder.getInstance().getAllergies()[2]);
            Spice.setChecked(DataHolder.getInstance().getAllergies()[3]);

        } else {

            DataHolder.getInstance().setActivityModifier(1.00);
        }


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                DataHolder.getInstance().setActivityModifier((100 + progress)*0.01);
                if (progress >= 0 && progress < 10) {
                    activityDescription.setText(DataHolder.getInstance().getActivityModifier() + ": Little or no Exercise.");
                } else if (progress >= 10 && progress < 20) {
                    activityDescription.setText(DataHolder.getInstance().getActivityModifier() + ": Light Exercise / sports 1 - 3 days/week.");
                } else if (progress >= 20 && progress < 35) {
                    activityDescription.setText(DataHolder.getInstance().getActivityModifier() + ": Moderate Exercise / sports 3 - 5 days/week.");
                } else if (progress >= 35 && progress < 45) {
                    activityDescription.setText(DataHolder.getInstance().getActivityModifier() + ": Hard Exercise / sports 6 - 7 days/week.");
                } else if (progress >= 45 && progress <= 100) {
                    activityDescription.setText(DataHolder.getInstance().getActivityModifier() + ": Very Hard / sports 6 - 7 days/week.");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        advanced.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ailmentsToggle = !ailmentsToggle;
                if (ailmentsToggle) {
                    ailmentsCard.setVisibility(View.VISIBLE);
                } else {
                    ailmentsCard.setVisibility(View.GONE);
                }


            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean pwede = true;
                boolean aller[] = {false, false, false, false};

                if (name.getText().toString().equals("")) {
                    nameTIL.setErrorEnabled(true);
                    nameTIL.setError("This field is required.");
                    pwede = false;
                } else {
                    nameTIL.setErrorEnabled(false);
                }
                if (age.getText().toString().equals("")) {
                    ageTIL.setErrorEnabled(true);
                    ageTIL.setError("This field is required.");
                    pwede = false;
                } else {
                    ageTIL.setErrorEnabled(false);
                }
                if (height.getText().toString().equals("")) {
                    heightTIL.setErrorEnabled(true);
                    heightTIL.setError("This field is required.");
                    pwede = false;
                } else {
                    heightTIL.setErrorEnabled(false);
                }
                if (weight.getText().toString().equals("")) {
                    weightTIL.setErrorEnabled(true);
                    weightTIL.setError("This field is required.");
                    pwede = false;
                } else {
                    weightTIL.setErrorEnabled(false);
                }
                if (pwede && selected) {
                    DataHolder.getInstance().setNamae(name.getText().toString());
                    DataHolder.getInstance().setAge(Integer.parseInt(age.getText().toString()));
                    DataHolder.getInstance().setHeight(Double.parseDouble(height.getText().toString()));
                    DataHolder.getInstance().setWeight(Double.parseDouble(weight.getText().toString()));
                    DataHolder.getInstance().setGender(wew);
                    double BMR;
                    BMR = getBMR(DataHolder.getInstance().getHeight(), DataHolder.getInstance().getWeight(),DataHolder.getInstance().getAge(), DataHolder.getInstance().getGender());
                    DataHolder.getInstance().setBMR(BMR);
                    DataHolder.getInstance().setTDEE(BMR*DataHolder.getInstance().getActivityModifier());
                    DataHolder.getInstance().setInitialized(true);
                    aller[0] = Nuts.isChecked();
                    aller[1] = Dairy.isChecked();
                    aller[2] = SeaFood.isChecked();
                    aller[3] = Spice.isChecked();
                    DataHolder.getInstance().setAllergies(aller);
                    TDEE.setText("Total Daily Energy Expenditure: " + DataHolder.getInstance().getTDEE() + " calories.");
                    finish();

                }
            }
        });
    }
}
