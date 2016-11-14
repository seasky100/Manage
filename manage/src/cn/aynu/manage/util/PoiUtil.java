package cn.aynu.manage.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.aynu.manage.vo.Caseinfo;

/**
 * Excel�ļ����������࣬��������д���ϲ��ȹ���
 */
public class PoiUtil {

	// %%%%%%%%-------�������� ��ʼ----------%%%%%%%%%
	/**
	 * Ĭ�ϵĿ�ʼ��ȡ����λ��Ϊ��һ�У�����ֵΪ0��
	 */
	private final static int READ_START_POS = 0;

	/**
	 * Ĭ�Ͻ�����ȡ����λ��Ϊ���һ�У�����ֵ=0���ø�������ʾ������n�У�
	 */
	private final static int READ_END_POS = 0;

	/**
	 * Ĭ��Excel���ݵĿ�ʼ�Ƚ���λ��Ϊ��һ�У�����ֵΪ0��
	 */
	private final static int COMPARE_POS = 0;

	/**
	 * Ĭ�϶��ļ��ϲ���ʱ��Ҫ�����ݱȽϣ���ͬ�����ݲ��ظ����֣�
	 */
	private final static boolean NEED_COMPARE = true;

	/**
	 * Ĭ�϶��ļ��ϲ������ļ����������ظ�ʱ�����и���
	 */
	private final static boolean NEED_OVERWRITE = true;

	/**
	 * Ĭ��ֻ����һ��sheet
	 */
	private final static boolean ONLY_ONE_SHEET = true;

	/**
	 * Ĭ�϶�ȡ��һ��sheet�У�ֻ�е�ONLY_ONE_SHEET = trueʱ��Ч��
	 */
	private final static int SELECTED_SHEET = 0;

	/**
	 * Ĭ�ϴӵ�һ��sheet��ʼ��ȡ������ֵΪ0��
	 */
	private final static int READ_START_SHEET = 0;

	/**
	 * Ĭ�������һ��sheet������ȡ������ֵ=0���ø�������ʾ������n�У�
	 */
	private final static int READ_END_SHEET = 0;

	/**
	 * Ĭ�ϴ�ӡ������Ϣ
	 */
	private final static boolean PRINT_MSG = true;

	// %%%%%%%%-------�������� ����----------%%%%%%%%%

	// %%%%%%%%-------�ֶβ��� ��ʼ----------%%%%%%%%%
	/**
	 * Excel�ļ�·��
	 */
	private String excelPath = "data.xlsx";

	/**
	 * �趨��ʼ��ȡ��λ�ã�Ĭ��Ϊ1
	 */
	private int startReadPos = 1;

	/**
	 * �趨������ȡ��λ�ã�Ĭ��Ϊ0���ø�������ʾ������n��
	 */
	private int endReadPos = READ_END_POS;

	/**
	 * �趨��ʼ�Ƚϵ���λ�ã�Ĭ��Ϊ0
	 */
	private int comparePos = COMPARE_POS;

	/**
	 * �趨���ܵ��ļ��Ƿ���Ҫ�滻��Ĭ��Ϊtrue
	 */
	private boolean isOverWrite = NEED_OVERWRITE;

	/**
	 * �趨�Ƿ���Ҫ�Ƚϣ�Ĭ��Ϊtrue(��������дĿ����������Ч����isOverWrite=falseʱ��Ч)
	 */
	private boolean isNeedCompare = NEED_COMPARE;

	/**
	 * �趨�Ƿ�ֻ������һ��sheet
	 */
	private boolean onlyReadOneSheet = ONLY_ONE_SHEET;

	/**
	 * �趨������sheet������ֵ
	 */
	private int selectedSheetIdx = SELECTED_SHEET;

	/**
	 * �趨������sheet������
	 */
	private String selectedSheetName = "";

	/**
	 * �趨��ʼ��ȡ��sheet��Ĭ��Ϊ0
	 */
	private int startSheetIdx = READ_START_SHEET;

	/**
	 * �趨������ȡ��sheet��Ĭ��Ϊ0���ø�������ʾ������n��
	 */
	private int endSheetIdx = READ_END_SHEET;

	/**
	 * �趨�Ƿ��ӡ��Ϣ
	 */
	private boolean printMsg = PRINT_MSG;

