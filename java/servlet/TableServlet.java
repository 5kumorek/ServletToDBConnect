package servlet;

import databaseHelper.Table;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "TableServlet",
        urlPatterns = {"/table"}
)
public class TableServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tableName = request.getParameter("tableName");
        request.setAttribute("tableName", tableName);

        String query = "SELECT * FROM " + tableName;

        try {
            Table myService = new Table(query);
            ArrayList<ArrayList<String>> myTable = myService.getTableRecords();
            ArrayList<String> myColumns = myService.getTableColumns();
            ArrayList<String> myTypes = myService.getColumnTypes(tableName);
            request.setAttribute("records", myTable);
            request.setAttribute("columns", myColumns);
            request.setAttribute("types", myTypes);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher("records.jsp");
        dispatcher.forward(request, response);
    }
}
