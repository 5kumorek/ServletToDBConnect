package servlet;

import databaseHelper.DeleteRecord;
import databaseHelper.InsertRecord;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "DeleteServlet",
        urlPatterns = {"/delete"}
)
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tableName = request.getParameter("tableName");
        request.setAttribute("tableName", tableName);

        int recordToDelete = Integer.parseInt(request.getParameter("recordToDelete"));

        //now I send index of record to delete
        DeleteRecord service = new DeleteRecord(tableName);

        String exception;
        try {
            service.deleteRecord(recordToDelete);
        } catch (SQLException e) {
            exception = e.getMessage();
            request.setAttribute("exception", exception);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("table");
        dispatcher.forward(request, response);
    }

}
