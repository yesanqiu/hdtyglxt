package com.example.hdtyglxt.base.util.modelmapper;

import com.chuangshu.livable.entity.Allocation;
import com.chuangshu.livable.service.AllocationService;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: wws
 * @Date: 2019-11-14 15:55
 */
@Component
public class ToAllocation {

    @Autowired
    AllocationService allocationService;

    public Converter<Integer, Allocation> getToAllocation() {
        Converter<Integer, Allocation> toAllocation = new AbstractConverter<Integer, Allocation>() {
            @Override
            protected Allocation convert(Integer source) {
                Allocation allocation = null;
                try {
                    allocation = allocationService.get(source);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return allocation;
            }
        };
        return toAllocation;
    }
}
