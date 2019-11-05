package rndmjck.com.notifikasijadwalkuliahtiuniks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rndmjck.com.notifikasijadwalkuliahtiuniks.Utility.SessionManager;
import rndmjck.com.notifikasijadwalkuliahtiuniks.response.DetailUsersResponse;
import rndmjck.com.notifikasijadwalkuliahtiuniks.rest.ApiClient;
import rndmjck.com.notifikasijadwalkuliahtiuniks.rest.ApiInterface;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edit_text_nip_nim)
    EditText editTextNipNim;
    @BindView(R.id.edit_text_password)
    EditText editTextPassword;
    @BindView(R.id.button_login)
    Button buttonLogin;


    ApiInterface apiInterface;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        sessionManager = new SessionManager(getApplicationContext());
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


    }

    private void login() {
        String nipNim = editTextNipNim.getText().toString();
        String password = editTextPassword.getText().toString();
        apiInterface.login(nipNim, password).enqueue(new Callback<DetailUsersResponse>() {
            @Override
            public void onResponse(Call<DetailUsersResponse> call, Response<DetailUsersResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        sessionManager.createLoginSession(response.body().getData());
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DetailUsersResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "koneksi gagal" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
