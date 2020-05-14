package com.tanitim.universitetanitim.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.tanitim.universitetanitim.Models.Universiteler;
import com.tanitim.universitetanitim.R;
import com.tanitim.universitetanitim.retrofit.SpinnerInterface;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.R.layout.simple_spinner_item;

public class KarsilastirFragment extends Fragment {
    public KarsilastirFragment() {
    }
    private ArrayList<Universiteler> universitelerArrayList;
    private ArrayList<String> playerNames = new ArrayList<String>();
    private NiceSpinner uni2spin,uni1spin;
    private TextView uni1text,uni2text,textTur1,textTur2,bolge,doktora,lisans,onlisans,bolge2,doktora2,lisans2,onlisans2,yukseklisans1,yukseklisans2;
    public static Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_karsilastir, container, false);
        uni1text=view.findViewById(R.id.uni1text);
        uni1spin = view.findViewById(R.id.uni1spin);
        uni2text=view.findViewById(R.id.uni2text);
        uni2spin = view.findViewById(R.id.uni2spin);
        textTur1=view.findViewById(R.id.textTur1);
        textTur2=view.findViewById(R.id.textTur2);

        bolge=view.findViewById(R.id.bolge1);
        doktora = view.findViewById(R.id.doktora1);
        lisans=view.findViewById(R.id.lisans1);
        onlisans = view.findViewById(R.id.onlisans1);
        bolge2=view.findViewById(R.id.bolge2);
        doktora2=view.findViewById(R.id.doktora2);
        lisans2=view.findViewById(R.id.lisans2);
        onlisans2=view.findViewById(R.id.onlisans2);
        yukseklisans1=view.findViewById(R.id.yukseklisans1);
        yukseklisans2=view.findViewById(R.id.yukseklisans2);



        mContext = container.getContext();

        fetchJSON();


        return view;
    }
    private void fetchJSON() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SpinnerInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        SpinnerInterface api = retrofit.create(SpinnerInterface.class);

        Call<String> call = api.getJSONString();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                // Log.i("Responsestring", response.body()); gelen json veri

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        String jsonresponse = response.body();
                        spinJSON(jsonresponse);
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void spinJSON(String response) {

        try {
            JSONObject obj = new JSONObject(response);
            universitelerArrayList = new ArrayList<>();
            JSONArray dataArray = obj.getJSONArray("universiteler");
            for (int i = 0; i < dataArray.length(); i++) {
                Universiteler spinnerModel = new Universiteler();
                JSONObject dataobj = dataArray.getJSONObject(i);
                spinnerModel.setIsim(dataobj.getString("isim"));
                spinnerModel.setToplam(dataobj.getString("toplam"));
                spinnerModel.setTur(dataobj.getString("tur"));
                spinnerModel.setBolge(dataobj.getString("bolge"));
                spinnerModel.setDoktoratoplam(dataobj.getString("doktoratoplam"));
                spinnerModel.setLisanstoplam(dataobj.getString("lisanstoplam"));
                spinnerModel.setOnlisanstoplam(dataobj.getString("onlisanstoplam"));
                spinnerModel.setYukseklisanstoplam(dataobj.getString("yukseklisanstoplam"));

                universitelerArrayList.add(spinnerModel);

            }

            for (int i = 0; i < universitelerArrayList.size(); i++) {

                playerNames.add(universitelerArrayList.get(i).getIsim());




            }


            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(mContext, simple_spinner_item, playerNames);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view

            playerNames.add(0,"Üniversite Seçiniz");
            uni1spin.attachDataSource(playerNames);
            uni2spin.attachDataSource(playerNames);


            uni1spin.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
                @Override
                public void onItemSelected(NiceSpinner parent, View view, int position, long id) {


                    int pozisyon=position-1;
                    if (position==0) {
                        uni1text.setText("");
                        textTur1.setText("");
                        bolge.setText("");
                        lisans.setText("");
                        onlisans.setText("");
                        doktora.setText("");
                        yukseklisans1.setText("");
                        pozisyon=0;
                    }
                    else {


                        uni1text.setText(universitelerArrayList.get(pozisyon).getToplam());
                        textTur1.setText(universitelerArrayList.get(pozisyon).getTur());
                        bolge.setText(universitelerArrayList.get(pozisyon).getBolge());
                        lisans.setText(universitelerArrayList.get(pozisyon).getLisanstoplam());
                        onlisans.setText(universitelerArrayList.get(pozisyon).getOnlisanstoplam());
                        doktora.setText(universitelerArrayList.get(pozisyon).getDoktoratoplam());
                        yukseklisans1.setText(universitelerArrayList.get(pozisyon).getYukseklisanstoplam());
                    }
                }
            });


            uni2spin.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
                @Override
                public void onItemSelected(NiceSpinner parent, View view, int position, long id) {

                    int pozisyon=position-1;
                    if (position==0) {
                        uni2text.setText("");
                        textTur2.setText("");
                        bolge2.setText("");
                        lisans2.setText("");
                        onlisans2.setText("");
                        doktora2.setText("");
                        yukseklisans2.setText("");
                        pozisyon=0;
                    }
                    else {

                        uni2text.setText(universitelerArrayList.get(pozisyon).getToplam());
                        textTur2.setText(universitelerArrayList.get(pozisyon).getTur());
                        bolge2.setText(universitelerArrayList.get(pozisyon).getBolge());
                        lisans2.setText(universitelerArrayList.get(pozisyon).getLisanstoplam());
                        onlisans2.setText(universitelerArrayList.get(pozisyon).getOnlisanstoplam());
                        doktora2.setText(universitelerArrayList.get(pozisyon).getDoktoratoplam());
                        yukseklisans2.setText(universitelerArrayList.get(pozisyon).getYukseklisanstoplam());
                    }


                }
            });



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
