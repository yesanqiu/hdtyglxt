package com.example.hdtyglxt.service.impl;

import com.example.hdtyglxt.entity.Contest;
import com.example.hdtyglxt.mapper.ContestMapper;
import com.example.hdtyglxt.service.ContestService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ContestServiceImpl implements ContestService {

    @Autowired
    private ContestMapper contestMapper;

    @Override
    public List<Contest> getContest() {
        return contestMapper.selectAll();
    }

    @Override
    public List<Contest> getContest(String cName) {
        if (StringUtils.isBlank(cName)) {
            return getContest();
        }
        Contest contest = new Contest();
        contest.setCName(cName);
        return contestMapper.select(contest);
    }

    @Override
    public void addContest(Contest contest) {
        contestMapper.insert(contest);
    }

    @Override
    public void deleteContest(Long cId) {
        contestMapper.deleteByPrimaryKey(cId);
    }
}
