package Core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  

public class Utils {
	static Logger logger = Logger.getLogger("devpinoyLogger");
	public static  FileInputStream fis = null;
	public  FileOutputStream fileOut =null;
    private static XSSFWorkbook workbook = null;
    private static XSSFSheet sheet = null;
    private static XSSFRow row   =null;
    private static XSSFCell cell = null;
	
	String filePath = "TestData\\TestData.xlsx";
	public Utils(AppiumDriver<?> driver) {
		this.driver = driver;
	}
	/*
	HashMap<String,String> map= new HashMap<String, String>();
	String filePath1="";
	map=readExcel(filePath1);
	*/
	public static HashMap<String, String> readExcel() throws IOException 
	{
	try  
		{  
		//reading data from a file in the form of bytes  
		FileInputStream fis=new FileInputStream("\\TestData\\TestData.xlsx");  
		workbook = new XSSFWorkbook(fis);
		  
		}  
		catch(FileNotFoundException e)  
		{  
		e.printStackTrace();  
		}  
		
		catch(IOException e1)  
		{  
		e1.printStackTrace();  
		}  
		sheet = workbook.getSheetAt(0);
		Iterator<Row> rowiterator = sheet.iterator();
	     HashMap<String,String> map= new HashMap<String, String>();
	     String key= null;
	     String value= null;
	     int rowCnt=1;
	     int colCnt=2;
	     while (rowiterator.hasNext()) {
	       row = rowiterator.next();
	       key = row.getCell(rowCnt).getStringCellValue();
	      //Cell cell=row.getCell(colCnt);
	       value = row.getCell(colCnt).getStringCellValue();
	       map.put(key, value);    
	       rowCnt++;
		}
	     return map;

	}
	public static HashMap<String, String> readXML(String nodeName){
		String eleLocator="";
		HashMap<String,String> map=new HashMap<String,String>();
		try
		{
			//creating a constructor of file class and parsing an XML file
			File file = new File("ObjectRepository\\Mobile_OR.xml");
			//an instance of factory that gives a document builder
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			//an instance of builder to parse the specified xml file
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file.getAbsolutePath());
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName(nodeName);
			// nodeList is not iterable, so we are using for loop
			for (int itr = 0; itr < nodeList.getLength(); itr++)
			{
			Node node = nodeList.item(itr);

				if (node.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element) node;
					if(nodeName.equalsIgnoreCase("CropHme")) {
						map=getCropElements(eElement);
						//eleLocator=eElement.getElementsByTagName(eleName).item(0).getTextContent();
					}
					else{
						map=getAddCropElement(eElement);
					}
				}
			}
		}
		catch (Exception e)
		{
		e.printStackTrace();
		}
		return map;
	}
	public static HashMap<String, String> getCropElements(Element eElement) {
		map.put("appInitialTitle",eElement.getElementsByTagName("appInitialTitle").item(0).getTextContent());
		map.put("appTtle",eElement.getElementsByTagName("appTtle").item(0).getTextContent());
		map.put("addFstCrop",eElement.getElementsByTagName("addFstCrop").item(0).getTextContent());
		map.put("newSampleBtn",eElement.getElementsByTagName("newSampleBtn").item(0).getTextContent());
		map.put("newsamplePgeTitle",eElement.getElementsByTagName("newsamplePgeTitle").item(0).getTextContent());
		map.put("adNxtCropdBtn",eElement.getElementsByTagName("adNxtCropdBtn").item(0).getTextContent());
		return map;
	}
	public static HashMap<String, String> getAddCropElement(Element eElement) {
		map.put("farmNme",eElement.getElementsByTagName("farmNme").item(0).getTextContent());
		map.put("riceVariety",eElement.getElementsByTagName("riceVariety").item(0).getTextContent());
		map.put("period",eElement.getElementsByTagName("period").item(0).getTextContent());
		map.put("frmSize",eElement.getElementsByTagName("frmSize").item(0).getTextContent());
		map.put("saveBtn",eElement.getElementsByTagName("saveBtn").item(0).getTextContent());
		return map;
	}
}