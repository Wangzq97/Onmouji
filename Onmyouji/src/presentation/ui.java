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
        background = new ImageIcon("pictures\\background.jpeg");// ����ͼƬ
        JLabel label = new JLabel(background);// �ѱ���ͼƬ��ʾ��һ����ǩ����  
        // �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������  
        label.setBounds(0, 0, background.getIconWidth(),
                background.getIconHeight());
        // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��  
        imagePanel = (JPanel) this.getContentPane();
        imagePanel.setOpaque(false);
        // ���ݴ���Ĭ�ϵĲ��ֹ�����ΪBorderLayout  
        imagePanel.setLayout(new FlowLayout());

        this.getLayeredPane().setLayout(null);
        // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����  
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(background.getIconWidth(), background.getIconHeight());
        this.setResizable(false);
        this.setVisible(true);

        ImageIcon icon=new ImageIcon("pictures\\icon.jpg"); //ͼ��
        this.setIconImage(icon.getImage());

        this.setTitle("Onmyouji");                              //���������ʾ
        this.setSize(960, 540);                  //����Ĵ�С
        this.setLocation(480,270);
        this.setLayout(null);

        info=new JTextField();
        info.setSize(480,270);
        info.setLocation(240,80);

        info.setHorizontalAlignment(SwingConstants.CENTER);
        info.setEditable(false);
        this.getContentPane().add(info);

        JButton getCommon=new JButton("����ķ���");
        getCommon.setSize(160,40);
        getCommon.setLocation(240,400);
        this.getContentPane().add(getCommon);
        getCommon.addActionListener(this);
        getCommon.setActionCommand("����ķ���");

        JButton getSpecial=new JButton("���صķ���");
        getSpecial.setSize(160,40);
        getSpecial.setLocation(560,400);
        this.getContentPane().add(getSpecial);
        getSpecial.addActionListener(this);
        getSpecial.setActionCommand("���صķ���");


        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("����ķ���")) {
            HERO hero =new HERO();
            try {
                hero=RemoteHelper.getInstance().getCallService().getCommon();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            info.setText(hero.getName()+" "+hero.getRarity());
        }

        if(e.getActionCommand().equals("���صķ���")){
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
