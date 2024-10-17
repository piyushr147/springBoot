package org.example;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response){
        System.out.println("hello servlet service");
    }
}
