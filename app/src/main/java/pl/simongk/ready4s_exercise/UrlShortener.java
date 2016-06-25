package pl.simongk.ready4s_exercise;

/**
 * Created by simongk on 24.06.16.
 */


import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UrlShortener {

    public static String shortUrl(String longUrl) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Gson gson = new Gson();

        RequestModel requestModel = new RequestModel(longUrl);
        String postBody = gson.toJson(requestModel);
        Request request = new Request.Builder()
                .url(Utils.BASE_URL + Utils.API_KEY)
                .post(RequestBody.create(Utils.MEDIA_TYPE, postBody))
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if(!response.isSuccessful()) return null;
            String responseStr = response.body().string();
            ResponseModel responseModel = gson.fromJson(responseStr, ResponseModel.class);
            return responseModel.getId();
        } catch (IOException io) {
            io.printStackTrace();
        }
        return null;
    }

}
