package edu.fpt.asm.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import edu.fpt.asm.sqlite.Sqlhepper;
import edu.fpt.asm.model.Phanloai;

public class PhanloaiDao {
    Sqlhepper sqlHepper;

    public PhanloaiDao(Context context) {
        sqlHepper = new Sqlhepper(context);

    }

    public ArrayList<Phanloai> getAll() {
        ArrayList<Phanloai> ds = new ArrayList<>();
        SQLiteDatabase dp = sqlHepper.getReadableDatabase();
        String sql = "SELECT*FROM PHANLOAI";
        Cursor cursor = dp.rawQuery(sql, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            int ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            String trangthai = cursor.getString(2);
            ds.add(new Phanloai(ma, ten, trangthai));
            cursor.moveToNext();
        }
        cursor.close();
        dp.close();
        return ds;
    }

    public ArrayList<Phanloai> getAll(String trangThai) {
        ArrayList<Phanloai> ds = new ArrayList<>();
        SQLiteDatabase dp = sqlHepper.getReadableDatabase();
        String sql = "SELECT*FROM PHANLOAI WHERE TRANGTHAI='" + trangThai + "'";
        Cursor cursor = dp.rawQuery(sql, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            int ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            String trangthai = cursor.getString(2);
            ds.add(new Phanloai(ma, ten, trangthai));
            cursor.moveToNext();
        }
        cursor.close();
        dp.close();
        return ds;
    }

    public boolean inset(Phanloai phanloai) {
        SQLiteDatabase dp = sqlHepper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TENLOAI", phanloai.getTENLOAI());
        contentValues.put("TRANGTHAI", phanloai.getTRANGTHAI());
        long row = dp.insert("PHANLOAI", null, contentValues);
        return (row > 0);
    }

    public boolean update(Phanloai phanloai) {

        SQLiteDatabase dp = sqlHepper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TENLOAI", phanloai.getTENLOAI());
        contentValues.put("TRANGTHAI", phanloai.getTRANGTHAI());
        int row = dp.update("PHANLOAI", contentValues, " MALOAI=?", new String[]{phanloai.getMALOAI() + ""});
        return (row > 0);
    }

    public boolean delete(int MALOAI) {
        String sql = "DELETE FORM PHANLOAI WHERE MALOAI=?";
        SQLiteDatabase dp = sqlHepper.getWritableDatabase();
        int row = dp.delete("PHANLOAI", " MALOAI=?", new String[]{MALOAI + ""});
        return (row > 0);
    }
}
