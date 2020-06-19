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
import com.tanitim.universitetanitim.Activity.UniversiteSehir;
import com.tanitim.universitetanitim.Models.Universiteler;
import com.tanitim.universitetanitim.R;
import com.tanitim.universitetanitim.retrofit.UniversitelerDAOinterface;

import java.util.List;



public class populerSehilerAdapter extends RecyclerView.Adapter<populerSehilerAdapter.CardTasarimTutucu> {
    private Context mContext;
    private List<Universiteler> universitelerListe;
    private UniversitelerDAOinterface universitelerDAOinterface;

    public populerSehilerAdapter(Context mContext, List<Universiteler> universitelerListe
            , UniversitelerDAOinterface universitelerDAOinterface) {
        this.mContext = mContext;
        this.universitelerListe = universitelerListe;
        this.universitelerDAOinterface = universitelerDAOinterface;
    }

    @Override
    public CardTasarimTutucu onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.populer_sehir_card, parent, false);

        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(final CardTasarimTutucu holder, final int position) {
        final Universiteler universite = universitelerListe.get(position);


       /* holder.resimProgress.getIndeterminateDrawable().setColorFilter(mContext.getResources()
                .getColor(R.color.purple_inactive), PorterDuff.Mode.SRC_IN); //ProgressBar Rengi */


        String il = karakterCevir(universitelerListe.get(position).getIl()); //türkçe karakter silmek için
        Glide.with(mContext).load("https://www.tohere.net/yekimsdfdf/yedek/sehir/" +
                il + "_optimized" + ".jpg").into(holder.arkaplan);//Sehir Resim


        holder.textViewSehir.setText(universite.getIl()); //   // il textView


        //carda tıklandığında olucaklar
        holder.sehirler_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, UniversiteSehir.class);
                intent.putExtra("nesne", universite);
                mContext.startActivity(intent);
            }
        });

    }

    public static String karakterCevir(String kelime) {
        String mesaj = kelime;
        char[] oldValue = new char[]{'ö', 'Ö', 'ü', 'Ü', 'ç', 'Ç', 'İ', 'ı', 'Ğ', 'ğ', 'Ş', 'ş'};
        char[] newValue = new char[]{'o', 'O', 'u', 'U', 'c', 'C', 'I', 'i', 'G', 'g', 'S', 's'};
        for (int sayac = 0; sayac < oldValue.length; sayac++) {
            mesaj = mesaj.replace(oldValue[sayac], newValue[sayac]);
        }
        return mesaj;
    }

    @Override
    public int getItemCount() {

        return universitelerListe.size();

    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder {
        private TextView textViewSehir;
        private CardView sehirler_card;
        private ImageView arkaplan;

        public CardTasarimTutucu(View itemView) {
            super(itemView);
            textViewSehir = itemView.findViewById(R.id.textViewSehir);
            sehirler_card = itemView.findViewById(R.id.sehirler_card);
            arkaplan = itemView.findViewById(R.id.arkaplan);

        }
    }
}
