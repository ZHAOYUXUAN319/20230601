package com.example.demo.obj;

public class UserDto {
  private Long id;
  private String userName;
  private String password;
  private String status;
  
  public UserDto(Long id, String userName, String password, String status) {
    this.id = id;
    this.userName = userName;
    this.password = password;
    this.status = status;
  }
  

  public UserDto() {
	// TODO Auto-generated constructor stub
}


public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getUserName() {
    return userName;
  }
  
  public void setUserName(String userName) {
    this.userName = userName;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getStatus() {
    return status;
  }
  
  public void setStatus(String status) {
    this.status = status;
  }
}
