package com.example.a11201811376_latiefsetiadi_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class tambahData extends AppCompatActivity {

    Button simpan;
    EditText edtnim;
    EditText edtnama;
    EditText edtsmstr;
    EditText edtipk;
    String NIM;
    String NAMA;
    String SEMESTER;
    String IPK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);
        edtnim = (EditText)findViewById(R.id.nim);
        edtnama = (EditText)findViewById(R.id.nama);
        edtsmstr = (EditText) findViewById(R.id.semester);
        edtipk =(EditText) findViewById(R.id.ipk);
        simpan = findViewById(R.id.tombolsimpan);

        NIM=edtnim.getText().toString();
        NAMA= edtnama.getText().toString();
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SEMESTER = edtsmstr.getText().toString();
                IPK = edtipk.getText().toString();
                simpandata(edtnim.getText().toString(),edtnama.getText().toString(),SEMESTER,IPK);
            }
        });

        if(!adaInternet()){
            Toast.makeText(this, "Koneksi Internet Mati, Silahkan Hidupkan Koneksi Internet", Toast.LENGTH_SHORT).show();
        }
    }


    private void simpandata(String nim,String nama,String semester,String ipk) {
        int kondisi=0;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        String id=myRef.push().getKey();
        if(edtnim.getText().toString().equals("")){
            edtnim.setError("Error");
            kondisi=1;
        }
        if(edtsmstr.getText().toString().equals("")){
            edtsmstr.setError("Error");
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
            Dataku dataku = new Dataku(id, nim, nama, semester, ipk);
            myRef.child(id).setValue(dataku).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    finish();
                    Toast.makeText(tambahData.this, "Tambah Data Berhasil", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean adaInternet(){
        ConnectivityManager koneksi = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return koneksi.getActiveNetworkInfo() != null;
    }
}