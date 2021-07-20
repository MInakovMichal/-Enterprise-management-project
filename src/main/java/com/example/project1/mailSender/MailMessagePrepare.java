package com.example.project1.mailSender;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailMessagePrepare {

    final String username = "yourMail.com";
    final String password = "yourPassword";

    public void prepareMessageObject(String recipient, String messageText) throws MessagingException {

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        int activationCod = getActivationCode();
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipient)
            );
            message.setSubject("Account activation");
            message.setText("This email is generated automatically.\n\n" +
                    messageText);
//            message.setText("This email is generated automatically.\n" +
//                    "\n\nActivation code: " + activationCod);

            Transport.send(message);


        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private int getActivationCode() {
        int a = 100000;
        int b = 999999;
        return a + (int) (Math.random() * ((b - a) + 1));
    }
}

