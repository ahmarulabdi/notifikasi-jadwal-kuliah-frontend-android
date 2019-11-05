package rndmjck.com.notifikasijadwalkuliahtiuniks.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import rndmjck.com.notifikasijadwalkuliahtiuniks.R;
import rndmjck.com.notifikasijadwalkuliahtiuniks.model.JadwalKuliah;


/**
 * Created by rndmjck on 15/08/18.
 */

public class RViewJadwalKuliahAdapter extends RecyclerView.Adapter<RViewJadwalKuliahAdapter.ViewHolderJadwalKuliah> {

    private List<JadwalKuliah> jadwalKuliahs;
    private Context context;

    public RViewJadwalKuliahAdapter(List<JadwalKuliah> jadwalKuliahs, Context context) {
        this.jadwalKuliahs = jadwalKuliahs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderJadwalKuliah onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jadwal_kuliah_list_view_adapter, parent, false);

        ViewHolderJadwalKuliah viewHolderJadwalKuliah = new ViewHolderJadwalKuliah(view);
        return viewHolderJadwalKuliah;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderJadwalKuliah holder, int position) {
        holder.textViewNama.setText(jadwalKuliahs.get(position).getNama());
        holder.textViewTempat.setText(jadwalKuliahs.get(position).getTempat());
        holder.textViewHari.setText(jadwalKuliahs.get(position).getHari());
        holder.textViewMulaiSelesai.setText(jadwalKuliahs.get(position).getJamMulai() + "-" + jadwalKuliahs.get(position).getJamSelesai());
        holder.textViewNamaDosen.setText(jadwalKuliahs.get(position).getNamaDosen());
        if (jadwalKuliahs.get(position).getKeterangan().equals("")) {
            holder.linearLayoutKeterangan.setVisibility(View.GONE);
        }
        holder.textViewKeterangan.setText(jadwalKuliahs.get(position).getKeterangan());


    }

    @Override
    public int getItemCount() {
        return jadwalKuliahs.size();
    }

    class ViewHolderJadwalKuliah extends RecyclerView.ViewHolder {


        TextView textViewNama;
        TextView textViewTempat;
        TextView textViewHari;
        TextView textViewMulaiSelesai;
        TextView textViewNamaDosen;
        TextView textViewKeterangan;
        LinearLayout linearLayoutKeterangan;

        public ViewHolderJadwalKuliah(View itemView) {
            super(itemView);

            textViewNama = itemView.findViewById(R.id.item_text_view_nama_jadwal_kuliah);
            textViewTempat = itemView.findViewById(R.id.item_text_view_tempat_jadwal_kuliah);
            textViewHari = itemView.findViewById(R.id.item_text_view_hari_jadwal_kuliah);
            textViewMulaiSelesai = itemView.findViewById(R.id.item_text_view_mulai_selesai_jadwal_kuliah);
            textViewNamaDosen = itemView.findViewById(R.id.item_text_view_nama_dosen_jadwal_kuliah);
            textViewKeterangan = itemView.findViewById(R.id.item_text_view_keterangan_jadwal_kuliah);
            linearLayoutKeterangan = itemView.findViewById(R.id.keterangan_container);

        }
    }
}
