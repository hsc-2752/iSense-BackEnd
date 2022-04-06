import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        long timeMill = System.currentTimeMillis();
        System.out.println(timeMill);
        System.out.println(dateFormat.format(timeMill));
        timeMill = timeMill-5*1000;
        System.out.println(timeMill);
        String format = dateFormat.format(timeMill);


    }
}
