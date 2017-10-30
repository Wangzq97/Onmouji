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

    public String getId(){
        return id;
    }

    public int getLevel(){
        return level;
    }

    public int getSpecialCharge(){
        return specialCharge;
    }

    public int getCommonCharge(){
        return commonCharge;
    }

    public int getExistDay(){
        return commonCharge;
    }

    public Date getLastLoginDate(){
        return lastLoginDate;
    }

    public void setId(String newId){
        this.id=newId;
    }

    public void setLevel(int newLevel){
        this.level=newLevel;
    }

    public void setSpecialCharge(int newSpecialChange){
        this.specialCharge=newSpecialChange;
    }

    public void setCommonCharge(int newCommonChange){
        this.commonCharge=newCommonChange;
    }

    public void setExistDay(int newExistDay){
        this.existDay=newExistDay;
    }

    public void setLastLoginDate(Date newDate){
        this.lastLoginDate=newDate;
    }


}
