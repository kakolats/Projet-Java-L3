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
public class TypePrestation extends TypeService {
    
    private final String TYPE="Prestation";
    
    public TypePrestation() {
    }

    public TypePrestation(int id, String libelle) {
        super(id, libelle);
        this.type=TYPE;
    }

    public TypePrestation(String libelle) {
        super(libelle);
        this.type=TYPE;
    }

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

    
    
    @Override
    public String toString() {
        return  libelle ;
    }
}
