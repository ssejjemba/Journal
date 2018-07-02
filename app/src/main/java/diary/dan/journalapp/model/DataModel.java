package diary.dan.journalapp.model;

public class DataModel {

    public DataModel(int id, String title, String content, int date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
    }
    public DataModel(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  int id;
    public String title;
    public String content;
    public int date;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

}
