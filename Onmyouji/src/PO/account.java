package PO;

import java.io.Serializable;
import java.util.Date;

public class account implements Serializable {

    private String id;
    private int level;
    private int specialCharge;      //����������
    private int commonCharge;       //��ͨ�������
    private int existDay;           //��½����
    private Date lastLoginDate;      //�ϴε�¼����

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
        return existDay;
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
