/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Tobias
 */
public class Timetable implements Serializable{

    List<Subject> references = new LinkedList<Subject>();
    String name = "";
    int lessons = 8;
    Subject[][] subjects = new Subject[7][10];
    int[] times = new int[10];
    boolean[] days = new boolean[7];

    public Timetable() {
        init();
    }

    public Timetable(String name) {
        init();
        this.name = name;
    }

    public void updateReferences() {
        for (int i = 0; i < subjects.length; i++) {
            for (int j = 0; j < subjects[0].length; j++) {
                if (references.size() > 0) {
                    boolean n = true;
                    for (int c = 0; c < references.size(); c++) {
                        if (subjects[i][j].equals(references.get(c))) {
                            n = false;
                            break;
                        }
                    }
                    if (n && subjects[i][j].getSubject().length() > 0) {
                        references.add(subjects[i][j]);
                    }
                } else if (subjects[i][j].getSubject().length() > 0) {
                    references.add(subjects[i][j]);
                }
            }
        }
    }

    public List<Subject> getAutocompleteOptions(String subject) {
        List<Subject> options = new ArrayList<Subject>();

        for (Subject s : references) {
            if (s.getSubject().startsWith(subject)) {
                options.add(s);
            }
        }

        return options;
    }

    public void init() {
        for (int i = 0; i < subjects.length; i++) {
            for (int j = 0; j < subjects[0].length; j++) {
                subjects[i][j] = new Subject("");
            }
        }

        for (int i = 0; i < times.length; i++) {
            times[i] = 0;
        }

        days = new boolean[]{true, true, true, true, true, false, false};
    }

    public String getSubjectText(int i, int j) {
        return subjects[i][j].getSubject();
    }

    public String getRoomText(int i, int j) {
        return subjects[i][j].getRoom();
    }

    public String getTeacherText(int i, int j) {
        return subjects[i][j].getTeacher();
    }

    public void setSubjectText(String s, int i, int j) {
        subjects[i][j].setSubject(s);
    }

    public void setRoomText(String s, int i, int j) {
        subjects[i][j].setRoom(s);
    }

    public void setTeacherText(String s, int i, int j) {
        subjects[i][j].setTeacher(s);
    }

    public String getTimeText(int i) {
        System.out.println(times[i]);
        String temp = String.format("%04d", times[i]);
        System.out.println(temp);
        String b = "" + temp.charAt(0) + temp.charAt(1) + ':' + temp.charAt(2) + temp.charAt(3);
        System.out.println(b);
        return b;
    }

    public void setTime(int time, int i) {
        times[i] = time;
    }

    public boolean isDayDisplayed(int i) {
        return days[i];
    }

    public void setDayDisplayed(boolean day, int i) {
        days[i] = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLessons() {
        return lessons;
    }

    public void setLessons(int lessons) {
        this.lessons = lessons;
    }

    public void addLessonRowAbove(int index) {
        if (lessons < 10) {
            lessons++;
            for (int i = 0; i < subjects.length; i++) {
                for (int j = lessons - 1; j > index; j--) {
                    subjects[i][j] = subjects[i][j - 1];
                }
                subjects[i][index] = new Subject();
            }
        }
    }

    public void addLessonRowBelow(int index) {
        if (lessons < 10) {
            lessons++;
            for (int i = 0; i < subjects.length; i++) {
                for (int j = lessons - 1; j > index + 1; j--) {
                    subjects[i][j] = subjects[i][j - 1];
                }
                subjects[i][index + 1] = new Subject();
            }
        }
    }

    public void removeLessonRow(int index) {
        if (lessons > 1) {
            lessons--;
            for (int i = 0; i < subjects.length; i++) {
                for (int j = index; j < lessons - 1; j++) {
                    subjects[i][j] = subjects[i][j + 1];
                }
                subjects[i][lessons] = new Subject();
            }
        }
    }

}
