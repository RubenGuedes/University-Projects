package org.gradle.demo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Configuração
 * 
 * name = atribui um nome ao servelt
 * urlPattern = é o endereço
 */
@WebServlet(name = "HelloServlet", urlPatterns = {"/hello"}, loadOnStartup = 1) 
public class HelloServlet extends HttpServlet 
{

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
        {
            response.getWriter().print("Hello, World!");

            // A linha abaixo faz com que as tags HTML sejam percebidas como tal
            response.setContentType("text/html;charset=UTF-8");

            String name = request.getParameter("name");
            if (name == null)
            {
                name = "World";
            }
            response.getWriter().println("<br/>Nome: "+ name);
        }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
        {
            String name = request.getParameter("name");
            
            if (name == null) 
            {    
                name = "World";
            }
            
            request.setAttribute("user", name); // comunicar com o modulo seguinte
            request.getRequestDispatcher("response.jsp").forward(request, response); 
        }
}
