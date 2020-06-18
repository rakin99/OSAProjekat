package email.read_send_mail;
import java.text.ParseException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import email.entity.MyMessage;
import email.tools.DateUtil;

public class SendMail {
   public static void send(MyMessage myMessage) throws ParseException {
      // Recipient's email ID needs to be mentioned.
      String to = "pantelijacappone@gmail.com";

      // Sender's email ID needs to be mentioned
      String from = "rakindejan@gmail.com";
      final String username = "rakindejan";//change accordingly
      final String password = "pexlqolkzswsczrj";//change accordingly

      // Assuming you are sending email through relay.jangosmtp.net
      String host = "smtp.gmail.com";

        Properties props = new Properties();
  props.put("mail.smtp.auth", "true");
  props.put("mail.smtp.starttls.enable", "true");
  props.put("mail.smtp.host", "smtp.gmail.com");
  props.put("mail.smtp.port", "587");

      // Get the Session object.
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
    }
         });

      try {
    // Create a default MimeMessage object.
    Message message = new MimeMessage(session);
 
    // Set From: header field of the header.
    message.setFrom(new InternetAddress(myMessage.get_from()));
 
    if(!myMessage.get_to().equals("")) {
        // Set To: header field of the header.
        message.setRecipients(Message.RecipientType.TO,
                   InternetAddress.parse(myMessage.get_to()));
    }
 
    if(!myMessage.get_cc().equals("")) {
    	message.setRecipients(Message.RecipientType.CC,
                InternetAddress.parse(myMessage.get_cc()));
    }
    
    if(!myMessage.get_bcc().equals("")) {
    	message.setRecipients(Message.RecipientType.BCC,
                InternetAddress.parse(myMessage.get_bcc()));
    }
 
    // Set Subject: header field
    message.setSubject(myMessage.getSubject());
    
    message.setSentDate(DateUtil.getDateFromGregorianCalendar(myMessage.getDateTime()));
    
    // Now set the actual message
    message.setText(myMessage.getContent());

    // Send message
    Transport.send(message);

    System.out.println("Sent message successfully....");

      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
   }
}
