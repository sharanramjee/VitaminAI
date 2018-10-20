package ai.vitamin.vitaminai;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import ai.vitamin.vitaminai.introFragments.IntroFragments;

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

    public static void setHeight ( Context context, Float height) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putFloat("height", height)
                .apply();

    }

    public static void setAge ( Context context, Float age) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putFloat("height", age)
                .apply();

    }

    public static void setWeight ( Context context, Float weight) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putFloat("height", weight)
                .apply();

    }

    public static void setFullName ( Context context, String name) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString("height", name)
                .apply();

    }

    public static void setUsername ( Context context, String email) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString("height", email)
                .apply();

    }

    public static void setPassword ( Context context, String password) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString("height", password)
                .apply();

    }



}
