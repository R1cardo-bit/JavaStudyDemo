package datetimedemo;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateTimeTest {
    static void main() {
        //带时区的时间
        ZonedDateTime zdt = ZonedDateTime.now();
        System.out.println(zdt);
        ZonedDateTime zny = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println(zny);
    }
}
