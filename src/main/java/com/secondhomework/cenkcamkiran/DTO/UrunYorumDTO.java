package com.secondhomework.cenkcamkiran.DTO;

import java.util.Date;

public class UrunYorumDTO {
    private String kullaniciAdi;
    private String urunAdi;
    private Long urunId;
    private String yorum;
    private Date yorumTarihi;

    public UrunYorumDTO(String kullaniciAdi, String urunAdi, Long urunId, String yorum, Date yorumTarihi) {
        this.kullaniciAdi = kullaniciAdi;
        this.urunAdi = urunAdi;
        this.urunId = urunId;
        this.yorum = yorum;
        this.yorumTarihi = yorumTarihi;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getUrunAdi() {
        return urunAdi;
    }

    public void setUrunAdi(String urunAdi) {
        this.urunAdi = urunAdi;
    }

    public Long getUrunId() {
        return urunId;
    }

    public void setUrunId(Long urunId) {
        this.urunId = urunId;
    }

    public String getYorum() {
        return yorum;
    }

    public void setYorum(String yorum) {
        this.yorum = yorum;
    }

    public Date getYorumTarihi() {
        return yorumTarihi;
    }

    public void setYorumTarihi(Date yorumTarihi) {
        this.yorumTarihi = yorumTarihi;
    }

}
