package ai.vitamin.vitaminai.introFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ai.vitamin.vitaminai.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class Authentication extends Fragment {
    
    Button submit;
    EditText name;
    EditText username;
    EditText password;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_authentication, container, false);
        username = (EditText) getActivity().findViewById(R.id.email);
        name = (EditText) getActivity().findViewById(R.id.Name);
        password = (EditText) getActivity().findViewById(R.id.password);
        submit = submit.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = username.getText().toString();
                String fullName = name.getText().toString();
                String pass = password.getText().toString();
                PreferenceManager.getDefaultSharedPreferences(getContext())
                        .edit()
                        .putString("name", fullName)
                        .apply();
            }
        });

        return null;
    }




}