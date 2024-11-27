package LAB5;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Logs")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Url")
    private String url;

    @Column(name = "AccessTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accessTime;

    @Column(name = "Username")  
    private String username;

    // Constructor
    public Log(String url, String username, Date currentTime) {
        this.url = url;
        this.username = username;
    }

    // Phương thức này sẽ được gọi tự động trước khi đối tượng được lưu vào cơ sở dữ liệu
    @PrePersist
    public void prePersist() {
        this.accessTime = new Date(); // Cập nhật thời gian khi lưu đối tượng
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public String getFullname() {
		// TODO Auto-generated method stub
		return null;
	}
}

