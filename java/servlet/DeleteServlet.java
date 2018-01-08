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
/**
 \class DeleteServlet provide information Provide availability of deleting records
 */
public class DeleteServlet extends HttpServlet {

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
