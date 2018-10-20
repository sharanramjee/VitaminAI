package ai.vitamin.vitaminai.recycleview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ai.vitamin.vitaminai.objects.Food;
import ai.vitamin.vitaminai.R;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewModel> {

    private ArrayList<Food> allFoods;

    //constructor method
    public MealAdapter(ArrayList<Food> allFoods){
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
        MealViewModel.mTime.setText(item.getTime());
        MealViewModel.mName.setText(item.getName());
        MealViewModel.mCal.setText(item.getItemCalories());
    }

    @Override
    public int getItemCount() {
        return allFoods.size();
    }

    class MealViewModel extends RecyclerView.ViewHolder{

        TextView mTime;
        TextView mName;
        TextView mCal;
        View view;

        MealViewModel(@NonNull View itemView) {
            super(itemView);
            mTime = itemView.findViewById(R.id.item_meal_time);
            mName = itemView.findViewById(R.id.item_meal_name);
            mCal = itemView.findViewById(R.id.item_meal_cal);
            view = itemView;
        }
    }

}
