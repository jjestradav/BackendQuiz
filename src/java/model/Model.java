/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Curso;
import entity.Estudiante;
import java.util.ArrayList;
import java.util.List;
import service.ServiceCurso;
import service.ServiceEstudiante;
import service.ServiceEstudianteCurso;

/**
 *
 * @author jonathan
 */
public class Model {
    
    private static Model instance= null;
    private ServiceEstudiante repoEstudiantes=  ServiceEstudiante.getInstance();
    private ServiceCurso repoCursos= ServiceCurso.getInstance();
    private ServiceEstudianteCurso repoEstudiantesCursos=ServiceEstudianteCurso.getInstance();
    
    
    private Model(){
        
    }
    public static Model getInstance(){
        return instance==null ? instance=new Model() : instance;
    }
    
    public void insertarEstudiante(Estudiante us){
        try{
            
        this.repoEstudiantes.insertarEstudiante(us);
        for(Curso curso: us.getCursos()){
           this.repoEstudiantesCursos.insertarEstudianteCurso(us.getId(), curso.getId());
        }
        
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public List<Estudiante> getAllEstudiantes(){
        try{
            return this.repoEstudiantes.listarEstudiante();
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
        return new ArrayList<>();
    }
    
    public boolean updateEstudiante(Estudiante es){
        try{
            this.repoEstudiantes.modificarEstudiante(es);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    } 
    
    public List<Curso> getCursosXEstudiante(String id){
        try{
            return this.repoEstudiantesCursos.getCursoXEstudiante(id);
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
           return new ArrayList<>(); 
    }
    
    public List<Curso> getAllCursos(){
        try{
            return this.repoCursos.getAllCursos();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    
    public boolean eliminarEstudiante(String id, String curso){
        try{
            this.repoEstudiantesCursos.eliminarEstudianteCurso(id, curso);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
   
}
