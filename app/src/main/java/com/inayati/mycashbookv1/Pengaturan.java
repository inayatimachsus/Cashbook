package com.inayati.mycashbookv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Pengaturan extends AppCompatActivity {
    Button btnGantiPassword, btnKembali, logout;
    EditText passLama, passBaru;
    DBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);
        database = new DBHelper(this);
        btnGantiPassword = (Button)findViewById(R.id.btnSimpanPass);
        btnKembali = (Button)findViewById(R.id.btnKembalis);
        passLama = (EditText)findViewById(R.id.formPasswordSaatIni);
        passBaru = (EditText)findViewById(R.id.formPasswordBaru);
        logout = (Button)findViewById(R.id.btnLogout);

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pengaturan.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean updateSes = database.updateSession("kosong",1);
                if(updateSes == true){
                    Toast.makeText(getApplicationContext(), "Sukses Logout",Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(Pengaturan.this,Login.class);
                    startActivity(loginIntent);
                    finish();
                }
            }
        });

        btnGantiPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldP = passLama.getText().toString();
                String newP = passBaru.getText().toString();

                if(oldP != "" && newP != "") {
                    Boolean checkSession = database.cekPassword(oldP);
                    if(checkSession == true){
                        Boolean updateP = database.updatePassword(newP,1);
                        if(updateP == true){
                            Toast.makeText(getApplicationContext(), "Sukses Ganti Password!",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Pengaturan.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Password Saat ini salah!",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Semua form wajib di isi !",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

