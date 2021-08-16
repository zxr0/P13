package sg.edu.rp.c346.id20000892.p13;

import java.io.Serializable;

public class sch implements Serializable {
    private int id;
    private String name;
    private String time;
    private String date;

    public sch(int id, String name, String time, String date) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() { return name  + "  |  " + date + "  |  " + time;  }

}
