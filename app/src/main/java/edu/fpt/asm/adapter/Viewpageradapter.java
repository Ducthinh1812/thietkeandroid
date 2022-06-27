package edu.fpt.asm.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.jetbrains.annotations.NotNull;

import edu.fpt.asm.ui.khoanthu.BlankFragment;
import edu.fpt.asm.ui.khoanthu.BlankFragment1;

public class Viewpageradapter extends FragmentStatePagerAdapter {

    public Viewpageradapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BlankFragment();
            case 1:
                return new BlankFragment1();
            default:
                return new BlankFragment();
        }

    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Khoản thu";
                break;
            case 1:
                title = "Loại Thu";
                break;
        }
        return title;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
