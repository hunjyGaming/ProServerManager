package de.hunjy.utils;

import de.hunjy.PSM;

/*
    Create by hunjy on 29.09.2019
    @auther: hunjy
    @date: 29.09.2019
    @time: 20:56
    @projekt: ProServerManager
*/
public class PrefixBuilder {

    String retString;

    public PrefixBuilder(String prefix) {
        this.retString =   PSM.getInstance().replaceVar ((String) PSM.getInstance().getMainConfig().get("Prefix"), "%prefix%", prefix);
    }

    public String build()  {return this.retString; }

}
