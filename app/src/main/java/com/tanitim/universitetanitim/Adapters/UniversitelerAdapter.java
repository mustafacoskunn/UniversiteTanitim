package com.tanitim.universitetanitim.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.tanitim.universitetanitim.DetayActivity;
import com.tanitim.universitetanitim.Models.Universiteler;
import com.tanitim.universitetanitim.R;

import java.util.List;


public class UniversitelerAdapter extends RecyclerView.Adapter<UniversitelerAdapter.CardTasarimTutucu> {
    private Context mContext;
    private List<Universiteler> universitelerListe;

    public UniversitelerAdapter(Context mContext, List<Universiteler> universitelerListe) {
        this.mContext = mContext;
        this.universitelerListe = universitelerListe;
    }

    @Override
    public CardTasarimTutucu onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim,parent,false);

        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(final CardTasarimTutucu holder, int position) {
        final Universiteler universite = universitelerListe.get(position);


        holder.resimProgress.getIndeterminateDrawable().setColorFilter(mContext.getResources()
                .getColor(R.color.purple_inactive), PorterDuff.Mode.SRC_IN);

        Picasso.with(mContext)
                .load("https://takipgym.com/logo/"+universitelerListe.get(position).getSlug()+".png")
                .into(holder.logo, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.resimProgress.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError() {
                        holder.resimProgress.setVisibility(View.VISIBLE);

                    }
                });
        Picasso.with(mContext)
                .load("https://takipgym.com/resim/"+universitelerListe.get(position).getSlug()+".webp")
                .into(holder.arkaplan);
        holder.textViewIsim.setText(universite.getIsim());
        holder.textViewID.setText(universite.getIl());

        holder.universiteler_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, DetayActivity.class);

                intent.putExtra("nesne",universite);

                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return universitelerListe.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        private TextView textViewIsim;
        private TextView textViewID;
        private CardView universiteler_card;
        private ImageView logo,arkaplan;
        private ProgressBar resimProgress;

        public CardTasarimTutucu(View itemView) {
            super(itemView);
            textViewIsim = itemView.findViewById(R.id.textViewIsim);
            textViewID = itemView.findViewById(R.id.textViewID);
            universiteler_card = itemView.findViewById(R.id.universiteler_card);
            logo = itemView.findViewById(R.id.logo);
            resimProgress = itemView.findViewById(R.id.resimProgress);
            arkaplan = itemView.findViewById(R.id.arkaplan);

        }
    }
}
