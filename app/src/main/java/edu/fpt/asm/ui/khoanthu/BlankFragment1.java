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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import edu.fpt.asm.dao.PhanloaiDao;
import edu.fpt.asm.R;
import edu.fpt.asm.adapter.Phanloaiadapter;
import edu.fpt.asm.model.Phanloai;


public class BlankFragment1 extends Fragment {

    RecyclerView rvLoaiThu;
    PhanloaiDao plDao;
    Phanloaiadapter adapter;
    ArrayList<Phanloai> arrayList;
    FloatingActionButton fab;

    public BlankFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvLoaiThu = view.findViewById(R.id.rcvLoaiThu);
        plDao = new PhanloaiDao(getActivity());

        arrayList = new ArrayList<>();
        arrayList = plDao.getAll("thu");

        adapter = new Phanloaiadapter(getActivity(), arrayList);
        rvLoaiThu.setAdapter(adapter);
        rvLoaiThu.setLayoutManager(new LinearLayoutManager(getActivity()));
        fab = view.findViewById(R.id.fabLoaiThu);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDIalog();
            }
        });
    }

    private void openDIalog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.phanloai_add, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText etenLoai = view.findViewById(R.id.editTenLoai);
        Button buttonThem = view.findViewById(R.id.buttonThem);
        Button buttonHuy = view.findViewById(R.id.buttonHuy);
        Spinner spinner = view.findViewById(R.id.spinnerTrangThai);
        String[] arrTrangThai = {"thu"};
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, arrTrangThai);
        spinner.setAdapter(spnAdapter);
        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenLoai = etenLoai.getText().toString();
                String trangthai = (String) spinner.getSelectedItem();
                if (plDao.inset(new Phanloai(tenLoai, trangthai))) {
                    Toast.makeText(getActivity(), "thành công", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    arrayList.clear();
                    arrayList.addAll(plDao.getAll("thu"));
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "ko thành công", Toast.LENGTH_LONG).show();
                }
            }
        });
        buttonHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}