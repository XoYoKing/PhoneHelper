package com.jrh.project.phonehelper.phonebook.config;

import com.jrh.project.phonehelper.phonebook.models.PhoneBookOpration;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by JiaRH on 2015/12/29.17:36
 */
public class PhoneBookConfig {



    /**
     * 获取所有的操作
     * @return
     */
    public static List<PhoneBookOpration> getOprarions() {
        List<PhoneBookOpration> oprarions = new ArrayList<>();
        for (PhoneBookOpration pbo : PhoneBookOpration.values()) {

            oprarions.add(pbo);
        }

        return oprarions;
    }
}
