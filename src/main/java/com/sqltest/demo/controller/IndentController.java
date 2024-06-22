package com.sqltest.demo.controller;

import com.sqltest.demo.service.FixedIndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/indents")
public class IndentController {

    @Autowired
    private FixedIndentService fixedIndentService;

    @GetMapping("/newIndents")
    public List<Map<String, Object>> getNewIndents() {
        return fixedIndentService.getFixedIndents();
    }
}
