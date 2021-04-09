package in.psgroup.psgroup.Network;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.annotation.Nullable;
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

public class GuestLoader extends AsyncTaskLoader<HashMap<String,String>> {
    private final static String TAG = GuestLoader.class.getName();
    HashMap<String, String> response = new HashMap<>();
    private int responsecode = 0;
    private Context context;

    public GuestLoader(Context context) {
        super(context);
        this.context = context;
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
    protected boolean onCancelLoad() {
        return super.onCancelLoad();
    }

    @Nullable
    @Override
    public HashMap<String, String> loadInBackground() {
        try {
            response = getData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }

    private HashMap<String, String> getData() throws JSONException {
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest();
        } catch (IOException e) {
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
                    response.put("response_message", jsonObject.getString("message"));

                } else {
//                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    //  response.put("Projects", jsonObject.getJSONArray("Projects ").toString()!=null?jsonObject.getJSONArray("Projects ").toString():"");
                    response.put("data", jsonResponse);

                }
//                 response=jsonObject.getString("0");//response_code

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            response.put("response_message", String.valueOf(responsecode));
        }
        return response;
    }

    private String makeHttpRequest() throws IOException, JSONException {
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String jsonResponse = "";
        OkHttpClient client = ApiClient.getClient();
        Gson gson = new Gson();
        Request request = new Request.Builder()
                .url(APIUrls.GUESTAPI)
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
