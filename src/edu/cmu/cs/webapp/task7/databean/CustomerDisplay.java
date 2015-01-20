package edu.cmu.cs.webapp.task7.databean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.genericdao.PrimaryKey;
@PrimaryKey("customerDisplayId")

public class CustomerDisplay {
	private int customerDisplayId;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String addLine1;
	private String addLine2;
	private String city;
	private String state;
	private String zip;
	private String cash;
	
	
	public CustomerDisplay(CustomerBean c, String cash ){
		this.userName=c.getUserName();
		this.password=c.getPassword();
		this.firstName=c.getFirstName();
		this.lastName=c.getLastName();
		this.addLine1=c.getAddress1();
		this.addLine2=c.getAddress2();
		this.city=c.getCity();
		this.state=c.getState();
		this.zip=c.getZipcode();
		this.cash=cash;
		
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddLine1() {
		return addLine1;
	}
	public void setAddLine1(String addLine1) {
		this.addLine1 = addLine1;
	}
	public String getAddLine2() {
		return addLine2;
	}
	public void setAddLine2(String addLine2) {
		this.addLine2 = addLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCash() {
		return cash;
	}
	public void setAvailableBalance(String cash) {
		this.cash = cash;
	}
	

}
