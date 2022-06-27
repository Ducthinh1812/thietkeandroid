package edu.fpt.asm.model;

public class Phanloai {
    private int MALOAI;
    private String TENLOAI;
    private String TRANGTHAI;

    public Phanloai(int MALOAI, String TENLOAI, String TRANGTHAI) {
        this.MALOAI = MALOAI;
        this.TENLOAI = TENLOAI;
        this.TRANGTHAI = TRANGTHAI;
    }

    public Phanloai(String TENLOAI, String TRANGTHAI) {
        this.TENLOAI = TENLOAI;
        this.TRANGTHAI = TRANGTHAI;
    }

    public int getMALOAI() {
        return MALOAI;
    }

    public void setMALOAI(int MALOAI) {
        this.MALOAI = MALOAI;
    }

    public String getTENLOAI() {
        return TENLOAI;
    }

    public void setTENLOAI(String TENLOAI) {
        this.TENLOAI = TENLOAI;
    }

    public String getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(String TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }
}
