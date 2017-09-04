package presentation;

import PO.HERO;
import rmi.RemoteHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static sun.rmi.transport.TransportConstants.Call;

public class ui extends JFrame implements ActionListener {
    private JPanel imagePanel;
    private ImageIcon background;
    private JTextField info;
    
    public ui() {
        linkToServer();
        background = new ImageIcon("pictures\\background.jpeg");// 背景图片
        JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面  
        // 把标签的大小位置设置为图片刚好填充整个面板  
        label.setBounds(0, 0, background.getIconWidth(),
                background.getIconHeight());
        // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明  
        imagePanel = (JPanel) this.getContentPane();
        imagePanel.setOpaque(false);
        // 内容窗格默认的布局管理器为BorderLayout  
        imagePanel.setLayout(new FlowLayout());

        this.getLayeredPane().setLayout(null);
        // 把背景图片添加到分层窗格的最底层作为背景  
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(background.getIconWidth(), background.getIconHeight());
        this.setResizable(false);
        this.setVisible(true);

        ImageIcon icon=new ImageIcon("pictures\\icon.jpg"); //图标
        this.setIconImage(icon.getImage());

        this.setTitle("Onmyouji");                              //窗体标题显示
        this.setSize(960, 540);                  //窗体的大小
        this.setLocation(480,270);
        this.setLayout(null);

        info=new JTextField();
        info.setSize(480,270);
        info.setLocation(240,80);

        info.setHorizontalAlignment(SwingConstants.CENTER);
        info.setEditable(false);
        this.getContentPane().add(info);

        JButton getCommon=new JButton("破碎的符咒");
        getCommon.setSize(160,40);
        getCommon.setLocation(240,400);
        this.getContentPane().add(getCommon);
        getCommon.addActionListener(this);
        getCommon.setActionCommand("破碎的符咒");

        JButton getSpecial=new JButton("神秘的符咒");
        getSpecial.setSize(160,40);
        getSpecial.setLocation(560,400);
        this.getContentPane().add(getSpecial);
        getSpecial.addActionListener(this);
        getSpecial.setActionCommand("神秘的符咒");


        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("破碎的符咒")) {
            HERO hero =new HERO();
            try {
                hero=RemoteHelper.getInstance().getCallService().getCommon();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            info.setText(hero.getName()+" "+hero.getRarity());
        }

        if(e.getActionCommand().equals("神秘的符咒")){
            HERO hero =new HERO();
            try {
                hero=RemoteHelper.getInstance().getCallService().getSpecial();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            info.setText(hero.getName()+" "+hero.getRarity());

        }

    }

    private void linkToServer() {
        try {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            RemoteHelper remoteHelper = RemoteHelper.getInstance();
            remoteHelper.setRemote(Naming.lookup("rmi://106.14.201.23:8888/LogicRemoteObject"));

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (RemoteException e) {
            e.printStackTrace();

        } catch (NotBoundException e) {
            e.printStackTrace();

        }
    }
}
