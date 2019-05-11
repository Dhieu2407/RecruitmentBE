package com.recruitmentbe.controller;

import com.recruitmentbe.model.Gmail;
import com.recruitmentbe.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    public JobService jobService;

    @ResponseBody
    @RequestMapping(value="/sendemailcandidate", method = RequestMethod.GET)
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
    @ResponseBody
    @PostMapping(value = "/sendEmail")
    public Gmail sendEmail(@RequestBody String body){
        Gmail gmail = new Gmail();
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(gmail.getEmailCandiate());
        message.setSubject("["+gmail.getCompany().getTenCongTy()+"] Tuyển dụng: "+gmail.getJob().getTenJob());
        String text =gmail.getContentMail();
        String b =
            gmail.getCompany().getTenCongTy()+"\n" +
            "Office address  : "+gmail.getCompany().getDiaChi()+" Str.,\n" +
            "Mobile               : +"+gmail.getCompany().getSdt()+"\n" +
            "E-mail                : "+gmail.getCompany().getEmail()+"\n";
            String a = "Trân trọng thông báo,\n" +
            "Bộ phận nhân sự - "+gmail.getCompany().getTenCongTy();
            String c = b+a;
            text = text+c;
        message.setText(text);


        // Send Message!
        this.emailSender.send(message);
        return gmail;
    }

}
