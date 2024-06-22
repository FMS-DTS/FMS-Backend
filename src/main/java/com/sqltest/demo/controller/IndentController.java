package com.sqltest.demo.controller;

import com.sqltest.demo.service.FixedIndentService;
import com.sqltest.demo.model.SubscribedIndents;
import com.sqltest.demo.service.saveIndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/indents")
public class IndentController{

    @Autowired
    private saveIndentService service;

    @PostMapping("/saveIndent")
    public ResponseEntity<?> createSubscribedIndent(@RequestBody SubscribedIndents indent) {
        return service.saveSubscribedIndent(indent);
    }

    @Autowired
    private FixedIndentService fixedIndentService;
    @GetMapping("/newIndents")
    public List<Map<String, Object>> getNewIndents() {
        return fixedIndentService.getFixedIndents();
    }


}