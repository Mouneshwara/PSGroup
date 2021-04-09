package in.psgroup.psgroup.Utility;

import java.io.IOException;

import in.psgroup.psgroup.PsGroupApplication;
import in.psgroup.psgroup.R;
import okhttp3.Interceptor;
import okhttp3.Response;

public class NetworkInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response;
        response = chain.proceed(chain.request());
        if (!new NetworkUtils().isConnected()) {
            throw (new IOException(PsGroupApplication.getpInstance().getApplicationContext().getResources().getString(R.string.no_internet)));
        }
        return response;
    }
}
