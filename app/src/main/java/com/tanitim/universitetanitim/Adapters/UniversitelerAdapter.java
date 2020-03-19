package com.tanitim.universitetanitim.Adapters;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tanitim.universitetanitim.Activity.DetayActivity;
import com.tanitim.universitetanitim.R;
import com.tanitim.universitetanitim.Models.Universiteler;
import com.tanitim.universitetanitim.retrofit.UniversitelerDAOinterface;

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


       /* holder.resimProgress.getIndeterminateDrawable().setColorFilter(mContext.getResources()
                .getColor(R.color.purple_inactive), PorterDuff.Mode.SRC_IN); //ProgressBar Rengi */


        Glide.with(mContext).load("https://tohere.net/yedek/logo/" +
                universitelerListe.get(position).getSlug() + ".png").into(holder.logo);   //Universite Logo
        Glide.with(mContext).load("https://tohere.net/yedek/resim/" +
                universitelerListe.get(position).getSlug() + ".jpg").into(holder.arkaplan);//Universite resim

        holder.textViewIsim.setText(universite.getIsim()); //universite adı textview
        holder.textViewDetay.setText(universite.getIl()); // il textView
        //carda tıklandığında olucaklar
        holder.universiteler_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetayActivity.class);
                intent.putExtra("nesne", universite);
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
        private TextView textViewDetay;
        private CardView universiteler_card;
        private ImageView logo, arkaplan;

        public CardTasarimTutucu(View itemView) {
            super(itemView);
            textViewIsim = itemView.findViewById(R.id.textViewIsim);
            textViewDetay = itemView.findViewById(R.id.textViewDetay);
            universiteler_card = itemView.findViewById(R.id.universiteler_card);
            logo = itemView.findViewById(R.id.logo);
            arkaplan = itemView.findViewById(R.id.arkaplan);
        }
    }
}
