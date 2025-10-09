package postcard.model;

public class OldCard {

    
    private String id;
    private boolean sent;
    private String thema;       
    private String type;        
    private String country;
    private int year;
    private String author;      // Може бути порожнім
    private String valuable;    

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public String getThema() {
        return thema;
    }

    public void setThema(String thema) {
        this.thema = thema;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getValuable() {
        return valuable;
    }

    public void setValuable(String valuable) {
        this.valuable = valuable;
    }

    
    @Override
    public String toString() {
        return "OldCard [" +
                "ID=" + id +
                ", Sent=" + sent +
                ", Thema='" + thema + '\'' +
                ", Type='" + type + '\'' +
                ", Country='" + country + '\'' +
                ", Year=" + year +
                ", Author='" + (author != null ? author : "N/A") + '\'' +
                ", Valuable='" + valuable + '\'' +
                ']';
    }
}