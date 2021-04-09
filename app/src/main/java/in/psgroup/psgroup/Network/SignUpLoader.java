package in.psgroup.psgroup.Network;


import android.content.AsyncTaskLoader;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

import in.psgroup.psgroup.Models.APIUrls;
import in.psgroup.psgroup.Models.ApiClient;
import in.psgroup.psgroup.R;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUpLoader extends AsyncTaskLoader<HashMap<String, String>> {
    private final static String TAG = SignUpLoader.class.getName();
    private final String urlNews = null;
    HashMap<String, String> response = new HashMap<>();
    HashMap<String, String> input = new HashMap<>();
    private int responsecode = 0;
    private String AccessToken;
    private Context context;

    public SignUpLoader(Context context, HashMap<String, String> input) {
        super(context);
        this.context = context;
        this.input = input;
    }

    @Override
    protected void onStartLoading() {
        if (!response.isEmpty()) {
            deliverResult(response);
        } else {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
    }

    @Override
    public HashMap<String, String> loadInBackground() {
//        HashMap<String,String> query_explain=new HashMap<String,String>();
        try {
            response = getData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public void cancelLoadInBackground() {
        super.cancelLoadInBackground();
    }


    @Override
    public void deliverResult(HashMap<String, String> data) {
        response = data;
        super.deliverResult(data);
    }

    /**
     * api call
     *
     * @return result of RequestModel
     */
    private HashMap<String, String> getData() throws JSONException {
        // Create URL object

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest();
        } catch (IOException e) {
        }
        Log.e(TAG, jsonResponse);
        return extractResponseData(jsonResponse);
    }


    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private String makeHttpRequest() throws IOException, JSONException {
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String jsonResponse = "";
        OkHttpClient client = ApiClient.getClient();
        Gson gson = new Gson();
        String jsonString = gson.toJson(input);
        Log.d(TAG, "json:" + jsonString);
        RequestBody body = RequestBody.create(JSON, jsonString);


        Request request = new Request.Builder()
                .url(APIUrls.SIGNUP_API)
                .header("Content-Type", "application/json")
                .post(body)
                .build();
        Response responses = null;

        responses = client.newCall(request).execute();
        if (responses != null) {
            responsecode = responses.code();
            String jsonData = responses.body().string();
            Log.d(TAG, "response:" + jsonData);
            jsonResponse = jsonData;
//            responseJson = new JSONObject(jsonData);
        }
        return jsonResponse;
    }

    private HashMap<String, String> extractResponseData(String jsonResponse) {
        Gson gson = new Gson();
        if (!TextUtils.isEmpty(jsonResponse)) {
            try {
                JSONObject jsonObject = new JSONObject(jsonResponse);
                Type type = new TypeToken<HashMap<String, String>>() {
                }.getType();
                response.put(getContext().getString(R.string.response_code), String.valueOf(responsecode));
                if ((responsecode != 200)&& (responsecode!=203)) {
                    if(jsonObject.has("status"))
                    {
                        response.put("status", jsonObject.getString("status"));
                        response.put("message", jsonObject.getString("message"));
                    }else {
                        response.put("message", jsonObject.getString("message"));
                    }
                }
                else {
                    response.put("Data", jsonResponse);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            response.put(getContext().getString(R.string.response_code), String.valueOf(responsecode));
        }
        return response;
    }
}
