
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	// components
	private JLabel nameLabel;
	private TextField nameText;
	private JLabel passwordLabel;
	private TextField passwordText;
	private JButton okButton;
	private JButton cancelButton;
	private JCheckBox saveAccount;
	private JPanel panel1;
	private JPanel panel2;
	private WindowHandler windowHandler;
	// associations

	public LoginDialog(ActionListener actionHandler) {
		// attributes


		this.windowHandler = new WindowHandler();
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setBackground(Color.green);
		// components
		this.addWindowListener(windowHandler);
		this.setLayout(new FlowLayout());

		panel1 = new JPanel();
		nameLabel = new JLabel("ID");
		panel1.add(nameLabel);
		nameText = new TextField(15);
		panel1.add(nameText);
		this.add(panel1);

		panel2 = new JPanel();
		passwordLabel = new JLabel("PW");
		panel2.add(passwordLabel);
		passwordText = new TextField(15);
		passwordText.setEchoChar('*');
		panel2.add(passwordText);
		this.add(panel2);

		JPanel panel3 = new JPanel();

		this.saveAccount = new JCheckBox("계정 저장");
		this.saveAccount.setActionCommand("save");
		this.saveAccount.addActionListener(actionHandler);
		panel3.add(this.saveAccount);

		okButton = new JButton("OK");
		okButton.addActionListener(actionHandler);
		okButton.setActionCommand("OK");
		panel3.add(okButton);

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(actionHandler);
		cancelButton.setActionCommand("Cancel");
		panel3.add(cancelButton);

		this.add(panel3);

		this.setVisible(true);
	}

	public void setAccount(ArrayList<String> account) {
		this.saveAccount.setSelected(true);
		this.nameText.setText(account.get(0));
		this.passwordText.setText(account.get(1));
		this.panel1.updateUI();
		this.panel2.updateUI();
	}

	public String getID() {
		return this.nameText.getText();
	}

	public boolean getSave() {
		System.out.println(this.saveAccount.isSelected());
		return this.saveAccount.isSelected();

	}

	public String getPW() {
		return this.passwordText.getText();
	}



	private class WindowHandler implements WindowListener {

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

	}

}