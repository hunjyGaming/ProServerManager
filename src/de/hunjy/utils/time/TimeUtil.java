package de.hunjy.utils.time;
/*
    Create by RiedCrafter on 07.10.2019
    @author: RiedCrafter
    @date: 07.10.2019
    @time: 18:52
    @projekt: ProServerManager
*/

public class TimeUtil {

    private long time;

    public TimeUtil(long time) {
        this.time = time;
    }

    public String format() {
        long seconds = 0, minutes = 0, hours = 0;
        while(this.time != 0) {
            time--;
            seconds++;
            if(seconds >= 60) {
                seconds = 0;
                minutes++;
            }
            if(minutes >= 60) {
                minutes = 0;
                hours ++;
            }
        }

        StringBuilder builder = new StringBuilder();
        if(hours == 0) {
            builder.append(check(minutes)).append("§8:").append(check(seconds));
        } else {
            builder.append(check(hours)).append("§8:").append(check(minutes)).append("§8:").append(check(seconds));
        }
        return builder.toString();
    }

    private String check(long input) {
        return (input >= 10) ? ("" + input) : ("0" + input);
    }



}
