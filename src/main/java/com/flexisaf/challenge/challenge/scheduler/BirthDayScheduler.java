package com.flexisaf.challenge.challenge.scheduler;

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
public class BirthDayScheduler {
    private final StudentService studentService;

    public BirthDayScheduler(StudentService studentService) {
        this.studentService = studentService;
    }


    //@Scheduled(cron="*/10 * * * * *")
    @Scheduled(cron="0 0 0 * * ?")
    public void sendBirthdayMessage() throws Exception {
        List<StudentDto> studentList = studentService.retrieveStudents();
        String localDate = LocalDate.now().toString();
        String month = localDate.split("-")[1];
        String day = localDate.split("-")[2];
        studentList.stream().forEach(studentDto -> {
            String birthDate = studentDto.getDateOfBirth();
            if( month.equals(birthDate.split("-")[1]) && day.equals(birthDate.split("-")[2])){
                Twilio.init("", "");
                Message.creator(new PhoneNumber(studentDto.getPhoneNumber()), new PhoneNumber("+15594713475"), "Happy birthday " +
                        ""+studentDto.getFirstName()+" "+studentDto.getLastName()+
                        "! Flexisaf wishes you a wonderful year ahead").create();
            }
        });
    }
}
