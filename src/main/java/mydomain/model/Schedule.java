package mydomain.model;

import mydomain.model.converter.LocalTimeConverter;

import javax.jdo.annotations.Convert;
import javax.jdo.annotations.EmbeddedOnly;
import javax.jdo.annotations.PersistenceCapable;
import java.io.Serializable;

@PersistenceCapable
@EmbeddedOnly
public class Schedule implements Serializable {

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getOpens() {
        return opens;
    }

    public void setOpens(String opens) {
        this.opens = opens;
    }

    public String getCloses() {
        return closes;
    }

    public void setCloses(String closes) {
        this.closes = closes;
    }

    private String day;
    @Convert(LocalTimeConverter.class)
    private String opens;
    @Convert(LocalTimeConverter.class)
    private String closes;

}

