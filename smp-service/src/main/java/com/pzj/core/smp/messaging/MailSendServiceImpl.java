package com.pzj.core.smp.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.pzj.message.mail.service.MailSendService;

@Service("mailSendService")
public class MailSendServiceImpl implements MailSendService {

	@Autowired
	private JavaMailSender mailSender;

	@Value("${mail.username}")
	String mailFrom;

	@Override
	public void sendMail(String subject, String content, String[] mailtoArrays) {
		SimpleMailMessage smm = createSimpleMailMessage(subject, content, mailtoArrays);
		mailSender.send(smm);
	}

	private SimpleMailMessage createSimpleMailMessage(String subject, String content, String[] mailtoArrays) {
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setFrom(mailFrom);
		smm.setTo(mailtoArrays);
		smm.setSubject(subject);
		smm.setText(content);
		return smm;
	}

}
