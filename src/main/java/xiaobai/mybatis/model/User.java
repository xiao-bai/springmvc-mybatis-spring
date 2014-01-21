package xiaobai.mybatis.model;

public class User {
    private String username;

    private String password;

    private String nickname;

    private String type;

    
    public User() {
		super();
	}

	public User(String username, String password, String nickname, String type) {
		super();
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.type = type;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}