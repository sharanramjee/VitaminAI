package ai.vitamin.vitaminai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class ImplementCloudVision {

    public static String[] finalans; // 0 - String of Calories; 1 - String of Fat

    static String x_app_id = "bc9bf73b";
    static String x_app_key = "a7ec465b35f9e9320eb17fd0f7dc3b46";

    static String endpointget= "https://api.nutritionix.com/v1_1/search/lays?fields=item_name%2Citem_id%2Cbrand_name%2Cnf_calories%2Cnf_total_fat&appId=bc9bf73b&appKey=a7ec465b35f9e9320eb17fd0f7dc3b46";
    static String query = "lays";

    public static void main() throws IOException{
        // Initialize brand_name
        // now_name=autologger.brand_name
        // String now_name = query;

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

        //for(int i =0 ; i<3; i++){
            //System.out.println(finalans[i]);
        //}
    }
}