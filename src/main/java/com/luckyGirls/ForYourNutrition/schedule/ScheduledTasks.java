package com.luckyGirls.ForYourNutrition.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.luckyGirls.ForYourNutrition.service.MemberService;

@Component
public class ScheduledTasks {

    @Autowired
    private MemberService memberService;

    @Scheduled(cron = "0 0 * * * *") // 매 시간마다 실행
    public void sendEmailsToMembers() {
        int currentHour = java.time.LocalTime.now().getHour();
        System.out.println("현재 hour = " + currentHour);
        memberService.sendEmail(currentHour);
    }
}
