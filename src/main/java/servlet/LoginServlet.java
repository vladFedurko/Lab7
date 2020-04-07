package servlet;

import entity.ChatUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

public class LoginServlet extends ChatServlet {
    private int sessionTimeout = 10*60;

    @Override
    public void init() throws ServletException {
        super.init();
        String value = getServletConfig().getInitParameter("SESSION_TIMEOUT");
        if(value != null) {
            sessionTimeout = Integer.parseInt(value);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = (String)request.getSession().getAttribute("name");
        String previousSessionId = null;
        if(name == null && request.getCookies() != null) {
            for(Cookie aCookie: request.getCookies()) {
                if(aCookie.getName().equals("sessionId")) {
                    previousSessionId = aCookie.getValue();
                    break;
                }
            }
            if(previousSessionId != null) {
                for(ChatUser aUser: activeUsers.values()) {
                    if(aUser.getSessionId().equals(previousSessionId)) {
                        name = aUser.getName();
                        aUser.setSessionId(request.getSession().getId());
                    }
                }
            }
        }
        boolean needsForward = true;
        if(name != null && !"".equals(name)) {
            String errorMessage = processLogonAttempt(name, request, response, false);
            request.getSession().setAttribute("error", errorMessage);
            if(errorMessage == null)
                needsForward = false;
        }
        if(needsForward) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("startPage.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String answering = request.getParameter("answering");
        String errorMessage;
        if(name == null || "".equals(name)) {
            errorMessage = "Имя пользователя не может быть пустым!";
        }
        else {
            errorMessage = processLogonAttempt(name, request, response, answering != null);
        }
        if(errorMessage != null) {
            request.getSession().setAttribute("name", null);
            request.getSession().setAttribute("error", errorMessage);
            response.sendRedirect(response.encodeRedirectURL("/chat"));
        }
    }

    String processLogonAttempt(String name, HttpServletRequest request, HttpServletResponse response, boolean answering) throws IOException {
        String sessionId = request.getSession().getId();
        ChatUser aUser = activeUsers.get(name);
        if(aUser == null) {
            aUser = new ChatUser(name, Calendar.getInstance().getTimeInMillis(), sessionId, answering);
            synchronized (activeUsers) {
                activeUsers.put(aUser.getName(), aUser);
            }
        }
        if (aUser.getSessionId().equals(sessionId) ||
                aUser.getLastInteractionTime() < (Calendar.getInstance().getTimeInMillis() - sessionTimeout * 1000)) {
            request.getSession().setAttribute("name", name);
            aUser.setLastInteractionTime(Calendar.getInstance().getTimeInMillis());
            Cookie sessionIdCookie = new Cookie("sessionId", sessionId);
            sessionIdCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(sessionIdCookie);
            response.sendRedirect(response.encodeRedirectURL("/chat/view.html"));
            return null;
        }
        else {
            return "Извините, но имя <strong>" + name + "</strong> уже кем-то занято. Пожалуйста выберите другое имя!";
        }
    }
}
