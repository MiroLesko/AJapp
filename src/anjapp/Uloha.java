/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package anjapp;

import java.util.Set;

/**
 *
 * @author MIRO
 */
public class Uloha {
    
    private String idUlohy;
    private String nazovUlohy;
    private String popisUlohy;
    private int level;
    private Set<PolozkaUlohy> mnozinaPoloziekUlohy;

    public Uloha(String idUlohy, String nazovUlohy, String popisUlohy, int level) {
        this.idUlohy = idUlohy;
        this.nazovUlohy = nazovUlohy;
        this.popisUlohy = popisUlohy;
        this.level = level;
    }

    public String getIdUlohy() {
        return idUlohy;
    }

    public void setIdUlohy(String idUlohy) {
        this.idUlohy = idUlohy;
    }

    public String getNazovUlohy() {
        return nazovUlohy;
    }

    public void setNazovUlohy(String nazovUlohy) {
        this.nazovUlohy = nazovUlohy;
    }

    public String getPopisUlohy() {
        return popisUlohy;
    }

    public void setPopisUlohy(String popisUlohy) {
        this.popisUlohy = popisUlohy;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Set<PolozkaUlohy> getMnozinaPoloziekUlohy() {
        return mnozinaPoloziekUlohy;
    }

    public void setMnozinaPoloziekUlohy(Set<PolozkaUlohy> mnozinaPoloziekUlohy) {
        this.mnozinaPoloziekUlohy = mnozinaPoloziekUlohy;
    }
    
    
    
    
}
