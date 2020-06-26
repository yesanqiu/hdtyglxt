package com.example.hdtyglxt.controller;


import com.example.hdtyglxt.entity.Contest;
import com.example.hdtyglxt.service.ContestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contest")
@Slf4j
@CrossOrigin
public class ContestController {

    @Autowired
    private ContestService contestService;

    @GetMapping("/getContestAll")
    @ResponseBody
    public HttpEntity<List<Contest>> getContestAll(final String cName) {
        return new HttpEntity<>(contestService.getContest(cName));
    }
    @GetMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteContest(final Long cId) {
        contestService.deleteContest(cId);
    }

    @PostMapping("/addContest")
    @ResponseStatus(HttpStatus.OK)
    public void addContest(@RequestBody Contest contest) {
        contestService.addContest(contest);
    }


}
