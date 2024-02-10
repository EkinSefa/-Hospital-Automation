package Model;

import Helper.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;

public class Clinic {

    private int id;
    private String name;

    DBConnection conn = new DBConnection();
    Statement st = null;
    ResultSet rs = null;
    Connection con = (Connection) conn.connDB();
    PreparedStatement preparedStatement = null;

    public Clinic() {
    }

    public Clinic(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ArrayList<Clinic> getList() throws SQLException {

        ArrayList<Clinic> list = new ArrayList<>();
        Clinic obj;
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM clinic");
            while (rs.next()) {
                obj = new Clinic(rs.getInt("id"), rs.getString("name"));
                list.add(obj);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
         
        }
        return list;
    }
    public ArrayList<User> getClinicDoktorList(int clinic_id) throws SQLException {

        ArrayList<User> list = new ArrayList<>();
        User obj;

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT u.id,u.tcno,u.password,u.name,u.type FROM worker w LEFT JOIN user u ON w.user_id = u.id WHERE clinic_id="+clinic_id);
            while (rs.next()) {
                obj = new User(rs.getInt("u.id"), rs.getString("u.tcno"), rs.getString("u.name"), rs.getString("u.password"), rs.getString("u.type"));
                list.add(obj);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return list;
    }

    public boolean addClinic(String name) throws SQLException {
        String query = "INSERT INTO clinic" + "(name) VALUES" + "(?)";
        boolean key = false;
        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            key = true;

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
           
        }
        if (key) {
            return true;
        } else {
            return false;
        }

    }
    public boolean deleteClinic(int id) throws SQLException {
        boolean key = false;
        String query = "DELETE FROM clinic WHERE id = ?";
        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            key = true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            
        }
        if (key) {
            return true;
        } else {
            return false;
        }

    }
    public boolean updateClinic(String name, int id) throws SQLException {
        boolean key = false;
        String query = "UPDATE clinic SET name = ? WHERE id = ?";
        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            key = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            
        }
        if (key) {
            return true;
        } else {
            return false;
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
