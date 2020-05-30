/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Curso;
import entity.Estudiante;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jonathan
 */
public class ServiceEstudiante {
    
     private Service service= Service.getInstance();
         private static final String INSERTAR_ESTUDIANTE = "{call insertaEstudiante(?,?,?,?)}";
    private static final String MODIFICAR_ESTUDIANTE = "{call actualizaEstudiante(?,?,?,?)}";
    private static final String BUSCAR_ESTUDIANTE = "{call getEstudiante(?)}";
    private static final String LISTAR_ESTUDIANTE = "{call todosLosEstudiantes()}";
    private static final String ELIMINAR_ESTUDIANTE = "{call ELIMINAR_CARRERA(?)}";
    
    
    
    private static ServiceEstudiante instance=null;
    
    private ServiceEstudiante(){
        
    }
    public static ServiceEstudiante getInstance(){
        return instance == null ? instance=new ServiceEstudiante() : instance;
    }
    
    public void insertarEstudiante(Estudiante es) throws Exception{
           CallableStatement call = null;
        try{
            this.service.conectar();
            call=this.service.getConnection().prepareCall(INSERTAR_ESTUDIANTE);
            call.setString(1, es.getId());
            call.setString(2, es.getNombre());
            call.setString(3, es.getApellidos());
            call.setInt(4, es.getEdad());
            call.execute();
            
        }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        finally{
            if(call != null)
                   call.close();
            
            this.service.desconectar();
        }
    }
    
    
     public List<Estudiante> listarEstudiante() throws Exception{
           CallableStatement call = null;
           ResultSet rs=null;
        try{
            this.service.conectar();
            call=this.service.getConnection().prepareCall(LISTAR_ESTUDIANTE);
            rs=call.executeQuery();
            Map<String,Estudiante> map= new HashMap<>();
          
            while(rs.next()){
                String id=rs.getString("id");
                Estudiante es=map.get(id);
                
                if(es==null){
                    es=new Estudiante(id,rs.getString("nombre"),rs.getString("apellido"),rs.getInt("edad"));
                    Curso cur= new Curso(rs.getString("cursoId"),rs.getString("descripcion"),rs.getInt("creditos"));
                    
                    es.getCursos().add(cur);
                    map.put(id, es);
                }
                else{
                  
                     Curso cur= new Curso(rs.getString("cursoId"),rs.getString("descripcion"),rs.getInt("creditos"));
                    es.getCursos().add(cur);
                }
                
            }
           return new ArrayList<Estudiante>(map.values());
        }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        finally{
            if(call != null)
                   call.close();
            
            if(rs != null)
                rs.close();
            
            this.service.desconectar();
        }
    }
     
     
     public Estudiante buscarEstudiante(Estudiante est) throws Exception{
           CallableStatement call = null;
           ResultSet rs=null;
        try{
            this.service.conectar();
            call=this.service.getConnection().prepareCall(BUSCAR_ESTUDIANTE);
            call.setString(1, est.getId());
            rs=call.executeQuery();
             Map<String,Estudiante> map= new HashMap<>();
          
            while(rs.next()){
                String id=rs.getString("id");
                Estudiante es=map.get(id);
                
                if(es==null){
                    es=new Estudiante(id,rs.getString("nombre"),rs.getString("apellido"),rs.getInt("edad"));
                    Curso cur= new Curso(rs.getString("cursoId"),rs.getString("descripcion"),rs.getInt("creditos"));
                    
                    es.getCursos().add(cur);
                    map.put(id, es);
                }
                else{
                  
                     Curso cur= new Curso(rs.getString("cursoId"),rs.getString("descripcion"),rs.getInt("creditos"));
                    es.getCursos().add(cur);
                }
                
            }
           return map.get(est.getId());
            
            
        }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        finally{
            if(call != null)
                   call.close();
            
            if(rs != null)
                rs.close();
            
            this.service.desconectar();
        }
     }
     
     
          public void modificarEstudiante(Estudiante es) throws Exception{
           CallableStatement call = null;
        try{
            this.service.conectar();
            call=this.service.getConnection().prepareCall(MODIFICAR_ESTUDIANTE);
            call.setString(1, es.getId());
            call.setString(2, es.getNombre());
            call.setString(3, es.getApellidos());
            call.setString(4, es.getNombre());
            call.execute();
            
            
            
        }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        finally{
            if(call != null)
                   call.close();
            
            this.service.desconectar();
        }
     }
}
