package com.example.hw.service;

import org.springframework.validation.BindingResult;

import java.util.Map;

public interface DtoValidation {

    Map<String, String> inputDtoValidation(BindingResult bindingResult);
}
