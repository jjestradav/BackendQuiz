/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import entity.Estudiante;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Model;

/**
 *
 * @author jonathan
 */
@WebServlet(name = "Estudiantes", urlPatterns = {"/getEstudiantes","/insertarEstudiante","/actualizarEstudiante",
"/eliminarEstudiante"})
public class Estudiantes extends HttpServlet {

    private Gson gson=new Gson();
    private Model model= Model.getInstance();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    
    
    private void insertar(HttpServletRequest request, HttpServletResponse response){
        
        try{
            
        Estudiante estudiante=gson.fromJson(request.getReader(), Estudiante.class);
        model.insertarEstudiante(estudiante);
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(String.valueOf(true));
        out.flush();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
        private void update(HttpServletRequest request, HttpServletResponse response){
        
        try{
            
        Estudiante estudiante=gson.fromJson(request.getReader(), Estudiante.class);
       boolean result= model.updateEstudiante(estudiante);
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(String.valueOf(result));
        out.flush();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
       private void list(HttpServletRequest request, HttpServletResponse response){
       
           try{
               String result= gson.toJson(model.getAllEstudiantes());
               response.setContentType("application/json");
               PrintWriter out = response.getWriter();
               out.print(result);
               out.flush();
           }
           catch(Exception e){
               e.printStackTrace();
           }
       }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        list(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        insertar(request, response);
    }
    
        @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        update(request, response);
    }


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
