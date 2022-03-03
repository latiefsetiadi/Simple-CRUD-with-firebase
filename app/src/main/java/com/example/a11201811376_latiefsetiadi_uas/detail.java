package com.example.a11201811376_latiefsetiadi_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class detail extends AppCompatActivity {

    String nim,nama,semester,ipk,ID;
    TextInputEditText txtnim,txtnama,txtsemester,txtipk;
    TextView detailid;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txtnim = findViewById(R.id.dtlNIM);
        txtnama = findViewById(R.id.dtlNama);
        txtsemester = findViewById(R.id.dtlsmstr);
        txtipk = findViewById(R.id.dtlipk);
        back = findViewById(R.id.btndtlBack);
        detailid = findViewById(R.id.dtlID);
        txtnim.setKeyListener(null);
        txtnama.setKeyListener(null);
        txtsemester.setKeyListener(null);
        txtipk.setKeyListener(null);
        Intent intent = getIntent();
        ID=intent.getStringExtra("ID");
        nim=intent.getStringExtra("NIM");
        nama=intent.getStringExtra("NAMA");
        semester=intent.getStringExtra("SEMESTER");
        ipk=intent.getStringExtra("IPK");
        detailid.setText(""+ID);
        txtnim.setText(""+nim);
        txtnama.setText(""+nama);
        txtsemester.setText(""+semester);
        txtipk.setText(""+ipk);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}