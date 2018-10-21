package ai.vitamin.vitaminai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.shchurov.horizontalwheelview.HorizontalWheelView;

import java.util.Locale;

public class EnterInfo extends AppCompatActivity {

    private HorizontalWheelView horizontalWheelView;
    private TextView tvAngle1;

    public static String myHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_info);
        initViews();
        setupListeners();
        updateUi();
    }

    private void initViews() {
        horizontalWheelView = (HorizontalWheelView) findViewById(R.id.horizontalWheelView);
        tvAngle1 = (TextView) findViewById(R.id.tvAngle1);
    }

    private void updateText() {
        String text = String.format(Locale.US, "%.0f", horizontalWheelView.getDegreesAngle());
        tvAngle1.setText(text);
    }

    private void updateUi() {
        updateText();
    }

    private void setupListeners() {
        horizontalWheelView.setListener(new HorizontalWheelView.Listener() {
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
