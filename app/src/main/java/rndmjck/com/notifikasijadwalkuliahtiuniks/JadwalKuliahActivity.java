package rndmjck.com.notifikasijadwalkuliahtiuniks;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rndmjck.com.notifikasijadwalkuliahtiuniks.Utility.SessionManager;
import rndmjck.com.notifikasijadwalkuliahtiuniks.adapter.RViewJadwalKuliahAdapter;
import rndmjck.com.notifikasijadwalkuliahtiuniks.model.JadwalKuliah;
import rndmjck.com.notifikasijadwalkuliahtiuniks.response.JadwalKuliahResponse;
import rndmjck.com.notifikasijadwalkuliahtiuniks.rest.ApiClient;
import rndmjck.com.notifikasijadwalkuliahtiuniks.rest.ApiInterface;

public class JadwalKuliahActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view_jadwal_kuliah_container)
    RecyclerView recyclerViewJadwalKuliahContainer;
    @BindView(R.id.swipe_refresh_jadwal_kuliah)
    SwipeRefreshLayout swipeRefreshLayoutJadwalKuliah;
    RViewJadwalKuliahAdapter rViewJadwalKuliahAdapter;
    ApiInterface apiInterface;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_kuliah);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(getApplicationContext());


        loadJadwalMahasiswa();

        swipeRefreshLayoutJadwalKuliah.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadJadwalMahasiswa();
                swipeRefreshLayoutJadwalKuliah.setRefreshing(false);
            }
        });


    }

    private void loadJadwalMahasiswa() {
        apiInterface.jadwalMahasiswa(sessionManager.getUserDetail().get("id_users")).enqueue(new Callback<JadwalKuliahResponse>() {
            @Override
            public void onResponse(Call<JadwalKuliahResponse> call, Response<JadwalKuliahResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        List<JadwalKuliah> jadwalKuliahs = response.body().getData();
                        rViewJadwalKuliahAdapter = new RViewJadwalKuliahAdapter(jadwalKuliahs,getApplicationContext());
                        recyclerViewJadwalKuliahContainer.setAdapter(rViewJadwalKuliahAdapter);
                        recyclerViewJadwalKuliahContainer.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<JadwalKuliahResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"koneksi gagal",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