	private final static String caseinfoSQL = "insert into caseinfo (`Number`,`Callper`,`Calltype`,`Calltime`,`Content`,`Address`,`Phone`,`Receive`,`Outtime`,`Outper`,`Outcar`,`Dealwith`,`Sugge`,`Dealper`,`Type`,`site`,`serious`,`caseType`,`siteX`,`siteY`,`smallContent`,`dealSuccessTime`) value (";
	private final static String carSQL = "insert into car (`Number`,`car_Explain`,`Carnum`,`Day`,`Name`,`Numa`,`Sex`,`Numb`,`Card`,`Address`,`Remark`) value (";
	private final static String personSQL = "insert into person (`Number`,`Name`,`Sex`,`Id_card`,`Type`,`Unit`,`Floor`,`Room`,`Address`,`Work_company`,`Professional`,`Tel`,`Live_reason`,`Household`,`Household_type`,`National`,`Cultural_level`,`Marital_status`,`Birthday`,`upload`) value (";
	private final static String shopSQL = "insert into shop (`Number`,`Unit`,`Floor`,`Room`,`Shop_name`,`Shop_type`,`Boss_name`,`Boss_id_card`) value (";

	// %%%%%%%%-------�ֶβ��� ����----------%%%%%%%%%

	public static void main(String[] args) {
	}

	public PoiUtil() {
	}

	public PoiUtil(String excelPath) {
		this.excelPath = excelPath;
	}

