/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.foi.ud.ztintor.glavna;

import hr.foi.ud.ztintor.memento.Caretaker;
import hr.foi.ud.ztintor.memento.Originator;
import hr.foi.ud.ztintor.observer.Klub;
import hr.foi.ud.ztintor.observer.Subject;
import hr.foi.ud.ztintor.observer.SubjectImp;
import java.io.BufferedReader;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Zoran Tintor
 */
public class Ztintor_zadaca_2 {

    public static ArrayList<Object> listaKlubova = new ArrayList<>();
    public static Subject s = new SubjectImp();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        Caretaker caretaker = new Caretaker();
        Caretaker caretaker1 = new Caretaker();
        Caretaker caretaker2 = new Caretaker();
        Originator originator = new Originator();
        Originator originator1 = new Originator();
        Originator originator2 = new Originator();
        BufferedReader br = new BufferedReader(new FileReader(args[0])); //"F:\\ztintor\\klubovi.txt"

        try {
            String line = br.readLine();
            int i = 1;

            while (line != null) {

                Klub klub = new Klub();
                klub.setBodovi(0);
                klub.setBrojUtakmica(0);
                klub.setMjesto(1);
                klub.setPrethodnoMjesto(1);
                klub.setEfikasnost(0);
                klub.setPrethodnaEfikasnost(0);
                klub.setRedniBroj(i);
                klub.setProvjera(false);
                klub.setSifra(Integer.parseInt(line.substring(0, 5).trim()));
                klub.setNaziv(line.substring(5));
                line = br.readLine();

                listaKlubova.add(klub);
                s.addObserver(klub);
                i++;
            }
            //caretaker.addMemento(listaKlubova);
        } finally {
            br.close();
        }

        Dretva dretva = new Dretva(args, s, caretaker, caretaker1, caretaker2);
        dretva.start();

        Console c = System.console();
        if (c == null) {
            System.err.println("console fail!");
            System.exit(1);
        }
        String input = c.readLine();
        if (input.equals("q")) {
            dretva.join();
            System.out.println("----------KOMANDE-------------");
            System.out.println("Rezutati kola: kolo broj");
            System.out.println("Rezutati kluba: klub naziv");
            System.out.println("Tablica kola: tablica broj");
            System.out.println("Broj arhiviranih podataka: arhiva");

            while (true) {
                String split[] = input.split(" ");
                switch (split[0]) {
                    case "kolo":
                        originator1.restoreFromMementoRezultatiKola(caretaker1.getMemento(Integer.parseInt(split[1])));
                        break;
                    case "klub":
                        System.out.println("--- Rezultati " + split[1].trim() + " -----");
                        for (int i = 0; i < Dretva.brojac; i++) {
                            originator2.restoreFromMementoRezultatiKluba(caretaker2.getMemento(i), split[1].trim());
                        }
                        break;
                    case "tablica":
                        originator.restoreFromMementoRedosljed(caretaker.getMemento(Integer.parseInt(split[1])));
                        break;
                    case "arhiva":
                        System.out.println("Broj arhiva je:"+ Dretva.brojacArhive);
                        break;
                }
            }
        }

    }
}
