package util;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.*;

public class EmailService {
    private static final Logger logger = Logger.getLogger(EmailService.class.getName());

    public static void sendCriticalEmail(String subject, Exception e) {
        Properties properties = new Properties();

        try (InputStream input = EmailService.class.getClassLoader().getResourceAsStream("email.properties")) {
            properties.load(input);
        } catch (Exception ex) {
            logger.severe("Помилка при зчитуванні конфігураційного файлу: " + ex.getMessage());
            return;
        }

        String username = properties.getProperty("email.username");
        String password = properties.getProperty("email.password");
        String recipient = properties.getProperty("recipient.email");

        properties.put("mail.smtp.host", properties.getProperty("mail.smtp.host"));
        properties.put("mail.smtp.port", properties.getProperty("mail.smtp.port"));
        properties.put("mail.smtp.auth", properties.getProperty("mail.smtp.auth"));
        properties.put("mail.smtp.starttls.enable", properties.getProperty("mail.smtp.starttls.enable"));

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText("Критична помилка: " + e.getMessage());

            Transport.send(message);
            logger.info("Критичний email надіслано.");
        } catch (MessagingException mex) {
            logger.severe("Помилка при відправці email: " + mex.getMessage());
        }
    }
}
