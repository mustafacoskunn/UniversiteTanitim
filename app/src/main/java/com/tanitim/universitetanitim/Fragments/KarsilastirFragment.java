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
    private Spinner uni2spin,uni1spin;
    private TextView uni1text,uni2text;
    public static Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_karsilastir, container, false);
        uni1text=view.findViewById(R.id.uni1text);
        uni1spin = view.findViewById(R.id.uni1spin);
        uni2text=view.findViewById(R.id.uni2text);
        uni2spin = view.findViewById(R.id.uni2spin);
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
                spinnerModel.setIl(dataobj.getString("il"));
                universitelerArrayList.add(spinnerModel);

            }

            for (int i = 0; i < universitelerArrayList.size(); i++) {
                playerNames.add(universitelerArrayList.get(i).getIsim());



            }


            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(mContext, simple_spinner_item, playerNames);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            uni1spin.setAdapter(spinnerArrayAdapter);
            uni2spin.setAdapter(spinnerArrayAdapter);

            uni1spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    uni1text.setText(universitelerArrayList.get(position).getIl());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            uni2spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    uni2text.setText(universitelerArrayList.get(position).getIl());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
