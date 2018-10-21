package ai.vitamin.vitaminai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.shchurov.horizontalwheelview.HorizontalWheelView;

import java.util.Locale;

public class EnterInfo extends AppCompatActivity {

    private HorizontalWheelView horizontalWheelView1;
    private HorizontalWheelView horizontalWheelView2;
    private HorizontalWheelView horizontalWheelView3;
    private TextView tvAngle1;
    private TextView tvAngle2;
    private TextView tvAngle3;

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
        tvAngle1 = (TextView) findViewById(R.id.tvAngle1);
        tvAngle2 = (TextView) findViewById(R.id.tvAngle2);
        tvAngle3 = (TextView) findViewById(R.id.tvAngle3);
    }

    private void updateText() {
        String text1 = String.format(Locale.US, "%.0f", horizontalWheelView1.getDegreesAngle());
        tvAngle1.setText(text1);
        String text2 = String.format(Locale.US, "%.0f", horizontalWheelView2.getDegreesAngle());
        tvAngle2.setText(text2);
        String text3 = String.format(Locale.US, "%.0f", horizontalWheelView3.getDegreesAngle());
        tvAngle3.setText(text3);
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
}
