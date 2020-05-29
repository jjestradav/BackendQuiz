/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author jonathan
 */
public class Curso {
    
    private String id;
    private String descripcion;
    private int creditos;

        public Curso() {
    }
    
        
    public Curso(String id, String descripcion, int creditos) {
        this.id = id;
        this.descripcion = descripcion;
        this.creditos = creditos;
    }
    
    public Curso(String id){
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    @Override
    public String toString() {
        return "Curso{" + "id=" + id + ", descripcion=" + descripcion + ", creditos=" + creditos + '}';
    }

    

    
}
