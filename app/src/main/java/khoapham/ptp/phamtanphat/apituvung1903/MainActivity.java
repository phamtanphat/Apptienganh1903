package khoapham.ptp.phamtanphat.apituvung1903;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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

public class MainActivity extends AppCompatActivity implements ListenData {

    ListView lvTuvung;
    TuvungAdapter tuvungAdapter,tuvungAdapterFilter;
    RelativeLayout relativeFormtrue,relativeFormfalse;
    Button btnForm,btnAddWord,btnCancel;
    EditText edtEn,edtVn;
    ArrayList<TuvungAPus> tuvungsfilter = new ArrayList<>();
    Spinner spinner;
    String[] mangoption = {"Show_All","Show_Forgot","Show_Memorized"};
    ArrayAdapter spinnerAdapter;
    ListenData listenData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listenData = this;
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
        btnAddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String en = edtEn.getText().toString().trim();
                String vn = edtVn.getText().toString().trim();
                if (en.length() >0 && vn.length() >0){
                    insert_word(en , vn);
                    relativeFormtrue.setVisibility(View.GONE);
                    relativeFormfalse.setVisibility(View.VISIBLE);
                    edtVn.setText("");
                    edtEn.setText("");
                    getWord();
                }else {
                    Toast.makeText(MainActivity.this, "Xem lai thong tin chua day du", Toast.LENGTH_SHORT).show();
                }
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
        edtEn = findViewById(R.id.edittextEn);
        edtVn = findViewById(R.id.edittextVn);
        spinner = findViewById(R.id.spinner);
        spinnerAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,mangoption);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

    }

    private void getWord() {
        APICallback apiCallback = Dataresponse.initRequestToServer();
        Call<List<TuvungAPus>> tuvungcallback = apiCallback.getDataTuvung();
        tuvungcallback.enqueue(new Callback<List<TuvungAPus>>() {
            @Override
            public void onResponse(Call<List<TuvungAPus>> call, Response<List<TuvungAPus>> response) {
                ArrayList<TuvungAPus> mangtuvung = (ArrayList<TuvungAPus>) response.body();
                if (mangtuvung.size() > 0){
                    tuvungAdapter = new TuvungAdapter(MainActivity.this,android.R.layout.simple_list_item_1,mangtuvung);
                    lvTuvung.setAdapter(tuvungAdapter);
                    listenData.onSuccessData(mangtuvung);
                }

            }

            @Override
            public void onFailure(Call<List<TuvungAPus>> call, Throwable t) {

            }
        });
    }

    private void insert_word(String en , String vn){
        APICallback apiCallback = Dataresponse.initRequestToServer();
        Call<String> tuvungcallback = apiCallback.insert(en , vn);
        tuvungcallback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String ketqua = response.body();

                if (ketqua == null){
                    Toast.makeText(MainActivity.this, "Khon co tu khoa", Toast.LENGTH_SHORT).show();
                }else{
                    if (ketqua.equals("true")){
                        Toast.makeText(MainActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "That bai", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    @Override
    public void onSuccessData(final ArrayList<TuvungAPus> mangtuvungs) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tuvungsfilter.clear();
                tuvungAdapterFilter = new TuvungAdapter(MainActivity.this,android.R.layout.simple_list_item_1,tuvungsfilter);
                lvTuvung.setAdapter(tuvungAdapterFilter);
                switch (mangoption[position]){
                    case "Show_All" :
                        tuvungsfilter.addAll(mangtuvungs);
                        tuvungAdapterFilter.notifyDataSetChanged();
                        break;
                    case "Show_Forgot" :
                        for (int i = 0 ; i<mangtuvungs.size() ;i++){
                            if (mangtuvungs.get(i).getIsMemorized().equals("true")){
                                tuvungsfilter.add(mangtuvungs.get(i));
                            }
                        }
                        tuvungAdapterFilter.notifyDataSetChanged();
                        break;
                    case "Show_Memorized" :
                        for (int i = 0 ; i<mangtuvungs.size() ;i++){
                            if (mangtuvungs.get(i).getIsMemorized().equals("false")){
                                tuvungsfilter.add(mangtuvungs.get(i));
                            }
                        }
                        tuvungAdapterFilter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onFail(String error) {

    }
}
