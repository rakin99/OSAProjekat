package email.dto;

import java.io.Serializable;

import email.entity.Account;

public class AccountDTO implements Serializable{

	private long id;
	private String smtpAddress;
	private int smtpPort;
	private short inServerType;
	private String inServerAddress;
	private int inServerPort;
	private String username;
	private String password;
	private String displayName;
	private boolean active;
	
	public AccountDTO() {
		super();
		this.smtpPort = 0;
		this.inServerType = 0;
		this.inServerPort = 0;
		this.displayName = "";
	}

	public AccountDTO(long id, String smtpAddress, int smtp, short inServerType, String inServerAddress,
			int inServerPort, String username, String password, String displayName,boolean active) {
		super();
		this.id = id;
		this.smtpAddress = smtpAddress;
		this.smtpPort = smtp;
		this.inServerType = inServerType;
		this.inServerAddress = inServerAddress;
		this.inServerPort = inServerPort;
		this.username = username;
		this.password = password;
		this.displayName = displayName;
		this.active=active;
	}
	
	public AccountDTO(Account account) {
		this(account.getId(),account.getSmtpAddress(),account.getSmtpPort(),account.getInServerType(),
				account.getInServerAddress(),account.getInServerPort(),account.getUsername(),
				account.getPassword(),account.getDisplayname(),account.isActive());
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

	public int getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(int smtp) {
		this.smtpPort = smtp;
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
}
