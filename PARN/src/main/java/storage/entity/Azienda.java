package storage.entity;

import java.util.List;

/**
 * Un oggetto <code>Azienda</code> è una sottoclasse di <Code>Utente</Code> che rappresenta
 * l'azienda che crea le candidature alle quali le persone posso sottoscriversi
 */
public class Azienda extends Utente{
    private String partitaIVA;
    private String ragioneSociale;
    private String link;
    private String areaInteresse;
    private int numeroDipendenti;
    private List<String> settoriCompetenza;
    private List<Sede> sedi;
    private List<Annuncio> annunci;

    public Azienda(){

    }

    public Azienda(int id, String nome, String mail, String password, String regione, String provincia, String foto, String cap, String telefono, String citta, String via, String partitaIVA, String ragioneSociale, String link, String areaInteresse, int numeroDipendenti, List<String> settoriCompetenza, List<Sede> sedi, List<Annuncio> annunci) {
        super(id, nome, mail, password, regione, provincia, foto, cap, telefono, citta, via);
        this.partitaIVA = partitaIVA;
        this.ragioneSociale = ragioneSociale;
        this.link = link;
        this.areaInteresse = areaInteresse;
        this.numeroDipendenti = numeroDipendenti;
        this.settoriCompetenza = settoriCompetenza;
        this.sedi = sedi;
        this.annunci = annunci;
    }

    public Azienda(String nome, String mail, String password, String regione, String provincia, String foto, String cap,
                   String telefono, String citta, String via, String partitaIVA, String ragioneSociale, String link,
                   String areaInteresse, int numeroDipendenti, List<String> settoriCompetenza, List<Sede> sedi,
                   List<Annuncio> annunci) {
        super(nome, mail, password, regione, provincia, foto, cap, telefono, citta, via);
        this.partitaIVA = partitaIVA;
        this.ragioneSociale = ragioneSociale;
        this.link = link;
        this.areaInteresse = areaInteresse;
        this.numeroDipendenti = numeroDipendenti;
        this.settoriCompetenza = settoriCompetenza;
        this.sedi = sedi;
        this.annunci = annunci;
    }



    public String getPartitaIVA() {
        return partitaIVA;
    }

    public void setPartitaIVA(String partitaIVA) {
        this.partitaIVA = partitaIVA;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAreaInteresse() {
        return areaInteresse;
    }

    public void setAreaInteresse(String areaInteresse) {
        this.areaInteresse = areaInteresse;
    }

    public int getNumeroDipendenti() {
        return numeroDipendenti;
    }

    public void setNumeroDipendenti(int numeroDipendenti) {
        this.numeroDipendenti = numeroDipendenti;
    }

    public List<String> getSettoriCompetenza() {
        return settoriCompetenza;
    }

    public void setSettoriCompetenza(List<String> settoriCompetenza) {
        this.settoriCompetenza = settoriCompetenza;
    }

    public List<Sede> getSedi() {
        return sedi;
    }

    public void setSedi(List<Sede> sedi) {
        this.sedi = sedi;
    }

    public List<Annuncio> getAnnunci() {
        return annunci;
    }

    public void setAnnunci(List<Annuncio> annunci) {
        this.annunci = annunci;
    }

}
