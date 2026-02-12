package datetimedemo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class LocalDataTimeTest {
    public static void main(String[] args) {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        LocalTime t = LocalTime.now();
        System.out.println(t);

        LocalDate d = LocalDate.now();
        System.out.println(d);

        // 获取指定时间
        LocalDateTime time = LocalDateTime.of(2020, 1, 1, 12, 30, 30);
        System.out.println(time);

        // 获取指定时间
        LocalDateTime time2 = LocalDateTime.of(2020, Month.JANUARY, 1, 12, 30, 30);

        //传入标准格式
        LocalDateTime dt = LocalDateTime.parse("2019-11-19T15:16:17");
        LocalDate d1 = LocalDate.parse("2019-11-19");
        LocalTime t2 = LocalTime.parse("15:16:17");

    }
}
