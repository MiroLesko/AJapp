/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package anjapp;

/**
 *
 * @author MIRO
 */
public class User {
    
    private String username;
    private String meno;
    private String email;
    private String heslo;
    private boolean isAdmin;
    private boolean isActivated;

    public User(String username, String meno, String email, String heslo, boolean isAdmin, boolean isActivated) {
        this.username = username;
        this.meno = meno;
        this.email = email;
        this.heslo = heslo;
        this.isAdmin = isAdmin;
        this.isActivated = isActivated;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isIsActivated() {
        return isActivated;
    }

    public void setIsActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }
         
    
    
    
}
