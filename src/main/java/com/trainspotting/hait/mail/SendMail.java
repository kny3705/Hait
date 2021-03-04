package com.trainspotting.hait.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SendMail {

	@Autowired
	private JavaMailSender mailSender;

	public void rejectMail(String email) {
		try {
			MailUtils sendMail = new MailUtils(mailSender);

			sendMail.setSubject("[H.ait] 서비스 신청이 거부되었습니다.");
			sendMail.setText("<h1>자세한 사항은 회사로 문의해주세요.</h1>");
			sendMail.setFrom("이메일@gmail.com", "H.ait");
			sendMail.setTo(email);
			sendMail.send();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public void acceptMail(String email, String tempPW) {
		try {
			MailUtils sendMail = new MailUtils(mailSender);

			sendMail.setSubject("[H.ait] 회원이 되신걸 환영합니다.");
			sendMail.setText("<h1>아래 발급된 비밀번호로 로그인 후 변경해주세요.</h1><br>"
					+ "<p>임시 비밀번호 : "
					+ tempPW + "</p>"
					);
			sendMail.setFrom("이메일@gmail.com", "H.ait");
			sendMail.setTo(email);
			sendMail.send();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
