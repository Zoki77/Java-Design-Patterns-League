/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.foi.ud.ztintor.glavna;

import hr.foi.ud.ztintor.memento.Caretaker;
import hr.foi.ud.ztintor.memento.Originator;
import hr.foi.ud.ztintor.observer.Klub;
import hr.foi.ud.ztintor.observer.Subject;
import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zoran Tintor
 */
public class Dretva extends Thread {

    private int interval;
    private int kontrola;
    private int prag;
    private long pocetak;
    private long razlika;
    public static int brojac;
    private Random rnd = new Random();
    private int broj;
    private ArrayList listaBrojeva;
    private Klub domaci;
    private Klub gosti;
    private int rezultat;
    private Par par;
    Caretaker caretaker;
    Originator originator = new Originator();
    Caretaker caretaker1;
    Originator originator1 = new Originator();
    Caretaker caretaker2;
    Originator originator2 = new Originator();
    private ArrayList listaKolo;
    private ArrayList listaSve;
    private ArrayList<Klub> listaSort;
    private ArrayList<Klub> listaPrethodnogKola;
    private boolean promjenaRedosljeda;
    public static int brojacArhive;
    private Subject s;

    public Dretva(String[] args, Subject s, Caretaker caretaker,Caretaker caretaker1,Caretaker caretaker2) {
        interval = Integer.parseInt(args[1]);
        kontrola = Integer.parseInt(args[2]);
        prag = Integer.parseInt(args[3]);
        brojac = 1;
        brojacArhive = 0;
        this.s = s;
        this.caretaker = caretaker;
        this.caretaker1 = caretaker1;
        this.caretaker2 = caretaker2;
    }

