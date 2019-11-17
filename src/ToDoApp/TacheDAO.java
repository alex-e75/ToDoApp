package ToDoApp;

import java.sql.*;

public class TacheDAO {
    private Connection conn = null;
    private Statement stmt = null;

    public TacheDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/devops", "username", "password");
            stmt = conn.createStatement();

            if (!conn.isClosed())
                System.out.println("Successfully connected to AWS Database");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public Boolean add(Tache t){
        Boolean result = false;
        int rowcount;

        try {
            String query = "INSERT INTO `taches` (`id`, `description`) VALUES (NULL, '"+ t.getLibelle()+"')";
            rowcount = stmt.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
            if (rowcount > 0) {
                result = true;
                ResultSet rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    t.setId(rs.getInt(1));
                }
            } else {
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

}
