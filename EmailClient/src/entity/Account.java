package entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name="accounts")
public class Account implements Serializable{
	
	
	@Id                                 // atribut je deo primarnog kljuca
	@GeneratedValue(strategy=IDENTITY)  // vrednost se generise automatski, u bazi
	@Column(name="account_id", unique=true, nullable=false)
	private long id;
	
	@Column(name="smtpAddress", unique=false, nullable=false)
	private String smtpAddress;
	
	@Column(name="inServerType", unique=false, nullable=false)
	private int smtp;
	private short inServerType;
	
	@Column(name="inServerAddress", unique=false, nullable=false)
	private String inServerAddress;
	
	@Column(name="inServerPort", unique=false, nullable=false)
	private int inServerPort;
	
	@Column(name="username", unique=false, nullable=false)
	private String username;
	
	
	@Column(name="password", unique=false, nullable=false)
	private String password;
	
	@Column(name="displayname", unique=false, nullable=false)
	private String displayname;

	public Account(long id, String smtpAddress, int smtp, short inServerType, String inServerAddress, int inServerPort ,String username, 
			String password, String displayName) {
		this.id=id;
		this.smtpAddress=smtpAddress;
		this.smtp=smtp;
		this.inServerType=inServerType;
		this.inServerAddress=inServerAddress;
		this.inServerPort=inServerPort;
		this.username=username;
		this.password=password;
		this.displayname=displayName;
	}
	
	public Account() {
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSmtpAddress() {
		return smtpAddress;
	}

	public void setSmtpAddress(String smtpAddress) {
		this.smtpAddress = smtpAddress;
	}

	public int getSmtp() {
		return smtp;
	}

	public void setSmtp(int smtp) {
		this.smtp = smtp;
	}

	public short getInServerType() {
		return inServerType;
	}

	public void setInServerType(short inServerType) {
		this.inServerType = inServerType;
	}

	public String getInServerAddress() {
		return inServerAddress;
	}

	public void setInServerAddress(String inServerAddress) {
		this.inServerAddress = inServerAddress;
	}

	public int getInServerPort() {
		return inServerPort;
	}

	public void setInServerPort(int inServerPort) {
		this.inServerPort = inServerPort;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	
	public String toString() {
	    return "(Account)[id="+id+",smtpAddress="+smtpAddress+",smtp="+smtp+"inServerType="+inServerType+",inServerAddress="+inServerAddress+
	    		",inServerPort="+inServerPort+",username="+username+",password="+password+",displayname="+displayname+"]";
	  }
}
