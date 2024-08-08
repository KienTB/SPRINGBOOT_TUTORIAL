package com.tutorial.apidemo.Springboot_tutorial.Controller;

import com.tutorial.apidemo.Springboot_tutorial.Model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/v1")
public class CustomerGreetingController {
    private static final String greetingTemplate = "Hello, %s %s";
    private final AtomicLong counter = new AtomicLong(); // lưu trữ id cho những lần kế tiếp
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "gender", defaultValue = "0")boolean gender, //yeu cau customer truyen vao gia tri
                             @RequestParam(value = "customerName", defaultValue = "Customer") String customerName
                             ) {
        return Greeting.builder()
                .id(counter.incrementAndGet()) //hàm này tự động tăng lên 1 khi request
                .content(String.format(greetingTemplate, gender ? "Mr." : "Ms.", customerName))
                .build();
    }
}
