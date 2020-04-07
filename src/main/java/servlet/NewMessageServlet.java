package servlet;

import entity.ChatMessage;
import entity.ChatUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

public class NewMessageServlet extends ChatServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        response.sendError(404);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String message = request.getParameter("message");
        String recipient = request.getParameter("recipient");
        ChatUser author = activeUsers.get((String) request.getSession().getAttribute("name"));
        if (message != null && !"".equals(message)) {
            synchronized (messages) {
                messages.add(new ChatMessage(author, Calendar.getInstance().getTimeInMillis(), message, recipient));
            }
        }
        ChatUser user = activeUsers.get(recipient);
        if(user != null && user.isAnswering()) {
            synchronized (messages) {
                messages.add(new ChatMessage(user, Calendar.getInstance().getTimeInMillis(),
                        "Спасибо большое, я обязательно подумаю об этом!", author.getName()));
            }
        }
        response.sendRedirect("/chat/messages.html");
    }
}
