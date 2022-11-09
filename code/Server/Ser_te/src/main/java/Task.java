import java.util.Date;

public class Task {
    public String content;
    public Date time;
    public int type;
    public int id;
    public String courses;
    public String teacher;

    public String getContent() {
        return content;
    }

    public Date getTime() {
        return time;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getCourses() {
        return courses;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
