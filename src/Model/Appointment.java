
package Model;

import Helper.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;


public class Appointment {
    private int id,doctorID,hastaID;
    private String doctorName,hastaName,appDate;
    
    
    Statement st = null;
    ResultSet rs = null;
    DBConnection conn = new DBConnection();
    PreparedStatement preparedStatement = null;

    public Appointment(int id, int doctorID, int hastaID, String doctorName, String hastaName, String appDate) {
        this.id = id;
        this.doctorID = doctorID;
        this.hastaID = hastaID;
        this.doctorName = doctorName;
        this.hastaName = hastaName;
        this.appDate = appDate;
    }

    public Appointment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public int getHastaID() {
        return hastaID;
    }

    public void setHastaID(int hastaID) {
        this.hastaID = hastaID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getHastaName() {
        return hastaName;
    }

    public void setHastaName(String hastaName) {
        this.hastaName = hastaName;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }
    
    
}
