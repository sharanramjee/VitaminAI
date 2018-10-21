package ai.vitamin.vitaminai.objects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Weight {

    private int id;
    private long date; //the date the user chooses for this specific item
    private double height ; //the name of the item
    private double weight; //digit from 0 to 1 that represent how much of the item they ate

    public Weight(int id, long date, double height, double weight)
    {
        this.id = id;
        this.date = date;
        this.height = height;
        this.weight = weight;
    }

    public Weight(long date, double height, double weight)
    {
        this.id = -1;
        this.date = date;
        this.height = height;
        this.weight = weight;
    }


    public long getDate(){
        return date;
    }

    public double getHeight()
    {
        return height;
    }

    public double getWeight(){
        return weight;
    }

    public int getId(){
        return id;
    }

    public double getBMI(){
        return weight / (weight * weight) / 10000;
    }

}
