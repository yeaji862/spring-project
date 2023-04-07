package com.example.petsProject.CoolSMS;

import org.springframework.stereotype.Service;

@Service
public interface CertificationNumber {
    String sendOne(String user_phone , String randomNumber);
}
