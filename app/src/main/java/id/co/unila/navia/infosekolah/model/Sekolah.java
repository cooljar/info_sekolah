package id.co.unila.navia.infosekolah.model;

import org.parceler.Parcel;

/**
 * Created by japra_awok on 24/03/2017.
 */

@Parcel
public class Sekolah {
    public Integer id;
    public String tipe;
    public String kecamatan;
    public String npsn;
    public String nama;
    public String alamat;
    public String nama_kepsek;
    public String no_telp;
    public String email;
    public String website;
    public Integer jumlah_guru;
    public Integer jumlah_kelas;
    public Integer daya_tampung;
    public String r_nilai_un;
    public String max_nilai_un;
    public String r_nilai_un_ips;
    public String max_nilai_un_ips;
    public Integer jumlah_prestasi;
    public String akreditasi;
    public String nilai_akreditasi;
    public String berdiri_sejak;
    public Double lat;
    public Double lng;
    public String foto;
    public Integer peringkat;

    public Sekolah() {
    }

    public Sekolah(Integer id, String tipe, String kecamatan, String npsn, String nama, String alamat, String nama_kepsek, String no_telp, String email, String website, Integer jumlah_guru, Integer jumlah_kelas, Integer daya_tampung, String r_nilai_un, String max_nilai_un, String r_nilai_un_ips, String max_nilai_un_ips, Integer jumlah_prestasi, String akreditasi, String nilai_akreditasi, String berdiri_sejak, Double lat, Double lng, String foto, Integer peringkat) {
        this.id = id;
        this.tipe = tipe;
        this.kecamatan = kecamatan;
        this.npsn = npsn;
        this.nama = nama;
        this.alamat = alamat;
        this.nama_kepsek = nama_kepsek;
        this.no_telp = no_telp;
        this.email = email;
        this.website = website;
        this.jumlah_guru = jumlah_guru;
        this.jumlah_kelas = jumlah_kelas;
        this.daya_tampung = daya_tampung;
        this.r_nilai_un = r_nilai_un;
        this.max_nilai_un = max_nilai_un;
        this.r_nilai_un_ips = r_nilai_un_ips;
        this.max_nilai_un_ips = max_nilai_un_ips;
        this.jumlah_prestasi = jumlah_prestasi;
        this.akreditasi = akreditasi;
        this.nilai_akreditasi = nilai_akreditasi;
        this.berdiri_sejak = berdiri_sejak;
        this.lat = lat;
        this.lng = lng;
        this.foto = foto;
        this.peringkat = peringkat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getNpsn() {
        return npsn;
    }

    public void setNpsn(String npsn) {
        this.npsn = npsn;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNama_kepsek() {
        return nama_kepsek;
    }

    public void setNama_kepsek(String nama_kepsek) {
        this.nama_kepsek = nama_kepsek;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getJumlah_guru() {
        return jumlah_guru;
    }

    public void setJumlah_guru(Integer jumlah_guru) {
        this.jumlah_guru = jumlah_guru;
    }

    public Integer getJumlah_kelas() {
        return jumlah_kelas;
    }

    public void setJumlah_kelas(Integer jumlah_kelas) {
        this.jumlah_kelas = jumlah_kelas;
    }

    public Integer getDaya_tampung() {
        return daya_tampung;
    }

    public void setDaya_tampung(Integer daya_tampung) {
        this.daya_tampung = daya_tampung;
    }

    public String getR_nilai_un() {
        return r_nilai_un;
    }

    public void setR_nilai_un(String r_nilai_un) {
        this.r_nilai_un = r_nilai_un;
    }

    public String getMax_nilai_un() {
        return max_nilai_un;
    }

    public void setMax_nilai_un(String max_nilai_un) {
        this.max_nilai_un = max_nilai_un;
    }

    public String getR_nilai_un_ips() {
        return r_nilai_un_ips;
    }

    public void setR_nilai_un_ips(String r_nilai_un_ips) {
        this.r_nilai_un_ips = r_nilai_un_ips;
    }

    public String getMax_nilai_un_ips() {
        return max_nilai_un_ips;
    }

    public void setMax_nilai_un_ips(String max_nilai_un_ips) {
        this.max_nilai_un_ips = max_nilai_un_ips;
    }

    public Integer getJumlah_prestasi() {
        return jumlah_prestasi;
    }

    public void setJumlah_prestasi(Integer jumlah_prestasi) {
        this.jumlah_prestasi = jumlah_prestasi;
    }

    public String getAkreditasi() {
        return akreditasi;
    }

    public void setAkreditasi(String akreditasi) {
        this.akreditasi = akreditasi;
    }

    public String getNilai_akreditasi() {
        return nilai_akreditasi;
    }

    public void setNilai_akreditasi(String nilai_akreditasi) {
        this.nilai_akreditasi = nilai_akreditasi;
    }

    public String getBerdiri_sejak() {
        return berdiri_sejak;
    }

    public void setBerdiri_sejak(String berdiri_sejak) {
        this.berdiri_sejak = berdiri_sejak;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getPeringkat() {
        return peringkat;
    }

    public void setPeringkat(Integer peringkat) {
        this.peringkat = peringkat;
    }
}
