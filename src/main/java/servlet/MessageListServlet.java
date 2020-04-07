package servlet;

import entity.ChatMessage;
import entity.ChatUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MessageListServlet extends ChatServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf8");
        PrintWriter pw = response.getWriter();
        pw.println("<html><head><meta http-equiv='Content-Type' " +
                "content='text/html; charset=utf-8'/><meta http-equiv='refresh' content='3'></head>");
        pw.println("<body>");
        for (int i = messages.size() - 1; i >= 0; --i) {
            ChatMessage aMessage = messages.get(i);
            String name = (String)request.getSession().getAttribute("name");
            if(name.equals(aMessage.getRecipient())){
                pw.print("<div><strong style=\"color:red\">");
            } else
            {
                pw.print("<div><strong>");
            }
            pw.println(aMessage.getAuthor().getName() + " -> " + aMessage.getRecipient() +
                    "</strong>: " + aMessage.getMessage() + "</div>");
        }
        pw.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String m = request.getParameter("auto");
        ChatUser user = activeUsers.get((String) request.getSession().getAttribute("name"));
        user.setAnswering(m.equals("true"));
    }
}

