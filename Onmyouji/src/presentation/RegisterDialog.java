package presentation;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bussinessLogicService.LoginService;
import rmi.RemoteHelper;

public class RegisterDialog {
	private JLabel label1 = new JLabel();
	private JLabel labelid = new JLabel();
	private JLabel labelpwd = new JLabel();
	private JLabel labelpwd2 = new JLabel();
	private JDialog dialog = null;
	private JTextField idField = new JTextField("Your ID", 16);
	private JPasswordField pwdField = new JPasswordField(16);
	private JPasswordField pwdField2 = new JPasswordField(16);
	private JButton handInButton = new JButton("现在注册");

	public void showDialog(JFrame father) {

		label1.setText("欢迎您!");
		label1.setBounds(130, 6, 200, 20);
		labelid.setText("你的账号:");
		labelid.setBounds(80, 66, 100, 20);
		labelpwd.setText("密码:");
		labelpwd.setBounds(90, 126, 100, 20);
		labelpwd2.setText("确认密码:");
		labelpwd2.setBounds(80, 186, 200, 20);
		idField.setBounds(140, 66, 100, 20);
		pwdField.setBounds(140, 126, 100, 20);
		pwdField2.setBounds(140, 186, 100, 20);
		handInButton.setBounds(70, 260, 160, 30);
		handInButton.addActionListener(new regActionListener());

		dialog = new JDialog(father, true);
		dialog.setTitle("现在注册!");
		dialog.setLayout(null);
		dialog.add(label1);
		dialog.add(labelid);
		dialog.add(labelpwd);
		dialog.add(labelpwd2);

		dialog.add(idField);
		dialog.add(pwdField);
		dialog.add(pwdField2);

		dialog.add(handInButton);

		dialog.pack();
		dialog.setSize(new Dimension(300, 400));
		dialog.setLocation(525, 350);
		dialog.setVisible(true);

	}

	class regActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			registerSuccessDialog dd = new registerSuccessDialog();

			if (new String(pwdField.getPassword()).equals(new String(pwdField2.getPassword()))) {
				LoginService loginService = RemoteHelper.getInstance().getLoginService();
				boolean regsuc = loginService.isExist(idField.getText());

				if (!regsuc) {
					dd.showDialog(0);
				} else {
					dd.showDialog(1);
				}
			} else {
				dd.showDialog(2);
			}
		}

	}

	public class registerSuccessDialog {
		private JLabel label1 = new JLabel();
		private JButton OKButton = new JButton("OK");
		private JDialog dialog2 = new JDialog(dialog, true);

		public void showDialog(int i) {
			if (i == 0) {
				label1.setText("账号注册成功，请完善信息");
				label1.setBounds(120, 6, 200, 20);

				OKButton.setBounds(120, 60, 160, 30);
				OKButton.addActionListener(new SuccessActionListener());
				dialog2.add(label1);
				dialog2.add(OKButton);

				dialog2.setLayout(null);
				dialog2.pack();
				dialog2.setSize(new Dimension(400, 160));
				dialog2.setLocation(525, 300);
				dialog2.setVisible(true);
			} else {
				if (i == 1) {
					label1.setText("抱歉，该账号已存在");
					label1.setBounds(140, 6, 280, 20);

					OKButton.setBounds(120, 60, 160, 30);
					OKButton.addActionListener(new OKActionListener());
					dialog2.add(label1);
					dialog2.add(OKButton);

					dialog2.setLayout(null);
					dialog2.pack();
					dialog2.setSize(new Dimension(400, 160));
					dialog2.setLocation(525, 300);
					dialog2.setVisible(true);
				} else {
					label1.setText("抱歉，不过您输入的两次密码不一致");
					label1.setBounds(90, 6, 280, 20);

					OKButton.setBounds(120, 60, 160, 30);
					OKButton.addActionListener(new OKActionListener());
					dialog2.add(label1);
					dialog2.add(OKButton);

					dialog2.setLayout(null);
					dialog2.pack();
					dialog2.setSize(new Dimension(400, 160));
					dialog2.setLocation(525, 300);
					dialog2.setVisible(true);
				}
			}
		}

		class OKActionListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				dialog2.dispose();

			}

		}
		
		class SuccessActionListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dialog.dispose();
				dialog2.dispose();

			}

		}
	}

}
