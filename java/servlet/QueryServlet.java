package servlet;

import databaseHelper.Table;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "QueryServlet",
        urlPatterns = {"/query"}
)
public class QueryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sqlQuery = request.getParameter("query");

        try {
            String exception;
            try {
                Table myService = new Table(sqlQuery);
                ArrayList<ArrayList<String>> myRecords = myService.getTableRecords();
                request.setAttribute("records", myRecords);
                ArrayList<String> myColumns = myService.getTableColumns();
                request.setAttribute("columns", myColumns);
            } catch (SQLException e) {
                exception = e.getMessage();
                request.setAttribute("exception", exception);
            }
        }
         catch (NullPointerException e) {
            request.setAttribute("exception", "Query is empty");
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher("query.jsp");
        dispatcher.forward(request, response);
    }
}
