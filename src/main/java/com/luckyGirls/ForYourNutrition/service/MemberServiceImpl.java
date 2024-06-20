package com.luckyGirls.ForYourNutrition.service;

import java.util.List;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckyGirls.ForYourNutrition.dao.CartDao;
import com.luckyGirls.ForYourNutrition.dao.MemberDao;
import com.luckyGirls.ForYourNutrition.domain.Cart;
import com.luckyGirls.ForYourNutrition.domain.Member;

import jakarta.transaction.Transactional;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired 
	private CartDao cartDao;
	
	public Member getMember(String id) {
		return memberDao.getMember(id);
	}
	
	//id, password로 회원 정보 가져오기
	public Member getMember(String id, String password) {
		return memberDao.getMember(id, password);
	}
	
	//회원 가입
	@Transactional
	public void insertMember(Member member) {
		memberDao.insertMember(member);
	}
	
	//회원 정보 수정
	public void updateMember(Member member) {
		memberDao.updateMember(member);
	}
	
	//회원 탈퇴
	public void deleteMember(String id) {
		memberDao.deleteMember(id);
	}
	
	//아이디 찾기
	public String findId(String email, String name) {
		return memberDao.findId(email, name);
	}
	
	//비밀번호 찾기
	public String findPassword(String id, String email) {
		return memberDao.findPassword(id, email);
	}
	
	@Override
	public void sendEmail(int takingTime) {
        List<Member> members = memberDao.getMembers(takingTime);

        // 이메일 설정
        String host = "smtp.gmail.com";
        final String user = "foryournutrition20@gmail.com";
        final String password = "iogq cdqd iirt vylp";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // TLS 사용 설정
        props.put("mail.smtp.port", "587"); // TLS 포트

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        // 각 멤버에게 이메일 보내기
        for (Member member : members) {
            String to = member.getEmail();  // 멤버의 이메일 주소
            String nickname = member.getNickname();
            System.out.println(to);
            
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(user));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                // 이메일 제목과 내용 설정
                message.setSubject("[For Your Nutrition]영양제 섭취 시간 알림");
                message.setText(nickname + "님 영양제 섭취하실 시간입니다!");

                // 이메일 보내기
                Transport.send(message);

                System.out.println("Email sent successfully to " + to);

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
	}
	
	@Override
	public void sendIdEmail(String email, String id) {
        // 이메일 설정
        String host = "smtp.gmail.com";
        final String user = "foryournutrition20@gmail.com";
        final String password = "iogq cdqd iirt vylp";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // TLS 사용 설정
        props.put("mail.smtp.port", "587"); // TLS 포트

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            // 이메일 제목과 내용 설정
            message.setSubject("[For Your Nutrition]아이디 찾기 결과");
            message.setText("For Your Nutrition에서\n아이디 찾기 결과 알려드립니다.\n회원님의 아이디는 \"" + id + "\" 입니다.\n감사합니다.");

            // 이메일 보내기
            Transport.send(message);

            System.out.println("Email sent successfully to " + email);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
	
	@Override
	public void sendPasswordEmail(String email, String pw) {
        // 이메일 설정
        String host = "smtp.gmail.com";
        final String user = "foryournutrition20@gmail.com";
        final String password = "iogq cdqd iirt vylp";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // TLS 사용 설정
        props.put("mail.smtp.port", "587"); // TLS 포트

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            // 이메일 제목과 내용 설정
            message.setSubject("[For Your Nutrition]비밀번호 찾기 결과");
            message.setText("For Your Nutrition에서\n비밀번호 찾기 결과 알려드립니다.\n회원님의 비밀번호는 \"" + pw + "\" 입니다.\n감사합니다.");

            // 이메일 보내기
            Transport.send(message);

            System.out.println("Email sent successfully to " + email);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
