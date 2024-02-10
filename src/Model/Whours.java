package Model;

import Helper.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;

public class Whours {

    private int id, doctor_id;
    private String doctor_name, wdate, status;

    DBConnection conn = new DBConnection();
    Statement st = null;
    ResultSet rs = null;
    Connection con = (Connection) conn.connDB();
    PreparedStatement preparedStatement = null;

    public Whours() {
    }

    public Whours(int id, int doctor_id, String doctor_name, String wdate, String status) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.doctor_name = doctor_name;
        this.wdate = wdate;
        this.status = status;
    }

    public boolean updateHours(int doctor_id, String wdate) throws SQLException {

        String query = "UPDATE whours SET status='p' WHERE doctor_id=" + doctor_id + " AND wdate='" + wdate + "'";
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
            rs = st.executeQuery("SELECT * FROM whours WHERE status='a' AND doctor_id=" + doctor_id);
            while (rs.next()) {
                obj = new Whours(rs.getInt("id"), rs.getInt("doctor_id"), rs.getString("doctor_name"), rs.getString("wdate"), rs.getString("status"));
                list.add(obj);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getWdate() {
        return wdate;
    }

    public void setWdate(String wdate) {
        this.wdate = wdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
