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
import models.MessageModule;
import models.User;

/**
 *
 * @author Mark
 */
@WebServlet(urlPatterns = {"/chatServlet"})
public class chatServlet extends HttpServlet {

    public static ArrayList<MessageModule> messages = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MessageModule msg = new MessageModule();
         HttpSession session = request.getSession(false);
         User user = (User) session.getAttribute("user");
         
        msg.setName(user.getName());
        msg.setMessage(request.getParameter("message"));
        messages.add(msg);
        response.setContentType("application/json");
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(messages.get(messages.size() - 1)));
        response.getWriter().close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(messages));
        response.getWriter().close();
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
