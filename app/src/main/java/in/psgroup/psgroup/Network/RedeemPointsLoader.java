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
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RedeemPointsLoader extends AsyncTaskLoader<HashMap<String, String>> {
    private final static String TAG = RedeemPointsLoader.class.getName();
    private Context mContext;
    private String acesstoken;
    private String id;
    HashMap<String, String> input = new HashMap<>();
    HashMap<String, String> response = new HashMap<>();
    private int responsecode = 0;
    public RedeemPointsLoader(Context context, String AccessToken, HashMap<String, String> input) {
        super(context);
        this.mContext=context;
        this.acesstoken=AccessToken;
        this.input=input;
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
    public void deliverResult(HashMap<String, String> data) {
        response=data;
        super.deliverResult(data);
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
    }

    @Override
    protected boolean onCancelLoad() {
        return super.onCancelLoad();
    }

    @Override
    public HashMap<String, String> loadInBackground()  {
        try {
            response = getData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }

    private HashMap<String, String> getData()throws JSONException {
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e(TAG, jsonResponse);
        return extractResponseData(jsonResponse);
    }

    private HashMap<String, String> extractResponseData(String jsonResponse) {
        Gson gson = new Gson();
        if (!TextUtils.isEmpty(jsonResponse)) {
            try {

                Type type = new TypeToken<HashMap<String, String>>() {
                }.getType();

                response.put(getContext().getString(R.string.response_code), String.valueOf(responsecode));
                if (responsecode != 200) {

                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    response.put("message", jsonObject.getString("message"));

                } else {
                    response.put("data", jsonResponse);


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            response.put(getContext().getString(R.string.response_code), String.valueOf(responsecode));
        }
        return response;
    }

    private String makeHttpRequest() throws IOException{
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String jsonResponse = "";
        OkHttpClient client = ApiClient.getClient();
        Gson gson = new Gson();
        String jsonString = gson.toJson(input);
        Log.d(TAG, "json:" + jsonString);
        RequestBody body = RequestBody.create(JSON, jsonString);
        Request request = new Request.Builder()
                .url(APIUrls.RedeemPoints_API)
                .addHeader("Content-Type","application/json")
                .addHeader("access_token",acesstoken)
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
}
