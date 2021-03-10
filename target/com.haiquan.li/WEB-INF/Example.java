
public class Example extends HttpServlet {    
    @Override
    protected void doGet(
      HttpServletRequest request, 
      HttpServletResponse response) throws ServletException, IOException {
 
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<p>Hello World!</p>");
    }
    
}

