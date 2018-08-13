package profil;

public class direktorluk {

    private int direkid;
    private String direkad;
    private String adres;

    public direktorluk(int direkid, String direkad, String adres) {
        this.direkid = direkid;
        this.direkad = direkad;
        this.adres = adres;
    }

    public direktorluk() {

    }

    public int getDirekid() {
        return direkid;
    }

    public void setDirekid(int direkid) {
        this.direkid = direkid;
    }

    public String getDirekad() {
        return direkad;
    }

    public void setDirekad(String direkad) {
        this.direkad = direkad;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
}
