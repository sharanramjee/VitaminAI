package ai.vitamin.vitaminai.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ai.vitamin.vitaminai.objects.Food;
import ai.vitamin.vitaminai.objects.Weight;

public class DataMethod {

    public static ArrayList<Food> getAllFood(Context context, Date date)
    {
        SimpleDateFormat year = new SimpleDateFormat("yyyy", Locale.US);
        SimpleDateFormat month = new SimpleDateFormat("M", Locale.US);
        SimpleDateFormat day = new SimpleDateFormat("d", Locale.US);
        SimpleDateFormat min = new SimpleDateFormat("m", Locale.US);
        String selection = DataContract.Food.COLUMN_TIME_YEAR + "=? AND "
                + DataContract.Food.COLUMN_TIME_MONTH + "=? AND "
                + DataContract.Food.COLUMN_TIME_DAY + "=?"  ;
        String[] selectionArgs = new String[]{year.format(date), month.format(date), day.format(date)};
        ArrayList<Food> foodItems = new ArrayList<>();
        @SuppressLint("Recycle")
        Cursor cursor = context.getContentResolver().query(DataContract.Food.EVENT_CONTENT_URI, DataContract.Food.PROJECTION_ARRAY, selection, selectionArgs, DataContract.Food.COLUMN_TIME_HOUR);
        assert cursor != null;
        cursor.moveToPosition(-1);
        while(cursor.moveToNext()){
            int id = cursor.getInt(DataContract.Food.COLUMN_ID_ARRAY_INDEX);
            int m = cursor.getInt(DataContract.Food.COLUMN_TIME_MONTH_INDEX);
            int d = cursor.getInt(DataContract.Food.COLUMN_TIME_DAY_INDEX);
            int y = cursor.getInt(DataContract.Food.COLUMN_TIME_YEAR_INDEX);
            int h = cursor.getInt(DataContract.Food.COLUMN_TIME_HOUR_INDEX);
            int mi = cursor.getInt(DataContract.Food.COLUMN_TIME_MIN_INDEX);
            String n = cursor.getString(DataContract.Food.COLUMN_FOOD_NAME_ARRAY_INDEX);
            double consum = cursor.getDouble(DataContract.Food.COLUMN_AMOUNT_CONSUMED_INDEX);
            double calor = cursor.getDouble(DataContract.Food.COLUMN_CALORIES_INDEX);
            double fat = cursor.getDouble(DataContract.Food.COLUMN_COLUMN_FAT);
            Calendar calendar = Calendar.getInstance();
            calendar.set(y, m, d, h, mi);

            Food temp = new Food(id, calendar.getTimeInMillis(), n, consum, calor, fat);
            foodItems.add(temp);
        }
        return foodItems;
    }

    public static void addFoodItem(Context context, Food food){
        SimpleDateFormat year = new SimpleDateFormat("yyyy", Locale.US);
        SimpleDateFormat month = new SimpleDateFormat("M", Locale.US);
        SimpleDateFormat day = new SimpleDateFormat("d", Locale.US);
        SimpleDateFormat hour = new SimpleDateFormat("k", Locale.US);
        SimpleDateFormat min = new SimpleDateFormat("m", Locale.US);
        Date date = new Date(food.getLongTime());
        String name = food.getName();
        double portion = food.getPortion();
        double totalCalories = food.getTotalCalories();
        double totalFats = food.getTotalFat();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataContract.Food.COLUMN_TIME_MONTH, month.format(date));
        contentValues.put(DataContract.Food.COLUMN_TIME_DAY, day.format(date));
        contentValues.put(DataContract.Food.COLUMN_TIME_YEAR, year.format(date));
        contentValues.put(DataContract.Food.COLUMN_TIME_HOUR, hour.format(date));
        contentValues.put(DataContract.Food.COLUMN_TIME_MIN, min.format(date));
        contentValues.put(DataContract.Food.COLUMN_FOOD_NAME, name);
        contentValues.put(DataContract.Food.COLUMN_CONSUMED, portion);
        contentValues.put(DataContract.Food.COLUMN_CALORIES, totalCalories);
        contentValues.put(DataContract.Food.COLUMN_FAT, totalFats);
        context.getContentResolver().insert(DataContract.Food.EVENT_CONTENT_URI, contentValues);
    }

    public static ArrayList<ArrayList<String>> getUIElements(Context context, Date date){
        ArrayList<ArrayList<String>> formattingIt = new ArrayList<>();
        ArrayList<Food> allFoods = getAllFood(context, date);
        Log.v(DataMethod.class.getSimpleName(), "Size: " + allFoods.size());
        for (int i = 0; i < 4; i++){
            ArrayList<String> temp = new ArrayList<>();
            for (int j = 0; j < allFoods.size(); j++){
                temp.add(allFoods.get(j).getValue(i));
            }
            formattingIt.add(temp);
        }
        return formattingIt;
    }

    public static ArrayList<Weight> getAllWeight(Context context){
        ArrayList<Weight> allweight = new ArrayList<>();
        @SuppressLint("Recycle")
        Cursor cursor = context.getContentResolver().query(DataContract.Charts.EVENT_CONTENT_URI, DataContract.Charts.PROJECTION_ARRAY, null, null, null);
        assert cursor != null;
        cursor.moveToPosition(-1);
        while(cursor.moveToNext()){
            int id = cursor.getInt(DataContract.Charts.COLUMN_ID_ARRAY_INDEX);
            int m = cursor.getInt(DataContract.Charts.COLUMN_MONTH_INDEX);
            int y = cursor.getInt(DataContract.Charts.COLUMN_YEAR_INDEX);
            int d = cursor.getInt(DataContract.Charts.COLUMN_DAY_INDEX);
            int h = cursor.getInt(DataContract.Charts.COLUMN_HOUR_INDEX);
            int min = cursor.getInt(DataContract.Charts.COLUMN_MIN_INDEX);
            double weight = cursor.getDouble(DataContract.Charts.COLUMN_WEIGHT_INDEX);
            double height = cursor.getDouble(DataContract.Charts.COLUMN_HEIGHT_INDEX);

            Calendar calendar = Calendar.getInstance();
            calendar.set(y, m, d, h, min);

            Weight temp = new Weight(id, calendar.getTimeInMillis(), height, weight);
            allweight.add(temp);
        }
        return allweight;
    }

    public static void addWeightItem(Context context, Weight weight){
        SimpleDateFormat year = new SimpleDateFormat("yyyy", Locale.US);
        SimpleDateFormat month = new SimpleDateFormat("M", Locale.US);
        SimpleDateFormat day = new SimpleDateFormat("d", Locale.US);
        SimpleDateFormat hour = new SimpleDateFormat("k", Locale.US);
        SimpleDateFormat min = new SimpleDateFormat("m", Locale.US);
        ContentValues contentValues = new ContentValues();
        Date date = new Date(weight.getDate());
        contentValues.put(DataContract.Charts.COLUMN_MONTH, month.format(date));
        contentValues.put(DataContract.Charts.COLUMN_YEAR, year.format(date));
        contentValues.put(DataContract.Charts.COLUMN_DAY, day.format(date));
        contentValues.put(DataContract.Charts.COLUMN_HOUR, hour.format(date));
        contentValues.put(DataContract.Charts.COLUMN_MIN, min.format(date));
        contentValues.put(DataContract.Charts.COLUMN_WEIGHT, weight.getWeight());
        contentValues.put(DataContract.Charts.COLUMN_HEIGHT, weight.getHeight());
        context.getContentResolver().insert(DataContract.Charts.EVENT_CONTENT_URI, contentValues);
    }

}
