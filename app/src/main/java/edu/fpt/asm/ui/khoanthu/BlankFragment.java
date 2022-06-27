package edu.fpt.asm.ui.khoanthu;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import edu.fpt.asm.R;
import edu.fpt.asm.adapter.Khoanthuadapter;
import edu.fpt.asm.dao.GDDao;
import edu.fpt.asm.model.Giaodich;

public class BlankFragment extends Fragment {
    RecyclerView rvkhoanThu;
    GDDao gdDao;
    Khoanthuadapter adapter;
    ArrayList<Giaodich> arrayList;
    FloatingActionButton fab;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvkhoanThu = view.findViewById(R.id.rcvKhoanThu);
        gdDao = new GDDao(getActivity());
        arrayList = new ArrayList<>();
        arrayList = gdDao.getAll("thu");
        adapter = new Khoanthuadapter(getActivity(), arrayList);
        fab = view.findViewById(R.id.fabKhoanThu);
        rvkhoanThu.setAdapter(adapter);
        rvkhoanThu.setLayoutManager(new LinearLayoutManager(getActivity()));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View view = inflater.inflate(R.layout.thuchi_add, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                EditText edmathuchi = view.findViewById(R.id.editmatd);
                EditText edmucdich = view.findViewById(R.id.editTieude);
                EditText edngay = view.findViewById(R.id.editngay);
                EditText edtien = view.findViewById(R.id.editTien);
                EditText edmota = view.findViewById(R.id.editMota);
                Button btnHuy = view.findViewById(R.id.buttonHuythem);
                Button btnThem = view.findViewById(R.id.buttonThemthuchi);
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int matd = Integer.parseInt(edmathuchi.getText().toString());
                        String tieude = edmucdich.getText().toString();
                        String ngay = edngay.getText().toString();

                        Double tien = Double.parseDouble(edtien.getText().toString());
                        String mota = edmota.getText().toString();
                        Giaodich gd = new Giaodich(tieude, ngay, tien, mota, matd);
                        if (gdDao.inset(gd)) {
                            Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_LONG).show();

                            dialog.dismiss();
                            arrayList.clear();
                            arrayList.addAll(gdDao.getAll("thu"));
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getActivity(), "Thêm không thành công", Toast.LENGTH_LONG).show();

                        }

                    }

                });

            }
        });
    }


}