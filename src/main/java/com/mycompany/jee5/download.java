/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jee5;

import java.io.*;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Main-PC
 */
//@WebServlet("/download")
public class download extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURL().toString();
        String []LINK = url.split("/src=");
        String fileLocation = "C:\\"+LINK[1].replace("/","\\").replace("%20"," "); //change the path according to you
        File file = new File(fileLocation);
        FileInputStream fis = new FileInputStream(file);
        ServletOutputStream sos = resp.getOutputStream();
        resp.setContentType("application/octet-stream");
        byte[] buffer = new byte[4096];

        while((fis.read(buffer, 0, 4096)) != -1){
            sos.write(buffer, 0, 4096);
        }
        fis.close();
    }

}
