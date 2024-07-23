package com.springboot.member.entity;

import com.springboot.helper.EmailSender;
import com.springboot.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.mail.MailSendException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;





@Slf4j
@Component
@EnableAsync
public class EmailListener {
    private final EmailSender emailSender;
    private final MemberService memberService;

    public EmailListener(EmailSender emailSender, MemberService memberService) {

        this.emailSender = emailSender;
        this.memberService = memberService;
    }


    @Async
    @EventListener
    public void sendEmail(CreatedMailEvent createdMailEvent) throws Exception {
        try {
            emailSender.sendEmail("any email message");
        } catch (MailSendException e) {
            log.error("이메일이 발송되지 않아서, 등록한 member정보를 롤백합니다.", e);
            memberService.deleteMember(createdMailEvent.getMember().getMemberId());
        }
    }

}
