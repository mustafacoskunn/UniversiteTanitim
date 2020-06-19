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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tanitim.universitetanitim.Activity.DetayActivity;
import com.tanitim.universitetanitim.Models.Universiteler;
import com.tanitim.universitetanitim.R;
import com.tanitim.universitetanitim.retrofit.UniversitelerDAOinterface;

import java.util.List;

public class populerUniversitelerAdapter extends RecyclerView.Adapter<populerUniversitelerAdapter.CardTasarimTutucu> {
    private Context mContext;
    private List<Universiteler> universitelerListe;
    private UniversitelerDAOinterface universitelerDAOinterface;

    public populerUniversitelerAdapter(Context mContext, List<Universiteler> universitelerListe
            , UniversitelerDAOinterface universitelerDAOinterface) {
        this.mContext = mContext;
        this.universitelerListe = universitelerListe;
        this.universitelerDAOinterface = universitelerDAOinterface;
    }

    @Override
    public CardTasarimTutucu onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.populer_card, parent, false);

        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(final CardTasarimTutucu holder, final int position) {
        final Universiteler universite = universitelerListe.get(position);




        Glide.with(mContext).load("https://www.tohere.net/yekimsdfdf/yedek/resim/" +
                universitelerListe.get(position).getSlug() + "-1.jpg").diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.arkaplan);//Universite resim

        holder.textViewIsim.setText(universite.getIsim()); //universite adı textview
      //  holder.textViewSehir.setText(universite.getIl() + ""); // il textView

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
        //private TextView textViewSehir;
        private CardView universiteler_card;
        private ImageView  arkaplan;

        public CardTasarimTutucu(View itemView) {
            super(itemView);
            textViewIsim = itemView.findViewById(R.id.textViewIsim);
         //   textViewSehir = itemView.findViewById(R.id.textViewSehir);
            universiteler_card = itemView.findViewById(R.id.universiteler_card);
            arkaplan = itemView.findViewById(R.id.arkaplan);
        }
    }


}
