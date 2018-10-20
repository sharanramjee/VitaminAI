package ai.vitamin.vitaminai;

import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import ai.vitamin.vitaminai.fragments.AboutFragment;
import ai.vitamin.vitaminai.fragments.HomeFragment;
import ai.vitamin.vitaminai.fragments.MealsFragment;
import ai.vitamin.vitaminai.fragments.TrendFragment;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //getting the views
        final FrameLayout frameLayout = findViewById(R.id.activity_home_frame_layout);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        //setting on tap behavior for the bottom navigation view
        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        fragment = HomeFragment.newInstance();
                        break;
                    case R.id.navigation_trends:
                        fragment = TrendFragment.newInstance();
                        break;
                    case R.id.navigation_meal_logs:
                        fragment = MealsFragment.newInstance();
                        break;
                    case R.id.navigation_about:
                        fragment = AboutFragment.newInstance();
                        break;
                }
                getSupportFragmentManager().beginTransaction().add(R.id.activity_home_frame_layout, fragment).commit();
                return true;
            }
        };

        //setting the behavior declared above to the bottom navigation object
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //displaying the first view -> navigation home
        getSupportFragmentManager().beginTransaction().add(R.id.activity_home_frame_layout, new HomeFragment()).commit();



    }

}
