/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jee5;

import java.io.*;
import java.io.PrintWriter;
import java.nio.file.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Main-PC
 */
public class FileSending extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>File Sending</title>");
            out.println("</head>");
            out.println("<body style='background-color:gray;'>");

            String appPath = "Users\\Public\\Documents\\servlet";
            String folderName = request.getParameter("folder");
            appPath +="\\"+folderName+"\\";
            Part part = request.getPart("file");
            String[] elem = part.getSubmittedFileName().split("\\\\");
            String orgFileName = elem[elem.length-1];
            part.write(appPath+orgFileName);
            try {
                File myObj = new File("C:\\Users\\Public\\Documents\\servlet\\pliki.txt");
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            try {
                Files.write(Paths.get("C:\\Users\\Public\\Documents\\servlet\\pliki.txt"), (appPath+orgFileName+"\n").getBytes(), StandardOpenOption.APPEND);
            }catch (IOException e) {
            }
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Public\\Documents\\servlet\\pliki.txt"));
            String st;
            while ((st = br.readLine()) != null){
                String[] str = st.split("\\\\");

                out.println("<a href='download\\src="+st+"'>"+str[str.length-2]+"/"+str[str.length-1]+"<br/>");
            }




            out.println("</body>");
            out.println("</html>");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
