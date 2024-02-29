package vn.techmaster.demothymeleafspringsecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    public final JavaMailSender emailSender;

    // Gửi email thông báo tạo tài khoản
    public void sendMailCreateAccount(String email, String content) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Xác nhận tài khoản của Hiên!");
        message.setText(content);

        // Send Message!
        emailSender.send(message);
    }
}
