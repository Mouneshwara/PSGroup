package in.psgroup.psgroup.Network;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import in.psgroup.psgroup.Models.APIUrls;
import in.psgroup.psgroup.Models.ApiClient;
import in.psgroup.psgroup.Models.ComplaintsBean;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class QueryExplainLoader extends AsyncTaskLoader<HashMap<String,String>> {
    private final static String TAG = QueryExplainLoader.class.getName();
    HashMap<String,String> query_explain;
    private final String urlNews=null;
    private String AccessToken;
    private String category_id;
    private  Context context;
    public QueryExplainLoader(Context context, String AccessToken,String category_id) {
        super(context);
        this.context=context;
        this.AccessToken=AccessToken;
        this.category_id=category_id;
    }

    @Override
    protected void onStartLoading() {
        if(query_explain!=null){
            deliverResult(query_explain);
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
    public HashMap<String,String> loadInBackground() {
        HashMap<String,String> query_explain=new HashMap<String,String>();
        try {
            query_explain= getData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return query_explain;
    }


    @Override
    public void deliverResult(HashMap<String,String> data) {
        query_explain=data;
        super.deliverResult(data);
    }

    /**
     * api call
     *
     * @return arraylist of ComplaintModel
     */
    private HashMap<String,String> getData() throws JSONException {
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
        json.put("sk_category_id", category_id);
        String jsonString = json.toString();
        Log.d(TAG, "json:" + jsonString);
        RequestBody body = RequestBody.create(JSON, jsonString);
        Request request = new Request.Builder()
                .url(APIUrls.SUPPORT_DETAIL_API)
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
    private HashMap<String,String> extractNewsData(String jsonResponse) throws JSONException {
        query_explain = new HashMap<String,String>();
        Gson gson=new Gson();
        if (!TextUtils.isEmpty(jsonResponse)) {
            JSONObject jsonObject=new JSONObject(jsonResponse);
            JSONArray jsonArray=jsonObject.getJSONArray("support_type");
            Type type = new TypeToken<HashMap<String, String>>(){}.getType();
            query_explain = gson.fromJson(jsonArray.getJSONObject(0).toString(), type);
        }
        return query_explain;
    }
}
