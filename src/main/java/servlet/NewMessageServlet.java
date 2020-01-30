package servlet;

import entity.ChatMessage;
import entity.ChatUser;
import servlet.ChatServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

public class NewMessageServlet extends ChatServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        response.sendError(404);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String message = request.getParameter("message");
        if (message != null && !"".equals(message)) {
            ChatUser author = activeUsers.get((String) request.getSession().getAttribute("name"));
            synchronized (messages) {
                messages.add(new ChatMessage(message, author,
                        Calendar.getInstance().getTimeInMillis()));
            }
        }
        response.sendRedirect("/chat/messages.html");
    }
}
