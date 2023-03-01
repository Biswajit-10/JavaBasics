package keyword.engine;

import org.apache.poi.ss.usermodel.Sheet;

import utils.BaseFunction;
import utils.OpenBrowser;

public class KeyWordEngine2 extends BaseFunction {

	public static Sheet sheet;

	/*
	 * Read data on excel/csv file at once and store it in array for faster
	 * operation
	 * 
	 */

	public void start(String sheetName) {

		for (int i = 1; i < sheet.getLastRowNum(); i++) {
			String keyword = sheet.getRow(i).getCell(3).toString().trim();
			String data = null;
			String locatorType = null;
			String locatorValue = null;

			switch (keyword.toUpperCase()) {
			case "OPEN BROWSER":
				OpenBrowser.openBrowser(data);
				break;

			case "ENTER URL":
				OpenBrowser.openUrl(data);
				break;

			case "CLICK":
				click(findElement(locatorType, locatorValue));
				break;

			case "JSCLICK":
				jsClick(findElement(locatorType, locatorValue));
				break;

			case "SENDKEYS":
				sendKeys(findElement(locatorType, locatorValue), data);
				break;

			case "SENDKEYSWITHFIELDNAME":
				sendKeys(findElement(locatorType, locatorValue), data, data);
				break;

			case "VERIFY TITLE":

				break;

			case "VERIFY":

				break;

			case "QUIT":
				OpenBrowser.quitBrowser();
				break;

			default:
				break;
			}

		}
	}
}