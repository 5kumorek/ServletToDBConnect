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
/**
 \class TableServlet provide information Provide detailed information about table
 */
public class TableServlet extends HttpServlet {
    /**
     \brief doGet function obtain data by get method, processed it and send to jsp
     \param[in] request object, which passes argument to jsp
     \param[in] response object, which obtain argument from jsp
     \throws ServletException, IOException
     */
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
            System.out.println("elo, query jest "+query);
            e.printStackTrace();
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher("records.jsp");
        dispatcher.forward(request, response);
    }
}
