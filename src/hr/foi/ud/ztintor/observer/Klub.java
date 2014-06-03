/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.foi.ud.ztintor.observer;

import hr.foi.ud.ztintor.state.Natjecatelj;
import hr.foi.ud.ztintor.state.State;

/**
 *
 * @author Zoran Tintor
 */
public class Klub implements Cloneable,Observer{
    private String naziv;
    private int bodovi;
    private int brojUtakmica;
    private int mjesto;
    private int prethodnoMjesto;
    private int redniBroj;
    private int sifra;
    private double efikasnost;
    private double prethodnaEfikasnost;
    private State myState;
    private boolean provjera;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getPrethodnoMjesto() {
        return prethodnoMjesto;
    }

    public void setPrethodnoMjesto(int prethodnoMjesto) {
        this.prethodnoMjesto = prethodnoMjesto;
    }

    public int getBodovi() {
        return bodovi;
    }

    public void setBodovi(int bodovi) {
        this.bodovi = bodovi;
    }

    public int getBrojUtakmica() {
        return brojUtakmica;
    }

    public void setBrojUtakmica(int brojUtakmica) {
        this.brojUtakmica = brojUtakmica;
    }


    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }    

    public double getEfikasnost() {
        return efikasnost;
    }

    public void setEfikasnost(double efikasnost) {
        this.efikasnost = efikasnost;
    }

    public int getMjesto() {
        return mjesto;
    }

    public void setMjesto(int mjesto) {
        this.mjesto = mjesto;
    }

    public double getPrethodnaEfikasnost() {
        return prethodnaEfikasnost;
    }

    public void setPrethodnaEfikasnost(double prethodnaEfikasnost) {
        this.prethodnaEfikasnost = prethodnaEfikasnost;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public boolean isProvjera() {
        return provjera;
    }

    public void setProvjera(boolean provjera) {
        this.provjera = provjera;
    }

    
    
    @Override
    public void update(Subject o) {
        if(prethodnaEfikasnost!=efikasnost){
            System.out.println("info: "+naziv+"----> Prethodna efikasnost: "+prethodnaEfikasnost+ "  Nova efikasnost: " + efikasnost + "  Poboljšanje efikasnosti: " +(efikasnost-prethodnaEfikasnost));
        }
        
    }
            
    public Klub() {
        setState(new Natjecatelj());
    }
// normally only called by classes implementing the State interface
    public void setState(State stateName) {
        this.myState = stateName;
    }

    public void postaviStanje(int prag) {
        this.myState.postaviStanje(this, prag);
    }
    
}