	/**
	 * ���ݵ�������dataList�е�������
	 * @param headCell
	 * @param fileNameAndPath
	 * @param dataList
	 */
	public void writeExcel(String[] headCell, String fileNameAndPath, List<List<String>> dataList) {
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("������Ϣ");
		Row rowTitle = sheet.createRow(0);
		for (int i = 0; i < headCell.length; i++) {
			rowTitle.createCell(i).setCellValue(headCell[i]);
		}
		for (int i = 0; dataList != null && i < dataList.size(); i++) {
			Row rowData = sheet.createRow(i + 1);
			List<String> list = dataList.get(i);
			for (int j = 0; j < list.size(); j++) {
				Cell cell = rowData.createCell(j);
				cell.setCellValue(list.get(j)+"");
			}
		}
		try {
			wb.write(new FileOutputStream(fileNameAndPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("���ݵ����ɹ�������" + dataList.size() + "��������" + fileNameAndPath);
	}
	
	
	
	/**
	 * ��ԭ�趨����ʵ������newһ���µĶ��󲢷��أ�
	 * 
	 * @return
	 */
	public PoiUtil RestoreSettings() {
		PoiUtil instance = new PoiUtil(this.excelPath);
		return instance;
	}

	/**
	 * �Զ������ļ���չ�������ö�Ӧ�Ķ�ȡ����
	 * 
	 * @Title: writeExcel
	 * @Date : 2014-9-11 ����01:50:38
	 * @param xlsPath
	 * @throws IOException
	 */
	public List<Row> readExcel() throws IOException {
		return readExcel(this.excelPath);
	}

	/**
	 * �Զ������ļ���չ�������ö�Ӧ�Ķ�ȡ����
	 */
	public List<Row> readExcel(String xlsPath) throws IOException {

		// ��չ��Ϊ��ʱ��
		if (xlsPath.equals("")) {
			throw new IOException("�ļ�·������Ϊ�գ�");
		} else {
			File file = new File(xlsPath);
			if (!file.exists()) {
				throw new IOException("�ļ������ڣ�");
			}
		}

		// ��ȡ��չ��
		String ext = xlsPath.substring(xlsPath.lastIndexOf(".") + 1);

		try {

			if ("xls".equals(ext)) { // ʹ��xls��ʽ��ȡ
				return readExcel_xls(xlsPath);
			} else if ("xlsx".equals(ext)) { // ʹ��xlsx��ʽ��ȡ
				return readExcel_xlsx(xlsPath);
			} else { // ���γ���xls��xlsx��ʽ��ȡ
				out("��Ҫ�������ļ�û����չ�������ڳ�����xls��ʽ��ȡ...");
				try {
					return readExcel_xls(xlsPath);
				} catch (IOException e1) {
					out("������xls��ʽ��ȡ�����ʧ�ܣ������ڳ�����xlsx��ʽ��ȡ...");
					try {
						return readExcel_xlsx(xlsPath);
					} catch (IOException e2) {
						out("������xls��ʽ��ȡ�����ʧ�ܣ�\n����ȷ�������ļ���Excel�ļ�����������Ȼ�����ԡ�");
						throw e2;
					}
				}
			}
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * �Զ������ļ���չ�������ö�Ӧ��д�뷽��
	 */
	public void writeExcel(List<Row> rowList) throws IOException {
		writeExcel(rowList, excelPath);
	}

	/**
	 * �Զ������ļ���չ�������ö�Ӧ��д�뷽��
	 */
	public void writeExcel(List<Row> rowList, String xlsPath) throws IOException {

		// ��չ��Ϊ��ʱ��
		if (xlsPath.equals("")) {
			throw new IOException("�ļ�·������Ϊ�գ�");
		}

		// ��ȡ��չ��
		String ext = xlsPath.substring(xlsPath.lastIndexOf(".") + 1);

		try {

			if ("xls".equals(ext)) { // ʹ��xls��ʽд��
				writeExcel_xls(rowList, xlsPath);
			} else if ("xlsx".equals(ext)) { // ʹ��xlsx��ʽд��
				writeExcel_xlsx(rowList, xlsPath);
			} else { // ���γ���xls��xlsx��ʽд��
				out("��Ҫ�������ļ�û����չ�������ڳ�����xls��ʽд��...");
				try {
					writeExcel_xls(rowList, xlsPath);
				} catch (IOException e1) {
					out("������xls��ʽд�룬���ʧ�ܣ������ڳ�����xlsx��ʽ��ȡ...");
					try {
						writeExcel_xlsx(rowList, xlsPath);
					} catch (IOException e2) {
						out("������xls��ʽд�룬���ʧ�ܣ�\n����ȷ�������ļ���Excel�ļ�����������Ȼ�����ԡ�");
						throw e2;
					}
				}
			}
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * �޸�Excel��97-03�棬xls��ʽ��
	 */
	public void writeExcel_xls(List<Row> rowList, String dist_xlsPath) throws IOException {
		writeExcel_xls(rowList, excelPath, dist_xlsPath);
	}

	/**
	 * �޸�Excel��97-03�棬xls��ʽ��
	 */
	public void writeExcel_xls(List<Row> rowList, String src_xlsPath, String dist_xlsPath) throws IOException {

		// �ж��ļ�·���Ƿ�Ϊ��
		if (dist_xlsPath == null || dist_xlsPath.equals("")) {
			out("�ļ�·������Ϊ��");
			throw new IOException("�ļ�·������Ϊ��");
		}
		// �ж��ļ�·���Ƿ�Ϊ��
		if (src_xlsPath == null || src_xlsPath.equals("")) {
			out("�ļ�·������Ϊ��");
			throw new IOException("�ļ�·������Ϊ��");
		}

		// �ж��б��Ƿ������ݣ����û�����ݣ��򷵻�
		if (rowList == null || rowList.size() == 0) {
			out("�ĵ�Ϊ��");
			return;
		}

		try {
			HSSFWorkbook wb = null;

			// �ж��ļ��Ƿ����
			File file = new File(dist_xlsPath);
			if (file.exists()) {
				// �����д����ɾ����
				if (isOverWrite) {
					file.delete();
					// ����ļ������ڣ��򴴽�һ���µ�Excel
					// wb = new HSSFWorkbook();
					// wb.createSheet("Sheet1");
					wb = new HSSFWorkbook(new FileInputStream(src_xlsPath));
				} else {
					// ����ļ����ڣ����ȡExcel
					wb = new HSSFWorkbook(new FileInputStream(file));
				}
			} else {
				// ����ļ������ڣ��򴴽�һ���µ�Excel
				// wb = new HSSFWorkbook();
				// wb.createSheet("Sheet1");
				wb = new HSSFWorkbook(new FileInputStream(src_xlsPath));
			}

			// ��rowlist������д��Excel��
			writeExcel(wb, rowList, dist_xlsPath);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �޸�Excel��97-03�棬xls��ʽ��
	 * 
	 */
	public void writeExcel_xlsx(List<Row> rowList, String dist_xlsPath) throws IOException {
		writeExcel_xls(rowList, excelPath, dist_xlsPath);
	}

	/**
	 * �޸�Excel��2007�棬xlsx��ʽ��
	 */
	public void writeExcel_xlsx(List<Row> rowList, String src_xlsPath, String dist_xlsPath) throws IOException {

		// �ж��ļ�·���Ƿ�Ϊ��
		if (dist_xlsPath == null || dist_xlsPath.equals("")) {
			out("�ļ�·������Ϊ��");
			throw new IOException("�ļ�·������Ϊ��");
		}
		// �ж��ļ�·���Ƿ�Ϊ��
		if (src_xlsPath == null || src_xlsPath.equals("")) {
			out("�ļ�·������Ϊ��");
			throw new IOException("�ļ�·������Ϊ��");
		}

		// �ж��б��Ƿ������ݣ����û�����ݣ��򷵻�
		if (rowList == null || rowList.size() == 0) {
			out("�ĵ�Ϊ��");
			return;
		}

		try {
			// ��ȡ�ĵ�
			XSSFWorkbook wb = null;

			// �ж��ļ��Ƿ����
			File file = new File(dist_xlsPath);
			if (file.exists()) {
				// �����д����ɾ����
				if (isOverWrite) {
					file.delete();
					// ����ļ������ڣ��򴴽�һ���µ�Excel
					// wb = new XSSFWorkbook();
					// wb.createSheet("Sheet1");
					wb = new XSSFWorkbook(new FileInputStream(src_xlsPath));
				} else {
					// ����ļ����ڣ����ȡExcel
					wb = new XSSFWorkbook(new FileInputStream(file));
				}
			} else {
				// ����ļ������ڣ��򴴽�һ���µ�Excel
				// wb = new XSSFWorkbook();
				// wb.createSheet("Sheet1");
				wb = new XSSFWorkbook(new FileInputStream(src_xlsPath));
			}
			// ��rowlist��������ӵ�Excel��
			writeExcel(wb, rowList, dist_xlsPath);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * //��ȡExcel 2007�棬xlsx��ʽ
	 */
	public List<Row> readExcel_xlsx() throws IOException {
		return readExcel_xlsx(excelPath);
	}

	/**
	 * //��ȡExcel 2007�棬xlsx��ʽ
	 */
	public List<Row> readExcel_xlsx(String xlsPath) throws IOException {
		// �ж��ļ��Ƿ����
		File file = new File(xlsPath);
		if (!file.exists()) {
			throw new IOException("�ļ���Ϊ" + file.getName() + "Excel�ļ������ڣ�");
		}

		XSSFWorkbook wb = null;
		List<Row> rowList = new ArrayList<Row>();
		try {
			FileInputStream fis = new FileInputStream(file);
			// ȥ��Excel
			wb = new XSSFWorkbook(fis);

			// ��ȡExcel 2007�棬xlsx��ʽ
			rowList = readExcel(wb);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return rowList;
	}

	/**
	 * //��ȡExcel 2007�棬xlsx��ʽ
	 */
	public List<String> readExcel_xlsx_sql(String xlsPath, String table) throws IOException {
		// �ж��ļ��Ƿ����
		File file = new File(xlsPath);
		if (!file.exists()) {
			throw new IOException("�ļ���Ϊ" + file.getName() + "Excel�ļ������ڣ�");
		}

		XSSFWorkbook wb = null;
		List<String> sqls = new ArrayList<String>();
		try {
			FileInputStream fis = new FileInputStream(file);
			// ȥ��Excel
			wb = new XSSFWorkbook(fis);

			// ��ȡExcel 2007�棬xlsx��ʽ
			sqls = readExceltoSql(wb, table);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqls;
	}

	/***
	 * ��ȡExcel(97-03�棬xls��ʽ)
	 */
	public List<Row> readExcel_xls() throws IOException {
		return readExcel_xls(excelPath);
	}

	/***
	 * ��ȡExcel(97-03�棬xls��ʽ)
	 */
	public List<Row> readExcel_xls(String xlsPath) throws IOException {

		// �ж��ļ��Ƿ����
		File file = new File(xlsPath);
		if (!file.exists()) {
			throw new IOException("�ļ���Ϊ" + file.getName() + "Excel�ļ������ڣ�");
		}

		HSSFWorkbook wb = null;// ����Workbook���Ĳ�����������ɾ��Excel
		List<Row> rowList = new ArrayList<Row>();

		try {
			// ��ȡExcel
			wb = new HSSFWorkbook(new FileInputStream(file));

			// ��ȡExcel 97-03�棬xls��ʽ
			rowList = readExcel(wb);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return rowList;
	}

	/***
	 * ��ȡExcelΪsql(97-03�棬xls��ʽ)
	 */
	public List<String> readExcel_xls_sql(String xlsPath, String table) throws Exception {

		// �ж��ļ��Ƿ����
		File file = new File(xlsPath);
		if (!file.exists()) {
			throw new IOException("�ļ���Ϊ" + file.getName() + "Excel�ļ������ڣ�");
		}

		HSSFWorkbook wb = null;// ����Workbook���Ĳ�����������ɾ��Excel
		List<String> sqls = new ArrayList<String>();

		try {
			// ��ȡExcel
			wb = new HSSFWorkbook(new FileInputStream(file));

			// ��ȡExcel 97-03�棬xls��ʽ
			sqls = readExceltoSql(wb, table);

		} catch (IOException e) {
			throw e;
		}
		return sqls;
	}

	/***
	 * ��ȡ��Ԫ���ֵ
	 */
	private String getCellValue(Cell cell) {
		DecimalFormat df = new DecimalFormat("#");
		if (cell == null)
			return "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				return sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
			}
			return df.format(cell.getNumericCellValue());
		case HSSFCell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case HSSFCell.CELL_TYPE_FORMULA:
			return cell.getCellFormula();
		case HSSFCell.CELL_TYPE_BLANK:
			return "";
		case HSSFCell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue() + "";
		case HSSFCell.CELL_TYPE_ERROR:
			return cell.getErrorCellValue() + "";
		}
		return "";
	}

	/**
	 * �ѵ����excel���תΪsql��� ���뵽���ݿ���
	 * 
	 * @throws Exception
	 */
	public List<String> readExceltoSql(String xlsPath, String table) throws Exception {
		// ��չ��Ϊ��ʱ��
		if (xlsPath.equals("")) {
			throw new IOException("�ļ�·������Ϊ�գ�");
		} else {
			File file = new File(xlsPath);
			if (!file.exists()) {
				throw new IOException("�ļ������ڣ�");
			}
		}

		// ��ȡ��չ��
		String ext = xlsPath.substring(xlsPath.lastIndexOf(".") + 1);

		try {

			if ("xls".equals(ext)) { // ʹ��xls��ʽ��ȡ
				return readExcel_xls_sql(xlsPath, table);
			} else if ("xlsx".equals(ext)) { // ʹ��xlsx��ʽ��ȡ
				return readExcel_xlsx_sql(xlsPath, table);
			} else { // ���γ���xls��xlsx��ʽ��ȡ
				out("��Ҫ�������ļ�û����չ�������ڳ�����xls��ʽ��ȡ...");
				try {
					return readExcel_xls_sql(xlsPath, table);
				} catch (IOException e1) {
					out("������xls��ʽ��ȡ�����ʧ�ܣ������ڳ�����xlsx��ʽ��ȡ...");
					try {
						return readExcel_xlsx_sql(xlsPath, table);
					} catch (IOException e2) {
						out("������xls��ʽ��ȡ�����ʧ�ܣ�\n����ȷ�������ļ���Excel�ļ�����������Ȼ�����ԡ�");
						throw e2;
					}
				}
			}
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * ͨ�ö�ȡExcel
	 */
	private List<Row> readExcel(Workbook wb) {
		List<Row> rowList = new ArrayList<Row>();

		int sheetCount = 1;// ��Ҫ������sheet����

		Sheet sheet = null;
		if (onlyReadOneSheet) { // ֻ����һ��sheet
			// ��ȡ�趨������sheet(����趨�����ƣ������Ʋ飬��������ֵ��)
			sheet = selectedSheetName.equals("") ? wb.getSheetAt(selectedSheetIdx) : wb.getSheet(selectedSheetName);
		} else { // �������sheet
			sheetCount = wb.getNumberOfSheets();// ��ȡ���Բ�����������
		}

		// ��ȡsheet��Ŀ
		for (int t = startSheetIdx; t < sheetCount + endSheetIdx; t++) {
			// ��ȡ�趨������sheet
			if (!onlyReadOneSheet) {
				sheet = wb.getSheetAt(t);
			}

			// ��ȡ����к�
			int lastRowNum = sheet.getLastRowNum();

			if (lastRowNum > 0) { // ���>0����ʾ������
				out("\n��ʼ��ȡ��Ϊ��" + sheet.getSheetName() + "�������ݣ�");
			}

			Row row = null;
			// ѭ����ȡ
			for (int i = startReadPos; i <= lastRowNum + endReadPos; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					rowList.add(row);
					out("��" + (i + 1) + "�У�", false);
					// ��ȡÿһ��Ԫ���ֵ
					for (int j = 0; j < row.getLastCellNum(); j++) {
						String value = getCellValue(row.getCell(j));
						if (!value.equals("")) {
							out(value + " | ", false);
						}
					}
					out("");
				}
			}
		}
		return rowList;
	}

	/**
	 * ͨ�ö�ȡExcel Ϊsql
	 */
	private List<String> readExceltoSql(Workbook wb, String table) {
		List<String> sqls = new ArrayList<String>();

		int sheetCount = 1;// ��Ҫ������sheet����

		Sheet sheet = null;
		if (onlyReadOneSheet) { // ֻ����һ��sheet
			// ��ȡ�趨������sheet(����趨�����ƣ������Ʋ飬��������ֵ��)
			sheet = selectedSheetName.equals("") ? wb.getSheetAt(selectedSheetIdx) : wb.getSheet(selectedSheetName);
		} else { // �������sheet
			sheetCount = wb.getNumberOfSheets();// ��ȡ���Բ�����������
		}

		// ��ȡsheet��Ŀ
		for (int t = startSheetIdx; t < sheetCount + endSheetIdx; t++) {
			// ��ȡ�趨������sheet
			if (!onlyReadOneSheet) {
				sheet = wb.getSheetAt(t);
			}

			// ��ȡ����к�
			int lastRowNum = sheet.getLastRowNum();

			if (lastRowNum > 0) { // ���>0����ʾ������
				// out("\n��ʼ��ȡ��Ϊ��"+sheet.getSheetName()+"�������ݣ�");
			}

			Row row = null;
			//�õ���һ�� ��ͷ
			Row rowIndex = sheet.getRow(0);
			// ѭ����ȡ
			for (int i = startReadPos; i <= lastRowNum + endReadPos; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					/*String sql = "insert into " + table
							+ " (`Number`,`Callper`,`Calltype`,`Calltime`,`Content`,`Address`,`Phone`,`Receive`,`Outtime`,`Outper`,`Outcar`,`Dealwith`,`Sugge`,`Dealper`,`Type`,`site`,`serious`,`caseType`,`siteX`,`siteY`,`smallContent`,`dealSuccessTime`) value (";*/
					String sql = getSQLByTableName(table);
					// out("��"+(i+1)+"�У�",false);
					// ��ȡÿһ��Ԫ���ֵ
					for (int j = 0; j < rowIndex.getLastCellNum(); j++) {
						String value = getCellValue(row.getCell(j));
						if (ValidateUtil.isValid(value)) {
							sql = sql + "'" + value + "',";
						} else {
							sql = sql + "null,";
						}
					}
					sql = sql.substring(0, sql.length() - 1) + ");";
					// out(sql);
					sqls.add(sql);
				}
			}
		}
		return sqls;
	}

	/**
	 * �޸�Excel�������Ϊ
	 */
	private void writeExcel(Workbook wb, List<Row> rowList, String xlsPath) {

		if (wb == null) {
			out("�����ĵ�����Ϊ�գ�");
			return;
		}

		Sheet sheet = wb.getSheetAt(0);// �޸ĵ�һ��sheet�е�ֵ

		// ���ÿ����д����ô��ӿ�ʼ��ȡ��λ��д���������ȡԴ�ļ����µ��С�
		int lastRowNum = isOverWrite ? startReadPos : sheet.getLastRowNum() + 1;
		int t = 0;// ��¼������ӵ�����
		out("Ҫ��ӵ�����������Ϊ��" + rowList.size());
		for (Row row : rowList) {
			if (row == null)
				continue;
			// �ж��Ƿ��Ѿ����ڸ�����
			int pos = findInExcel(sheet, row);

			Row r = null;// ����������Ѿ����ڣ����ȡ����д�������Զ��������С�
			if (pos >= 0) {
				sheet.removeRow(sheet.getRow(pos));
				r = sheet.createRow(pos);
			} else {
				r = sheet.createRow(lastRowNum + t++);
			}

			// �����趨��Ԫ����ʽ
			CellStyle newstyle = wb.createCellStyle();

			// ѭ��Ϊ���д�����Ԫ��
			for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
				Cell cell = r.createCell(i);// ��ȡ��������
				cell.setCellValue(getCellValue(row.getCell(i)));// ���Ƶ�Ԫ���ֵ���µĵ�Ԫ��
				// cell.setCellStyle(row.getCell(i).getCellStyle());//����
				if (row.getCell(i) == null)
					continue;
				copyCellStyle(row.getCell(i).getCellStyle(), newstyle); // ��ȡԭ���ĵ�Ԫ����ʽ
				cell.setCellStyle(newstyle);// ������ʽ
				// sheet.autoSizeColumn(i);//�Զ���ת�п��
			}
		}
		out("���м�⵽�ظ�����Ϊ:" + (rowList.size() - t) + " ��׷������Ϊ��" + t);

		// ͳһ�趨�ϲ���Ԫ��
		setMergedRegion(sheet);

		try {
			// ���½�����д��Excel��
			FileOutputStream outputStream = new FileOutputStream(xlsPath);
			wb.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			out("д��Excelʱ�������� ");
			e.printStackTrace();
		}
	}

	/**
	 * ����ĳ�������Ƿ���Excel���д��ڣ�����������
	 */
	private int findInExcel(Sheet sheet, Row row) {
		int pos = -1;

		try {
			// �����дĿ���ļ������߲���Ҫ�Ƚϣ���ֱ�ӷ���
			if (isOverWrite || !isNeedCompare) {
				return pos;
			}
			for (int i = startReadPos; i <= sheet.getLastRowNum() + endReadPos; i++) {
				Row r = sheet.getRow(i);
				if (r != null && row != null) {
					String v1 = getCellValue(r.getCell(comparePos));
					String v2 = getCellValue(row.getCell(comparePos));
					if (v1.equals(v2)) {
						pos = i;
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pos;
	}

	/**
	 * ����һ����Ԫ����ʽ��Ŀ�ĵ�Ԫ����ʽ
	 */
	public static void copyCellStyle(CellStyle fromStyle, CellStyle toStyle) {
		toStyle.setAlignment(fromStyle.getAlignment());
		// �߿�ͱ߿���ɫ
		toStyle.setBorderBottom(fromStyle.getBorderBottom());
		toStyle.setBorderLeft(fromStyle.getBorderLeft());
		toStyle.setBorderRight(fromStyle.getBorderRight());
		toStyle.setBorderTop(fromStyle.getBorderTop());
		toStyle.setTopBorderColor(fromStyle.getTopBorderColor());
		toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());
		toStyle.setRightBorderColor(fromStyle.getRightBorderColor());
		toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor());

		// ������ǰ��
		toStyle.setFillBackgroundColor(fromStyle.getFillBackgroundColor());
		toStyle.setFillForegroundColor(fromStyle.getFillForegroundColor());

		// ���ݸ�ʽ
		toStyle.setDataFormat(fromStyle.getDataFormat());
		toStyle.setFillPattern(fromStyle.getFillPattern());
		// toStyle.setFont(fromStyle.getFont(null));
		toStyle.setHidden(fromStyle.getHidden());
		toStyle.setIndention(fromStyle.getIndention());// ��������
		toStyle.setLocked(fromStyle.getLocked());
		toStyle.setRotation(fromStyle.getRotation());// ��ת
		toStyle.setVerticalAlignment(fromStyle.getVerticalAlignment());
		toStyle.setWrapText(fromStyle.getWrapText());

	}

	/**
	 * ��ȡ�ϲ���Ԫ���ֵ
	 */
	public void setMergedRegion(Sheet sheet) {
		int sheetMergeCount = sheet.getNumMergedRegions();

		for (int i = 0; i < sheetMergeCount; i++) {
			// ��ȡ�ϲ���Ԫ��λ��
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstRow = ca.getFirstRow();
			if (startReadPos - 1 > firstRow) {// �����һ���ϲ���Ԫ���ʽ����ʽ���ݵ����棬��������
				continue;
			}
			int lastRow = ca.getLastRow();
			int mergeRows = lastRow - firstRow;// �ϲ�������
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			// ���ݺϲ��ĵ�Ԫ��λ�úʹ�С���������е������и�ʽ��
			for (int j = lastRow + 1; j <= sheet.getLastRowNum(); j++) {
				// �趨�ϲ���Ԫ��
				sheet.addMergedRegion(new CellRangeAddress(j, j + mergeRows, firstColumn, lastColumn));
				j = j + mergeRows;// �����Ѻϲ�����
			}

		}
	}

	/**
	 * ��ӡ��Ϣ��
	 */
	private void out(String msg) {
		if (printMsg) {
			out(msg, true);
		}
	}

	/**
	 * ��ӡ��Ϣ��
	 */
	private void out(String msg, boolean tr) {
		if (printMsg) {
			System.out.print(msg + (tr ? "\n" : ""));
		}
	}

	/**
	 * ���ݵõ��ı�������sql��ѯ���
	 * 
	 * @param tableName
	 * @return
	 */
	public String getSQLByTableName(String tableName) {
		if (null != tableName && !tableName.equals("")) {
			if (tableName.equals("caseinfo")) {
				return caseinfoSQL;
			} else if (tableName.equals("car")) {
				return carSQL;
			} else if (tableName.equals("person")) {
				return personSQL;
			} else if(tableName.equals("shop")){
				return shopSQL;
			}
		}
		return null;
	}

	public String getExcelPath() {
		return this.excelPath;
	}

	public void setExcelPath(String excelPath) {
		this.excelPath = excelPath;
	}

	public boolean isNeedCompare() {
		return isNeedCompare;
	}

	public void setNeedCompare(boolean isNeedCompare) {
		this.isNeedCompare = isNeedCompare;
	}

	public int getComparePos() {
		return comparePos;
	}

	public void setComparePos(int comparePos) {
		this.comparePos = comparePos;
	}

	public int getStartReadPos() {
		return startReadPos;
	}

	public void setStartReadPos(int startReadPos) {
		this.startReadPos = startReadPos;
	}

	public int getEndReadPos() {
		return endReadPos;
	}

	public void setEndReadPos(int endReadPos) {
		this.endReadPos = endReadPos;
	}

	public boolean isOverWrite() {
		return isOverWrite;
	}

	public void setOverWrite(boolean isOverWrite) {
		this.isOverWrite = isOverWrite;
	}

	public boolean isOnlyReadOneSheet() {
		return onlyReadOneSheet;
	}

	public void setOnlyReadOneSheet(boolean onlyReadOneSheet) {
		this.onlyReadOneSheet = onlyReadOneSheet;
	}

	public int getSelectedSheetIdx() {
		return selectedSheetIdx;
	}

	public void setSelectedSheetIdx(int selectedSheetIdx) {
		this.selectedSheetIdx = selectedSheetIdx;
	}

	public String getSelectedSheetName() {
		return selectedSheetName;
	}

	public void setSelectedSheetName(String selectedSheetName) {
		this.selectedSheetName = selectedSheetName;
	}

	public int getStartSheetIdx() {
		return startSheetIdx;
	}

	public void setStartSheetIdx(int startSheetIdx) {
		this.startSheetIdx = startSheetIdx;
	}

	public int getEndSheetIdx() {
		return endSheetIdx;
	}

	public void setEndSheetIdx(int endSheetIdx) {
		this.endSheetIdx = endSheetIdx;
	}

	public boolean isPrintMsg() {
		return printMsg;
	}

	public void setPrintMsg(boolean printMsg) {
		this.printMsg = printMsg;
	}

}