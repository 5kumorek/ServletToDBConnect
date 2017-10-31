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
        name = "ClassServlet",
        urlPatterns = {"/class"}
)
public class ClassServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String exception;
            try {
                Table myService = new Table("Select id_rocznika as Rocznik from roczniki;");
                ArrayList<ArrayList<String>> myGenerations = myService.getTableRecords();
                int classCount = myGenerations.size();
                //here I will store my classes
                ArrayList<ArrayList<ArrayList<String>>> myClasses= new ArrayList<ArrayList<ArrayList<String>>>();

                for(int i=1;i<=classCount;i++)
                {
                    Table temp = new Table("Select profil, nazwa, id_klasy from klasy where id_rocznika="+i+";");
                    myClasses.add(temp.getTableRecords());
                }
                request.setAttribute("generations", myGenerations);
                request.setAttribute("classes", myClasses);
            } catch (SQLException e) {
                exception = e.getMessage();
                request.setAttribute("exception", exception);
            }
        }
        catch (NullPointerException e) {
            request.setAttribute("exception", "Query is empty");
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher("diary.jsp");
        dispatcher.forward(request, response);
    }
}
