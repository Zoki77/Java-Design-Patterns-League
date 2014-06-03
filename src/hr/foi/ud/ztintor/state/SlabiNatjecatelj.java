/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.foi.ud.ztintor.state;

import hr.foi.ud.ztintor.observer.Klub;

/**
 *
 * @author Zoran Tintor
 */
public class SlabiNatjecatelj implements State {

    private int count = 1;

    @Override
    public void postaviStanje(Klub klub, int prag) {        
        if (klub.getMjesto()<klub.getPrethodnoMjesto()) {
            klub.setState(new Natjecatelj());
        }else{
            count++;
            if(count==prag){
                klub.setProvjera(true);
            }
        }        
    }
}