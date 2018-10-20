package ai.vitamin.vitaminai.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import ai.vitamin.vitaminai.R;
import ai.vitamin.vitaminai.objects.Food;
import ai.vitamin.vitaminai.recycleview.MealAdapter;

public class MealsFragment extends Fragment {

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private View view;

    public MealsFragment() {
    }

    public static MealsFragment newInstance() {
        return new MealsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_meals, container, false); //the entire view for meal fragment
        toolbar = view.findViewById(R.id.calendar_toolbar); //the toolbar of the view
        RecyclerView recyclerView = view.findViewById(R.id.meals_recycle_view); //the recycle view that is displaying all the data
        CalendarView calendarView = view.findViewById(R.id.calendar_cv); //the calendar view

        //setting up Action Bar as the Supporting action bar
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);

        //setting up the collapsing toolbar
        collapsingToolbarLayout = view.findViewById(R.id.calendar_toolbar_layout);
        appBarLayout = view.findViewById(R.id.calendar_app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @SuppressLint("RestrictedApi")
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if (scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + i == 0){
                    LinearLayout linearLayout = view.findViewById(R.id.activity_calendar_ll);
                    linearLayout.setVisibility(View.GONE);
                    collapsingToolbarLayout.setTitle(Objects.requireNonNull(getContext()).getString(R.string.title_meal_logs));
                    isShow = true;
                }else if (isShow){
                    LinearLayout linearLayout = view.findViewById(R.id.activity_calendar_ll);
                    linearLayout.setVisibility(View.VISIBLE);
                    collapsingToolbarLayout.setTitle("");
                    toolbar.setTitle("");
                    isShow = false;
                }
            }
        });
        appBarLayout.setExpanded(false);

        //setting up behavior for calendar view
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int date) {
                appBarLayout.setExpanded(false);
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, date);
            }
        });

        //setting layout and displaying the content
        ArrayList<Food> items = new ArrayList<>();
        items.add(new Food(Calendar.getInstance().getTimeInMillis(), "Beans", 0.5, 1000));
        items.add(new Food(Calendar.getInstance().getTimeInMillis() + TimeUnit.HOURS.toMillis(1), "Beans", 0.5, 1000));
        items.add(new Food(Calendar.getInstance().getTimeInMillis() + TimeUnit.HOURS.toMillis(2), "Corn", 0.6, 1000));
        items.add(new Food(Calendar.getInstance().getTimeInMillis() + TimeUnit.HOURS.toMillis(3), "Milk", 0.7, 1000));
        items.add(new Food(Calendar.getInstance().getTimeInMillis() + TimeUnit.HOURS.toMillis(4), "Cheese", 0.8, 1000));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MealAdapter(getContext(), items));

        return view;
    }
}
