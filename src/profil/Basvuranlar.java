package profil;
public class Basvuranlar {

    private long basvurantc;
    private String basvurutarihi;
    private int ilanid;
    private String durumu;
    private String ogrenciAdi;

    public Basvuranlar(long basvurantc, String basvurutarihi, int ilanid, String durumu,String ogrenciAdi) {
        this.basvurantc = basvurantc;
        this.basvurutarihi = basvurutarihi;
        this.ilanid = ilanid;
        this.durumu = durumu;
        this.ogrenciAdi=ogrenciAdi;
    }

    public String getOgrenciAdi() {
        return ogrenciAdi;
    }

    public void setOgrenciAdi(String ogrenciAdi) {
        this.ogrenciAdi = ogrenciAdi;
    }

    public Basvuranlar() {

    }

    public long getBasvurantc() {
        return basvurantc;
    }

    public void setBasvurantc(long basvurantc) {
        this.basvurantc = basvurantc;
    }

    public String getBasvurutarihi() {
        return basvurutarihi;
    }

    public void setBasvurutarihi(String basvurutarihi) {
        this.basvurutarihi = basvurutarihi;
    }

    public int getIlanid() {
        return ilanid;
    }

    public void setIlanid(int ilanid) {
        this.ilanid = ilanid;
    }

    public String getDurumu() {
        return durumu;
    }

    public void setDurumu(String durumu) {
        this.durumu = durumu;
    }

}
