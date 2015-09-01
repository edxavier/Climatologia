package info.cafenica.climatologia.db;

import java.util.Date;

/**
 * Created by Eder Xavier Rojas on 31/08/2015.
 */
public class Utilidades {
    public static int dateToInt(Date fecha) {
        int res =(int) (fecha.getTime()/1000);
        return res;
    }
    public static Date dateFromInt(int fechaInt) {
        long lf = (long)fechaInt*1000L;
        return new Date(lf) ;
    }
}
