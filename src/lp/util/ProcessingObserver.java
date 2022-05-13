/*
 * Copyright (C) 2021 Sicut
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
/* 
    Author     : H. KASSIMI
*/

package lp.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleIntegerProperty;

public class ProcessingObserver {
    private final ConcurrentHashMap<String, SimpleIntegerProperty> jobs = new ConcurrentHashMap();
    private int total;
    private LocalDateTime startTime, endTime;

    public ProcessingObserver() {
        total = 1;
    }

    public void setJobs(String key, int jobs) {
        this.jobs.put(key, new SimpleIntegerProperty(jobs));
        total = this.jobs.values().stream().collect(Collectors.summingInt(o -> o.get()));
    }
    
    public void start() {
        startTime = LocalDateTime.now();
    }
    
    public void stop(){
        endTime = LocalDateTime.now();
    }
    
    public SimpleIntegerProperty getJobsProperty(String key) {
        return jobs.get(key);
    }
    
    public synchronized void decrement(String key){
        SimpleIntegerProperty value = jobs.get(key);
        if (value != null) {
            value.set(value.get() - 1);
        }
    }
    
    public boolean isDone(String key) {
        SimpleIntegerProperty value = jobs.get(key);
        if (value != null)
            return value.get() < 1;
        return false;
    }
    
    public boolean isDone() {
        return jobs.values().stream().filter(value -> value.get() < 1).count() < 1;
    }
    
    public double getPercentage(String key) {
        SimpleIntegerProperty value = jobs.get(key);
        if (value != null)
            return 1 - value.get() / (double)total;
        return -1;
    }
    
    public double getPercentage() {
        return 1 - jobs.values().stream().collect(Collectors.summingInt(o -> o.get())) / (double)total / jobs.size();
    }
    
    public float getDuration() {
        return ChronoUnit.MILLIS.between(startTime, endTime) / 1000f;
    }
}
