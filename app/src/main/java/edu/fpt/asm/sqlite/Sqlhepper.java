package edu.fpt.asm.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Sqlhepper extends SQLiteOpenHelper {
    public Sqlhepper(Context context) {
        super(context, "asm.sql", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE PHANLOAI(MALOAI integer primary key autoincrement," +
                "TENLOAI text, TRANGTHAI text)";
        db.execSQL(sql);
        sql = "insert into PHANLOAI values(null,'Tiền ăn','chi')";
        db.execSQL(sql);
        sql = "insert into PHANLOAI values(null,'Tiền lương','thu')";
        db.execSQL(sql);
        sql = "insert into PHANLOAI values(null,'Tiền nhà','chi')";
        db.execSQL(sql);
        sql = "insert into PHANLOAI values(null,'Tiền điện','chi')";
        db.execSQL(sql);
        sql = "CREATE TABLE GIAODICH(MAGD integer primary key autoincrement," +
                "TIEUDE text, NGAYGD text, TIEN float, MOTA text," +
                " MALOAI integer references PHANLOAI(MALOAI))";
        db.execSQL(sql);
        sql = "INSERT INTO GIAODICH VALUES(null,'Tiền lương','18-12-2002',2000000,'hi',1)";
        db.execSQL(sql);
        sql = "INSERT INTO GIAODICH VALUES(null,'Tiền ăn','18-12-2002',2000000,'hii',2)";
        db.execSQL(sql);
        sql = "INSERT INTO GIAODICH VALUES(null,'Tiền nhà','18-12-2002',2000000,'hiii',3)";
        db.execSQL(sql);
        sql = "INSERT INTO GIAODICH VALUES(null,'Tiền điện','18-12-2002',2000000,'hiiii',4)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists PHANLOAi");
        db.execSQL("drop table if exists GIAODICH");
        onCreate(db);
    }
}
