
/**
 * @file SimpleFormInsert.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SimpleFormInsert")
public class SimpleFormInsert extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public SimpleFormInsert() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String firstName = request.getParameter("firstName");
	  String lastName = request.getParameter("lastName");
	  String homePhone = request.getParameter("homePhone");
	  String cellPhone = request.getParameter("cellPhone");
      String email = request.getParameter("email");
      String address = request.getParameter("address");

      Connection connection = null;
      String insertSql = " INSERT INTO myTable (id, FIRST_NAME, LAST_NAME, HOME_PHONE, CELL_PHONE, EMAIL, ADDRESS) values (default, ?, ?, ?, ?, ?, ?)";

      try {
         DBConnection.getDBConnection();
         connection = DBConnection.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, firstName);
         preparedStmt.setString(2, lastName);
         preparedStmt.setString(3, homePhone);
         preparedStmt.setString(4, cellPhone);
         preparedStmt.setString(5, email);
         preparedStmt.setString(6, address);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert Data to DB table";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>First Name</b>: " + firstName + "\n" + //
            "  <li><b>Last Name</b>: " + lastName + "\n" + //
            "  <li><b>Home Phone</b>: " + homePhone + "\n" + //
            "  <li><b>Cell Phone</b>: " + cellPhone + "\n" + //
            "  <li><b>Email</b>: " + email + "\n" + //
            "  <li><b>Address</b>: " + address + "\n" + //

            "</ul>\n");

      out.println("<a href=/webproject/simpleFormSearch.html>Search Contacts</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
