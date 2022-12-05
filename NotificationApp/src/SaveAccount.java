import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveAccount {

	private String ID = "";
	private String PW = "";

	public SaveAccount() {

	}

	public void setSave(String ID, String PW) {
		this.ID = ID;
		this.PW = PW;
	}

	public String getID() {
		return this.ID;
	}

	public String getPW() {
		return this.PW;
	}

	public boolean checkFile() {
		File file = new File("save.txt");
		if (file.exists()) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<String> getSave() {
		ArrayList<String> account = new ArrayList<String>();
		File file = new File("save.txt");
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNext()) {
				account.add(scan.next());
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
	}

	public void save() {

		File file = new File("save.txt");
		FileWriter writer = null;

		try {
			// 기존 파일의 내용에 이어서 쓰려면 true를, 기존 내용을 없애고 새로 쓰려면 false를 지정한다.
			writer = new FileWriter(file, false);
			writer.write(ID + "\n" + PW);
			writer.flush();

			System.out.println("DONE");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
