package edu.fpt.asm.model;

import java.util.Date;

public class Giaodich {
    private int MAGD;
    private String TIEUDE;
    private String NGAYGD;
    private Double TIEN;
    private String MOTA;
    private int MALOAI;

    public Giaodich(int MAGD, String TIEUDE, String NGAYGD, Double TIEN, String MOTA, int MALOAI) {
        this.MAGD = MAGD;
        this.TIEUDE = TIEUDE;
        this.NGAYGD = NGAYGD;
        this.TIEN = TIEN;
        this.MOTA = MOTA;
        this.MALOAI = MALOAI;
    }

    public Giaodich(String TIEUDE, String NGAYGD, Double TIEN, String MOTA, int MALOAI) {
        this.TIEUDE = TIEUDE;
        this.NGAYGD = NGAYGD;
        this.TIEN = TIEN;
        this.MOTA = MOTA;
        this.MALOAI = MALOAI;
    }

    public int getMAGD() {
        return MAGD;
    }

    public void setMAGD(int MAGD) {
        this.MAGD = MAGD;
    }

    public String getTIEUDE() {
        return TIEUDE;
    }

    public void setTIEUDE(String TIEUDE) {
        this.TIEUDE = TIEUDE;
    }

    public String getNGAYGD() {
        return NGAYGD;
    }

    public void setNGAYGD(String NGAYGD) {
        this.NGAYGD = NGAYGD;
    }

    public Double getTIEN() {
        return TIEN;
    }

    public void setTIEN(Double TIEN) {
        this.TIEN = TIEN;
    }

    public String getMOTA() {
        return MOTA;
    }

    public void setMOTA(String MOTA) {
        this.MOTA = MOTA;
    }

    public int getMALOAI() {
        return MALOAI;
    }

    public void setMALOAI(int MALOAI) {
        this.MALOAI = MALOAI;
    }
}
