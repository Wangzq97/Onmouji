package presentation;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ziqi on 2017/9/19.
 */
class MyButton extends JButton {
    private Font font;
    private String str;

    public MyButton(String icon,String rollOverIcon,String pressedIcon,String text) {
        super();
        setMargin(new Insets(0, 0, 0, 0));//设置边距
        setContentAreaFilled(false);//不绘制按钮区域
        setBorderPainted(false);//不绘制边框
        setIcon(new ImageIcon(icon));//设置默认图片
        setRolloverIcon(new ImageIcon(rollOverIcon));//设置鼠标经过图片
        setPressedIcon(new ImageIcon(pressedIcon));//设置鼠标按下图片
        this.setText(text);
    }
}