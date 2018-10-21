package ai.vitamin.vitaminai.introscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.github.shchurov.horizontalwheelview.HorizontalWheelView;

import java.util.Locale;

import ai.vitamin.vitaminai.Home;
import ai.vitamin.vitaminai.PreferenceUtils;
import ai.vitamin.vitaminai.R;

public class EnterInfo extends AppCompatActivity {

    private HorizontalWheelView horizontalWheelView1;
    private HorizontalWheelView horizontalWheelView2;
    private HorizontalWheelView horizontalWheelView3;
    private TextView heighttv;
    private TextView weighttv;
    private TextView agetv;
    private EditText nametv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_info);
        initViews();
        setupListeners();
        updateUi();
    }

    private void initViews() {
        horizontalWheelView1 = (HorizontalWheelView) findViewById(R.id.horizontalWheelView1);
        horizontalWheelView2 = (HorizontalWheelView) findViewById(R.id.horizontalWheelView2);
        horizontalWheelView3 = (HorizontalWheelView) findViewById(R.id.horizontalWheelView3);
        heighttv = (TextView) findViewById(R.id.heighttv);
        weighttv = (TextView) findViewById(R.id.weighttv);
        agetv = (TextView) findViewById(R.id.agetv);
        nametv = findViewById(R.id.nameet);
    }

    private void updateText() {
        String text1 = String.format(Locale.US, "%.0f", horizontalWheelView1.getDegreesAngle());
        heighttv.setText(text1);
        String text2 = String.format(Locale.US, "%.0f", horizontalWheelView2.getDegreesAngle());
        weighttv.setText(text2);
        String text3 = String.format(Locale.US, "%.0f", horizontalWheelView3.getDegreesAngle());
        agetv.setText(text3);
    }

    private void updateUi() {
        updateText();
    }

    private void setupListeners() {
        horizontalWheelView1.setListener(new HorizontalWheelView.Listener() {
            @Override
            public void onRotationChanged(double radians) {
                updateUi();
            }
        });
        horizontalWheelView2.setListener(new HorizontalWheelView.Listener() {
            @Override
            public void onRotationChanged(double radians) {
                updateUi();
            }
        });
        horizontalWheelView3.setListener(new HorizontalWheelView.Listener() {
            @Override
            public void onRotationChanged(double radians) {
                updateUi();
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        updateUi();
    }


    public void tapNext(View view) {
        PreferenceUtils.setStartup(this, true);
        PreferenceUtils.setFullName(this, nametv.getText().toString());
        PreferenceUtils.setHeight(this, Float.parseFloat(heighttv.getText().toString()));
        PreferenceUtils.setWeight(this, Float.parseFloat(weighttv.getText().toString()));
        PreferenceUtils.setAge(this, Integer.parseInt(agetv.getText().toString()));

        Intent intent = new Intent(this, Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
