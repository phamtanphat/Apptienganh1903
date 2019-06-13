package khoapham.ptp.phamtanphat.apituvung1903;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import khoapham.ptp.phamtanphat.apituvung1903.Api.connection.APICallback;
import khoapham.ptp.phamtanphat.apituvung1903.Api.connection.Dataresponse;
import khoapham.ptp.phamtanphat.apituvung1903.Api.modelapi.TuvungAPus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TuvungAdapter extends ArrayAdapter<TuvungAPus> {
    List<TuvungAPus> mangupdate;

    public TuvungAdapter(@NonNull Context context, int resource,@NonNull List<TuvungAPus> objects) {
        super(context, resource, objects);
        mangupdate = objects;
    }
    @NonNull
    @Override
    public View getView(int position,@Nullable View convertView,@NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        convertView = layoutInflater.inflate(R.layout.dong_tuvung_item,null);

        TextView txtEn = convertView.findViewById(R.id.textviewEn);
        TextView txtVn = convertView.findViewById(R.id.textviewVn);
        Button btnToggle = convertView.findViewById(R.id.buttonToggleWord);
        Button btnRemove = convertView.findViewById(R.id.buttonRemoveWord);

        final TuvungAPus tuvung = getItem(position);

        txtEn.setText(tuvung.getEn());
        txtVn.setText(tuvung.getIsMemorized().equals("true") ? "----" : tuvung.getVn());
        btnToggle.setText(tuvung.getIsMemorized().equals("true") ? "Forgot" : "isMemorized");
        btnToggle.setBackgroundColor(tuvung.getIsMemorized().equals("true") ? Color.rgb(33,136,56) : Color.rgb(200,35,51));

        btnToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean memorized = Boolean.parseBoolean(tuvung.getIsMemorized());
                toggleWord(tuvung.getId(), String.valueOf(!memorized));
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tuvung.getId()!= null){
                    remove_word(tuvung.getId());

                }else {
                    Toast.makeText(getContext(), "Gia tri hien tai khong co!!", Toast.LENGTH_SHORT).show();
                }
                updateLayout();

            }
        });
        return convertView;
    }
    private void toggleWord(String id , String isMemorized){
        APICallback apiCallback = Dataresponse.initRequestToServer();
        Call<String> tuvungcallback = apiCallback.istoggleWord(id,isMemorized);
        tuvungcallback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String ketqua = response.body();

                if (ketqua == null){
                    Toast.makeText(getContext(), "Khon co tu khoa", Toast.LENGTH_SHORT).show();
                }else{
                    if (ketqua.equals("true")){
                        Toast.makeText(getContext(), "Thanh cong", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "That bai", Toast.LENGTH_SHORT).show();
                    }
                    updateLayout();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
    private void updateLayout() {
        APICallback apiCallback = Dataresponse.initRequestToServer();
        Call<List<TuvungAPus>> tuvungcallback = apiCallback.getDataTuvung();
        tuvungcallback.enqueue(new Callback<List<TuvungAPus>>() {
            @Override
            public void onResponse(Call<List<TuvungAPus>> call, Response<List<TuvungAPus>> response) {
                List<TuvungAPus> mangtuvung =  response.body();
                mangupdate.clear();
                mangupdate.addAll(mangtuvung);
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<TuvungAPus>> call, Throwable t) {

            }
        });
    }
    private void remove_word(String id){
        APICallback apiCallback = Dataresponse.initRequestToServer();
        Call<String> tuvungcallback = apiCallback.remove(id);
        tuvungcallback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String ketqua = response.body();

                if (ketqua == null){
                    Toast.makeText(getContext(), "Khon co tu khoa", Toast.LENGTH_SHORT).show();
                }else{
                    if (ketqua.equals("true")){
                        Toast.makeText(getContext(), "Thanh cong", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "That bai", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}