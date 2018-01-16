package presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.rmi.RemoteException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.*;

import bussinessLogicService.LoginService;
import rmi.RemoteHelper;

public class RegisterDialog extends JDialog{
	private JLabel label1 = new JLabel();
	private JLabel idwarninglabel = new JLabel();
	private JLabel pwdwarninglabel = new JLabel();
	private JLabel successlabel = new JLabel();
	private JTextField idField = new JTextField("Your ID", 16);
	private JPasswordField pwdField = new JPasswordField(16);
	private JPasswordField pwdField2 = new JPasswordField(16);
	private JButton handInButton = new JButton("现在注册");
	private JPanel imagePanel;
	private Icon background;

	public RegisterDialog() {

		java.net.URL imgURL = this.getClass().getResource("/pictures/register.jpg");
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



		this.setSize(new Dimension(672, 378));
		this.setLocation(624, 352);


		label1.setText("欢迎您!");
		label1.setBounds(310, 6, 200, 30);
		idwarninglabel.setText("对不起，但该用户名已存在!");
		idwarninglabel.setForeground(Color.RED);
		idwarninglabel.setBounds(410, 66, 200, 30);
		idwarninglabel.setVisible(false);

		pwdwarninglabel.setText("对不起，两次密码不相同!");
		pwdwarninglabel.setForeground(Color.RED);
		pwdwarninglabel.setBounds(410, 126, 200, 30);
		pwdwarninglabel.setVisible(false);

		successlabel.setText("注册成功!");
		successlabel.setForeground(Color.BLUE);
		successlabel.setBounds(280, 210, 200, 30);
		successlabel.setVisible(false);


		idField.setBounds(280, 66, 100, 30);
		pwdField.setBounds(280, 126, 100, 30);
		pwdField2.setBounds(280, 186, 100, 30);

		MyButton handIn = new MyButton("/pictures/mybutton1_1.jpg","/pictures/mybutton1_2.jpg","/pictures/mybutton1_3.jpg","现在注册");
		handIn.setOpaque(false);
		handIn.setHorizontalTextPosition(SwingConstants.CENTER);
		handIn.setSize(128, 49);
		handIn.setLocation(270, 260);
		this.add(handIn);
		handIn.setVisible(true);
		handIn.addActionListener(new handInButtonActionListener());

		//handInButton.addActionListener(new regActionListener());

		this.setTitle("现在注册!");
		this.setLayout(null);
		this.add(label1);
		this.add(idwarninglabel);
		this.add(pwdwarninglabel);
		this.add(successlabel);
		this.add(handInButton);
		this.setFocusable(true);

		//this.pack();


		// 文本框
		idField.setText("你的账号");
		idField.setForeground(Color.GRAY);
		idField.setVisible(true);
		idField.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				if(idField.getForeground()==Color.gray) {
					idField.setForeground(Color.BLACK);
					idField.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e)
			{

				if(idField.getText().equals("")){
					idField.setForeground(Color.GRAY);
					idField.setText("你的账号");
				}

			}
		});
		this.add(idField);


		pwdField.setText("你的密码");
		pwdField.setEchoChar((char)(0));
		pwdField.setForeground(Color.GRAY);
		pwdField.setVisible(true);
		pwdField.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				if(pwdField.getForeground()==Color.gray) {
					pwdField.setEchoChar('*');
					pwdField.setForeground(Color.BLACK);
					pwdField.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e)
			{

				if(pwdField.getText().equals("")){
					pwdField.setEchoChar((char)(0));
					pwdField.setForeground(Color.GRAY);
					pwdField.setText("你的密码");
				}


			}
		});
		this.add(pwdField);

		pwdField2.setText("请重复输入密码");
		pwdField2.setEchoChar((char)(0));
		pwdField2.setForeground(Color.GRAY);
		pwdField2.setVisible(true);
		pwdField2.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				if(pwdField2.getForeground()==Color.gray) {
					pwdField2.setEchoChar('*');
					pwdField2.setForeground(Color.BLACK);
					pwdField2.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e)
			{

				if(pwdField2.getText().equals("")){
					pwdField2.setEchoChar((char)(0));
					pwdField2.setForeground(Color.GRAY);
					pwdField2.setText("请重复输入密码");
				}


			}
		});
		this.add(pwdField2);



		this.setModal(true);
		this.setVisible(true);

	}


	private class handInButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			idwarninglabel.setVisible(false);
			pwdwarninglabel.setVisible(false);

			if(!pwdField.getText().equals(pwdField2.getText())){
				pwdwarninglabel.setVisible(true);
				return;
			}
			if(RemoteHelper.getInstance().getLoginService().isExist(idField.getText())){
				idwarninglabel.setVisible(true);
				return;
			}
			if(RemoteHelper.getInstance().getLoginService().register(idField.getText(),new String(pwdField.getPassword()))){
				successlabel.setVisible(true);
				new WarningDialog("注册成功!");
				dispose();
			}else{
				System.out.println("出现未知错误");
			}
		}
	}
}


