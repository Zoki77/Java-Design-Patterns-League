/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.foi.ud.ztintor.glavna;

import hr.foi.ud.ztintor.observer.Klub;

/**
 *
 * @author Zoran Tintor
 */
public class Par {
    private Klub domaci;
    private Klub gosti;
    private int rezultat;

    public Par(Klub domaci, Klub gosti, int rezultat) {
        this.domaci = domaci;
        this.gosti = gosti;
        this.rezultat = rezultat;
    }

    public Klub getDomaci() {
        return domaci;
    }

    public void setDomaci(Klub domaci) {
        this.domaci = domaci;
    }

    public Klub getGosti() {
        return gosti;
    }

    public void setGosti(Klub gosti) {
        this.gosti = gosti;
    }

    public int getRezultat() {
        return rezultat;
    }

    public void setRezultat(int rezultat) {
        this.rezultat = rezultat;
    }
    
    
}
