package ai.vitamin.vitaminai.recycleview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import ai.vitamin.vitaminai.objects.Food;
import ai.vitamin.vitaminai.R;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewModel> {

    private ArrayList<Food> allFoods;
    private Context context;

    //constructor method
    public MealAdapter(Context context, ArrayList<Food> allFoods){
        this.context = context;
        this.allFoods = allFoods;
    }

    @NonNull
    @Override
    public MealViewModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_view_item_meals, viewGroup, false);
        return new MealViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewModel MealViewModel, final int i) {
        Food item = allFoods.get(i);
        MealViewModel.mTime.setText("Time Eaten: " + item.getTime());
        MealViewModel.mName.setText(item.getName());
        MealViewModel.mCal.setText("Number of Calories: " + Integer.toString((int) (item.getTotalCalories() * item.getPortion())));

        //setting the image
        @SuppressLint("Recycle")
        TypedArray imgs = context.getResources().obtainTypedArray(R.array.random_images_array);
        final Random rand = new Random();
        final int rndInt = rand.nextInt(imgs.length());
        final int resID = imgs.getResourceId(rndInt, 0);
        MealViewModel.mImage.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), resID));
    }

    public void setValues(ArrayList<Food> allFoods){
        this.allFoods = allFoods;
        notifyDataSetChanged();
    }

    private int getMealTimeColor(Context context, int mag){
        int magnitudeColorResourceId;
        switch (mag) {
            case 1:
                magnitudeColorResourceId = R.color.meal1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.meal2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.meal3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.meal4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.meal5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.meal6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.meal7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.meal8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.meal9;
                break;
            case 10:
                magnitudeColorResourceId = R.color.meal10;
                break;
            case 11:
                magnitudeColorResourceId = R.color.meal11;
                break;
            case 12:
                magnitudeColorResourceId = R.color.meal12;
                break;
            case 13:
                magnitudeColorResourceId = R.color.meal13;
                break;
            case 14:
                magnitudeColorResourceId = R.color.meal14;
                break;
            case 15:
                magnitudeColorResourceId = R.color.meal15;
                break;
            case 16:
                magnitudeColorResourceId = R.color.meal16;
                break;
            case 17:
                magnitudeColorResourceId = R.color.meal17;
                break;
            case 18:
                magnitudeColorResourceId = R.color.meal18;
                break;
            case 19:
                magnitudeColorResourceId = R.color.meal19;
                break;
            case 20:
                magnitudeColorResourceId = R.color.meal20;
                break;
            case 21:
                magnitudeColorResourceId = R.color.meal21;
                break;
            case 22:
                magnitudeColorResourceId = R.color.meal22;
                break;
            case 23:
                magnitudeColorResourceId = R.color.meal23;
                break;
            default:
                magnitudeColorResourceId = R.color.meal1;
                break;
        }
        return ContextCompat.getColor(context, magnitudeColorResourceId);
    }



    @Override
    public int getItemCount() {
        return allFoods.size();
    }

    class MealViewModel extends RecyclerView.ViewHolder{

        ImageView mImage;
        TextView mTime;
        TextView mName;
        TextView mCal;

        MealViewModel(@NonNull View itemView) {
            super(itemView);
            mTime = itemView.findViewById(R.id.item_meal_time);
            mName = itemView.findViewById(R.id.item_meal_name);
            mCal = itemView.findViewById(R.id.item_meal_calories);
            mImage = itemView.findViewById(R.id.item_image);
        }
    }

}
