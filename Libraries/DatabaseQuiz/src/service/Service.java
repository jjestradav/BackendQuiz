/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author jonathan
 */
public class Service {

    private Connection conexion= null;
    
    private static Service instance=null;
    
    
    private Service() {
        
    }
    
    public static Service getInstance(){
        
        if(instance==null)
            instance=new Service();
        
        return instance;
    }
    
    protected void conectar() throws SQLException,ClassNotFoundException 
    {
          Class.forName("com.mysql.jdbc.Driver"); 
       // try {
             conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Quiz", "root", "root");
            //conexion = getJdbcMydbsource();
       /* } catch (NamingException ex) {
            ex.printStackTrace();
        }*/
        
    }
    
    protected void desconectar() throws SQLException{
        if(!conexion.isClosed())
        {
            conexion.close();
        }
    }

    private Connection getJdbcMydbsource() throws NamingException {
        Context c = new InitialContext();
        try {
            return ((DataSource) c.lookup("jdbc/Mydbsource")).getConnection();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public Connection getConnection(){
        return this.conexion;
    }
}