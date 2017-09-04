package presentation;

import java.awt.Graphics;
import java.awt.Image;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import rmi.RemoteHelper;

public class LinkedUI extends JDialog {
	private RemoteHelper remoteHelper;
	JLabel linkState = new JLabel("loading");
	JPanel myPanel = new MyPanel();
	
	LinkedUI() {
		this.setSize(300, 200);
		this.setLocation(350, 400);
		this.setTitle("³õÊ¼»¯ÍøÂç");
		this.setVisible(true);
		
		JPanel pale = new JPanel();
		pale.setSize(300, 200);
		pale.setLocation(0, 0);
		pale.setVisible(true);
		pale.setLayout(null);
		this.add(pale);
		
		linkState.setBounds(120, 60, 200, 30);
		linkState.setVisible(true);
		pale.add(linkState);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myPanel.setBounds(0, 0, 0, 0);
		getContentPane().add(myPanel);
		setVisible(true);

		linkToServer();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(linkState.getText().equals("Link failed.")){
			System.exit(0);
		}
		this.dispose();
	}

	class MyPanel extends JPanel {
		public MyPanel() {

		}

		public void paint(Graphics g) {
			super.paint(g);
			ImageIcon gif = new ImageIcon("Pictures\\loading.gif");
			Image gifs = gif.getImage();
			
			g.drawImage(gifs, 110,30,this);
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
			remoteHelper = RemoteHelper.getInstance();
			remoteHelper.setRemote(Naming.lookup("rmi://106.14.201.23:8888/LogicRemoteObject"));
			linkState.setBounds(90, 60, 200, 30);
			linkState.setText("Linked successfully.");
			myPanel.setVisible(false);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			linkState.setBounds(110, 60, 200, 30);
			linkState.setText("Link failed.");
			myPanel.setVisible(false);

		} catch (RemoteException e) {
			e.printStackTrace();
			linkState.setBounds(110, 60, 200, 30);
			linkState.setText("Link failed.");
			myPanel.setVisible(false);

		} catch (NotBoundException e) {
			e.printStackTrace();
			linkState.setBounds(110, 60, 200, 30);
			linkState.setText("Link failed.");
			myPanel.setVisible(false);

		}
	}

}
