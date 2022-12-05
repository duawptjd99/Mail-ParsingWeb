
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	public Mail(String code, String ID, String PW) {
		if (code.equals("공지사항")) {
			code = "공지사항 - 링크 : https://www.aihub.or.kr/aihubnews/notice/list.do?currMenu=132&topMenu=103";
		}
		if (code.equals("사업공고")) {
			code = "사업공고 - 링크 : https://www.aihub.or.kr/aihubnews/bsnspblanc/list.do?currMenu=133&topMenu=103";
		}
		String recipient = ID;

		// 1. 발신자의 메일 계정과 비밀번호 설정
		final String user = ID;
		final String password = PW;

		// 2. Property에 SMTP 서버 정보 설정
		Properties prop = new Properties();
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.host", "nextor.hanbiro.net");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.ssl.trust", "nextor.hanbiro.net");
		prop.put("mail.smtp.auth", "true");
		prop.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
		/*
		 * prop.put("mail.smtp.host", "smtp.naver.com"); prop.put("mail.smtp.port", 587
		 * ); prop.put("mail.smtp.auth", "true"); prop.put("mail.smtp.ssl.enable",
		 * "true"); prop.put("mail.smtp.ssl.trust", "smtp.naver.com");
		 */
		// 3. SMTP 서버정보와 사용자 정보를 기반으로 Session 클래스의 인스턴스 생성
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// 4. Message 클래스의 객체를 사용하여 수신자와 내용, 제목의 메시지를 작성한다.
		// 5. Transport 클래스를 사용하여 작성한 메세지를 전달한다.

		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(user));

			// 수신자 메일 주소
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

			// Subject
			message.setSubject("허브뉴스에서 업데이트 사항이 있습니다.");

			// Text
			message.setText("[" + code + "]업데이트 알림이 있습니다. ");

			Transport.send(message); // send message

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		System.out.println("sendMail");
	}
}