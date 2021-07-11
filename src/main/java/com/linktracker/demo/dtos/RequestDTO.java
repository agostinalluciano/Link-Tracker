package com.linktracker.demo.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class RequestDTO {


    @URL
    private String url;
}
