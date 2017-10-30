package presentation;

import java.awt.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.*;

import rmi.RemoteHelper;

public class LinkedUI extends JDialog {
	private RemoteHelper remoteHelper;
	private JPanel imagePanel;
	private Icon background;
	JLabel linkState = new JLabel("连接中......");

	LinkedUI() {
		this.setTitle("Onmyouji");                              //窗体标题显示
		this.setSize(960, 540);                  //窗体的大小
		this.setLocation(480,270);
		linkState.setBounds(450,200,300,30);
		this.add(linkState);
		repaint();


		java.net.URL imgURL = this.getClass().getResource("/pictures/timg (1).jpg");
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

		this.setLayout(null);
		this.setVisible(true);

		linkToServer();


		this.dispose();
	}


	private void linkToServer() {
		try {


			remoteHelper = RemoteHelper.getInstance();
			remoteHelper.setRemote(Naming.lookup("rmi://106.14.201.23:8888/LogicRemoteObject"));

		} catch (MalformedURLException e) {
			e.printStackTrace();
			linkState.setBounds(350,200,300,30);
			linkState.setText("无法连接到服务器，请检查网络连接");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(0);

		} catch (RemoteException e) {
			e.printStackTrace();
			linkState.setBounds(350,200,300,30);
			linkState.setText("无法连接到服务器，请检查网络连接");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(0);

		} catch (NotBoundException e) {
			e.printStackTrace();
			linkState.setBounds(350,200,300,30);
			linkState.setText("无法连接到服务器，请检查网络连接");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(0);

		}
	}

}
