package com.semillero.ubuntu.Services;

public interface EmailService {
    void sendEmailToUsers();
    void sendEmail(String user_name, String user_email, long newMicrosQuantity, long newPublicationsQuantity);
}
