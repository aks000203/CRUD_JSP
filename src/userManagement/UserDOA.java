package userManagement;

import com.sun.corba.se.pept.transport.ConnectionCache;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDOA {
    private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false&allowPublicKeyRetrieval=true";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_USERS_SQL= "INSERT INTO users (name, email, country) VALUES (?, ?, ?);";
    private static final String SELECT_USER_BY_ID="SELECT id, name, email, country from users where id=?;";
    private static final String DELETE_USERS_SQL="delete from users where id=?;";
    private static final String UPDATE_USERS_SQL="UPDATE users set name=?, email=?, country=? where id=?;";
    private static final String SELECT_ALL_USERS="select * from users;";

    protected Connection getConnection(){

        Connection conn=null;
        try {
           // Class.forName("net.sourceforge.jtds.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

        }
        catch (SQLException e){
            e.printStackTrace();
        }
//        catch (ClassNotFoundException e){
//            e.printStackTrace();
//        }

       return conn;
    }

    public void insertUser(User user) throws SQLException{
        System.out.println(INSERT_USERS_SQL);
        try(Connection conn=getConnection(); PreparedStatement ppstmt=conn.prepareStatement(INSERT_USERS_SQL)){
            ppstmt.setString(1, user.getName());
            ppstmt.setString(2, user.getEmail());
            ppstmt.setString(3, user.getCountry());
            System.out.println(ppstmt);
            ppstmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public User selectUser(int id){
        User user=null;
        try(Connection conn=getConnection(); PreparedStatement ppstmt=conn.prepareStatement(SELECT_USER_BY_ID)){
            ppstmt.setInt(1, id);
            System.out.println(ppstmt);
            ResultSet rs=ppstmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");

                user = new User(name, email, country);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    public List<User> selectAllUsers(){
        List<User> users=new ArrayList<>();
            try(Connection conn=getConnection();
            PreparedStatement ppstmt=conn.prepareStatement(SELECT_ALL_USERS)){

            System.out.println(ppstmt);
            ResultSet rs=ppstmt.executeQuery();
            while (rs.next()) {
                int id=rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");

                users.add(new User(id, name, email, country));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    public boolean deleteUser(int id){
        boolean isDeleted=false;
        try(Connection conn=getConnection();
        PreparedStatement ppstmt=conn.prepareStatement(DELETE_USERS_SQL)){
            ppstmt.setInt(1, id);
            System.out.println(ppstmt);
            isDeleted=ppstmt.executeUpdate()>0;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return isDeleted;
    }

    public boolean updateUser(User user){
        boolean isUpdated=false;
        try(Connection conn=getConnection();
        PreparedStatement ppstmt=conn.prepareStatement(UPDATE_USERS_SQL)){
            ppstmt.setString(1, user.getName());
            ppstmt.setString(2, user.getEmail());
            ppstmt.setString(3, user.getCountry());
            ppstmt.setInt(4, user.getId());
            System.out.println(ppstmt);
            isUpdated=ppstmt.executeUpdate()>0;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
            return isUpdated;
    }

//    public static void main(String[] args) {
//        UserDOA usDOA=new UserDOA();
//        List<User> al=usDOA.selectAllUsers();
//        for (User elem: al) {
//            System.out.println(elem);
//        }
//    }
}

































