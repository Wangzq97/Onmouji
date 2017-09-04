package presentation;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class WarningDialog extends JDialog{

	public WarningDialog(String content){
		this.setTitle(content);
		JLabel warning=new JLabel(content);
		warning.setBounds(100, 60, 300, 30);
		this.setBounds(350, 400, 300, 200);
		this.setLayout(null);
		this.add(warning);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
	}
}
