package rndmjck.com.notifikasijadwalkuliahtiuniks.Utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;

import rndmjck.com.notifikasijadwalkuliahtiuniks.model.Users;


public class SessionManager {

    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor editor;

    private Context _context;

    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String ID_USERS = "id_users";
    public static final String NIP_NIM = "nip_nim";
    public static final String NAMA = "nama";
    public static final String HAK_AKSES = "hak_akses";

    public Context get_context() {
        return _context;
    }

    //constuctor
    public SessionManager(Context context) {
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(Users users) {
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(ID_USERS, users.getIdUsers());
        editor.putString(NIP_NIM, users.getNipNim());
        editor.putString(NAMA, users.getNama());
        editor.putString(HAK_AKSES, users.getHakAkses());
        editor.commit();
    }

    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(ID_USERS, sharedPreferences.getString(ID_USERS, null));
        user.put(NIP_NIM, sharedPreferences.getString(NIP_NIM, null));
        user.put(NAMA, sharedPreferences.getString(NAMA, null));
        user.put(HAK_AKSES, sharedPreferences.getString(HAK_AKSES, null));
        return user;
    }

    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }
}
