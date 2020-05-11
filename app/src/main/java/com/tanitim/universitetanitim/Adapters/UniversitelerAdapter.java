package com.tanitim.universitetanitim.Adapters;

import android.content.Context;
import android.content.Intent;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tanitim.universitetanitim.Activity.DetayActivity;
import com.tanitim.universitetanitim.Fragments.DetayBilgiFragment;
import com.tanitim.universitetanitim.R;
import com.tanitim.universitetanitim.Models.Universiteler;
import com.tanitim.universitetanitim.retrofit.UniversitelerDAOinterface;

import java.io.IOException;
import java.util.List;

public class UniversitelerAdapter extends RecyclerView.Adapter<UniversitelerAdapter.CardTasarimTutucu> {
    private Context mContext;
    private List<Universiteler> universitelerListe;
    private UniversitelerDAOinterface universitelerDAOinterface;

    public UniversitelerAdapter(Context mContext, List<Universiteler> universitelerListe
            , UniversitelerDAOinterface universitelerDAOinterface) {
        this.mContext = mContext;
        this.universitelerListe = universitelerListe;
        this.universitelerDAOinterface = universitelerDAOinterface;
    }

    @Override
    public CardTasarimTutucu onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_universite, parent, false);

        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(final CardTasarimTutucu holder, final int position) {
        final Universiteler universite = universitelerListe.get(position);





        Glide.with(mContext).load("https://www.diziblog.net/yekimsdfdf/yedek/logo/" +
                universitelerListe.get(position).getSlug() + "-logo.jpg").into(holder.logo);   //Universite Logo
        Glide.with(mContext).load("https://www.diziblog.net/yekimsdfdf/yedek/resim/" +
                universitelerListe.get(position).getSlug() + "-1.jpg").diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.arkaplan);//Universite resim

        holder.textViewIsim.setText(universite.getIsim()); //universite adı textview
        holder.textViewSehir.setText(universite.getIl() + ""); // il textView
        holder.textViewDetay.setText(universite.getToplamkadin());
        //carda tıklandığında olucaklar
        holder.universiteler_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetayActivity.class);
                intent.putExtra("nesne", universite);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                mContext.startActivity(intent);
            }
        });






    }

    @Override
    public int getItemCount() {
        return universitelerListe.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder {
        private TextView textViewIsim;
        private TextView textViewSehir, textViewDetay;
        private CardView universiteler_card;
        private ImageView logo, arkaplan;

        public CardTasarimTutucu(View itemView) {
            super(itemView);
            textViewIsim = itemView.findViewById(R.id.textViewIsim);
            textViewDetay = itemView.findViewById(R.id.textViewDetay);
            textViewSehir = itemView.findViewById(R.id.textViewSehir);
            universiteler_card = itemView.findViewById(R.id.universiteler_card);
            logo = itemView.findViewById(R.id.logo);
            arkaplan = itemView.findViewById(R.id.arkaplan);
        }
    }


}
