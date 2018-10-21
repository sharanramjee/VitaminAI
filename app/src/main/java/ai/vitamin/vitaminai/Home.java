package ai.vitamin.vitaminai;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.support.v4.app.Fragment;

import ai.vitamin.vitaminai.fragments.AboutFragment;
import ai.vitamin.vitaminai.fragments.HomeFragment;
//import ai.vitamin.vitaminai.fragments.MealsFragment;
import ai.vitamin.vitaminai.fragments.MealsFragment;
import ai.vitamin.vitaminai.fragments.TrendFragment;

public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //getting the views
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        //setting the behavior declared above to the bottom navigation object
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        //displaying the first view -> navigation home
        loadFragment(HomeFragment.newInstance());

    }

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

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment)
    {
        if (fragment != null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_home_frame_layout, fragment)
                    .commit();
            return true;
        }

        return false;
    }
}
