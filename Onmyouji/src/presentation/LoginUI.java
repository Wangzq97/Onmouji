package presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.*;

import PO.account;
import bussinessLogicService.LoginService;
import rmi.RemoteHelper;

public class LoginUI extends JFrame {
	// 登录文本与密码
	private JTextField id = new JTextField();
	private JPasswordField password = new JPasswordField();
	private JPanel imagePanel;
	private Icon background;
	private static final long serialVersionUID = -6315342820768576780L;
	private RemoteHelper remoteHelper;

	// 主窗口
	public LoginUI() {




		LinkedUI linkui = new LinkedUI();

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			// 运行时指定LookAndFeel，需要SwingUtilities.updateComponentTreeUI（Component
			// c)实现动态的更新
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		java.net.URL imgURL = this.getClass().getResource("/pictures/timg.jpg");
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

		// 面板容器
		this.setTitle("Onmyouji");                              //窗体标题显示
		this.setSize(960, 540);                  //窗体的大小
		this.setLocation(480,270);
		this.setLayout(null);

		this.setVisible(true);

		// 登录按钮
		MyButton login = new MyButton("pictures\\mybutton1_1.jpg","pictures\\mybutton1_2.jpg","pictures\\mybutton1_3.jpg","登录");
		login.setOpaque(false);
		login.setHorizontalTextPosition(SwingConstants.CENTER);
		login.setSize(128, 49);
		login.setLocation(272, 300);
		this.add(login);
		login.setVisible(true);
		login.addActionListener(new LoginButtonActionListener());

		// 注册按钮
		MyButton register = new MyButton("pictures\\mybutton1_1.jpg","pictures\\mybutton1_2.jpg","pictures\\mybutton1_3.jpg","注册");
		register.setHorizontalTextPosition(SwingConstants.CENTER);
		register.setSize(128, 49);
		register.setLocation(560, 300);
		this.add(register);
		register.setVisible(true);
		register.addActionListener(new RegisterButtonActionListener());

		// 文本框
		id.setText("你的账号");
		id.setForeground(Color.GRAY);
		id.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				if(id.getForeground()==Color.gray) {
					id.setForeground(Color.BLACK);
					id.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e)
			{

				if(id.getText().equals("")){
					id.setForeground(Color.GRAY);
					id.setText("你的账号");
				}


			}
		});
		id.setSize(200, 30);
		id.setLocation(380, 180);
		id.setVisible(true);
		this.add(id);

		password.setText("你的密码");
		password.setEchoChar((char)(0));
		password.setForeground(Color.GRAY);
		password.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				if(password.getForeground()==Color.gray) {
					password.setEchoChar('*');
					password.setForeground(Color.BLACK);
					password.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e)
			{

				if(password.getText().equals("")){
					password.setEchoChar((char)(0));
					password.setForeground(Color.GRAY);
					password.setText("你的密码");
				}


			}
		});
		password.setSize(200, 30);
		password.setLocation(380, 230);
		password.setVisible(true);
		this.add(password);


		this.repaint();

	}

	class LoginButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			account accountpo = null;
			LoginService loginService=RemoteHelper.getInstance().getLoginService();
			accountpo=loginService.login(id.getText(),new String(password.getPassword()));

			if (accountpo == null) {
				WarningDialog warning =new WarningDialog("用户名或密码错误");
			} else {
				setVisible(false);
				ui ui=new ui();
			}
		}

	}

	class RegisterButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			RegisterDialog dd = new RegisterDialog();
		}

	}

}
