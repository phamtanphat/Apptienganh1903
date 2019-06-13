package khoapham.ptp.phamtanphat.apituvung1903.Api.connection;

import android.support.annotation.NonNull;

import java.util.List;

import khoapham.ptp.phamtanphat.apituvung1903.Api.modelapi.TuvungAPus;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APICallback {

    @GET("tuvung.php")
    Call<List<TuvungAPus>> getDataTuvung();

    @GET("updatetuvung.php")
    Call<String> istoggleWord(@Query("id") String id , @Query("isMemorized") String isMemorized);

    @GET("deletetuvung.php")
    Call<String> remove(@Query("id") String id );

    @FormUrlEncoded
    @POST("inserttuvung.php")
    Call<String> insert(@NonNull @Field("en") String en , @Field("vn") String vn);
}
