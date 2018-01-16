package presentation;

import PO.HERO;
import PO.account;
import PO.callRecord;
import rmi.RemoteHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import static sun.rmi.transport.TransportConstants.Call;

public class ui extends JFrame implements ActionListener {
    private JPanel imagePanel;
    private Icon background;
    private JTextArea info;
    private account accountpo;
    JLabel commonChanges;
    JLabel specialChanges;

    public ui(account accountpo) {
        this.accountpo=accountpo;
        java.net.URL imgURL = this.getClass().getResource("/pictures/background.jpeg");
        background = new ImageIcon(imgURL);// 背景图片
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

        java.net.URL iconURL = this.getClass().getResource("/pictures/icon.jpg");
        ImageIcon icon=new ImageIcon(iconURL); //图标
        this.setIconImage(icon.getImage());

        this.setTitle("Onmyouji");                              //窗体标题显示
        this.setSize(960, 540);                  //窗体的大小
        this.setLocation(480,270);
        this.setLayout(null);

        info=new JTextArea();
        info.setSize(480,270);
        info.setLocation(240,80);

        info.setLineWrap(true);
        info.setEditable(false);
        info.setAlignmentX((float)0.5);
        JScrollPane JSP=new JScrollPane(info);
        JSP.setBounds(240,80,480,270);
        this.add(JSP);

        JButton getCommon=new JButton("破碎的符咒");
        getCommon.setSize(160,40);
        getCommon.setLocation(240,400);
        this.add(getCommon);
        getCommon.addActionListener(this);
        getCommon.setActionCommand("破碎的符咒");

        commonChanges=new JLabel("剩余次数："+accountpo.getCommonCharge(),JLabel.CENTER);
        commonChanges.setForeground(Color.white);
        commonChanges.setSize(160,40);
        commonChanges.setLocation(240,440);
        this.add(commonChanges);

        JButton callHistory=new JButton("查看历史");
        callHistory.setSize(160,40);
        callHistory.setLocation(0,0);
        this.add(callHistory);
        callHistory.addActionListener(this);
        callHistory.setActionCommand("查看历史");



        JButton getSpecial=new JButton("神秘的符咒");
        getSpecial.setSize(160,40);
        getSpecial.setLocation(560,400);
        this.getContentPane().add(getSpecial);
        getSpecial.addActionListener(this);
        getSpecial.setActionCommand("神秘的符咒");

        specialChanges=new JLabel("剩余次数："+accountpo.getSpecialCharge(),JLabel.CENTER);
        specialChanges.setForeground(Color.white);
        specialChanges.setSize(160,40);
        specialChanges.setLocation(560,440);
        this.add(specialChanges);


        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("破碎的符咒")) {
            if(accountpo.getCommonCharge()<=0){
                info.setText("剩余次数不足！");
                return;
            }
            HERO hero =new HERO();
            try {
                hero=RemoteHelper.getInstance().getCallService().getCommon(accountpo.getId());
                accountpo.setCommonCharge(accountpo.getCommonCharge()-1);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            update();
            info.setText(hero.getName()+" "+hero.getRarity());
        }

        if(e.getActionCommand().equals("神秘的符咒")){
            if(accountpo.getSpecialCharge()<=0){
                info.setText("剩余次数不足！");
                return;
            }
            HERO hero =new HERO();
            try {
                hero=RemoteHelper.getInstance().getCallService().getSpecial(accountpo.getId());
                accountpo.setSpecialCharge(accountpo.getSpecialCharge()-1);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            update();
            info.setText(hero.getName()+" "+hero.getRarity());

        }

        if(e.getActionCommand().equals("查看历史")){
            ArrayList<callRecord> list = new ArrayList<callRecord>();
            String test="";

            try {
                list=RemoteHelper.getInstance().getCallService().checkHistory(accountpo.getId());
                System.out.println(list.size());
                for(int i=0;i<list.size();i++){
                    test=test+list.get(i).getTime()+" "+list.get(i).getRarity()+" "+list.get(i).getHeroName()+"\n";
                    System.out.println("test");
                }

                info.setText(test);

            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        }

    }


    public void update(){
        commonChanges.setText("剩余次数："+accountpo.getCommonCharge());
        specialChanges.setText("剩余次数："+accountpo.getSpecialCharge());
    }


   /* private void linkToServer() {
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
    }*/
}
