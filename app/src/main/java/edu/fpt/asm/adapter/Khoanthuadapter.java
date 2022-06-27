package edu.fpt.asm.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import edu.fpt.asm.R;
import edu.fpt.asm.dao.GDDao;
import edu.fpt.asm.model.Giaodich;

public class Khoanthuadapter extends RecyclerView.Adapter<Khoanthuadapter.KhoanThuViewHodel> {
    GDDao gdDao;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public class KhoanThuViewHodel extends RecyclerView.ViewHolder {
        TextView tieude, ngay, tien, mota, magd;
        ImageView imgedit, imgDelete;
        CardView cvGiaodich;

        public KhoanThuViewHodel(View view) {
            super(view);
            tieude = view.findViewById(R.id.tieude);
            ngay = view.findViewById(R.id.ngay);
            tien = view.findViewById(R.id.tien);
            mota = view.findViewById(R.id.mota);
            imgedit = view.findViewById(R.id.editIcon);
            imgDelete = view.findViewById(R.id.deleteIcon);
            cvGiaodich = view.findViewById(R.id.cvgiaodich);
            magd = view.findViewById(R.id.ma);
        }
    }

    Context context;
    ArrayList<Giaodich> list;

    public Khoanthuadapter(Context context, ArrayList<Giaodich> list) {
        this.context = context;
        this.list = list;
        gdDao = new GDDao(context);
    }

    @Override
    public KhoanThuViewHodel onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.giaodich_item, parent, false);
        KhoanThuViewHodel viewHodel = new KhoanThuViewHodel(view);

        return viewHodel;
    }

    @Override
    public void onBindViewHolder(KhoanThuViewHodel holder, int position) {
        Giaodich gd = list.get(position);
        holder.magd.setText(gd.getMALOAI() + "");
        holder.tieude.setText(gd.getTIEUDE());
        holder.ngay.setText(gd.getNGAYGD());
        holder.tien.setText(gd.getTIEN() + "");
        holder.mota.setText(gd.getMOTA());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.remove(position);
                if (gdDao.delete(gd.getMAGD())) {
                    Toast.makeText(context, "xóa thành công", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(context, "xóa không thành công", Toast.LENGTH_LONG).show();
                }
                notifyItemRemoved(position);

            }
        });

        holder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                View view = inflater.inflate(R.layout.thuchi_edit, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                EditText edmathuchi = view.findViewById(R.id.editmatdsua);
                EditText edmucdich = view.findViewById(R.id.editTieudesua);
                EditText edngay = view.findViewById(R.id.editngaysua);
                EditText edtien = view.findViewById(R.id.editTiensua);
                EditText edmota = view.findViewById(R.id.editMotasua);
                Button btnHuy = view.findViewById(R.id.buttonHuysuathuchi);
                Button btnsua = view.findViewById(R.id.buttonsuathuchi);
                edmathuchi.setText(gd.getMALOAI() + "");
                edmucdich.setText(gd.getTIEUDE());
                edngay.setText(gd.getNGAYGD());
                edtien.setText(gd.getTIEN().toString());
                edmota.setText(gd.getMOTA());

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnsua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int matd = Integer.parseInt(edmathuchi.getText().toString());
                        String tieude = edmucdich.getText().toString();
                        String ngay = edngay.getText().toString();

                        Double tien = Double.parseDouble(edtien.getText().toString());
                        String mota = edmota.getText().toString();
                        gd.setMALOAI(matd);
                        gd.setTIEUDE(tieude);
                        gd.setTIEN(tien);
                        gd.setNGAYGD(ngay);
                        gd.setMOTA(mota);
                        if (gdDao.update(gd)) {
                            Toast.makeText(context, "cập nhập thành công", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "cập nhập không thành công", Toast.LENGTH_LONG).show();

                        }
                        notifyItemChanged(position);
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
