package userManagement;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID=1L;
    private UserDOA userDOA;
    public void init(){
        userDOA=new UserDOA();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getServletPath();

        try {
            switch (action) {
                case  "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException{
        List<User> users=userDOA.selectAllUsers();
        request.setAttribute("userList", users);
        request.getRequestDispatcher("user-list.jsp").forward(request, response);
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDOA.selectUser(id);
        request.setAttribute("user", existingUser);
        request.getRequestDispatcher("user-form.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException{
        request.getRequestDispatcher("user-form.jsp").forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException{
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String country=request.getParameter("country");
        User user=new User(name, email, country);
        userDOA.insertUser(user);
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
        int id=Integer.parseInt(request.getParameter("id"));
        userDOA.deleteUser(id);
        response.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException{
        int id=Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String country=request.getParameter("country");
        User user=new User(id, name, email, country);
        userDOA.updateUser(user);
        response.sendRedirect("list");
    }
}
