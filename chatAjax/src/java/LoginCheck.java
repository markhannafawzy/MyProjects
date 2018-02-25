/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(urlPatterns = {"/LoginCheck"})
public class LoginCheck extends HttpServlet {

    private static ArrayList<User> users = new ArrayList<User>();
    private static ArrayList<User> onlineUsers = new ArrayList<>();
    public String name;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session == null)
            response.sendRedirect("index.html");
        else
        {
            User user = (User) session.getAttribute("user");
            String urlString = new String();
            if (users.isEmpty())
            {
                urlString = "index.html#register";
            }
            else
            {
                for (User user1 : users) {
                    if(user.getName().equals(user1.getName())&&user.getPassword().equals(user1.getPassword()))
                    {
                        onlineUsers.add(user);
                        urlString = "chatWindow.html";
                    }
                    else
                    {
                        urlString = "index.html#register";
                    }
                }                
            }

            response.sendRedirect(urlString);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(onlineUsers));
        response.getWriter().close();          
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        LoginCheck.users = users;
    }

    public static ArrayList<User> getOnlineUsers() {
        return onlineUsers;
    }

    public static void setOnlineUsers(ArrayList<User> onlineUsers) {
        LoginCheck.onlineUsers = onlineUsers;
    }

    
}
