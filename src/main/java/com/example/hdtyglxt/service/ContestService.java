package com.example.hdtyglxt.service;

import com.example.hdtyglxt.entity.Contest;

import java.util.List;

public interface ContestService {

    /**
     *
     * @return
     */
    public List<Contest> getContest();

    /**
     *
     * @param cName cName
     * @return  list
     */
    public List<Contest> getContest(final String cName);

    void addContest(Contest contest);

    void deleteContest(Long cId);
}
