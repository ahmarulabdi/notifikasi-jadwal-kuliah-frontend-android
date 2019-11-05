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
 * Created by rndmjck on 23/08/18.
 */

public class RViewReviewKRSAdapter extends RecyclerView.Adapter<RViewReviewKRSAdapter.ViewHolderReviewKRS> {

    private List<JadwalKuliah> jadwalKuliahs;
    private Context context;

    public RViewReviewKRSAdapter(List<JadwalKuliah> jadwalKuliahs, Context context) {
        this.jadwalKuliahs = jadwalKuliahs;
        this.context = context;
    }

    public List<JadwalKuliah> getJadwalKuliahs() {
        return jadwalKuliahs;
    }

    @NonNull
    @Override
    public ViewHolderReviewKRS onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_krs_list_view_adapter,parent,false);
        ViewHolderReviewKRS viewHolderReviewKRS = new ViewHolderReviewKRS(view);

        return viewHolderReviewKRS;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderReviewKRS holder, int position) {
        holder.textViewNamaReviewKRS.setText(jadwalKuliahs.get(position).getNama());
        holder.textViewNamaDosenReviewKRS.setText(jadwalKuliahs.get(position).getNamaDosen());
        holder.textViewSKSReviewKRS.setText(jadwalKuliahs.get(position).getSks());
        holder.textViewSemesterReviewKRS.setText(jadwalKuliahs.get(position).getSemester());
    }

    @Override
    public int getItemCount() {
        return jadwalKuliahs.size();
    }

    class ViewHolderReviewKRS extends RecyclerView.ViewHolder {

        TextView textViewNamaReviewKRS;
        TextView textViewNamaDosenReviewKRS;
        TextView textViewSKSReviewKRS;
        TextView textViewSemesterReviewKRS;

        public ViewHolderReviewKRS(View itemView) {
            super(itemView);

            textViewNamaReviewKRS= itemView.findViewById(R.id.item_text_view_nama_review_krs);
            textViewNamaDosenReviewKRS = itemView.findViewById(R.id.item_text_view_nama_dosen_review_krs);
            textViewSKSReviewKRS = itemView.findViewById(R.id.item_text_view_sks_review_krs);
            textViewSemesterReviewKRS = itemView.findViewById(R.id.item_text_view_semester_review_krs);

        }
    }
}
