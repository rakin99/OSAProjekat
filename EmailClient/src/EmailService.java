import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
* Java Program to send text mail using default SMTP server and without authentication.
* You need mail.jar, smtp.jar and activation.jar to run this program.
*
* @author Javin Paul
*/
public class EmailService{

    public static void main(String args[]) {
        String to = "receive@abc.om";            // sender email
        String from = "sender@abc.com";       // receiver email
        String host = "127.0.0.1";                   // mail server host

       Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);

        Session session = Session.getDefaultInstance(properties); // default session

        try {
            MimeMessage message = new MimeMessage(session);        // email message
            message.setFrom(new InternetAddress(from));                    // setting header fields
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Test Mail from Java Program"); // subject line

            // actual mail body
            message.setText("You can send mail from Java program by using mail API, but you need"
                    + "couple of more JAR files e.g. smtp.jar and activation.jar");

            // Send message
            Transport.send(message);
            System.out.println("Email Sent successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

}
