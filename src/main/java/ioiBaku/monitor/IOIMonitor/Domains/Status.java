package ioiBaku.monitor.IOIMonitor.Domains;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="STATUS")
public class Status implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "COUNTRYCODE")
    private String countryCode;
@Column(name = "COUNTRY")
    private String country;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "MESSAGE")
    private String message;
    @Column(name = "EXTRALAN1")
    private String extra1;
    @Column(name = "EXTRALAN2")
    private String extra2;

    @Column(name = "Date")
    private String date;


    public Status(int id, String countryCode, String country, String status, String message, String extra1, String extra2, String date) {
        this.id = id;
        this.countryCode = countryCode;
        this.country = country;
        this.status = status;
        this.message = message;
        this.extra1 = extra1;
        this.extra2 = extra2;
        this.date = date;
    }

    public Status(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExtra1() {
        return extra1;
    }

    public void setExtra1(String extra1) {
        this.extra1 = extra1;
    }

    public String getExtra2() {
        return extra2;
    }

    public void setExtra2(String extra2) {
        this.extra2 = extra2;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", countryCode='" + countryCode + '\'' +
                ", country='" + country + '\'' +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", extra1='" + extra1 + '\'' +
                ", extra2='" + extra2 + '\'' +
                ", date=" + date +
                '}';
    }
}
