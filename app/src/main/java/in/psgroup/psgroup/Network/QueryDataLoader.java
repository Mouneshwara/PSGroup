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
import in.psgroup.psgroup.Models.QueryBean;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class QueryDataLoader extends AsyncTaskLoader<ArrayList<QueryBean>> {
    private final static String TAG = QueryDataLoader.class.getName();
    ArrayList<QueryBean> queryBeanArrayList;
    private final String urlNews=null;
    private String AccessToken;
    private  Context context;
    public QueryDataLoader(Context context,String AccessToken) {
        super(context);
        this.context=context;
        this.AccessToken=AccessToken;
    }

    @Override
    protected void onStartLoading() {
        if(queryBeanArrayList!=null){
            deliverResult(queryBeanArrayList);
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
    public ArrayList<QueryBean> loadInBackground() {
        ArrayList<QueryBean> queryBeanArrayList=new ArrayList<>();
        try {
            queryBeanArrayList= getData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return queryBeanArrayList;
    }


    @Override
    public void deliverResult(ArrayList<QueryBean> data) {
        queryBeanArrayList=data;
        super.deliverResult(data);
    }

    /**
     * api call
     *
     * @return arraylist of QueryModel
     */
    private ArrayList<QueryBean> getData() throws JSONException {
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
        json.put("support_type", "Queries");
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
    private ArrayList<QueryBean> extractNewsData(String jsonResponse) {
     queryBeanArrayList = new ArrayList<>();
//        Type listType = new TypeToken<ArrayList<QueryBean>>() {}.getType();
        Gson gson=new Gson();
        if (!TextUtils.isEmpty(jsonResponse)) {
            try {
                JSONObject jsonObject=new JSONObject(jsonResponse);
                JSONArray jsonArray=jsonObject.getJSONArray("support_type");
                for (int i=0;i<jsonArray.length();i++){
                    QueryBean queryBean= gson.fromJson(jsonArray.getJSONObject(i).toString(), QueryBean.class);
                    queryBeanArrayList.add(queryBean);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return queryBeanArrayList;
    }
}
