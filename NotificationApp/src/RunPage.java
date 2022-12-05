import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RunPage {

	private String privousbsnspblanc = "";
	private String privousnotice = "";
	private boolean running = false;

	private String ID = "";
	private String PW = "";

	public RunPage(String ID, String PW) {
		this.ID = ID;
		this.PW = PW;
		this.privousnotice = noticeSearch();
		this.privousbsnspblanc = bsnspblancSearch();
		while (true) {

			this.runs();
			try {

				Thread.sleep(3600000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setRunning(boolean type) {
		this.running = type;
	}

	public boolean getRunning() {
		return this.running;
	}

	public void runs() {
		String notice = noticeSearch();
		String bsnspblanc = bsnspblancSearch();
		if (!(notice.equals(this.privousnotice))) {
			sendeMail("공지사항", ID, PW);
		}
		if (!(bsnspblanc.equals(this.privousbsnspblanc))) {
			sendeMail("사업공고", ID, PW);
		}
		this.privousnotice = notice;
		this.privousbsnspblanc = bsnspblanc;
	}

	public String bsnspblancSearch() {
		try {
			String URL = "https://www.aihub.or.kr/aihubnews/bsnspblanc/list.do?currMenu=133&topMenu=103";
			Connection conn = Jsoup.connect(URL);
			Document document = conn.get();
			Elements parsingDivs = document.getElementsByClass("list");
			Element parsingDiv = parsingDivs.get(0);
			Elements parsingTitle = parsingDiv.getElementsByTag("td");
			String target = parsingTitle.get(0).toString();
			String filter1 = target.replaceAll("<td>", "");
			String filter2 = filter1.replaceAll("</td>", "");
			String filter3 = filter2.replaceAll(" ", "");
			String value = filter3;
			return value;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public String noticeSearch() {
		try {
			String URL = "https://www.aihub.or.kr/aihubnews/notice/list.do?currMenu=132&topMenu=103";
			Connection conn = Jsoup.connect(URL);
			Document document = conn.get();
			Elements parsingDivs = document.getElementsByClass("list");
			Element parsingDiv = parsingDivs.get(0);
			Elements top = parsingDiv.getElementsByClass("top");
			Elements table = parsingDiv.getElementsByTag("tr");

			int topsize = top.size();
			Element tag = table.get(topsize + 1);
			Elements seperateTag = tag.getElementsByTag("td");
			String target = seperateTag.get(0).toString();
			String filter1 = target.replaceAll("<td>", "");
			String filter2 = filter1.replaceAll("</td>", "");
			String filter3 = filter2.replaceAll(" ", "");
			String value = filter3;

			return value;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public void sendeMail(String type, String ID, String PW) {
		// TODO Auto-generated method stub
		Mail mail = new Mail(type, ID, PW);
	}
}
