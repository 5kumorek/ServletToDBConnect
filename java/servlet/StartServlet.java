package servlet;

import databaseHelper.Names;
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
        name = "StartServlet",
        urlPatterns = {"/start"}
)
public class StartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Names service = new Names();
            ArrayList<String> myNames = service.getNames();
            ArrayList<Integer> recordsCount = service.recordsCount();
            ArrayList<Integer> columnsCount = service.columnsCount();
            request.setAttribute("names", myNames);
            request.setAttribute("records", recordsCount);
            request.setAttribute("columns", columnsCount);
            RequestDispatcher dispatcher = request.getRequestDispatcher("names.jsp");
            dispatcher.forward(request, response);
        }catch(SQLException s)
        {
            s.printStackTrace();
        }catch(IOException s)
        {
            s.printStackTrace();
        }
    }

}
