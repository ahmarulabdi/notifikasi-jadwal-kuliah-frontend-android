package rndmjck.com.notifikasijadwalkuliahtiuniks;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rndmjck.com.notifikasijadwalkuliahtiuniks.Utility.SessionManager;
import rndmjck.com.notifikasijadwalkuliahtiuniks.model.FlagIsiKRS;
import rndmjck.com.notifikasijadwalkuliahtiuniks.response.FlagIsiKRSResponse;
import rndmjck.com.notifikasijadwalkuliahtiuniks.rest.ApiClient;
import rndmjck.com.notifikasijadwalkuliahtiuniks.rest.ApiInterface;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.text_view_nama)
    TextView textViewNama;
    @BindView(R.id.text_view_nip_nim)
    TextView textViewNipNim;
    @BindView(R.id.card_view_profil)
    CardView cardViewProfil;
    @BindView(R.id.card_view_menu)
    CardView cardViewMenu;
    @BindView(R.id.swipe_refresh_main)
    SwipeRefreshLayout swipeRefreshLayoutMain;
    @BindView(R.id.button_pengisian_krs)
    Button buttonPengisianKRS;
    @BindView(R.id.button_jadwal_kuliah)
    Button buttonJadwalKuliah;
    Boolean flagIsiKRS;
    SessionManager sessionManager;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(getApplicationContext());
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        loadLayout();

        swipeRefreshLayoutMain.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadLayout();
                swipeRefreshLayoutMain.setRefreshing(false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.set_flag_krs);

        // return true so that the menu pop up is opened
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            logout();
        }
        if (id == R.id.set_flag_krs) {
            setFlagKRS();
        }
        return true;
    }

    private void setFlagKRS() {
        showButtonIsiKRS();


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("setting pengisian KRS");
        if (flagIsiKRS) {
            builder.setMessage("tutup pengisian KRS ? ");
        } else {
            builder.setMessage("buka pengisian KRS ? ");
        }
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                newSetIsiKRS();
            }
        }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();

    }

    private void newSetIsiKRS() {
        apiInterface.setIsiKRS(!flagIsiKRS).enqueue(new Callback<FlagIsiKRSResponse>() {
            @Override
            public void onResponse(Call<FlagIsiKRSResponse> call, Response<FlagIsiKRSResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        loadLayout();
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<FlagIsiKRSResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "koneksi gagal" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadLayout() {
        showCardProfil();
        showButtonIsiKRS();
        buttonJadwalKuliah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),JadwalKuliahActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showButtonIsiKRS() {
        apiInterface.flagIsiKRS().enqueue(new Callback<FlagIsiKRSResponse>() {
            @Override
            public void onResponse(Call<FlagIsiKRSResponse> call, Response<FlagIsiKRSResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        FlagIsiKRS flag = response.body().getData();
                        flagIsiKRS = flag.getBuka();
                        if (flagIsiKRS) {
                            buttonPengisianKRS.setVisibility(View.VISIBLE);
                            buttonPengisianKRS.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getApplicationContext(), IsiKRSActivity.class);
                                    startActivity(intent);
                                }
                            });
                        } else {
                            buttonPengisianKRS.setVisibility(View.GONE);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<FlagIsiKRSResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "koneksi gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void showCardProfil() {
        textViewNama.setText(": " + sessionManager.getUserDetail().get("nama"));
        textViewNipNim.setText(": " + sessionManager.getUserDetail().get("nip_nim"));
    }


    private void logout() {
        sessionManager.logoutUser();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


}
