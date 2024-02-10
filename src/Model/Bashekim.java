package Model;

import java.util.ArrayList;
import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;
import java.sql.*;

public class Bashekim extends User {

    Statement st = null;
    ResultSet rs = null;
    Connection con = (Connection) conn.connDB();
    PreparedStatement preparedStatement = null;

    public Bashekim(int id, String tcno, String name, String password, String type) {
        super(id, tcno, name, password, type);
    }

    public Bashekim() {
    }

    public ArrayList<User> getDoctorList() throws SQLException {

        ArrayList<User> list = new ArrayList<>();
        User obj;

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM user WHERE type = 'Doktor'");
            while (rs.next()) {
                obj = new User(rs.getInt("id"), rs.getString("tcno"), rs.getString("name"), rs.getString("password"), rs.getString("type"));
                list.add(obj);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

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

    public boolean addDoctor(String tcno, String password, String name) throws SQLException {
        String query = "INSERT INTO user" + "(tcno,password,name,type) VALUES" + "(?,?,?,?)";
        boolean key = false;
        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, tcno);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, "Doktor");
            preparedStatement.executeUpdate();
            key = true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
        }
        if (key) {
            return true;
        } else {
            return false;
        }

    }

    public boolean deleteDoktorModel(int id) throws SQLException {
        boolean key = false;
        String query = "DELETE FROM user WHERE id = ?";
        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            key = true;
        } catch (Exception e) {
            e.printStackTrace();
        } 

        if (key) {
            return true;
        } else {
            return false;
        }

    }

    public boolean updateDoctor(String name, String tcno, String password, int ıd) throws SQLException {
        boolean key = false;
        String query = "UPDATE user SET name = ?, tcno = ?, password = ? WHERE id = ?";
        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, tcno);
            preparedStatement.setString(3, password);
            preparedStatement.setInt(4, ıd);
            preparedStatement.executeUpdate();
            key = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        if (key) {
            return true;
        } else {
            return false;
        }

    }
    public boolean addWorker(int user_id, int clinic_id) throws SQLException {
        String query = "INSERT INTO worker" + "(clinic_id,user_id) VALUES" + "(?,?)";
        boolean key = false;
        int count = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM worker WHERE clinic_id="+clinic_id+" AND user_id="+user_id);
            while(rs.next()){
                count++;
            }
            if(count == 0){
                preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, clinic_id);
            preparedStatement.setInt(2, user_id);
            preparedStatement.executeUpdate();
            key = true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
        if (key) {
            return true;
        } else {
            return false;
        }

    }
}
