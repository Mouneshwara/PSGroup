package in.psgroup.psgroup.Network;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import in.psgroup.psgroup.Models.APIUrls;
import in.psgroup.psgroup.Models.ApiClient;
import in.psgroup.psgroup.Models.RequestsBean;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FeedbackLoader extends AsyncTaskLoader<String> {
    private final static String TAG = FeedbackLoader.class.getName();
    String response_message;
    private final String urlNews=null;
    private String AccessToken;
    private  Context context;
    public FeedbackLoader(Context context, String AccessToken) {
        super(context);
        this.context=context;
        this.AccessToken=AccessToken;
    }

    @Override
    protected void onStartLoading() {
        if(response_message!=null){
            deliverResult(response_message);
        }else {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
    }

    @Override
    public void cancelLoadInBackground() {
        super.cancelLoadInBackground();
    }

    @Override
    public String loadInBackground() {
        ArrayList<RequestsBean> requestsBeanArrayList=new ArrayList<>();
        String response_message=null;
        try {
            response_message= getData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response_message;
    }


    @Override
    public void deliverResult(String data) {
        response_message=data;
        super.deliverResult(data);
    }

    /**
     * api call
     *
     * @return arraylist of RequestModel
     */
    private String getData() throws JSONException {
        // Create URL object

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest();
        } catch (IOException e) {
        }
        Log.e(TAG, jsonResponse);
        return extractNewsData(jsonResponse);
    }



    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private String makeHttpRequest() throws IOException,JSONException {
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String jsonResponse = "";
        OkHttpClient client = ApiClient.getClient();
        JSONObject json = new JSONObject();
        json.put("support_type", "Requests");
        String jsonString = json.toString();
        Log.d(TAG, "json:" + jsonString);
        RequestBody body = RequestBody.create(JSON, jsonString);
        Request request = new Request.Builder()
                .url(APIUrls.SUPPORT_QUERY_API)
                .header("access_token",AccessToken)
                .post(body)
                .build();
        Response responses = null;

        responses = client.newCall(request).execute();
        if (responses != null) {
//            response_code = responses.code();
            String jsonData = responses.body().string();
            Log.d(TAG, "response:" + jsonData);
            jsonResponse=jsonData;
//            responseJson = new JSONObject(jsonData);
        }
        return jsonResponse;
    }
    private String extractNewsData(String jsonResponse) {
        Gson gson=new Gson();
        if (!TextUtils.isEmpty(jsonResponse)) {
            try {
                JSONObject jsonObject=new JSONObject(jsonResponse);
                 response_message=jsonObject.getString("0");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return response_message;
    }
}
