package edu.fpt.asm.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.fpt.asm.dao.PhanloaiDao;
import edu.fpt.asm.R;
import edu.fpt.asm.model.Phanloai;

public class Phanloaiadapter extends RecyclerView.Adapter<Phanloaiadapter.phanLoaiViewHodel> {
    PhanloaiDao plDao;

    public class phanLoaiViewHodel extends RecyclerView.ViewHolder {
        TextView viewTenLoai, viewTrangThai, viewmal;
        ImageView imgedit, imgDelete;
        CardView cvPhanLoai;

        public phanLoaiViewHodel(View view) {
            super(view);
            viewTenLoai = view.findViewById(R.id.phanloai);
            viewTrangThai = view.findViewById(R.id.trangthai);
            imgedit = view.findViewById(R.id.editIcon);
            imgDelete = view.findViewById(R.id.deleteIcon);
            cvPhanLoai = view.findViewById(R.id.cvPhanLoai);
            viewmal = view.findViewById(R.id.mal);
        }
    }

    Context context;
    ArrayList<Phanloai> list;

    public Phanloaiadapter(Context context, ArrayList<Phanloai> list) {
        this.context = context;
        this.list = list;
        plDao = new PhanloaiDao(context);
    }

    @Override
    public phanLoaiViewHodel onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.phanloai_item, parent, false);
        phanLoaiViewHodel viewHodel = new phanLoaiViewHodel(view);

        return viewHodel;
    }

    @Override
    public void onBindViewHolder(phanLoaiViewHodel holder, int position) {
        Phanloai pl = list.get(position);
        holder.viewmal.setText(pl.getMALOAI() + "");
        holder.viewTenLoai.setText(pl.getTENLOAI());
        holder.viewTrangThai.setText(pl.getTRANGTHAI());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.remove(position);
                plDao.delete(pl.getMALOAI());

                notifyItemChanged(position);
            }
        });
        holder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dialogedit(pl);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                View view = inflater.inflate(R.layout.phanloai_update, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                EditText etenLoai = view.findViewById(R.id.editTenLoai);
                Button buttonThem = view.findViewById(R.id.buttonThem);
                Button buttonHuy = view.findViewById(R.id.buttonHuy);
                Spinner spinner = view.findViewById(R.id.spinnerTrangThai);
                String[] arrTrangThai = {"thu", "chi"};
                ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, arrTrangThai);
                spinner.setAdapter(spnAdapter);
                etenLoai.setText(pl.getTENLOAI());
                for (int i = 0; i < arrTrangThai.length; i++) {
                    if (pl.getTRANGTHAI().equalsIgnoreCase(arrTrangThai[i])) {
                        spinner.setSelection(i);
                    }
                }
                buttonThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tenLoai = etenLoai.getText().toString();
                        String trangthai = (String) spinner.getSelectedItem();
                        pl.setTENLOAI(tenLoai);
                        pl.setTRANGTHAI(trangthai);
                        if (plDao.update(pl)) {
                            Toast.makeText(context, "cập nhập thành công", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "cập nhập không thành công", Toast.LENGTH_LONG).show();
                        }
                        notifyItemChanged(position);
                    }
                });
                buttonHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
