package ai.vitamin.vitaminai.fragments;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dinuscxj.progressbar.CircleProgressBar;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import ai.vitamin.vitaminai.AutoLogger;
import ai.vitamin.vitaminai.ManualLogger;
import ai.vitamin.vitaminai.MapsActivity;
import ai.vitamin.vitaminai.R;
import ai.vitamin.vitaminai.UpdateWeight;

public class HomeFragment extends Fragment implements SensorEventListener {

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public static TextView tv_steps;
    public static Integer circle_prog;
    public Integer total;
    SensorManager sensorManager;
    boolean running = false;
    public static CircleProgressBar mLineProgressBar;


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

        FloatingActionButton updateWeight = view.findViewById(R.id.update_weight); //options for the floating action button
        //behavior for floating action buttons
        updateWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(getContext(), UpdateWeight.class);
                startActivity(intent4);
            }
        });

        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        tv_steps = (TextView) view.findViewById(R.id.tv_steps);
        mLineProgressBar = (CircleProgressBar) view.findViewById(R.id.line_progress);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor != null){
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(getContext(), "Sensor not found!", Toast.LENGTH_SHORT).show();
        }

        simulateProgress();
    }

    @Override
    public void onPause() {
        super.onPause();
        running = false;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(running){

            circle_prog = (int)Double.parseDouble(String.valueOf(sensorEvent.values[0]));
            total = circle_prog;
            tv_steps.setText(Integer.toString(total-54000));
        }
    }

    private void simulateProgress() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int progress =  (int)Double.parseDouble(tv_steps.getText().toString());
                mLineProgressBar.setProgress(progress);
            }
        });
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(4000);
        animator.start();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
