package ai.vitamin.vitaminai.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ai.vitamin.vitaminai.PreferenceUtils;
import ai.vitamin.vitaminai.R;


public class AboutFragment extends Fragment {

    public AboutFragment() {
    }

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView name = view.findViewById(R.id.name);
        TextView height = view.findViewById(R.id.height);
        TextView weight = view.findViewById(R.id.weight);
        TextView age = view.findViewById(R.id.age);

        name.setText(PreferenceUtils.getName(getContext()));
        height.setText(Float.toString(PreferenceUtils.getHeight(getContext())));
        weight.setText(Float.toString(PreferenceUtils.getWeight(getContext())));
        age.setText(Integer.toString(PreferenceUtils.getAge(getContext())));
    }
}
