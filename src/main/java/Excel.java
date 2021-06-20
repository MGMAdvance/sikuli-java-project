import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Excel {

    private final List<Student> listStudent = new ArrayList<Student>();
    private static final URL excel = Excel.class.getResource("notas.xls");

    public Excel(){
        prepareValues();
    }

    public List<Student> getListStudent() {
        return listStudent;
    }

    public void prepareValues(){
        try {
            assert excel != null;
            FileInputStream file = new FileInputStream(excel.getFile());

            HSSFWorkbook workbook = new HSSFWorkbook(file);

            HSSFSheet sheetStudents = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheetStudents.iterator();

            rowIterator.next();

            while (rowIterator.hasNext()){
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                String name = "";
                double firstTest = 0, secondTest = 0;

                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cell.getColumnIndex()) {
                        case 0 -> name = cell.getStringCellValue();
                        case 1 -> firstTest = cell.getNumericCellValue();
                        case 2 -> secondTest = cell.getNumericCellValue();
                    }
                }

                listStudent.add(new Student(name,firstTest,secondTest));
            }
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
