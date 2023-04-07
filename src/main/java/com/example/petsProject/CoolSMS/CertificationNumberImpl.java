package com.example.petsProject.CoolSMS;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("CertificationNumber")
public class CertificationNumberImpl implements CertificationNumber{
    @Value("${key}")
    private String key;
    @Value("${secret}")
    private String secret;

    final DefaultMessageService messageService;
    public CertificationNumberImpl() {
//         반드시 계정 내 등록된 유효한 API 키, API Secret Key를 입력해주셔야 합니다!
        this.messageService = NurigoApp.INSTANCE.initialize(key, secret, "https://api.coolsms.co.kr");
    }
    @Override
    public String sendOne(String user_phone , String randomNumber) {
        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom("01024196086");
        message.setTo(user_phone);
        message.setText("["+String.valueOf(randomNumber)+"] Pets에서 보낸 인증번호 입니다.");
        String statusMessage = this.messageService.sendOne(new SingleMessageSendingRequest(message)).getStatusCode(); // 2000 3000 4000

        return statusMessage;
    }
}
