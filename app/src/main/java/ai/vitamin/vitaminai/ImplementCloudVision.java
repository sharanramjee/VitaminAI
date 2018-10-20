package ai.vitamin.vitaminai;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static ai.vitamin.vitaminai.data.DataHelper.TAG;


public class ImplementCloudVision {

    public static String[] finalans; // 0 - String of Calories; 1 - String of Fat

    static String x_app_id = "bc9bf73b";
    static String x_app_key = "a7ec465b35f9e9320eb17fd0f7dc3b46";

    static String endpointget= "https://api.nutritionix.com/v1_1/search/lays?fields=item_name%2Citem_id%2Cbrand_name%2Cnf_calories%2Cnf_total_fat&appId=bc9bf73b&appKey=a7ec465b35f9e9320eb17fd0f7dc3b46";
    public static String query = "lays";

    new AsyncTask<Object, Void, String>() {
        @Override
        protected String doInBackground(Object... params) {
            try {


                public static void main(String input) throws IOException{
                    // Initialize brand_name
                    // now_name=autologger.brand_name
                    // String now_name = query;
                    query = input;
                    String base = "https://api.nutritionix.com/v1_1/search/";
                    String finale = "?fields=item_name%2Citem_id%2Cbrand_name%2Cnf_calories%2Cnf_total_fat&appId=bc9bf73b&appKey=a7ec465b35f9e9320eb17fd0f7dc3b46";

                    String based = base.concat(query).concat(finale);
                    endpointget = based;
                    //	System.out.println(based);

                    URL obj = new URL(endpointget);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");
                    String respons = "u";

                    finalans = new String[10];

                    int responseCode = con.getResponseCode();
                    //System.out.println("GET Response Code :: " + responseCode);
                    if (responseCode == HttpURLConnection.HTTP_OK) { // success
                        BufferedReader in = new BufferedReader(new InputStreamReader(
                                con.getInputStream()));
                        String inputLine;
                        StringBuffer response = new StringBuffer();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();
                        // print result
                        //System.out.println(response);
                        respons = response.toString();
                    } else {
                        System.out.println("GET request not worked");
                    }

                    String delims = "[{";
                    String[] tokens = respons.split("nf_calories");
                    //System.out.println(tokens[1]);
                    String[] ttokens = tokens[1].split(",");
                    //String[] calories = respons.split(":");
                    //System.out.println(ttokens[0]);
                    String ans = ttokens[0].substring(2);
                    //System.out.println(ans);
                    float cal,fat;
                    finalans[0] = ans;
                    cal = Float.valueOf(ans);
                    String[] yetr = respons.split("nf_total_fat");
                    String fate = yetr[1].substring(2);
                    String[] fatt = fate.split(",");
                    finalans[1] = fatt[0];
                    fat = Float.valueOf(fatt[0]);
                    String[] fokens = respons.split("item_name");
                    String tags = fokens[1].substring(3);
                    String[] tages = tags.split("-");
                    //System.out.println(tages[0]);
                    //finalans[2] = tages[0];

                    for(int i =0 ; i<2; i++){
                        System.out.println(finalans[i]);
                    }
                }

            } catch (GoogleJsonResponseException e) {
                Log.d(TAG, "failed to make API request because " + e.getContent());
            } catch (IOException e) {
                Log.d(TAG, "failed to make API request because of other IOException " + e.getMessage());
            }
            return "Cloud Vision API request failed. Check logs for details.";
        }

        protected void onPostExecute(String result) {
//                visionAPIData.setText(result);
            brand_name = result;
//                imageUploadProgress.setVisibility(View.INVISIBLE);
        }
    }.execute();



}