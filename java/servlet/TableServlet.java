package servlet;

import databaseHelper.Table;

import java.io.IOException;
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
        Table service = new Table(tableName);

        ArrayList<ArrayList<String>> myTable=service.getTableRecords();
        ArrayList<String> myColumns=service.getTableColumns();
        ArrayList<String> myTypes=service.getColumnTypes();
        request.setAttribute("records", myTable);
        request.setAttribute("columns", myColumns);
        request.setAttribute("types", myTypes);


        RequestDispatcher dispatcher = request.getRequestDispatcher("records.jsp");
        dispatcher.forward(request, response);
    }
}