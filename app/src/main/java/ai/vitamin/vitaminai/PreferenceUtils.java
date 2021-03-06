package ai.vitamin.vitaminai;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

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

    public static void setAge ( Context context, int age) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt("age", age)
                .apply();

    }

    public static void setWeight ( Context context, Float weight) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putFloat("weight", weight)
                .apply();

    }

    public static void setFullName ( Context context, String name) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString("name", name)
                .apply();

    }

    public static void setUsername ( Context context, String email) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString("email", email)
                .apply();

    }

    public static void setPassword ( Context context, String password) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString("password", password)
                .apply();

    }

    public static void setStartup(Context context, boolean status) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean("firstRun", status)
                .apply();
    }

    public static boolean didStartUp(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean("firstRun", false);
    }



}
