/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.foi.ud.ztintor.memento;

import hr.foi.ud.ztintor.glavna.Par;
import hr.foi.ud.ztintor.observer.Klub;
import java.util.ArrayList;

/**
 *
 * @author Zoran Tintor
 */
public class Originator {

    private ArrayList<Object> savedStates;
    private Klub klub;
    private Par par;

    public void set(ArrayList<Object> savedStates) {
        this.savedStates = savedStates;
    }

    public Object saveToMemento() {
        return new Memento(savedStates);
    }

    public void restoreFromMementoRedosljed(Object m) {
        if (m instanceof Memento) {
            Memento memento = (Memento) m;
            savedStates = memento.getSavedState();
            System.out.println("TABLICA");
            System.out.println("mjesto----naziv----bodovi---broj utakmica ");
            for (int i = 0; i < savedStates.size(); i++) {
                klub = (Klub) savedStates.get(i);
                System.out.println(klub.getMjesto() + ". " + klub.getNaziv() + "   " + klub.getBodovi() + "   " + klub.getBrojUtakmica());
            }
        }
    }

    public void restoreFromMementoRezultatiKola(Object m) {
        if (m instanceof Memento) {
            Memento memento = (Memento) m;
            savedStates = memento.getSavedState();
            System.out.println("**** Rezultati Kola ****");
            for (int i = 0; i < savedStates.size(); i++) {
                par = (Par) savedStates.get(i);
                if (par.getRezultat() != 3) {
                    System.out.println(par.getDomaci().getNaziv() + "   " + par.getGosti().getNaziv() + "  " + par.getRezultat());
                } else {
                    System.out.println(par.getDomaci().getNaziv() + "  SLOBODAN");
                }
            }
        }
    }

    public void restoreFromMementoRezultatiKluba(Object m, String naziv) {
        if (m instanceof Memento) {
            Memento memento = (Memento) m;
            savedStates = memento.getSavedState();            
            for (int i = 0; i < savedStates.size(); i++) {
                par = (Par) savedStates.get(i);
                if (par.getRezultat() != 3) {
                    if (par.getDomaci().getNaziv().equals(naziv) || par.getGosti().getNaziv().equals(naziv)) {
                        System.out.println(par.getDomaci().getNaziv() + "   " + par.getGosti().getNaziv() + "  " + par.getRezultat());
                    }
                } else {
                    if (par.getDomaci().getNaziv().equals(naziv)) {
                        System.out.println(par.getDomaci().getNaziv() + "  SLOBODAN");
                    }
                }
            }
        }
    }

    private static class Memento {

        private ArrayList<Object> savedStates;

        public Memento(ArrayList<Object> stateToSave) {
            savedStates = stateToSave;
        }

        public ArrayList<Object> getSavedState() {
            return savedStates;
        }
    }
}
