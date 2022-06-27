package edu.fpt.asm.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import edu.fpt.asm.model.Giaodich;
import edu.fpt.asm.sqlite.Sqlhepper;

public class GDDao {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Sqlhepper sqlHepper;

    public GDDao(Context context) {
        sqlHepper = new Sqlhepper(context);

    }

    public ArrayList<Giaodich> getAll() {
        ArrayList<Giaodich> ds = new ArrayList<>();
        SQLiteDatabase dp = sqlHepper.getReadableDatabase();
        String sql = "SELECT*FROM GIAODICH";
        Cursor cursor = dp.rawQuery(sql, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            int ma = cursor.getInt(0);
            String tieude = cursor.getString(1);
            String ngay = cursor.getString(2);

            double tien = cursor.getDouble(3);
            String mota = cursor.getString(4);
            int maloai = cursor.getInt(5);
            ds.add(new Giaodich(ma, tieude, ngay, tien, mota, maloai));
            cursor.moveToNext();
        }
        cursor.close();
        dp.close();
        return ds;
    }

    public ArrayList<Giaodich> getAll(String trangTHAI) {
        ArrayList<Giaodich> ds = new ArrayList<>();
        SQLiteDatabase database = sqlHepper.getReadableDatabase();
        String sql = "SELECT *FROM GIAODICH JOIN PHANLOAI ON GIAODICH.MALOAI=PHANLOAI.MALOAI " +
                "WHERE TRANGTHAI='" + trangTHAI + "'";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            int ma = cursor.getInt(0);
            String tieude = cursor.getString(1);
            String ngay = cursor.getString(2);

            double tien = cursor.getDouble(3);
            String mota = cursor.getString(4);
            int maloai = cursor.getInt(5);
            ds.add(new Giaodich(ma, tieude, ngay, tien, mota, maloai));
            cursor.moveToNext();
        }
        return ds;
    }

    public boolean inset(Giaodich gd) {
        SQLiteDatabase database = sqlHepper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TIEUDE", gd.getTIEUDE());
        values.put("NGAYGD", gd.getNGAYGD());
        values.put("TIEN", gd.getTIEN());
        values.put("MOTA", gd.getMOTA());
        values.put("MALOAI", gd.getMALOAI());
        long row = database.insert("GIAODICH", null, values);
        return (row > 0);
    }

    public boolean update(Giaodich gd) {
        SQLiteDatabase database = sqlHepper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MALOAI", gd.getMALOAI());
        values.put("TIEUDE", gd.getTIEUDE());
        values.put("NGAYGD", gd.getNGAYGD());
        values.put("TIEN", gd.getTIEN());
        values.put("MOTA", gd.getMOTA());

        int row = database.update("GIAODICH", values, "MAGD=?", new String[]{gd.getMAGD() + ""});
        return (row > 0);
    }

    public boolean delete(int magd) {
        SQLiteDatabase db = sqlHepper.getWritableDatabase();
        int row = db.delete("GIAODICH", "MAGD=?", new String[]{magd + ""});
        return (row > 0);
    }
}
