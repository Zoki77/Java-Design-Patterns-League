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
public interface State {

    public void postaviStanje(Klub klub, int prag);
}
