package PO;

import java.io.Serializable;

/*式神*/
public class HERO implements Serializable {
    private String rarity;      //稀有度
    private String name;
    private int level;

    private static final long serialVersionUID = 1L;

    public String getRarity(){
        return rarity;
    }

    public void setRarity(String newRarity){
        this.rarity=newRarity;
    }

    public String getName(){
        return name;
    }

    public void setName(String newName){
        this.name=newName;
    }

    public int getLevel(){ return level;}

    public void setLevel(int newLevel){ this.level=newLevel;}
}
