package mydomain.model;

import javax.jdo.annotations.PersistenceCapable;
import java.io.Serializable;
import java.util.List;

@PersistenceCapable
public class Hub implements Serializable {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    private String name;
    private List<Schedule> schedules;

}
