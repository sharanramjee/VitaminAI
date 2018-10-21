package ai.vitamin.vitaminai.objects;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Food {

    private int id;
    private long date; //the date the user chooses for this specific item
    private String name; //the name of the item
    private double portion; //digit from 0 to 1 that represent how much of the item they ate
    private double totalCalories; //total amount of calories for all the items
    private double totalFats;

    /*
    Parameter: date, long, the date
    * name, String, name of the food
    * portion, double, how much the user ate of that item
    * totalCalories, double, how many calories that item have
    Description: Constructor Method
    Return: void, Nothing
     */
    public Food(int id, long date, String name, double portion, double totalCalories, double totalFats)
    {
        this.id = id;
        this.date = date;
        this.name = name;
        this.portion = portion;
        this.totalCalories = totalCalories;
        this.totalFats = totalFats;
    }

    public Food(long date, String name, double portion, double totalCalories, double totalFats)
    {
        this.id = -1;
        this.date = date;
        this.name = name;
        this.portion = portion;
        this.totalCalories = totalCalories;
        this.totalFats = totalFats;
    }

    /*
    Parameter: nothing
    Description: returns the time that can be displayed for the recycle view
    Return: String, the digit from 1 to 11
     */
    public String getTime()
    {
        SimpleDateFormat number = new SimpleDateFormat("h:m a", Locale.US);
        return number.format(new Date(date));
    }

    public long getLongTime(){
        return date;
    }

    /*
    Parameter: nothing
    Description: returns how intense the color of the time should be based off of when they ate it
    Return: Int, time of day from 1 to 24
     */
    public int getIntensity(){
        SimpleDateFormat density = new SimpleDateFormat("k", Locale.US);
        return Integer.parseInt(density.format(new Date(date)));
    }

    public String getName(){
        return name;
    }

    public String getItemCalories(){
        return Double.toString(totalCalories * portion);
    }

    public String getItemFats(){
        return Double.toString(totalFats * portion);
    }

    public double getPortion(){
        return (float) portion;
    }

    public double getTotalCalories(){
        return totalCalories;
    }

    public double getTotalFat(){
        return totalFats;
    }

    public String getValue(int pos){
        switch (pos){
            case 0: return getTime();
            case 1: return name;
            case 2: return getItemCalories();
            case 3: return getItemFats();
        }
        return "";
    }

    public void setDate(){
        date = Calendar.getInstance().getTimeInMillis();
    }
}
