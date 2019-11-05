package rndmjck.com.notifikasijadwalkuliahtiuniks;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rndmjck.com.notifikasijadwalkuliahtiuniks.adapter.RViewMataKuliahAdapter;
import rndmjck.com.notifikasijadwalkuliahtiuniks.model.JadwalKuliah;
import rndmjck.com.notifikasijadwalkuliahtiuniks.response.JadwalKuliahResponse;
import rndmjck.com.notifikasijadwalkuliahtiuniks.rest.ApiClient;
import rndmjck.com.notifikasijadwalkuliahtiuniks.rest.ApiInterface;

public class IsiKRSActivity extends AppCompatActivity {
    private static final String TAG = "IsiKRSActivity";
    ApiInterface apiInterface;
    @BindView(R.id.recycler_view_isi_mata_kuliah_container)
    RecyclerView recyclerViewJadwalKuliahContainer;
    @BindView(R.id.swipe_refresh_isi_krs)
    SwipeRefreshLayout swipeRefreshLayoutIsiKRS;
    @BindView(R.id.button_review_krs)
    Button buttonReviewKRS;
    RViewMataKuliahAdapter rViewMataKuliahAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isi_krs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        loadLayout();

        swipeRefreshLayoutIsiKRS.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadLayout();
                swipeRefreshLayoutIsiKRS.setRefreshing(false);
            }
        });

    }

    private void loadLayout() {
        getJadwalKuliah();
    }

    private void getJadwalKuliah() {
        apiInterface.getJadwalKuliah().enqueue(new Callback<JadwalKuliahResponse>() {
            @Override
            public void onResponse(Call<JadwalKuliahResponse> call, Response<JadwalKuliahResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        final List<JadwalKuliah> jadwalKuliahs = response.body().getData();
                        rViewMataKuliahAdapter = new RViewMataKuliahAdapter(jadwalKuliahs, getApplicationContext());
                        recyclerViewJadwalKuliahContainer.setAdapter(rViewMataKuliahAdapter);
                        recyclerViewJadwalKuliahContainer.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                        buttonReviewKRS.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ArrayList<Integer>  idJadwalKuliahs = rViewMataKuliahAdapter.getIdJadwalKuliah();
                                if (idJadwalKuliahs.size() == 0) {
                                    Toast.makeText(getApplicationContext(), "belum ada mata kuliah yang anda centang", Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent intent = new Intent(getApplicationContext(), ReviewKRSActivity.class);
                                    for (Integer idJadwalKuliah : idJadwalKuliahs){
                                        Log.d(TAG, "onClick: jadwal : idJadwal " + idJadwalKuliah);
                                    }
                                    intent.putIntegerArrayListExtra("id_jadwal_kuliahs",idJadwalKuliahs);
                                    startActivity(intent);
                                }

                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<JadwalKuliahResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "koneksi gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
