package khoapham.ptp.phamtanphat.apituvung1903;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import khoapham.ptp.phamtanphat.apituvung1903.Api.connection.APICallback;
import khoapham.ptp.phamtanphat.apituvung1903.Api.connection.Dataresponse;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        APICallback apiCallback = Dataresponse.initRequestToServer();

    }
}
