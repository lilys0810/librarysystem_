package pojo;

public class Reader {
    private int id;
    private String readername;
    private String readtype;
    private String sex;
    private int max_num;
    private int days_num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReadername() {
        return readername;
    }

    public void setReadername(String readername) {
        this.readername = readername;
    }

    public String getReadtype() {
        return readtype;
    }

    public void setReadtype(String readtype) {
        this.readtype = readtype;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getMax_num() {
        return max_num;
    }

    public void setMax_num(int max_num) {
        this.max_num = max_num;
    }

    public int getDays_num() {
        return days_num;
    }

    public void setDays_num(int days_num) {
        this.days_num = days_num;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", readername='" + readername + '\'' +
                ", readtype='" + readtype + '\'' +
                ", sex='" + sex + '\'' +
                ", max_num=" + max_num +
                ", days_num=" + days_num +
                '}';
    }
}
