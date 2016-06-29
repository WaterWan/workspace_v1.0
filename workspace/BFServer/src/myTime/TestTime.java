package myTime;

import java.util.Calendar;

public class TestTime {
	 // 获得当前日期
    public static String getDate() {
        Calendar cal = Calendar.getInstance();
        return getDate(cal);
    }

    // 获得日期
    private static String getDate(Calendar cal) {
        String v_strDate = "";
        int v_intYear = cal.get(Calendar.YEAR);
        int v_intMonth = cal.get(Calendar.MONTH) + 1;
        int v_intDAY = cal.get(Calendar.DAY_OF_MONTH);
        int v_intHOUR = cal.get(Calendar.HOUR_OF_DAY);
        int v_intMINUTE = cal.get(Calendar.MINUTE);
        int v_intSECOND = cal.get(Calendar.SECOND);

        v_strDate = v_intYear + "";
        if (v_intMonth < 10) {
			v_strDate = v_strDate + "_0" + v_intMonth + "";
		}else {
			v_strDate = v_strDate + "_" + v_intMonth + "";
		}
        if (v_intDAY < 10) {
        	v_strDate = v_strDate + "_0" + v_intDAY + "";
		}else {
			v_strDate = v_strDate + "_" + v_intDAY + "";
		}
        v_strDate = v_strDate + "_";
        if (v_intHOUR < 10) {
            v_strDate = v_strDate + "0" + v_intHOUR + "_";
        }else {
            v_strDate = v_strDate + v_intHOUR + "_";
        }
        if (v_intMINUTE < 10) {
            v_strDate = v_strDate + "0" + v_intMINUTE + "_";
        } else {
            v_strDate = v_strDate + v_intMINUTE + "_";
        }
        if (v_intSECOND < 10) {
            v_strDate = v_strDate + "0" + v_intSECOND;
        } else {
            v_strDate = v_strDate + v_intSECOND;
        }
        cal = null;
        return v_strDate;
    }
}
