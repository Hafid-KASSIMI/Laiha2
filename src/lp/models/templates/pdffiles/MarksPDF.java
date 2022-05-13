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
package lp.models.templates.pdffiles;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;
import lp.Settings;
import lp.models.templates.pdffiles.metrics.StudentPRectangle;
import net.mdrassty.object.Group;
import net.mdrassty.object.Student;
import net.mdrassty.object.Matter;
import net.mdrassty.util.SEMESTER;
import org.apache.pdfbox.pdmodel.PDPage;

/**
 *
 * @author Hafid KASSIMI (@mdrassty.net)
 */
public abstract class MarksPDF extends ListPDF {
    
    protected final DecimalFormat DECIMAL = new DecimalFormat();
    protected final int TESTS_COUNT = 4;

    public MarksPDF() {
        super();
        DECIMAL.applyPattern("00.00");
    }
    
    protected void placeStudent(int index, PDPage page, Student stu,
            Matter matter, SEMESTER semester) {
        placeStudent(index, page, stu);
        Float mark;
        for ( int i = 0; i < TESTS_COUNT; i++ ) {
            mark = stu.getCCMark(matter.getCode(), semester, i);
            if ( mark != null )
                pdf.placeString(pageMetrics.getStudentRow(index).getMark(i), page, DECIMAL.format(mark));
        }
        mark = stu.getAiMark(matter.getCode(), semester);        
        if ( mark != null )
            pdf.placeString(pageMetrics.getStudentRow(index).getAi(), page, DECIMAL.format(mark));
    }
    
    protected void add1PageGroupWithMarks(Group group, PDPage page, int start, int stuCount, 
            Matter matter, SEMESTER semester) {
        pageMetrics.prepareRows(stuCount - start);
        for ( int i = start, j = 0; i < stuCount; i++, j++ ) {
            placeStudent(j, page, group.getStudent(i), matter, semester);
            pdf.fill(pageMetrics.getHDivider(j), page);
        }
        dividePage(page);
    }
    
    protected void add1PageGroupNoMarks(Group group, PDPage page, int start, int stuCount) {
        pageMetrics.prepareRows(stuCount - start);
        for ( int i = start, j = 0; i < stuCount; i++, j++ ) {
            placeStudent(j, page, group.getStudent(i));
            pdf.fill(pageMetrics.getHDivider(j), page);
        }
        dividePage(page);
    }
    
    private void dividePage(PDPage page) {
        while ( pageMetrics.emptySpaceExists() ) {
            pdf.fill(pageMetrics.nextHDivider(), page);
        }
        if ( pageMetrics.isPageEmpty() )
            pdf.fill(pageMetrics.getHDivider(), page);
        while ( pageMetrics.hasVDividers() ) {
            pdf.fill(pageMetrics.nextVDivider(), page);
        }
    }
    
    @Override
    protected void add1PageGroup(Group grp, PDPage page, int start, int stuCount) {
        if ( unifyLevelGroupsSize )
            unifyLevSize.accept(grp, stuCount);
        if ( Settings.PREF_BUNDLE.get(pageMetrics.getTemplate() + "_INCLUDE_MARKS").equals("Y") ) {
            ArrayList<Matter> mats;
            int MATTER_CODE = 0;
            try {
                MATTER_CODE = Integer.parseInt(Settings.PREF_TEMPORARY.get(pageMetrics.getTemplate() + "_SELECTED_MATTER"));
            } catch ( NumberFormatException ex ) { }
            if ( MATTER_CODE == 0 ) {
                mats = grp.getAssociatedMatters().stream()
                        .map(mat -> new Matter(mat, Settings.PREF_TEMPORARY.get("MATTER_" + mat + "_LABEL")))
                        .collect(Collectors.toCollection(ArrayList::new));
            }
            else {
                int code = MATTER_CODE;
                mats = grp.getAssociatedMatters().stream()
                .filter(mat -> code == mat).map(mat -> new Matter(mat, Settings.PREF_TEMPORARY.get("MATTER_" + mat + "_LABEL")))
                .collect(Collectors.toCollection(ArrayList::new));
            }
            if ( mats.isEmpty() ) {
                add1PageGroupNoMarks(grp, page, start, stuCount);
            }
            else {
                ArrayList<SEMESTER> semesters = grp.getAssociatedSemesters();
                mats.forEach(mat -> {
                    semesters.forEach(sem -> {
                        PDPage newPage = pdf.clonePage(page);
                        add1PageGroupWithMarks(grp, newPage, start, stuCount, mat, sem);
                    });
                });
                pdf.removePage(page);
            }
        }
        else {
            add1PageGroupNoMarks(grp, page, start, stuCount);
        }
    }
}
