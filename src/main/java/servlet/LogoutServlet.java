package servlet;

import entity.ChatUser;
import servlet.ChatServlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends ChatServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = (String) request.getSession().getAttribute("name");
        if (name != null) {
            ChatUser aUser = activeUsers.get(name);
            if (aUser.getSessionId().equals(request.getSession().getId())) {
                synchronized (activeUsers) {
                    activeUsers.remove(name);
                }
                request.getSession().setAttribute("name", null);
                response.addCookie(new Cookie("sessionId", null));
                response.sendRedirect(response.encodeRedirectURL("/chat"));
            } else {
                response.sendRedirect(response.encodeRedirectURL("/chat/view.html"));
            }
        } else {
            response.sendRedirect(response.encodeRedirectURL("/chat/view.html"));
        }
    }
}
