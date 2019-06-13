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

import java.util.List;

import khoapham.ptp.phamtanphat.apituvung1903.Api.modelapi.TuvungAPus;

public class TuvungAdapter extends ArrayAdapter<TuvungAPus> {
    public TuvungAdapter(@NonNull Context context, int resource,@NonNull List<TuvungAPus> objects) {
        super(context, resource, objects);
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

        TuvungAPus tuvung = getItem(position);

        txtEn.setText(tuvung.getEn());
        txtVn.setText(tuvung.getIsMemorized().equals("true") ? "----" : tuvung.getVn());
        btnToggle.setText(tuvung.getIsMemorized().equals("true") ? "Forgot" : "isMemorized");
        btnToggle.setBackgroundColor(tuvung.getIsMemorized().equals("true") ? Color.rgb(33,136,56) : Color.rgb(200,35,51));
        return convertView;
    }
}