package com.example.duanmau.Class;

public class HoaDonChiTiet {
    private int maHdct;
    private HoaDon hoaDon;
    private SachClass sachClass;
    private int slMua;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int maHdct, HoaDon hoaDon, SachClass sachClass, int slMua) {
        this.maHdct = maHdct;
        this.hoaDon = hoaDon;
        this.sachClass = sachClass;
        this.slMua = slMua;
    }

    public int getMaHdct() {
        return maHdct;
    }

    public void setMaHdct(int maHdct) {
        this.maHdct = maHdct;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public SachClass getSachClass() {
        return sachClass;
    }

    public void setSachClass(SachClass sachClass) {
        this.sachClass = sachClass;
    }

    public int getSlMua() {
        return slMua;
    }

    public void setSlMua(int slMua) {
        this.slMua = slMua;
    }
}
