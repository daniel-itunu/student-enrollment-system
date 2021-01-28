package com.flexisaf.challenge.challenge.util;

import com.flexisaf.challenge.challenge.dto.StudentDto;
import com.flexisaf.challenge.challenge.model.Student;
import com.flexisaf.challenge.challenge.service.StudentService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class Scheduler {
    private final StudentService studentService;

    public Scheduler(StudentService studentService) {
        this.studentService = studentService;
    }


    @Scheduled(cron="0 0 0 * * ?")
    public void sendBirthdayMessage() throws Exception {
        List<StudentDto> studentList = studentService.retrieveStudents();
        String localDate = LocalDate.now().toString();
        String month = localDate.split("-")[1];
        String day = localDate.split("-")[2];
        studentList.stream().forEach(studentDto -> {
            String birthDate = studentDto.getDateOfBirth();
            if( month==birthDate.split("-")[1] && day==birthDate.split("-")[2]){
                //send
                //send sms
                Twilio.init("AC9e01a9b4702e9902a2bd1438211c4f88", "c5e1527aac7ace08e246dbc75568e135");
                Message.creator(new PhoneNumber(orderInfo.getUser().getPhoneNumber()), new PhoneNumber("+15594713475"),
                        info).create();
            }
        });

    }
}
