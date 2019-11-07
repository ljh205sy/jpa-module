package com.example.manytoone.controller;

import com.example.manytoone.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liujinhui
 * @Date: 2019/11/3 20:29
 */

@RestController
public class PersionController {

    @Autowired
    private PersonService PersonServiceImpl;

}
