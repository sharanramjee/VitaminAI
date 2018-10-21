package ai.vitamin.vitaminai.fragments;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;

import org.qap.ctimelineview.TimelineRow;
import org.qap.ctimelineview.TimelineViewAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import ai.vitamin.vitaminai.R;
import ai.vitamin.vitaminai.data.DataMethod;
import ai.vitamin.vitaminai.objects.Food;
import ai.vitamin.vitaminai.recycleview.MealAdapter;


public class MealsFragment extends Fragment {

    public ArrayList<ArrayList<String>> meals;
    public ArrayList<String> meal_times;
    public ArrayList<String> meal_names;
    public ArrayList<String> meal_calories;

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private View view;

    ArrayList<TimelineRow> timelineRowsList = new ArrayList<>();
    ArrayAdapter<TimelineRow> myAdapter;
    public static Integer row;
    public Integer resID;

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

        meals = DataMethod.getUIElements(getContext(), new Date());
        meal_times = meals.get(0);
        meal_names = meals.get(1);
        meal_calories = meals.get(2);

        view = inflater.inflate(R.layout.fragment_meals, container, false); //the entire view for meal fragment
        toolbar = view.findViewById(R.id.calendar_toolbar); //the toolbar of the view
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
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + i == 0) {
                    LinearLayout linearLayout = view.findViewById(R.id.activity_calendar_ll);
                    linearLayout.setVisibility(View.GONE);
                    collapsingToolbarLayout.setTitle(Objects.requireNonNull(getContext()).getString(R.string.title_meal_logs));
                    isShow = true;
                } else if (isShow) {
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

        System.out.println(meal_names.size());
        for(int i = 0; i < meal_names.size(); i++) {
            timelineRowsList.add(createRandomTimelineRow(i));
        }

        myAdapter = new TimelineViewAdapter(getContext(), 0, timelineRowsList,
                //if true, list will be sorted by date
                true);

        Log.v(MealsFragment.class.getSimpleName(), "Size of TimeLine: " + timelineRowsList.size());
        ListView myListView = view.findViewById(R.id.timeline_listView);
        myListView.setAdapter(myAdapter);

        myListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int currentVisibleItemCount;
            private int currentScrollState;
            private int currentFirstVisibleItem;
            private int totalItem;
            private LinearLayout lBelow;


            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub
                this.currentScrollState = scrollState;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                // TODO Auto-generated method stub
                this.currentFirstVisibleItem = firstVisibleItem;
                this.currentVisibleItemCount = visibleItemCount;
                this.totalItem = totalItemCount;
            }
        });

//        //if you wish to handle the clicks on the rows
//        AdapterView.OnItemClickListener adapterListener = new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                TimelineRow row = timelineRowsList.get(position);
//                Toast.makeText(MealsFragment.this, row.getTitle(), Toast.LENGTH_SHORT).show();
//            }
//        };
//        myListView.setOnItemClickListener(adapterListener);

        return view;
    }

    //Method to create new Timeline Row
    private TimelineRow createRandomTimelineRow(int id) {

        // Create new timeline row (pass your Id)
        TimelineRow myRow = new TimelineRow(id);

        //to set the row Date (optional)
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        try {
            date = sdf.parse(meal_times.get(id));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        myRow.setDate(date);

        //to set the row Title (optional)
        myRow.setTitle(meal_names.get(id));

        //to set the row Description (optional)
        myRow.setDescription(meal_calories.get(id) + " Calories");

        //to set the row bitmap image (optional)
        final TypedArray imgs = getResources().obtainTypedArray(R.array.random_images_array);
        final Random rand = new Random();
        final int rndInt = rand.nextInt(imgs.length());
        final int resID = imgs.getResourceId(rndInt, 0);
        myRow.setImage(BitmapFactory.decodeResource(getResources(), resID));

        //to set row Below Line Color (optional)
        myRow.setBellowLineColor(Color.parseColor("#FFFFFFFF"));

        //to set row Below Line Size in dp (optional)
        myRow.setBellowLineSize(4);

        //to set row Date text color (optional)
        myRow.setDateColor(Color.parseColor("#FFFFFFFF"));

        //to set row Title text color (optional)
        myRow.setTitleColor(Color.parseColor("#FFFFFFFF"));

        //to set row Description text color (optional)
        myRow.setDescriptionColor(Color.parseColor("#FFFFFFFF"));

        return myRow;
    }
}
