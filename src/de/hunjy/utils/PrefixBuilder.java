package de.hunjy.utils;

import de.hunjy.utils.random.RandomUtil;

/*
    Create by hunjy on 29.09.2019
    @auther: hunjy
    @date: 29.09.2019
    @time: 20:56
    @projekt: ProServerManager
*/
public class PrefixBuilder {

    private String retString = "§8│ %prefix% §8» §f";

    public PrefixBuilder(String prefix) {
        retString = retString.replaceAll("%prefix%", prefix);
    }

    public String build() {
        return retString;
    }

}
