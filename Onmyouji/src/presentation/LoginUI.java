package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import PO.account;
import bussinessLogicService.LoginService;
import rmi.RemoteHelper;

public class LoginUI extends JFrame {
	private JPanel pale = new JPanel();
	// ��¼�ı�������
	private JTextField id = new JTextField();
	private JPasswordField password = new JPasswordField();

	private static final long serialVersionUID = -6315342820768576780L;
	private RemoteHelper remoteHelper;

	// ������
	public LoginUI() {

		this.setSize(400, 400);
		this.setLocation(300, 300);
		this.setTitle("��¼");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		LinkedUI linkui = new LinkedUI();

		// �������
		pale.setSize(400, 400);
		pale.setLocation(0, 0);
		pale.setVisible(true);
		pale.setLayout(null);
		this.add(pale);

		// ��¼��ť
		JButton login = new JButton("��¼");
		login.setSize(100, 50);
		login.setLocation(80, 200);
		pale.add(login);
		login.setVisible(true);
		login.addActionListener(new LoginButtonActionListener());

		// ע�ᰴť
		JButton register = new JButton("ע��");
		register.setSize(100, 50);
		register.setLocation(220, 200);
		pale.add(register);
		register.setVisible(true);
		register.addActionListener(new RegisterButtonActionListener());

		// �ı���
		id.setSize(200, 30);
		id.setLocation(100, 80);
		id.setVisible(true);
		pale.add(id);

		password.setSize(200, 30);
		password.setLocation(100, 130);
		password.setVisible(true);
		pale.add(password);

		// ��ǩ
		JLabel idLabel = new JLabel("��¼��");
		idLabel.setSize(100, 30);
		idLabel.setLocation(20, 80);
		idLabel.setVisible(true);
		JLabel passwordLabel = new JLabel("����");
		passwordLabel.setSize(100, 30);
		passwordLabel.setLocation(20, 130);
		passwordLabel.setVisible(true);
		pale.add(idLabel);
		pale.add(passwordLabel);

		this.repaint();

	}

	class LoginButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			account accountpo = null;
			LoginService loginService=RemoteHelper.getInstance().getLoginService();
			accountpo=loginService.login(id.getText(),password.getPassword().toString());
			if (accountpo == null) {
				WarningDialog warning =new WarningDialog("�û������������");
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
			dd.showDialog(null);
		}

	}

}
