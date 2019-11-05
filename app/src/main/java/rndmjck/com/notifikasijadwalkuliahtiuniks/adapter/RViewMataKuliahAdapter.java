package rndmjck.com.notifikasijadwalkuliahtiuniks.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rndmjck.com.notifikasijadwalkuliahtiuniks.R;
import rndmjck.com.notifikasijadwalkuliahtiuniks.model.JadwalKuliah;


/**
 * Created by rndmjck on 15/08/18.
 */

public class RViewMataKuliahAdapter extends RecyclerView.Adapter<RViewMataKuliahAdapter.ViewHolderMataKuliah> {

    private List<JadwalKuliah> jadwalKuliahs;
    private Context context;
    private ArrayList<Integer> idJadwalKuliah;

    public RViewMataKuliahAdapter(List<JadwalKuliah> jadwalKuliahs, Context context) {
        this.jadwalKuliahs = jadwalKuliahs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderMataKuliah onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mata_kuliah_list_view_adapter, parent, false);
        ViewHolderMataKuliah viewHolderMataKuliah = new ViewHolderMataKuliah(view);
        idJadwalKuliah = new ArrayList<>();
        return viewHolderMataKuliah;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMataKuliah holder, final int position) {


        holder.textViewNama.setText(jadwalKuliahs.get(position).getNama());
        holder.textViewNamaDosen.setText(jadwalKuliahs.get(position).getNamaDosen());
        holder.textViewSKS.setText(jadwalKuliahs.get(position).getSks());
        holder.textViewSemester.setText(jadwalKuliahs.get(position).getSemester());




        holder.checkBoxMataKuliah.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    idJadwalKuliah.add(Integer.parseInt(jadwalKuliahs.get(position).getIdJadwalKuliah()));
                } else {
                    idJadwalKuliah.remove(position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return jadwalKuliahs.size();
    }

    public ArrayList<Integer> getIdJadwalKuliah() {
        return idJadwalKuliah;
    }

    public void setIdJadwalKuliah(ArrayList<Integer> idJadwalKuliah) {
        this.idJadwalKuliah = idJadwalKuliah;
    }

    class ViewHolderMataKuliah extends RecyclerView.ViewHolder {

        TextView textViewNama;
        TextView textViewNamaDosen;
        TextView textViewSKS;
        TextView textViewSemester;
        CheckBox checkBoxMataKuliah;

        public ViewHolderMataKuliah(View itemView) {
            super(itemView);

            textViewNama = itemView.findViewById(R.id.item_text_view_nama_mata_kuliah);
            textViewNamaDosen = itemView.findViewById(R.id.item_text_view_nama_dosen_mata_kuliah);
            textViewSKS = itemView.findViewById(R.id.item_text_view_sks_mata_kuliah);
            textViewSemester = itemView.findViewById(R.id.item_text_view_semester_jadwal_kuliah);
            checkBoxMataKuliah = itemView.findViewById(R.id.checkbox_mata_kuliah);
        }
    }
}
