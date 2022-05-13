/*
 * Copyright (C) 2022 Hafid KASSIMI (@mdrassty.net)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package lp.models.templates.workbooks;

import java.util.ArrayList;
import java.util.stream.Collectors;
import lp.Settings;
import lp.models.templates.workbooks.metrics.XStudentRow;
import net.mdrassty.object.Group;
import net.mdrassty.object.Matter;
import net.mdrassty.object.Student;
import net.mdrassty.util.SEMESTER;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 *
 * @author Hafid KASSIMI (@mdrassty.net)
 */
public abstract class MarksWorkbook extends ListWorkbook {

    private final int TESTS_COUNT = 4;
    
    public MarksWorkbook() {
        super();
    }
    
    protected void placeStudent(int index, XSSFSheet sheet, Student stu, Matter matter, SEMESTER semester) {
        XStudentRow row = sheetMetrics.getStudentRow(index);
        placeStudent(index, sheet, stu);
        XSSFRow xlRow = sheet.getRow(row.getRowIndex());
        Float mark;
        for ( int i = 0; i < TESTS_COUNT; i++ ) {
            mark = stu.getCCMark(matter.getCode(), semester, i);
            if ( mark != null )
                xlRow.getCell(row.getMark(i).getX()).setCellValue(mark);
        }
        mark = stu.getAiMark(matter.getCode(), semester);
        if ( mark != null )
            xlRow.getCell(row.getAi().getX()).setCellValue(mark);
    }
    
    protected void add1PageGroupWithMarks(Group group, XSSFSheet sheet, int start, int stuCount, 
            Matter matter, SEMESTER semester) {
        sheetMetrics.prepareRows(stuCount - start);
        for ( int i = start, j = 0; i < stuCount; i++, j++ ) {
            placeStudent(j, sheet, group.getStudent(i), matter, semester);
        }
        dividePage(sheet);
    }
    
    protected void add1PageGroupNoMarks(Group group, XSSFSheet sheet, int start, int stuCount) {
        sheetMetrics.prepareRows(stuCount - start);
        for ( int i = start, j = 0; i < stuCount; i++, j++ ) {
            placeStudent(j, sheet, group.getStudent(i));
        }
        dividePage(sheet);
    }

    @Override
    protected void add1PageGroup(Group group, XSSFSheet sheet, int start, int stuCount) {
        normalRow = highlightedRow = null;
        if ( unifyLevelGroupsSize )
            unifyLevSize.accept(group, stuCount);
        if ( Settings.PREF_BUNDLE.get(sheetMetrics.getTemplate() + "_INCLUDE_MARKS").equals("Y") ) {
            ArrayList<Matter> mats;
            int MATTER_CODE = 0;
            try {
                MATTER_CODE = Integer.parseInt(Settings.PREF_TEMPORARY.get(sheetMetrics.getTemplate() + "_SELECTED_MATTER"));
            } catch ( NumberFormatException ex ) { }
            if ( MATTER_CODE == 0 ) {
                mats = group.getAssociatedMatters().stream()
                        .map(mat -> new Matter(mat, Settings.PREF_TEMPORARY.get("MATTER_" + mat + "_LABEL")))
                        .collect(Collectors.toCollection(ArrayList::new));
            }
            else {
                int code = MATTER_CODE;
                mats = group.getAssociatedMatters().stream()
                .filter(mat -> code == mat).map(mat -> new Matter(mat, Settings.PREF_TEMPORARY.get("MATTER_" + mat + "_LABEL")))
                .collect(Collectors.toCollection(ArrayList::new));
            }
            if ( mats.isEmpty() ) {
                add1PageGroupNoMarks(group, sheet, start, stuCount);
            }
            else {
                ArrayList<SEMESTER> semesters = group.getAssociatedSemesters();
                coreFile.removeSheetAt(coreFile.getSheetIndex(sheet));
                mats.forEach(mat -> {
                    semesters.forEach(sem -> {
                        validateSheetName(group.getName() + "_" + mat.getCode() + "_S" + sem.intValue());
                        add1PageGroupWithMarks(group, coreFile.cloneSheet(0, sheetName), start, stuCount, mat, sem);
                        
                    });
                });
            }
        }
        else {
            add1PageGroupNoMarks(group, sheet, start, stuCount);
        }
    }
    
    private void dividePage(XSSFSheet sheet) {
        while ( sheetMetrics.emptySpaceExists() ) {
            addEmptyRow(sheetMetrics.getNextStudentRow(), sheet);
        }
    }
}
