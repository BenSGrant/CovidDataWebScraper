import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

public class NotifService {
	public static void displayNotif(String[] info, String lastUpdated) throws AWTException {
		SystemTray sysTray = SystemTray.getSystemTray();
		Image image = Toolkit.getDefaultToolkit().createImage("res/icon.png");
		TrayIcon trayIcon = new TrayIcon(image, "Coronavirus UK stats");
		trayIcon.setImageAutoSize(true);
		trayIcon.setToolTip("System tray icon for COVID-19");
		sysTray.add(trayIcon);
		trayIcon.displayMessage("UK COVID-19 Info", lastUpdated + "\nTotal Cases: " + info[0] + "\nDeaths: " + info[1] + "\nRecovered: " + info[2], MessageType.INFO);
	}
}