package com.sqltest.demo.controller;

import com.sqltest.demo.model.SubscribedIndents;
import com.sqltest.demo.service.saveIndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/indents")
public class indentController{

    @Autowired
    private saveIndentService service;
    @PostMapping("/saveIndent")
    public ResponseEntity<?> createSubscribedIndent(@RequestBody SubscribedIndents indent) {
        return service.saveSubscribedIndent(indent);
    }

}
