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
package lp.models.templates.workbooks.metrics;

import net.mdrassty.util.excel.XRectangle;

/**
 *
 * @author Hafid KASSIMI (@mdrassty.net)
 */
public class MarksMetrics extends ListMetrics {
    
    protected XRectangle teacher, matter, semester, teacherLbl, matterLbl, semesterLbl, yearLbl;

    public MarksMetrics() {
        super();
        
    }

    public XRectangle getTeacher() {
        return teacher;
    }

    public XRectangle getMatter() {
        return matter;
    }

    public XRectangle getSemester() {
        return semester;
    }

    public XRectangle getTeacherLbl() {
        return teacherLbl;
    }

    public XRectangle getMatterLbl() {
        return matterLbl;
    }

    public XRectangle getSemesterLbl() {
        return semesterLbl;
    }

    public XRectangle getYearLbl() {
        return yearLbl;
    }
    
}
