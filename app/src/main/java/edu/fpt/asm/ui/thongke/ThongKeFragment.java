package edu.fpt.asm.ui.thongke;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import edu.fpt.asm.dao.PhanloaiDao;
import edu.fpt.asm.R;
import edu.fpt.asm.adapter.Phanloaiadapter;
import edu.fpt.asm.model.Phanloai;


public class ThongKeFragment extends Fragment {
    RecyclerView rvPhanLoai;
    PhanloaiDao plDao;
    Phanloaiadapter adapter;
    ArrayList<Phanloai> arrayList;
    FloatingActionButton fab;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_thongke, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPhanLoai = view.findViewById(R.id.rcvPhanLoai);
        plDao = new PhanloaiDao(getActivity());

        arrayList = new ArrayList<>();
        arrayList = plDao.getAll();

        adapter = new Phanloaiadapter(getActivity(), arrayList);
        rvPhanLoai.setAdapter(adapter);
        rvPhanLoai.setLayoutManager(new LinearLayoutManager(getActivity()));
        fab = view.findViewById(R.id.fabPhanLoai);
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
        String[] arrTrangThai = {"thu", "chi"};
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
                    arrayList.addAll(plDao.getAll());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "không thành công", Toast.LENGTH_LONG).show();
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