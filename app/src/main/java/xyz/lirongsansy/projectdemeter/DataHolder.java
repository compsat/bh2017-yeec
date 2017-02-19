package xyz.lirongsansy.projectdemeter;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;

public class DataHolder{
    private String namae;
    private int age;
    private int gender;
    private double height;
    private double weight;
    private double activityModifier;
    private double BMR;
    private double TDEE;
    private int exercise;
    private boolean initialized;
    private boolean allergies[];
    private FoodItem foods[];
    public int numFood;

    public String getNamae() {return namae;}
    public int getAge() {return age;}
    public int getGender() {return gender;}
    public double getHeight() {return height;}
    public double getWeight() {return weight;}
    public double getActivityModifier() {return round(activityModifier, 2);}
    public double getBMR() {return BMR;}
    public double getTDEE() {return TDEE;}
    public double getExerciseType() {return exercise;}
    public boolean[] getAllergies() {return allergies;}
    public FoodItem[] getFoods() {return foods;}
    public boolean isInitialized() {return initialized;}

    public void setNamae(String namae) {this.namae = namae;}
    public void setAge(int age) {this.age = age;}
    public void setGender(int gender) {this.gender = gender;}
    public void setHeight(double height) {this.height = height;}
    public void setWeight(double weight) {this.weight = weight;}
    public void setActivityModifier(double activityModifier) {this.activityModifier = activityModifier;}
    public void setBMR(double BMR) {this.BMR = BMR;}
    public void setTDEE(double TDEE) {this.TDEE = TDEE;}
    public void setAllergies(boolean all[]) {allergies = all;}
    public void setExerciseType(int exerciseType) {this.exercise = exerciseType;}
    public void setInitialized(boolean init) {this.initialized = init;}
    public void setFoods(FoodItem fuds[]) {foods = fuds;}

    double round(double value, int places) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private static final DataHolder holder = new DataHolder();
    public static DataHolder getInstance() {return holder;}

    private DataHolder() {
        numFood = 0;
    }
}
