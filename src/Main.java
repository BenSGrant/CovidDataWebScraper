import java.awt.AWTException;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

	static String[] elementNames = {"Total Cases:", "Deaths:", "Recovered:"};
	
	public static void main(String[] args) {
		System.out.println("Starting Scraper...");
		try {
			System.out.println("Connecting to website");
			Document doc = Jsoup.connect("https://www.worldometers.info/coronavirus/country/uk/").userAgent("mozilla/17.0").get(); 
			/* the userAgent part fakes that we are using mozilla 17.0 
				because most websites would be able to tell if something 
				was a scraper or a normal web surfing person
			*/
			
			System.out.println("Retreiving all data in class maincounter-number\n\n\n");
			Elements stats = doc.select("div.maincounter-number");
			Elements temp = doc.select("div[style]");
			
			String lastUpdated = "";
			for(Element data: temp) {
				lastUpdated = data.getElementsByTag("div").first().text();
				if(lastUpdated.contains("Last updated: ")) {
					break;
				}
			}
			
			String cases = "";
			String deaths = "";
			String recovered = "";
			int i = 0;
			for(Element data: stats) {
				System.out.println(elementNames[i] + " " + data.getElementsByTag("span").first().text());
				if(i == 0) {
					cases = data.getElementsByTag("span").first().text();
				} else if (i == 1) {
					deaths = data.getElementsByTag("span").first().text();
				} else if (i == 2) {
					recovered = data.getElementsByTag("span").first().text();
				}
				i++;
			}
			
			String[] data = {cases, deaths, recovered};
			lastUpdated = lastUpdated.replace("Last updated: ", "As of ");
			NotifService.displayNotif(data, lastUpdated);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (AWTException e) {
			System.out.println("Error displaying Notification");
			e.printStackTrace();
		}
		System.exit(0);
	}
	
}
