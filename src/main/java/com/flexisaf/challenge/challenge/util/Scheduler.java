package com.flexisaf.challenge.challenge.util;

import com.flexisaf.challenge.challenge.dto.StudentDto;
import com.flexisaf.challenge.challenge.service.StudentService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
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
                //send sms
                Twilio.init("AC9e01a9b4702e9902a2bd1438211c4f88", "c5e1527aac7ace08e246dbc75568e135");
                Message.creator(new PhoneNumber(studentDto.getPhoneNumber()), new PhoneNumber("+15594713475"), "Happy birthday" +
                        ""+studentDto.getFirstName()+" "+studentDto.getLastName()+
                        "Flexisaf wishes you a wonderful year ahead").create();
            }
        });

    }
}
