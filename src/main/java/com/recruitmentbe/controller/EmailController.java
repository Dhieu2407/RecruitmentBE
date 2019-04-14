package com.recruitmentbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmailController {

    @Autowired
    public JavaMailSender emailSender;

    @ResponseBody
    @RequestMapping(value="/sendSimpleEmail", method = RequestMethod.GET)
    public String sendSimpleEmail(@RequestParam(value="email")String email,@RequestParam(value="username")String username) {

        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Comfirm Active Account.");
        String text ="Nhấp vào link đăng kí để kích hoạt tài khoản của bạn: http://localhost:8082/updateactive?username="+username;
        message.setText(text);

        // Send Message!
        this.emailSender.send(message);

        return "Email Sent!";
    }

}
