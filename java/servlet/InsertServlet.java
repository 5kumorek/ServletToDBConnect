package servlet;

import databaseHelper.InsertRecord;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "InsertServlet",
        urlPatterns = {"/insert"}
)
/**
 \class InsertServlet provide information Available adding of records to db
 */
public class InsertServlet extends HttpServlet {

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

        int countOfVariables = Integer.parseInt(request.getParameter("how_many"));
        String[] arrayOfValues = new String[countOfVariables];
        for(int i=0;i<countOfVariables;i++) {
            arrayOfValues[i]=request.getParameter(""+i);
        }

        //now I send record to preperty method
        InsertRecord service = new InsertRecord(tableName);
        boolean isInsert = true;
        String exception;
        try {
            isInsert = service.insertRecord(arrayOfValues);
            exception = service.getException();
        } catch (SQLException e) {
            isInsert = false;
            exception = e.getMessage();
        }

        if(!isInsert)
        {
            request.setAttribute("exception", exception);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("table");
        dispatcher.forward(request, response);
    }

}
