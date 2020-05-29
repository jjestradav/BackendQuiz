/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonathan
 */
public class Estudiante {
    private String id;
    private String nombre;
    private String apellidos;
    private int edad;
    private List<Curso> cursos= new ArrayList<>();
    

    
       public Estudiante() {
    }
    
    
    public Estudiante(String id){
        this.id=id;
    }
    public Estudiante(String id, String nombre,String apellidos ,int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.apellidos=apellidos;
    }

       public Estudiante(String id, String nombre,String apellidos ,int edad, List<Curso> cursos) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.apellidos=apellidos;
        this.cursos=cursos;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

  
    
}
