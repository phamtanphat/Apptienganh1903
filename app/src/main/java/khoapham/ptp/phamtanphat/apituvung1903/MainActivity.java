package khoapham.ptp.phamtanphat.apituvung1903;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import khoapham.ptp.phamtanphat.apituvung1903.Api.connection.APICallback;
import khoapham.ptp.phamtanphat.apituvung1903.Api.connection.Dataresponse;
import khoapham.ptp.phamtanphat.apituvung1903.Api.modelapi.TuvungAPus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ListView lvTuvung;
    TuvungAdapter tuvungAdapter;
    RelativeLayout relativeFormtrue,relativeFormfalse;
    Button btnForm,btnAddWord,btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        event();
        getWord();

    }

    private void event() {
        btnForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeFormtrue.setVisibility(View.VISIBLE);
                relativeFormfalse.setVisibility(View.GONE);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeFormtrue.setVisibility(View.GONE);
                relativeFormfalse.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initView() {
        lvTuvung = findViewById(R.id.listviewWord);
        relativeFormfalse = findViewById(R.id.relativeFormfalse);
        relativeFormtrue = findViewById(R.id.relativeFormtrue);
        btnForm = findViewById(R.id.buttonForm);
        btnAddWord = findViewById(R.id.buttonAddword);
        btnCancel = findViewById(R.id.buttonCancel);
    }

    private void getWord() {
        APICallback apiCallback = Dataresponse.initRequestToServer();
        Call<List<TuvungAPus>> tuvungcallback = apiCallback.getDataTuvung();
        tuvungcallback.enqueue(new Callback<List<TuvungAPus>>() {
            @Override
            public void onResponse(Call<List<TuvungAPus>> call, Response<List<TuvungAPus>> response) {
                ArrayList<TuvungAPus> mangtuvung = (ArrayList<TuvungAPus>) response.body();

                tuvungAdapter = new TuvungAdapter(MainActivity.this,android.R.layout.simple_list_item_1,mangtuvung);
                lvTuvung.setAdapter(tuvungAdapter);
            }

            @Override
            public void onFailure(Call<List<TuvungAPus>> call, Throwable t) {

            }
        });
    }


}
