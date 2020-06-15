package email.read_send_mail;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;  
import javax.mail.Folder;  
import javax.mail.Message;  
import javax.mail.MessagingException;  
import javax.mail.NoSuchProviderException;  
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.sun.mail.pop3.POP3Store;

import email.entity.MyMessage;

import javax.mail.*;
public class ReadMail{  
  
 public static List<MyMessage> receiveEmail(String pop3Host, String storeType,  
  final String user, final String password) {  
 	Message[] messages;
 	List<MyMessage> mess=new ArrayList<MyMessage>();
	  try {
		  
	  EntityManagerFactory factory = Persistence.createEntityManagerFactory("EmailClient");
	  EntityManager manager = factory.createEntityManager();
	   //1) get the session object  
	   Properties properties = new Properties();  
	   properties.put("mail.store.protocol", "imaps");    
	    Session emailSession = Session.getDefaultInstance(properties,
	   new javax.mail.Authenticator() {
	    protected PasswordAuthentication getPasswordAuthentication() {
	     return new PasswordAuthentication(user,password);
	    }
	   }); 
	   //2) create the POP3 store object and connect with the pop server  
	   Store emailStore = emailSession.getStore("imaps");
	 emailStore.connect("imap.gmail.com","rakindejan@gmail.com", password);
	    
	  
	   //3) create the folder object and open it  
	   Folder emailFolder = emailStore.getFolder("INBOX");  
	   emailFolder.open(Folder.READ_ONLY);  
	  
	   //4) retrieve the messages from the folder in an array and print it  
	   messages = emailFolder.getMessages(); 
//	   System.out.println("Pocinjem sa upisivanje Message u bazu!");
	   for (int i = 0; i < 20; i++) {  
	    Message m = messages[i]; 
	    MyMessage message=new MyMessage();
	    message.set_from(m.getFrom()[0].toString());
	    if(m.getRecipients(Message.RecipientType.TO)[0]!=null) {
	    	message.set_to(m.getRecipients(Message.RecipientType.TO)[0].toString());	
	    }else if(m.getRecipients(Message.RecipientType.CC)[0]!=null) {
	    	message.set_cc(m.getRecipients(Message.RecipientType.CC)[0].toString());
	    }else if(m.getRecipients(Message.RecipientType.BCC)[0]!=null) {
	    	message.set_bcc(m.getRecipients(Message.RecipientType.BCC)[0].toString());
	    }
	    message.setDateTime(m.getSentDate());
	    message.setSubject(m.getSubject());
	    message.setContent(m.getContent().toString());
	    message.setUnread(true);
	    mess.add(message);
//	    
//	    EntityTransaction tx = manager.getTransaction();
//		tx.begin();
//		manager.persist(message);
//		tx.commit();
//		System.out.println("Upisana prva poruka");
	   }  
//	   System.out.println("Zavrsio sa upisivanjem Message u bazu!");
	  
	   //5) close the store and folder objects  
	   emailFolder.close(false);  
	   emailStore.close(); 
	   return mess;
	  } catch (NoSuchProviderException e) {e.printStackTrace();}   
	  catch (MessagingException e) {e.printStackTrace();}  
  catch (IOException e) {e.printStackTrace();}  
	  return null;
 }  
  
// public static void main(String[] args) {  
//  
//  String host = "smtp.gmail.com";//change accordingly  
//  String mailStoreType = "pop3";  
//  final String username= "rakindejan@gmail.com";  
//  final String password= "pexlqolkzswsczrj";//change accordingly  
//  
//  receiveEmail(host, mailStoreType, username, password);  
//  
// }  
}
