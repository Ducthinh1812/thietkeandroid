package edu.fpt.asm.ui.khoanchi;

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
import edu.fpt.asm.adapter.Viewpagerchi;


public class KhoanChiFragment extends Fragment {
    private TabLayout mtTabLayoutchi;
    private ViewPager mviewPagerChi;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_khoanchi, container, false);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mtTabLayoutchi = view.findViewById(R.id.tablayoutChi);
        mviewPagerChi = view.findViewById(R.id.viewPagerChi);
        Viewpagerchi adapter = new Viewpagerchi(getActivity().getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mviewPagerChi.setAdapter(adapter);
        mtTabLayoutchi.setupWithViewPager(mviewPagerChi);
    }
}