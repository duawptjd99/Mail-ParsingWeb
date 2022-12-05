
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
		if (code.equals("��������")) {
			code = "�������� - ��ũ : https://www.aihub.or.kr/aihubnews/notice/list.do?currMenu=132&topMenu=103";
		}
		if (code.equals("�������")) {
			code = "������� - ��ũ : https://www.aihub.or.kr/aihubnews/bsnspblanc/list.do?currMenu=133&topMenu=103";
		}
		String recipient = ID;

		// 1. �߽����� ���� ������ ��й�ȣ ����
		final String user = ID;
		final String password = PW;

		// 2. Property�� SMTP ���� ���� ����
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
		// 3. SMTP ���������� ����� ������ ������� Session Ŭ������ �ν��Ͻ� ����
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// 4. Message Ŭ������ ��ü�� ����Ͽ� �����ڿ� ����, ������ �޽����� �ۼ��Ѵ�.
		// 5. Transport Ŭ������ ����Ͽ� �ۼ��� �޼����� �����Ѵ�.

		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(user));

			// ������ ���� �ּ�
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

			// Subject
			message.setSubject("��괺������ ������Ʈ ������ �ֽ��ϴ�.");

			// Text
			message.setText("[" + code + "]������Ʈ �˸��� �ֽ��ϴ�. ");

			Transport.send(message); // send message

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		System.out.println("sendMail");
	}
}