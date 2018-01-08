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
/**
 \class QueryServlet provide information Available on executing every query
 */
public class QueryServlet extends HttpServlet {
    /**
     \brief doGet function obtain data by get method, processed it and send to jsp
     \param[in] request object, which passes argument to jsp
     \param[in] response object, which obtain argument from jsp
     \throws ServletException, IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sqlQuery = request.getParameter("query");
        String myPage = request.getParameter("page");

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


        RequestDispatcher dispatcher = request.getRequestDispatcher(myPage);
        dispatcher.forward(request, response);
    }
}
