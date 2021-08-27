package com.kate.cartspringboot.controller;

import com.kate.cartspringboot.exception.DuplicateCustomerException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class LibraryControllerAdvice {

    @ExceptionHandler(value = DuplicateCustomerException.class)
    public ModelAndView duplicateCustomerException(DuplicateCustomerException e) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ref", e.getCustomer().getId());
        modelAndView.addObject("object", e.getCustomer());
        modelAndView.addObject("message", "Cannot add an already existing customer");
        modelAndView.setViewName("error-customer");
        return modelAndView;
    }
}