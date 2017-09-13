package com.applab.goodmorning.Event.decorater;

import android.util.Log;

import com.applab.goodmorning.Event.fragment.MonthFragment;
import com.applab.goodmorning.R;
import com.applab.goodmorning.Utilities.Utilities;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 22-Mar-16.
 */
public class CalenderDecorator implements DayViewDecorator {
    private Date mDate = null;
    private String mDateFormat = "yyyy-MM-dd";

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return Utilities.setCalendarDate(mDateFormat, day.getDate()).equals(Utilities.setCalendarDate(mDateFormat, mDate));
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(10, R.color.color_red_text));
    }

    public void setCalendar(Date date) {
        this.mDate = date;
    }
}
