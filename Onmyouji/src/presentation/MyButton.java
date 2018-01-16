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
        java.net.URL iconURL = this.getClass().getResource(icon);
        java.net.URL rollOverURL = this.getClass().getResource(rollOverIcon);
        java.net.URL pressedURL = this.getClass().getResource(pressedIcon);
        setMargin(new Insets(0, 0, 0, 0));//设置边距
        setContentAreaFilled(false);//不绘制按钮区域
        setBorderPainted(false);//不绘制边框
        setIcon(new ImageIcon(iconURL));//设置默认图片
        setRolloverIcon(new ImageIcon(rollOverURL));//设置鼠标经过图片
        setPressedIcon(new ImageIcon(pressedURL));//设置鼠标按下图片
        this.setText(text);
    }
}