package edu.fpt.asm.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.jetbrains.annotations.NotNull;

import edu.fpt.asm.ui.khoanchi.BlankFragment2;
import edu.fpt.asm.ui.khoanchi.BlankFragment3;

public class Viewpagerchi extends FragmentStatePagerAdapter {
    public Viewpagerchi(@NonNull @NotNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BlankFragment2();
            case 1:
                return new BlankFragment3();
            default:
                return new BlankFragment2();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Khoản chi";
                break;
            case 1:
                title = "Loại Chi";
                break;
        }
        return title;
    }
}
