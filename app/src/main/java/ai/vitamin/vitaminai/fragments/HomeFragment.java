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
        FloatingActionButton addFood = view.findViewById(R.id.add_food_fb); //options for the floating action button

        //behavior for floating action buttons
        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AutoLogger.class);
                startActivity(intent);
            }
        });

//        FloatingActionsMenu menu2 = view.findViewById(R.id.home_floating_action_menu2); //the floating action button that will open the menu option
//        FloatingActionButton minusFood = view.findViewById(R.id.minus_food_fb); //options for the floating action button
//
//        //behavior for floating action buttons
//        minusFood.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent2 = new Intent(getContext(), MapsActivity.class);
//                startActivity(intent2);
//            }
//        });


        return view;
    }

}
