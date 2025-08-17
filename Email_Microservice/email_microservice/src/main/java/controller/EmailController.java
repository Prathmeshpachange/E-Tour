package controller;



import service.*;
import service.impl.*;
import dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
        String result = emailService.sendSimpleEmail(request);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/test")
    public String test() {
        
        return "testing";
    }
}
