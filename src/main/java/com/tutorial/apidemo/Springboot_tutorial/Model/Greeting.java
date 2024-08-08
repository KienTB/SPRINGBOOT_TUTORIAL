package com.tutorial.apidemo.Springboot_tutorial.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder //gan du lieu
public class Greeting {
    private long id;
    private String content;
}
