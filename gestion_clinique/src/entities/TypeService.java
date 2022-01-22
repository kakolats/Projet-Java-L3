/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author HP
 */
public class TypeService {
    protected int id;
    protected String libelle;
    protected String type;
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    

    public TypeService() {
    }

    public TypeService(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public TypeService(String libelle) {
        this.libelle = libelle;
    }
    
    public TypeService(int id){
        this.id=id;
    }
    
    @Override
    public String toString(){
        return libelle;
    }
    
}
