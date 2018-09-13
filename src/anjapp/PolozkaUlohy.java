/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package anjapp;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author MIRO
 */
public class PolozkaUlohy {
    private String idPolozky;
    private String zadaniePolozky;
    private Set<String> mnozinaMoznosti;
    private Set<String> mnozinaOdpovedi;

    public PolozkaUlohy(String idPolozky, String zadaniePolozky) {
        this.idPolozky = idPolozky;
        this.zadaniePolozky = zadaniePolozky;
        this.mnozinaOdpovedi =  new HashSet<>();
        
    }

    public String getIdPolozky() {
        return idPolozky;
    }

    public void setIdPolozky(String idPolozky) {
        this.idPolozky = idPolozky;
    }

    public String getZadaniePolozky() {
        return zadaniePolozky;
    }

    public void setZadaniePolozky(String zadaniePolozky) {
        this.zadaniePolozky = zadaniePolozky;
    }

    public Set<String> getMnozinaMoznosti() {
        return mnozinaMoznosti;
    }

    public void setMnozinaMoznosti(Set<String> mnozinaMoznosti) {
        this.mnozinaMoznosti = mnozinaMoznosti;
    }

    public Set<String> getMnozinaOdpovedi() {
        return mnozinaOdpovedi;
    }

    public void setMnozinaOdpovedi(Set<String> mnozinaOdpovedi) {
        this.mnozinaOdpovedi = mnozinaOdpovedi;
    }
    
    
}
