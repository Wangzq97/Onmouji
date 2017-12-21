package PO;

import java.io.Serializable;
import java.util.Date;

public class callRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idcallStorage;
    private String userName;
    private String rarity;
    private String heroName;
    private Date time;

    public int getIdcallStorage(){
        return idcallStorage;
    }

    public void setIdcallStorage(int newid){
        idcallStorage=newid;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String NewuserName){
        userName=NewuserName;
    }

    public String getRarity(){
        return rarity;
    }

    public void setRarity(String Newrarity){
        rarity=Newrarity;
    }

    public String getHeroName(){
        return heroName;
    }

    public void setHeroName(String NewheroName){
        heroName=NewheroName;
    }

    public Date getTime(){
        return time;
    }

    public void setTime(Date Newtime){
        time=Newtime;
    }


}
