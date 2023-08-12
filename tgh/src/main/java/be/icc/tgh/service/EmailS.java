package be.icc.tgh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailS {
    @Autowired
    private JavaMailSender mailSender;

    public void sendConfirmationEmail(String to, String confirmationLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Confirmation de votre inscription");
        message.setText("Cliquez sur le lien suivant pour confirmer votre inscription : " + confirmationLink);
        mailSender.send(message);
    }

}
