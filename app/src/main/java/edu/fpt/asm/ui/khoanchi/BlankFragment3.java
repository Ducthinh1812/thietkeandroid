package edu.fpt.asm.ui.khoanchi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import edu.fpt.asm.dao.PhanloaiDao;
import edu.fpt.asm.R;
import edu.fpt.asm.adapter.Phanloaiadapter;
import edu.fpt.asm.model.Phanloai;


public class BlankFragment3 extends Fragment {
    RecyclerView rvLoaiChi;
    PhanloaiDao plDao;
    Phanloaiadapter adapter;
    ArrayList<Phanloai> arrayList;
    FloatingActionButton fab;


    public BlankFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvLoaiChi = view.findViewById(R.id.rcvLoaiChi);
        plDao = new PhanloaiDao(getActivity());

        arrayList = new ArrayList<>();
        arrayList = plDao.getAll("chi");

        adapter = new Phanloaiadapter(getActivity(), arrayList);
        rvLoaiChi.setAdapter(adapter);
        rvLoaiChi.setLayoutManager(new LinearLayoutManager(getActivity()));
        fab = view.findViewById(R.id.fabLoaiChi);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}