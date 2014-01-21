package xiaobai.mybatis.model;

import java.util.HashSet;
import java.util.Set;

public class Role {
    private String roleid;

    private String userid;

    private User user;
    
    private Set<Authority> authoritys = new HashSet<Authority>();
    
    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Authority> getAuthoritys() {
		return authoritys;
	}

	public void setAuthoritys(Set<Authority> authoritys) {
		this.authoritys = authoritys;
	}
	
    
}