package ai.vitamin.vitaminai;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private float thexcor;
    private float theycor;
    void get_cordinates(){
        StringEntity stringEntity = null;
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("app_id", "sGpfaLAc1QCajLzW394h");
        client.addHeader("app_code", "vn7qW0PkVTm6TolwWtuCww");
        client.addHeader("dep", "49.36446767,-123.119102027");
        client.addHeader("arr", "49.21229508,-123.106722459");
        client.addHeader("time", "2018-11-19T07%3A30%3A00");
        client.addHeader("details", "0");
        client.addHeader("intermodal_max", "1");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("profile","parkandride");

        } catch (JSONException e) {
            Log.d("route", e.toString());
        }
        try {
            stringEntity = new StringEntity(jsonObject.toString());
        } catch (UnsupportedEncodingException e) {
            Log.d("Healthier", e.toString());
        }

       // client.get(this, "https://mobility.api.here.com/v1/route.json?", stringEntity, "application/json",
                new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                    }

                    @Override
                    public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    }
                };

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        get_cordinates();
        LatLng Here = new LatLng(40.424484, -86.912640);
        //LatLng Here = new LatLng(thexcor, theycor);
        mMap.addMarker(new MarkerOptions().position(Here).title("Hicks"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Here));
    }
}
