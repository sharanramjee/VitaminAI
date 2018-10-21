package ai.vitamin.vitaminai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.shchurov.horizontalwheelview.HorizontalWheelView;

import java.util.Calendar;
import java.util.Locale;

import ai.vitamin.vitaminai.data.DataMethod;
import ai.vitamin.vitaminai.objects.Weight;

public class UpdateWeight extends AppCompatActivity {

    private HorizontalWheelView horizontalWheelView1;
    private TextView weighttv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_weight);
        initViews();
        setupListeners();
        updateUi();
    }

    private void initViews() {
        horizontalWheelView1 = (HorizontalWheelView) findViewById(R.id.horizontalWheelView1);
        weighttv = (TextView) findViewById(R.id.weighttv);
    }

    private void updateText() {
        String text1 = String.format(Locale.US, "%.0f", horizontalWheelView1.getDegreesAngle());
        weighttv.setText(text1);
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
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        updateUi();
    }


    public void tapNext(View view) {
        PreferenceUtils.setStartup(this, true);
        PreferenceUtils.setWeight(this, Float.parseFloat(weighttv.getText().toString()));

        Weight weight = new Weight(Calendar.getInstance().getTimeInMillis(), Double.parseDouble(weighttv.getText().toString()) , (double) PreferenceUtils.getHeight(this));
        DataMethod.addWeightItem(this, weight);

        Intent intent = new Intent(this, Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
