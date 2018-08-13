package profil;

public class ilan {

    private int direkid;
    private String ilanadi;
    private int ilanid;
    private String aciklama;
    private String bolum;
    private int minSinif;
    private double minOrt;
    private String yayintarihi;
    private String sonbasvuru;
    private String direktorAdi;
    private String adres;

    public ilan(int direkid, String ilanadi, int ilanid, String aciklama, String bolum, int minSinif, double minOrt, String yayintarihi, String sonbasvuru) {
        this.direkid = direkid;
        this.ilanadi = ilanadi;
        this.ilanid = ilanid;
        this.aciklama = aciklama;
        this.bolum = bolum;
        this.minSinif = minSinif;
        this.minOrt = minOrt;
        this.yayintarihi = yayintarihi;
        this.sonbasvuru = sonbasvuru;
    }

    public ilan(int direkid, String ilanadi, int ilanid, String aciklama, String bolum, int minSinif, double minOrt, String yayintarihi, String sonbasvuru, String direktorAdi, String adres) {
        this.direkid = direkid;
        this.ilanadi = ilanadi;
        this.ilanid = ilanid;
        this.aciklama = aciklama;
        this.bolum = bolum;
        this.minSinif = minSinif;
        this.minOrt = minOrt;
        this.yayintarihi = yayintarihi;
        this.sonbasvuru = sonbasvuru;
        this.direktorAdi = direktorAdi;
        this.adres = adres;
    }

    public ilan() {

    }

    public int getDirekid() {
        return direkid;
    }

    public void setDirekid(int direkid) {
        this.direkid = direkid;
    }

    public String getIlanadi() {
        return ilanadi;
    }

    public void setIlanadi(String ilanadi) {
        this.ilanadi = ilanadi;
    }

    public int getIlanid() {
        return ilanid;
    }

    public void setIlanid(int ilanid) {
        this.ilanid = ilanid;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getBolum() {
        return bolum;
    }

    public void setBolum(String bolum) {
        this.bolum = bolum;
    }

    public int getMinSinif() {
        return minSinif;
    }

    public void setMinSinif(int minSinif) {
        this.minSinif = minSinif;
    }

    public double getMinOrt() {
        return minOrt;
    }

    public void setMinOrt(double minOrt) {
        this.minOrt = minOrt;
    }

    public String getYayintarihi() {
        return yayintarihi;
    }

    public void setYayintarihi(String yayintarihi) {
        this.yayintarihi = yayintarihi;
    }

    public String getSonbasvuru() {
        return sonbasvuru;
    }

    public void setSonbasvuru(String sonbasvuru) {
        this.sonbasvuru = sonbasvuru;
    }

    public String getDirektorAdi() {
        return direktorAdi;
    }

    public void setDirektorAdi(String direktorAdi) {
        this.direktorAdi = direktorAdi;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
}
