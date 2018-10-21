package ai.vitamin.vitaminai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.algolia.search.saas.AlgoliaException;
import com.algolia.search.saas.Client;
import com.algolia.search.saas.CompletionHandler;
import com.algolia.search.saas.Index;
import com.algolia.search.saas.Query;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import ai.vitamin.vitaminai.data.DataMethod;
import ai.vitamin.vitaminai.objects.Food;
import cz.msebera.android.httpclient.entity.StringEntity;

public class ManualLogger extends AppCompatActivity {

    public Integer autocomplete_idx = 0;
    public String autocomplete_str = "durian";

    public static EditText autoField;
    public static EditText autoQty;

    public static Double calories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_logger);

        autoField = (EditText) findViewById(R.id.editText);
        autoQty = (EditText) findViewById(R.id.editText2);

        Button btnLog = (Button)findViewById(R.id.btnLog);
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentManual = new Intent(ManualLogger.this, Home.class);
                connectNutrionix(autoField.getText().toString());
                startActivity(intentManual);
                finish();
            }
        } );

//        Food food = new Food(Calendar.getInstance().getTimeInMillis(), autoField.getText(), Double.parseDouble(autoQty.getText().toString()), calories,0.0);
    }

    protected void algoliaQuery(View view) {

        Client client = new Client("91WCKSBFTE", "29ddfa0c991845808986cf2d56cb93c2");
        Index index = client.getIndex("fruits_list");

        CompletionHandler completionHandler = new CompletionHandler() {
            @Override
            public void requestCompleted(JSONObject content, AlgoliaException error) {

                try {
                    autocomplete_idx = content.getJSONArray("hits").getJSONObject(0).getJSONObject("_highlightResult").getJSONArray("fruits").toString().split("full")[0].split("value").length;
                    autocomplete_str = content.getJSONArray("hits").getJSONObject(0).getJSONArray("fruits").toString().split("\",\"")[autocomplete_idx-2];
                    if(autocomplete_str.substring(0,1).equals("[")) {
                        autoField.setText("apple");
                    }
                    else {
                        autoField.setText(autocomplete_str);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        index.searchAsync(new Query(autoField.getText()), completionHandler);
    }

    private void connectNutrionix(String foodName) {
        StringEntity stringEntity = null;
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("x-app-id", "bc9bf73b");
        client.addHeader("x-app-key", "a7ec465b35f9e9320eb17fd0f7dc3b46");
        client.addHeader("x-remote-user-id", "0");
        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("query", foodName);

        } catch (JSONException e) {
            Log.d("Healthier", e.toString());
        }
        try {
            stringEntity = new StringEntity(jsonObject.toString());
        } catch (UnsupportedEncodingException e) {
            Log.d("Healthier", e.toString());
        }

        client.post(this, "https://trackapi.nutritionix.com/v2/natural/nutrients", stringEntity, "application/json",
            new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                    Log.d("Healthier", "Success" + response.toString());
                    finishCalculate(response);
                }

                @Override
                public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    Log.d("Healthier", "failed" + errorResponse.toString());
                    Toast.makeText(ManualLogger.this, "Sorry, We couldn't match any of your foods",
                            Toast.LENGTH_SHORT).show();
                }
            });
    }

    protected void finishCalculate(final JSONObject jsonResult) {
        StringBuilder foodName = new StringBuilder();
        StringBuilder foodCal = new StringBuilder();
        StringBuilder foodWeight = new StringBuilder();

        try {
            JSONArray foods = jsonResult.getJSONArray("foods");
            int i = 0;
            while (!foods.isNull(i)) {

                JSONObject eachFood = foods.getJSONObject(i);
                foodName.append(eachFood.getString("food_name"));
                foodCal.append(eachFood.getString("nf_calories"));
                foodWeight.append(eachFood.getString("serving_qty"));
                i++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        calories = Double.parseDouble(foodCal.toString()) / Double.parseDouble(foodWeight.toString());
        Food food = new Food(Calendar.getInstance().getTimeInMillis(), autoField.getText().toString(), Double.parseDouble(autoQty.getText().toString()), calories,0.0);
//                    System.out.println(food.toString());
        DataMethod.addFoodItem(ManualLogger.this, food);
//        System.out.println(calories.toString());
    }
}
