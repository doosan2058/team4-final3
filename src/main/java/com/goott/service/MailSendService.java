package com.goott.service;

import java.util.Random;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSendService {
    @Autowired
    private JavaMailSenderImpl mailSender;

    /**
     * 이메일 인증시 사용할 랜덤 코드 생성하는 메서드
     *
     * @return rText 랜덤 코드 리턴
     */
    public String makeRandomCode() {
        String rText = UUID.randomUUID().toString();
        return rText;
    }

    /**
     * 가입 신청한 유저의 이메일로 랜덤 코드 발송하는 메서드(세팅)
     *
     * @param member_email 인증 코드 발송할 메일 주소(가입 선청한 이메일)
     * @return code 생성된 인증 코드 (유저 기입 확인)
     */
    public String joinEmail(String member_email) {
        String code = makeRandomCode();
        String from = "doosan2058@gmail.com";
        String to = member_email;
        String subject = "팀4 프로젝트 회원가입 인증 이메일 입니다.";
        String text = "인증 번호는 [ " + code + " ] 입니다.";
        //인증 메일 전송
        mailSend(from, to, subject, text);
        //확인 코드 auth_mail.jsp 로 전송
        return code;
    }

    /**
     * 가입 신청한 유저의 이메일로 랜덤 코드 발송하는 메서드(실행)
     *
     * @param from    관리자 이메일 주소
     * @param to      가입 신청 유저 이메일 주소
     * @param subject 제목
     * @param text    내용 (랜덤 코드 담아서 발송)
     */
    public void mailSend(String from, String to, String subject, String text) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {

            e.printStackTrace();
        }

    }

    /**
     * 비밀번호 초기호 이메일 보내기
     *
     * @param member_email 회원 이메일
     * @return 랜덤 코드
     */
    public String forgotPassword(String member_email) {
        String code = makeRandomCode();
        String from = "doosan2058@gmail.com";
        String to = member_email;
        String subject = "팀4 프로젝트 비밀번호 초기화 이메일 입니다.";
        String text = "비밀번호는 [ " + code + " ] 입니다.";
        mailSend(from, to, subject, text);
        return code;
    }

    /**
     * 메일 보내기
     *
     * @param toTemp      받는사람 이메일 주소
     * @param subjectTemp 제목
     * @param textTemp    내용
     */
    public void promotionMail(String toTemp, String subjectTemp, String textTemp) {
        String from = "doosan2058@gmail.com"; // 보내는 사람
        String to = toTemp;
        String subject = subjectTemp;
        String text = textTemp;
        mailSend(from, to, subject, text);
    }
}
