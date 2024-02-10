package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;

public class Doctor extends User {

    Statement st = null;
    ResultSet rs = null;
    Connection con = (Connection) conn.connDB();
    PreparedStatement preparedStatement = null;

    public Doctor(int id, String tcno, String name, String password, String type) {
        super(id, tcno, name, password, type);
    }

    public Doctor() {
        super();
    }
    public boolean deleteWhoursModel(int id) throws SQLException {
        boolean key = false;
        String query = "DELETE FROM whours WHERE id = ?";
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

    public boolean addHours(int doctor_id, String doctor_name, String wdate) throws SQLException {
        
        String query = "INSERT INTO whours" + "(doctor_id,doctor_name,wdate) VALUES" + "(?,?,?)";
        boolean key = false;
        int count = 0;
        try {

            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM whours WHERE status='a' AND doctor_id=" + doctor_id + " AND wdate='" + wdate + "'");
            while (rs.next()) {
                count++;
               
            }
            if (count == 0) {
                
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, doctor_id);
                preparedStatement.setString(2, doctor_name);
                preparedStatement.setString(3, wdate);
                preparedStatement.executeUpdate();
                 key = true;
            }
           
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
    public ArrayList<Whours> getWhoursList(int doctor_id) throws SQLException {

        ArrayList<Whours> list = new ArrayList<>();
        Whours obj;

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM whours WHERE status='a' AND doctor_id="+doctor_id);
            while (rs.next()) {
                obj = new Whours(rs.getInt("id"), rs.getInt("doctor_id"), rs.getString("doctor_name"), rs.getString("wdate"), rs.getString("status"));
                list.add(obj);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return list;
    }

}
