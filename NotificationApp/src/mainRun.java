import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class mainRun {

	private ActionHandler actionHandler;
	private LoginDialog login;
	private SaveAccount saveAccount;

	
	public mainRun() {
		this.actionHandler = new ActionHandler();
		this.saveAccount = new SaveAccount();

		login = new LoginDialog(this.actionHandler);
		if (this.saveAccount.checkFile()) {
			login.setAccount(this.saveAccount.getSave());
		}
		
	}
	
	public void login() {
		if (login.getSave()) {
			saveAccount.setSave(login.getID(), login.getPW());
			saveAccount.save();

			PopUp pop = new PopUp("시작되었습니다. 종료는 pc 종료시 자동 종료됩니다.");
			login.dispose();
			RunPage run = new RunPage(login.getID(), login.getPW());
			
		} else {
			
			PopUp pop = new PopUp("시작되었습니다. 종료는 pc 종료시 자동 종료됩니다.");
			login.dispose();
			RunPage run = new RunPage(login.getID(), login.getPW());
		
		}
	}

	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand().equals("Cancel")) {
				System.exit(0);
			} else if (e.getActionCommand().equals("OK")) {
				login();
			} else {

			}
		}

	}
	
}
