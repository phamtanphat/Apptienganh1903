package khoapham.ptp.phamtanphat.apituvung1903.Api.connection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInit {
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit(String base_url){
        //setup về bên mạng
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                                    .readTimeout(10, TimeUnit.SECONDS)
                                    .writeTimeout(10,TimeUnit.SECONDS)
                                    .retryOnConnectionFailure(true)
                                    .connectTimeout(10,TimeUnit.SECONDS)
                                    .protocols(Arrays.asList(Protocol.HTTP_1_1))
                                    .build();
        //xu lý cho json bên server
        Gson gson = new GsonBuilder().setLenient().create();

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                        .baseUrl(base_url)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
        }

        return retrofit;
    }
}
