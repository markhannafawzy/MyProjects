/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author Mark
 */
@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {



 
// @Override
//    public void init(ServletConfig config) {
//        User user = new User();
//        user.setName("mark");
//        user.setPassword("1234");
//        
//        users.add(user);
//    }

 
 
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               
                User user = new User();
                user.setName(request.getParameter("uName"));
                user.setPassword(request.getParameter("password"));
                HttpSession session = request.getSession(true);
                session.setAttribute("user",user);
                response.sendRedirect("LoginCheck");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = new User ();
        user.setName(request.getParameter("uName1"));
        user.setPassword(request.getParameter("password1"));
        user.setEmail(request.getParameter("email"));
        LoginCheck.getUsers().add(user);
        response.sendRedirect("index.html#login");
    }



}
