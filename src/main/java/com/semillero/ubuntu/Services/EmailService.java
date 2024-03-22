package com.semillero.ubuntu.Services;

public interface EmailService {
    void sendEmailToUsers();
    void sendEmail(String user_name, String user_email, int newMicrosQuantity, int newPublicationsQuantity);
}
