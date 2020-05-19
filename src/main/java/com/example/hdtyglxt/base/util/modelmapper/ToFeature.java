package com.example.hdtyglxt.base.util.modelmapper;

import com.chuangshu.livable.entity.Feature;
import com.chuangshu.livable.service.FeatureService;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: wws
 * @Date: 2019-11-14 15:37
 */
@Component
public class ToFeature {

    @Autowired
    FeatureService featureService;

    public Converter<Integer, Feature> getToFeature() {
        Converter<Integer, Feature> toFeature = new AbstractConverter<Integer, Feature>() {
            @Override
            protected Feature convert(Integer source) {
                Feature feature = new Feature();
                try {
                    feature = featureService.get(source);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return feature;
            }
        };
        return toFeature;
    }
}
