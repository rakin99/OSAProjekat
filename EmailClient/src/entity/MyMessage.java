package entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="messages")
public class MyMessage {

	@Id                                 // atribut je deo primarnog kljuca
	@GeneratedValue(strategy=IDENTITY)  // vrednost se generise automatski, u bazi
	@Column(name="admin_id", unique=true, nullable=false) 
	private long id;
	
	@Column(name="_from", unique=false, nullable=false)
	private String _from;
	
	@Column(name="_to", unique=false, nullable=false)
	private String _to;
	
	@Column(name="_cc", unique=false, nullable=false)
	private String _cc;
	
	@Column(name="_bcc", unique=false, nullable=false)
	private String _bcc;
	
	@Column(name="dateTime", unique=false, nullable=false)
	private Date dateTime;
	
	@Column(name="subject", unique=false, nullable=false)
	private String subject;
	
	@Column(name="content", unique=false, nullable=false)
	private String content;
	
	@Column(name="unread", unique=false, nullable=false)
	private boolean unread;

	public MyMessage() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String get_from() {
		return _from;
	}

	public void set_from(String _from) {
		this._from = _from;
	}

	public String get_to() {
		return _to;
	}

	public void set_to(String _to) {
		this._to = _to;
	}

	public String get_cc() {
		return _cc;
	}

	public void set_cc(String _cc) {
		this._cc = _cc;
	}

	public String get_bcc() {
		return _bcc;
	}

	public void set_bcc(String _bcc) {
		this._bcc = _bcc;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isUnread() {
		return unread;
	}

	public void setUnread(boolean unread) {
		this.unread = unread;
	}
	
	public MyMessage(long id, String _from, String _to, String _cc, String _bcc, Date dateTime, String subject,
			String content, boolean unread) {
		super();
		this.id = id;
		this._from = _from;
		this._to = _to;
		this._cc = _cc;
		this._bcc = _bcc;
		this.dateTime = dateTime;
		this.subject = subject;
		this.content = content;
		this.unread = unread;
	}
	
	public String toString() {
	    return "(Message)[id="+id+",_from="+_from+",_to="+_to+",_cc="+_cc+",_bcc="+_bcc+",dateTime="+dateTime+
	    		",subject="+subject+",content="+content+",unread="+unread+"]";
	  }
}
