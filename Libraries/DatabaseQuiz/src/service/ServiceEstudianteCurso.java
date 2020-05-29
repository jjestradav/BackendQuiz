/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Curso;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonathan
 */
public class ServiceEstudianteCurso {
    
    private static final String INSERTAR_ESTUDIANTE_CURSO = "{call insertaCursoEstudiante(?,?)}";
    private static final String MODIFICAR_ESTUDIANTE_CURSO = "{call MODIFICAR_CARRERA(?,?,?)}";
    private static final String BUSCAR_ESTUDIANTE = "{call BUSCAR_CARRERA(?)}";
    private static final String CURSOS_X_ESTUDIANTE = "{call cursoPorEstudiante(?)}";
    private static final String ELIMINAR_ESTUDIANTE_CURSO = "{call eliminaCurso(?,?)}";
    
    
         private  Service service= Service.getInstance();
    private ServiceEstudianteCurso(){
        
    }
    private static ServiceEstudianteCurso instance=null;
    
    public static ServiceEstudianteCurso getInstance(){
        return instance==null ? instance= new ServiceEstudianteCurso(): instance;
    }
    
    public void insertarEstudianteCurso(String estudiante, String curso) throws Exception{
           CallableStatement call = null;
        try{
            this.service.conectar();
            call=this.service.getConnection().prepareCall(INSERTAR_ESTUDIANTE_CURSO);
            call.setString(1, estudiante);
            call.setString(2, curso);
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
    
      public void eliminarEstudianteCurso(String estudiante, String curso) throws Exception{
           CallableStatement call = null;
        try{
            this.service.conectar();
            call=this.service.getConnection().prepareCall(ELIMINAR_ESTUDIANTE_CURSO);
            call.setString(1, estudiante);
            call.setString(2, curso);
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
      
      public List<Curso> getCursoXEstudiante(String estudiante) throws Exception{
                 CallableStatement call = null;
                 ResultSet rs=null;
        try{
            this.service.conectar();
            call=this.service.getConnection().prepareCall(CURSOS_X_ESTUDIANTE);
            call.setString(1, estudiante);
            rs=call.executeQuery();
            List<Curso> result= new ArrayList<>();
            while(rs.next()){
                Curso cur=new Curso();
                cur.setId(rs.getString("curso"));
                cur.setDescripcion(rs.getString("descripcion"));
                
                
                result.add(cur);
            }
            return result;
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
}
