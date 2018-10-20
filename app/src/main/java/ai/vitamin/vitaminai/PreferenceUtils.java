package ai.vitamin.vitaminai;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceUtils {

    public static float getHeight(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        return preferences.getFloat("height", Float.parseFloat("0.0"));
    }

    public static float getWeight(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getFloat("weight", Float.parseFloat("0.0"));
    }

    public static int getAge(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt("age", 0);
    }

    public static String getName(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("name", "google");
    }

}
