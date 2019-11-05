package rndmjck.com.notifikasijadwalkuliahtiuniks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rndmjck.com.notifikasijadwalkuliahtiuniks.Utility.SessionManager;
import rndmjck.com.notifikasijadwalkuliahtiuniks.adapter.RViewMataKuliahAdapter;
import rndmjck.com.notifikasijadwalkuliahtiuniks.adapter.RViewReviewKRSAdapter;
import rndmjck.com.notifikasijadwalkuliahtiuniks.model.JadwalKuliah;
import rndmjck.com.notifikasijadwalkuliahtiuniks.response.JadwalKuliahResponse;
import rndmjck.com.notifikasijadwalkuliahtiuniks.rest.ApiClient;
import rndmjck.com.notifikasijadwalkuliahtiuniks.rest.ApiInterface;

public class ReviewKRSActivity extends AppCompatActivity {

    private static final String TAG = "ReviewKRSActivity";
    ApiInterface apiInterface;
    ArrayList<Integer> idJadwalKuliahs;

    RViewReviewKRSAdapter rViewReviewKRSAdapter;
    @BindView(R.id.recycler_view_review_container)
    RecyclerView recyclerViewReviewContainer;
    @BindView(R.id.button_simpan_krs)
    Button buttonSimpanKRS;
    private List<JadwalKuliah> jadwalKuliahs;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_krs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(getApplicationContext());

        Log.d(TAG, "onCreate: ");
        idJadwalKuliahs = new ArrayList<>();
        idJadwalKuliahs = getIntent().getIntegerArrayListExtra("id_jadwal_kuliahs");

        loadReviewKRS();
    }


    private void loadReviewKRS() {
        for (int i = 0; i < idJadwalKuliahs.size(); i++) {
            Log.d(TAG, "loadReviewKRS: jadwal index" + i);
            Log.d(TAG, "loadReviewKRS: jadwal idJadwal" + idJadwalKuliahs.get(i));
        }
        apiInterface.reviewKRS(idJadwalKuliahs).enqueue(new Callback<JadwalKuliahResponse>() {
            @Override
            public void onResponse(Call<JadwalKuliahResponse> call, Response<JadwalKuliahResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        jadwalKuliahs = response.body().getData();

                        for (JadwalKuliah jadwalKuliah : jadwalKuliahs) {
                            Log.d(TAG, "onResponse: jadwal :idJadwal " + jadwalKuliah.getIdJadwalKuliah());
                        }
                        rViewReviewKRSAdapter = new RViewReviewKRSAdapter(jadwalKuliahs, getApplicationContext());
                        recyclerViewReviewContainer.setAdapter(rViewReviewKRSAdapter);
                        recyclerViewReviewContainer.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                        buttonSimpanKRS.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                simpanKRS();
                            }
                        });

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<JadwalKuliahResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "koneksi gagal " + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void simpanKRS() {
        apiInterface.simpanKRS(sessionManager.getUserDetail().get("id_users"), idJadwalKuliahs).enqueue(new Callback<JadwalKuliahResponse>() {
            @Override
            public void onResponse(Call<JadwalKuliahResponse> call, Response<JadwalKuliahResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
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
