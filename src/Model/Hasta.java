package Model;

import java.sql.*;
import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;

public class Hasta extends User {

    Statement st = null;
    ResultSet rs = null;
    Connection con = (Connection) conn.connDB();
    PreparedStatement preparedStatement = null;

    public Hasta(int id, String tcno, String name, String password, String type) {
        super(id, tcno, name, password, type);
    }

    public Hasta() {
    }

    public boolean addHasta(String tcno, String password, String name) throws SQLException {
        String query = "INSERT INTO user" + "(tcno,password,name,type) VALUES" + "(?,?,?,?)";
        boolean key = false;
        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, tcno);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, "Hasta");
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

    public boolean addAppointment(int doctor_id, int hasta_id, String doctor_name, String hasta_name, String app_date) throws SQLException {
        String query = "INSERT INTO appointment" + "(doctor_id,doctor_name,hasta_id,hasta_name,app_date) VALUES" + "(?,?,?,?,?)";
        boolean key = false;
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, doctor_id);
            preparedStatement.setString(2, doctor_name);
            preparedStatement.setInt(3, hasta_id);
            preparedStatement.setString(4, hasta_name);
            preparedStatement.setString(5, app_date);
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

    public boolean updateWhoursStatus(int doctor_id, String app_date) throws SQLException {
        String query = "UPDATE whours SET status= ? WHERE doctor_id= ? AND wdate = ?";
        boolean key = false;
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, "p");
            preparedStatement.setInt(2, doctor_id);
            preparedStatement.setString(3, app_date);
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
}
