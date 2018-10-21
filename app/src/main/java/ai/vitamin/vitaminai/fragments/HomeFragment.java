package ai.vitamin.vitaminai.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import ai.vitamin.vitaminai.AutoLogger;
import ai.vitamin.vitaminai.ManualLogger;
import ai.vitamin.vitaminai.R;

public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false); //the main view for the layout
        FloatingActionsMenu menu = view.findViewById(R.id.home_floating_action_menu); //the floating action button that will open the menu option

        FloatingActionButton addFoodAuto = view.findViewById(R.id.add_food_auto); //options for the floating action button
        //behavior for floating action buttons
        addFoodAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AutoLogger.class);
                startActivity(intent);
            }
        });

        FloatingActionButton addFoodManual = view.findViewById(R.id.add_food_manual); //options for the floating action button
        //behavior for floating action buttons
        addFoodManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getContext(), ManualLogger.class);
                startActivity(intent2);
            }
        });

        FloatingActionButton goForRun = view.findViewById(R.id.open_maps); //options for the floating action button
        //behavior for floating action buttons
        goForRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getContext(), MapsActivity.class);
                startActivity(intent3);
            }
        });


        return view;
    }

}