    @Override
    public synchronized void start() {
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {        
        while (Ztintor_zadaca_2.listaKlubova.size()>2) {
            pocetak = new Date().getTime();
            listaBrojeva = new ArrayList();
            listaKolo = new ArrayList();
            listaSve = new ArrayList();
            listaSort = new ArrayList();
            System.out.println("Kolo " + brojac);


            //RANDOM parovi
            while (listaBrojeva.size() != Ztintor_zadaca_2.listaKlubova.size()) {
                broj = rnd.nextInt(Ztintor_zadaca_2.listaKlubova.size()) + 1;
                if (!listaBrojeva.contains(broj)) {
                    listaBrojeva.add(broj);
                    if (listaBrojeva.size() % 2 == 1 && listaBrojeva.size() == Ztintor_zadaca_2.listaKlubova.size()) {
                        try {
                            domaci = (Klub) ((Klub) Ztintor_zadaca_2.listaKlubova.get(broj - 1)).clone();
                            ((Klub) Ztintor_zadaca_2.listaKlubova.get(domaci.getRedniBroj() - 1)).setPrethodnaEfikasnost(((Klub) Ztintor_zadaca_2.listaKlubova.get(domaci.getRedniBroj() - 1)).getEfikasnost());
                            domaci.setPrethodnaEfikasnost(domaci.getEfikasnost());
                            listaSort.add(domaci);
                            gosti = null;
                            rezultat = 3;
                            par = new Par(domaci, gosti, rezultat);
                            listaSve.add(par);
                        } catch (CloneNotSupportedException ex) {
                            Logger.getLogger(Dretva.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (listaBrojeva.size() % 2 == 1) {
                        try {
                            domaci = (Klub) ((Klub) Ztintor_zadaca_2.listaKlubova.get(broj - 1)).clone();
                        } catch (CloneNotSupportedException ex) {
                            Logger.getLogger(Dretva.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            gosti = (Klub) ((Klub) Ztintor_zadaca_2.listaKlubova.get(broj - 1)).clone();
                        } catch (CloneNotSupportedException ex) {
                            Logger.getLogger(Dretva.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        rezultat = rnd.nextInt(3);
                        par = new Par(domaci, gosti, rezultat);
                        listaKolo.add(par);
                    }
                }
            }

            for (int i = 0; i < listaKolo.size(); i++) {
                par = (Par) listaKolo.get(i);
                rezultat = par.getRezultat();
                domaci = par.getDomaci();
                gosti = par.getGosti();
                ((Klub) Ztintor_zadaca_2.listaKlubova.get(domaci.getRedniBroj() - 1)).setPrethodnaEfikasnost(((Klub) Ztintor_zadaca_2.listaKlubova.get(domaci.getRedniBroj() - 1)).getEfikasnost());
                ((Klub) Ztintor_zadaca_2.listaKlubova.get(gosti.getRedniBroj() - 1)).setPrethodnaEfikasnost(((Klub) Ztintor_zadaca_2.listaKlubova.get(gosti.getRedniBroj() - 1)).getEfikasnost());
                domaci.setPrethodnaEfikasnost(domaci.getEfikasnost());
                gosti.setPrethodnaEfikasnost(gosti.getEfikasnost());
                switch (rezultat) {
                    case 0:
                        ((Klub) Ztintor_zadaca_2.listaKlubova.get(domaci.getRedniBroj() - 1)).setBodovi(((Klub) Ztintor_zadaca_2.listaKlubova.get(domaci.getRedniBroj() - 1)).getBodovi() + 1);
                        ((Klub) Ztintor_zadaca_2.listaKlubova.get(gosti.getRedniBroj() - 1)).setBodovi(((Klub) Ztintor_zadaca_2.listaKlubova.get(gosti.getRedniBroj() - 1)).getBodovi() + 1);
                        domaci.setBodovi(domaci.getBodovi() + 1);
                        gosti.setBodovi(gosti.getBodovi() + 1);
                        break;
                    case 1:
                        ((Klub) Ztintor_zadaca_2.listaKlubova.get(domaci.getRedniBroj() - 1)).setBodovi(((Klub) Ztintor_zadaca_2.listaKlubova.get(domaci.getRedniBroj() - 1)).getBodovi() + 3);
                        domaci.setBodovi(domaci.getBodovi() + 3);
                        break;
                    case 2:
                        ((Klub) Ztintor_zadaca_2.listaKlubova.get(gosti.getRedniBroj() - 1)).setBodovi(((Klub) Ztintor_zadaca_2.listaKlubova.get(gosti.getRedniBroj() - 1)).getBodovi() + 3);
                        gosti.setBodovi(gosti.getBodovi() + 3);
                        break;
                }
                ((Klub) Ztintor_zadaca_2.listaKlubova.get(domaci.getRedniBroj() - 1)).setBrojUtakmica(((Klub) Ztintor_zadaca_2.listaKlubova.get(domaci.getRedniBroj() - 1)).getBrojUtakmica() + 1);
                domaci.setBrojUtakmica(domaci.getBrojUtakmica() + 1);
                ((Klub) Ztintor_zadaca_2.listaKlubova.get(gosti.getRedniBroj() - 1)).setBrojUtakmica(((Klub) Ztintor_zadaca_2.listaKlubova.get(gosti.getRedniBroj() - 1)).getBrojUtakmica() + 1);
                gosti.setBrojUtakmica(gosti.getBrojUtakmica() + 1);
                ((Klub) Ztintor_zadaca_2.listaKlubova.get(domaci.getRedniBroj() - 1)).setEfikasnost(((double) ((Klub) Ztintor_zadaca_2.listaKlubova.get(domaci.getRedniBroj() - 1)).getBodovi()) / ((double) ((Klub) Ztintor_zadaca_2.listaKlubova.get(domaci.getRedniBroj() - 1)).getBrojUtakmica()));
                ((Klub) Ztintor_zadaca_2.listaKlubova.get(gosti.getRedniBroj() - 1)).setEfikasnost(((double) ((Klub) Ztintor_zadaca_2.listaKlubova.get(gosti.getRedniBroj() - 1)).getBodovi()) / ((double) ((Klub) Ztintor_zadaca_2.listaKlubova.get(gosti.getRedniBroj() - 1)).getBrojUtakmica()));
                domaci.setEfikasnost((double) domaci.getBodovi() / (double) domaci.getBrojUtakmica());
                gosti.setEfikasnost((double) gosti.getBodovi() / (double) gosti.getBrojUtakmica());
                listaSort.add(domaci);
                listaSort.add(gosti);
                par = new Par(domaci, gosti, rezultat);
                listaSve.add(par);


            }
            s.setState(null);

            // Sortiranje liste klubova prema bodovima
            Collections.sort(listaSort, new Comparator<Klub>() {
                public int compare(Klub o1, Klub o2) {
                    if (o1.getBodovi() >= o2.getBodovi()) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            });

            // Postavljanje mjesta klubova
            listaPrethodnogKola = new ArrayList<>();
            for (Klub k : listaSort) {
                try {
                    listaPrethodnogKola.add((Klub) k.clone());
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(Dretva.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            //Ažururanje liste klubova
            listaSort.get(0).setMjesto(1);
            for (int i = 1; i < listaSort.size(); i++) {
                if (listaSort.get(i).getBodovi() == listaSort.get(i - 1).getBodovi()) {
                    listaSort.get(i).setMjesto(listaSort.get(i - 1).getMjesto());
                } else {
                    listaSort.get(i).setMjesto(i + 1);
                }
            }

            //Ažururanje liste klubova prethodnog kola
            for (int i = 0; i < listaSort.size(); i++) {
                for (int j = 0; j < listaSort.size(); j++) {
                    if (listaSort.get(i).getNaziv().equals(((Klub) Ztintor_zadaca_2.listaKlubova.get(j)).getNaziv())) {
                        ((Klub) Ztintor_zadaca_2.listaKlubova.get(j)).setMjesto(listaSort.get(i).getMjesto());
                    }
                }
            }

            // Provjera prethodnog i novog redosljeda
            promjenaRedosljeda = false;
            for (int i = 0; i < listaSort.size(); i++) {
                for (int j = 0; j < listaPrethodnogKola.size(); j++) {
                    if (listaSort.get(i).getNaziv().equals(listaPrethodnogKola.get(j).getNaziv())) {
                        if (listaSort.get(i).getMjesto() != listaPrethodnogKola.get(j).getMjesto()) {
                            promjenaRedosljeda = true;
                            j = listaPrethodnogKola.size();
                            i = listaSort.size();
                        }
                    }
                }
            }

            //MEMENTO
            originator.set((ArrayList) listaSort);
            originator1.set(listaSve);
            originator2.set(listaSve);
            caretaker2.addMemento(originator2.saveToMemento());

            //Spremanje arhive
            if (promjenaRedosljeda) {
                caretaker.addMemento(originator.saveToMemento());
                caretaker1.addMemento(originator1.saveToMemento());
                brojacArhive++;
                System.out.println("arhiva: *******   " + brojacArhive);
            }
            
            //----------------------------ISPISI VRAĆAJU TOČNE VRIJEDNOSTI------------------//
//            if (brojac == 3) {
//                originator.restoreFromMementoRedosljed(caretaker.getMemento(0));
//                originator1.restoreFromMementoRezultatiKola(caretaker1.getMemento(0));
//                System.out.println("--- Rezultati "+ "DINAMO"+" -----");
//                for (int i = 0; i < brojac; i++) {
//                    originator2.restoreFromMementoRezultatiKluba(caretaker2.getMemento(i), "DINAMO");
//                }
//            }

            //provjera stanja
            if (brojac % kontrola == 0) {
                for (int i = 0; i < Ztintor_zadaca_2.listaKlubova.size(); i++) {
                    ((Klub) Ztintor_zadaca_2.listaKlubova.get(i)).postaviStanje(prag);
                }
                for (int i = 0; i < Ztintor_zadaca_2.listaKlubova.size(); i++) {
                    Klub kl = new Klub();
                    kl = (Klub) Ztintor_zadaca_2.listaKlubova.get(i);
                    if (kl.isProvjera()) {
                        
                        Ztintor_zadaca_2.s.removeObserver((Klub) Ztintor_zadaca_2.listaKlubova.get(i));
                        Ztintor_zadaca_2.listaKlubova.remove((Klub) Ztintor_zadaca_2.listaKlubova.get(i));
                    }
                }

                
                for (int i = 0; i < Ztintor_zadaca_2.listaKlubova.size(); i++) {
                    ((Klub) Ztintor_zadaca_2.listaKlubova.get(i)).setPrethodnoMjesto(((Klub) Ztintor_zadaca_2.listaKlubova.get(i)).getMjesto());
                    ((Klub) Ztintor_zadaca_2.listaKlubova.get(i)).setRedniBroj(i + 1);
                }
            }

            brojac++;
            // Interval dretve
            try {
                razlika = new Date().getTime() - pocetak;
                sleep(interval * 1000 - razlika);
            } catch (InterruptedException ex) {
                Logger.getLogger(Dretva.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    @Override
    public void interrupt() {
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }
}
