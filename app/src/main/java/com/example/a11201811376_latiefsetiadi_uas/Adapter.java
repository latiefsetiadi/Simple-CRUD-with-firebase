package com.example.a11201811376_latiefsetiadi_uas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {
    Context context;
    List<Dataku> list;

    public Adapter(Context context, List<Dataku> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_data,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
    Dataku dataku = list.get(position);
    final String id = dataku.getId();
    final String nim = dataku.getNim();
    final String nama = dataku.getNama();
    final String semester = dataku.getSemester();
    final String ipk = dataku.getIpk();
    holder.NIM.setText(nim);
    holder.NAMA.setText(nama);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Menu");
            String[] option ={"Detail","Edit","Delete"};

            builder.setItems(option, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    switch (which){
                        case 0:
                            Intent intent = new Intent(context, detail.class);
                            intent.putExtra("ID",id);
                            intent.putExtra("NIM",nim);
                            intent.putExtra("NAMA",nama);
                            intent.putExtra("SEMESTER",semester);
                            intent.putExtra("IPK",ipk);
                            context.startActivity(intent);
                            break;

                        case 1:
                            Intent intent1 = new Intent(context, update.class);
                            intent1.putExtra("ID",id);
                            intent1.putExtra("NIM",nim);
                            intent1.putExtra("NAMA",nama);
                            intent1.putExtra("SEMESTER",semester);
                            intent1.putExtra("IPK",ipk);
                            context.startActivity(intent1);
                            break;
                        case 2:
                            deleteDialog(id,nama);
                            break;
                    }
                }
            });
            builder.create().show();
        }
    });
    }
private void deleteDialog(final String Id,final String nama){
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference("message");

    AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        builder.setMessage("Yakin ingin menghapus "+nama+"?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_delete);
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myRef.child(Id).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(context, "Data Telah Dihapus", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
}
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtnim,txtnama,NIM,NAMA;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtnim = itemView.findViewById(R.id.textNim);
            txtnama = itemView.findViewById(R.id.textnama);
            NIM = itemView.findViewById(R.id.NIM);
            NAMA = itemView.findViewById(R.id.NAMA);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
        }
    }
}
