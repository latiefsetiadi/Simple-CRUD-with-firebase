package com.example.a11201811376_latiefsetiadi_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class update extends AppCompatActivity {

    Button btnupdate;
    EditText edtnim,edtnama,edtsemester,edtipk;
    String nim,nama,semstr,ipk,ID;
    String nim1,nama1,semstr1,ipk1,ID1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        edtnim = findViewById(R.id.updtnim);
        edtnama = findViewById(R.id.updtnama);
        edtsemester = findViewById(R.id.updtsemester);
        edtipk = findViewById(R.id.updtipk);
        btnupdate = findViewById(R.id.tombolupdate);

        Intent intent1 = getIntent();
        ID=intent1.getStringExtra("ID");
        nim=intent1.getStringExtra("NIM");
        nama=intent1.getStringExtra("NAMA");
        semstr=intent1.getStringExtra("SEMESTER");
        ipk=intent1.getStringExtra("IPK");
        edtnim.setText(""+nim);
        edtnama.setText(""+nama);
        edtsemester.setText(""+semstr);
        edtipk.setText(""+ipk);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ID1=ID;
                nim1=edtnim.getText().toString();
                nama1=edtnama.getText().toString();
                semstr1=edtsemester.getText().toString();
                ipk1=edtipk.getText().toString();
                updateData(ID1,nim1,nama1,semstr1,ipk1);
            }
        });

        if(!adaInternet()){
            Toast.makeText(this, "Koneksi Internet Mati, Silahkan Hidupkan Koneksi Internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateData(String Id,String nim,String nama,String semester,String ipk) {
        int kondisi=0;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        if(edtnim.getText().toString().equals("")){
            edtnim.setError("Error");
            kondisi=1;
        }
        if(edtsemester.getText().toString().equals("")){
            edtsemester.setError("Error");
            kondisi=1;
        }
        if(edtnama.getText().toString().equals("")){
            edtnama.setError("Error");
            kondisi=1;
        }
        if(edtipk.getText().toString().equals("")){
            edtipk.setError("Error");
            kondisi=1;
        }
        if(kondisi==1){
            Toast.makeText(this, "Terdapat Data yang Kosong", Toast.LENGTH_SHORT).show();
        }
        else {
            Dataku dataku = new Dataku(Id, nim, nama, semester, ipk);
            myRef.child(Id).setValue(dataku).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                     finish();
                    Toast.makeText(update.this, "Update Berhasil", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void showDialog(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.succes);

        //Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
    }
    private boolean adaInternet(){
        ConnectivityManager koneksi = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return koneksi.getActiveNetworkInfo() != null;
    }
    }
