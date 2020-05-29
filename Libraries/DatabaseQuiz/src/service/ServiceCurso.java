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
public class ServiceCurso {
    
          private static final String BUSCAR_CURSOS= "{call todosLosCursos()}";   
         private Service service= Service.getInstance();
         private static ServiceCurso instance=null;
         private ServiceCurso(){
             
         }
         
         public static ServiceCurso getInstance() {
             return instance==null ? instance=new ServiceCurso(): instance;
         }
         
         public List<Curso> getAllCursos() throws Exception{
             
                   CallableStatement call = null;
                 ResultSet rs=null;
        try{
            this.service.conectar();
            call=this.service.getConnection().prepareCall(BUSCAR_CURSOS);
            rs=call.executeQuery();
            List<Curso> result= new ArrayList<>();
            while(rs.next()){
                Curso cur=new Curso();
                cur.setId(rs.getString("id"));
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
