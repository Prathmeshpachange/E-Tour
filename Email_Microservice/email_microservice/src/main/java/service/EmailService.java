package service;
import dto.*;

public interface EmailService {
    String sendSimpleEmail(EmailRequest request);
}