package VendingMachine.model;

import java.util.Date;

public class Record {
    private Date date;
    private String description;

    public Record(Date date, String description) {
        this.date = date;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }
}
