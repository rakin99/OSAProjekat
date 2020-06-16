package email.read_send_mail;
import java.io.IOException;
import java.text.ParseException;
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
import javax.mail.internet.MimeMultipart;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.sun.mail.pop3.POP3Store;

import email.entity.MyMessage;
import email.tools.DateUtil;

import javax.mail.*;
public class ReadMail{  
  
 public static Message[] receiveEmail(String pop3Host, String storeType,  
  final String user, final String password,int maxId) throws ParseException {  
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
	   System.out.println("Pocinjem sa upisivanjem mejlova!");
	   if(maxId==0) {
		   maxId=messages.length-30;
	   }
	   for (int i=maxId; i < messages.length; i++) {  
	    Message m = messages[i]; 
	    MyMessage message=new MyMessage();
	    message.setId(i+1);
	    System.out.println("\nId poruke je: "+message.getId());
	    message.set_from(m.getFrom()[0].toString());
	    if(m.getRecipients(Message.RecipientType.TO)[0]!=null) {
	    	message.set_to(m.getRecipients(Message.RecipientType.TO)[0].toString());	
	    }else if(m.getRecipients(Message.RecipientType.CC)[0]!=null) {
	    	message.set_cc(m.getRecipients(Message.RecipientType.CC)[0].toString());
	    }else if(m.getRecipients(Message.RecipientType.BCC)[0]!=null) {
	    	message.set_bcc(m.getRecipients(Message.RecipientType.BCC)[0].toString());
	    }
	    message.setDateTime(DateUtil.getGregorianCalendarFromDate(m.getSentDate()));
	    message.setSubject(m.getSubject());
	    String content="";
	    if (m.isMimeType("text/plain")) {
	    	content = message.getContent().toString();
	    } 
	    else if (m.isMimeType("multipart/*")) {
	        MimeMultipart mimeMultipart = (MimeMultipart) m.getContent();
	        content = getTextFromMimeMultipart(mimeMultipart);
	    }
	    
	    message.setContent(content);
	    message.setUnread(true);
	    
	    EntityTransaction tx = manager.getTransaction();
		tx.begin();
		manager.persist(message);
		tx.commit();
		System.out.println(i+". poruka je upisana.");
	   }  
	   System.out.println("Zavrsio sa upisivanjem mejlova!");
	  
	   //5) close the store and folder objects  
	   emailFolder.close(false);  
	   emailStore.close(); 
	   return messages;
	  } catch (NoSuchProviderException e) {e.printStackTrace();}   
	  catch (MessagingException e) {e.printStackTrace();} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return null;
 }  
 
 public static String getTextFromMimeMultipart(
	        MimeMultipart mimeMultipart)  throws MessagingException, IOException{
	    String result = "";
	    int count = mimeMultipart.getCount();
	    for (int i = 0; i < count; i++) {
	        BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	        if (bodyPart.isMimeType("text/plain")) {
	            result = result + "\n" + bodyPart.getContent();
	            break; // without break same text appears twice in my tests
	        } else if (bodyPart.isMimeType("text/html")) {
	            String html = (String) bodyPart.getContent();
	            result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
	        } else if (bodyPart.getContent() instanceof MimeMultipart){
	            result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
	        }
	    }
	    return result;
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
