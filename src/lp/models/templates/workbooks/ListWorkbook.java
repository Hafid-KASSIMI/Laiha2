

package lp.models.templates.workbooks;

import java.awt.Color;
import java.util.function.BiConsumer;
import lp.Settings;
import lp.models.templates.workbooks.metrics.ListMetrics;
import lp.models.templates.workbooks.metrics.XStudentRow;
import net.mdrassty.object.Group;
import net.mdrassty.object.Student;
import net.mdrassty.util.Misc;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public abstract class ListWorkbook extends BaseWorkbook {
    
    protected String sheetName;
    protected boolean unifyLevelGroupsSize, strip;
    protected ListMetrics sheetMetrics;
    protected BiConsumer<Group, Integer> unifyLevSize;
    protected int stripIndex;
    protected XSSFRow current, normalRow, highlightedRow;
    
    private final CellCopyPolicy policy = new CellCopyPolicy();
    private Color stripColor = Color.WHITE, stripTextColor = Color.BLACK;

    public ListWorkbook() {
        super();
        policy.setCopyCellValue(false);
    }
    
    protected void add1PageGroup(Group g, XSSFSheet sheet, int start, int stuCount) {
        if ( unifyLevelGroupsSize )
            unifyLevSize.accept(g, stuCount);
        sheetMetrics.prepareRows(stuCount - start);
        normalRow = highlightedRow = null;
        for ( int i = start, j = 0; i < stuCount; i++, j++ ) {
            placeStudent(j, sheet, g.getStudent(i));
        }
        while ( sheetMetrics.emptySpaceExists() ) {
            addEmptyRow(sheetMetrics.getNextStudentRow(), sheet);
        }
    }
    
    protected void addEmptyRow(XStudentRow row, XSSFSheet sht) {
        if ( normalRow == null ) {
            normalRow = initializeRow(row, sht);
        }
        else {
            XSSFRow xlRow = sht.createRow(row.getRowIndex());
            xlRow.copyRowFrom(normalRow, policy);
        }
    }
    
    protected abstract XSSFRow initializeRow(XStudentRow row, XSSFSheet sht);
    
    protected void placeStudent(int index, XSSFSheet sht, Student stu) {
        XStudentRow row = sheetMetrics.getStudentRow(index);
        if ( strip && ( index + 1 ) % stripIndex == 0 ) {
            if ( highlightedRow == null ) {
                highlightedRow = initializeRow(row, sht);
                for ( int i = highlightedRow.getFirstCellNum(), n = highlightedRow.getLastCellNum(); i < n; i++ ) {
                    highlightedRow.getCell(i).getCellStyle().setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    highlightedRow.getCell(i).getCellStyle().setFillForegroundColor(new XSSFColor(stripColor, null));
                    highlightedRow.getCell(i).getCellStyle().getFont().setColor(new XSSFColor(stripTextColor, null));
                }
                current = highlightedRow;
            }
            else {
                current = sht.createRow(row.getRowIndex());
                current.copyRowFrom(highlightedRow, policy);
            }
        }
        else {
            if ( normalRow == null ) {
                normalRow = initializeRow(row, sht);
                current = normalRow;
            }
            else {
                current = sht.createRow(row.getRowIndex());
                current.copyRowFrom(normalRow, policy);
            }
        }
    }
    
    @Override
    protected void add2PagesGroup(Group g) {
        int stuCount, ini_unify_lev_size, ini_empty_rows, ini_dbl_page_size, max;
        boolean ini_unify_lev;
        stuCount = g.getStudentsCount();
        ini_dbl_page_size = sheetMetrics.getDoublePageRowsCount();
        XSSFSheet sheet;
        if ( stuCount > ini_dbl_page_size ) {
            ini_unify_lev_size = sheetMetrics.getUniLevEmptyRows();
            ini_empty_rows = sheetMetrics.getEmptyRows();
            max = stuCount + ini_unify_lev_size + ini_empty_rows;
            if ( max > ini_dbl_page_size * 2 ) {
                max = max / 2 + max % 2;
            }
            else
                max = ini_dbl_page_size;
            ini_unify_lev = unifyLevelGroupsSize;
            unifyLevelGroupsSize = false;
            sheetMetrics.setUniLevEmptyRows(0);
            sheetMetrics.setEmptyRows(0);
            validateSheetName(g.getName());
            sheet = coreFile.cloneSheet(0, sheetName);
            sheet.getRow(sheetMetrics.getPager().getY()).getCell(sheetMetrics.getPager().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue("1 / 2");
            add1PageGroup(g, sheet, 0, max);
            unifyLevelGroupsSize = ini_unify_lev;
            sheetMetrics.setUniLevEmptyRows(ini_unify_lev_size);
            sheetMetrics.setEmptyRows(ini_empty_rows);
            validateSheetName(g.getName());
            sheet = coreFile.cloneSheet(0, sheetName);
            sheet.getRow(sheetMetrics.getPager().getY()).getCell(sheetMetrics.getPager().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue("2 / 2");
            add1PageGroup(g, sheet, max, stuCount);
        }
        else {
            validateSheetName(g.getName());
            sheet = coreFile.cloneSheet(0, sheetName);
            sheet.getRow(sheetMetrics.getPager().getY()).getCell(sheetMetrics.getPager().getX(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue("1 / 1");
            add1PageGroup(g, sheet, 0, stuCount);
        }
    }
    
    @Override
    public void addGroup(Group g, boolean force2Pages) {
        if ( coreFile == null || g == null )   return;
        int n;
        validateSheetName(g.getName());
        n = g.getStudentsCount();
        if ( force2Pages )
            add2PagesGroup(g);
        else
            add1PageGroup( g, coreFile.cloneSheet(0, sheetName), 0, n );
    }
    
    protected void validateSheetName(String name) {
        sheetName = name;
        if ( sheetsNames.contains(sheetName) ) {
            sheetName += " (" + sheetsNames.stream().filter(s -> s.equals(name)).count() + ")";
        }
        sheetsNames.add(name);
    }
    
    protected XSSFSheet clone(XSSFSheet sheet, String suffix) {
        return coreFile.cloneSheet(coreFile.getSheetIndex(sheet), sheet.getSheetName() + suffix);
    }
    
    protected void removeSheet(XSSFSheet sheet) {
        try {
            sheet.getWorkbook().removeSheetAt(sheet.getWorkbook().getSheetIndex(sheet));
            sheetsNames.remove(sheetName);
        } catch ( IllegalArgumentException ex ) {}
    }
    
    protected final void initialize() {
        tpl = tempaltesDir + sheetMetrics.getTemplate() + extension;
        unifyLevelGroupsSize = Settings.PREF_TEMPORARY.get("LSTTPL_UNIFY_ROWS_LEVEL").equals("Y");
        strip = Settings.PREF_TEMPORARY.get("LSTTPL_STRIPED").equals("Y");
        try {
            sheetMetrics.setEmptyRows(Integer.parseInt(Settings.PREF_BUNDLE.get("LSTTPL_ADDITIONAL_EMPTY_ROWS")));
        } catch ( NumberFormatException nfe ) { }
        try {
            sheetMetrics.setDoublePageRowsCount(Integer.parseInt(Settings.PREF_BUNDLE.get("LSTTPL_DOUBLE_PAGE_ROWS_COUNT")));
        } catch ( NumberFormatException nfe ) { }
        if ( unifyLevelGroupsSize )
            unifyLevSize = (g, count) -> { sheetMetrics.setUniLevEmptyRows(g.getLevel().getGroupsMaxSize() - count); };
        else
            unifyLevSize = (g, count) -> { };
        if ( strip ) {
            try {
                javafx.scene.paint.Color fxCol = javafx.scene.paint.Color.valueOf(Settings.PREF_BUNDLE.get("LSTTPL_STRIP_COLOR"));
                stripColor = new Color((int) (fxCol.getRed() * 255), (int) (fxCol.getGreen() * 255), (int) (fxCol.getBlue() * 255));
                stripTextColor = Misc.isDark(stripColor.getRed(), stripColor.getGreen(), stripColor.getBlue()) ? Color.WHITE : Color.BLACK;
            } catch ( IllegalArgumentException | NullPointerException nfe ) { }
            try {
                stripIndex = Integer.parseInt(Settings.PREF_BUNDLE.get("LSTTPL_STRIP_INDEX"));
            } catch ( NumberFormatException nfe ) {
                stripIndex = 5;
            }
        }
    }
}
