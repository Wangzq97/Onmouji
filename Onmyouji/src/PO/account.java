package PO;

import java.io.Serializable;
import java.util.Date;

public class account implements Serializable {

    private String id;
    private int level;
    private int specialCharge;      //特殊抽符次数
    private int commonCharge;       //普通抽符次数
    private int existDay;           //登陆天数
    private Date lastLoginDate;      //上次登录日期

}
