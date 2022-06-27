package edu.fpt.asm.ui.khoanthu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import edu.fpt.asm.R;
import edu.fpt.asm.adapter.Viewpageradapter;


public class KhoanThuFragment extends Fragment {
    private TabLayout mtTabLayout;
    private ViewPager mviewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_khoanthu, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mtTabLayout = view.findViewById(R.id.tablayout);
        mviewPager = view.findViewById(R.id.viewPager);
        Viewpageradapter adapter = new Viewpageradapter(getActivity().getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mviewPager.setAdapter(adapter);
        mtTabLayout.setupWithViewPager(mviewPager);


    }

}