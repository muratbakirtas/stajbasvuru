package profil;
public class stajyer {


    private long ogrencitc;
    private String ogrenciAdi;
    private Double ogrenciOrtalama;
    private String ogrenciCin;
    private String okulu;
    private String bolum;
    private String email;
    private String sifre;
    private int sinifi;
    private String dogum;


    public stajyer(long ogrencitc, String ogrenciAdi, Double ogrenciOrtalama, String ogrenciCin, String okulu, String bolum, String email, String sifre, int sinifi, String dogum) {
        this.ogrencitc = ogrencitc;
        this.ogrenciAdi = ogrenciAdi;
        this.ogrenciOrtalama = ogrenciOrtalama;
        this.ogrenciCin = ogrenciCin;
        this.okulu = okulu;
        this.bolum = bolum;
        this.email = email;
        this.sifre = sifre;
        this.sinifi = sinifi;
        this.dogum = dogum;
    }

    public stajyer(){}

    public long getOgrencitc() {
        return ogrencitc;
    }

    public void setOgrencitc(long ogrencitc) {
        this.ogrencitc = ogrencitc;
    }

    public String getOgrenciAdi() {
        return ogrenciAdi;
    }

    public void setOgrenciAdi(String ogrenciAdi) {
        this.ogrenciAdi = ogrenciAdi;
    }

    public Double getOgrenciOrtalama() {
        return ogrenciOrtalama;
    }

    public void setOgrenciOrtalama(Double ogrenciOrtalama) {
        this.ogrenciOrtalama = ogrenciOrtalama;
    }

    public String getOgrenciCin() {
        return ogrenciCin;
    }

    public void setOgrenciCin(String ogrenciCin) {
        this.ogrenciCin = ogrenciCin;
    }

    public String getOkulu() {
        return okulu;
    }

    public void setOkulu(String okulu) {
        this.okulu = okulu;
    }

    public String getBolum() {
        return bolum;
    }

    public void setBolum(String bolum) {
        this.bolum = bolum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public int getSinifi() {
        return sinifi;
    }

    public void setSinifi(int sinifi) {
        this.sinifi = sinifi;
    }

    public String getDogum() {
        return dogum;
    }

    public void setDogum(String dogum) {
        this.dogum = dogum;
    }
}
