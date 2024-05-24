package entity;

import java.sql.Date;

public class User {
	private int user_id;
	private String password;
	private String username;
	private String email;
	private Date created_at;
	private Date last_login;
	private String sdt;

	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getLast_login() {
		return last_login;
	}
	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User() {
		super();
	}
	public User(int user_id, String password, String username, String email, Date created_at, Date last_login,
			String sdt) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.username = username;
		this.email = email;
		this.created_at = created_at;
		this.last_login = last_login;
		this.sdt = sdt;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", password=" + password + ", username=" + username + ", email=" + email
				+ ", created_at=" + created_at + ", last_login=" + last_login + ", sdt=" + sdt + "]";
	}
	
	
}
