package com.conference.ws.rest.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

import com.conference.ws.rest.entity.Talk;
import com.conference.ws.rest.modelentity.TalkBinding;
import com.conference.ws.rest.utility.Util;

public class TalkBLL {

	private static final SimpleDateFormat SIMPLEDATEFORMAT = new SimpleDateFormat("hh:mm a");

	public static List<TalkBinding> Organize(List<Talk> talks) {

		List<TalkBinding> talkBinding = new ArrayList<TalkBinding>();

		Calendar calendar, calendarAux;
		calendar = Calendar.getInstance();
		calendarAux = Calendar.getInstance();

		setHour(calendar, 9, calendarAux, 12);

		int minute1, minute2, resultMinute, cont;
		int hour;
		Boolean fijarHora = false;
		cont = 1;

		do {

			if (calendar.get(Calendar.HOUR_OF_DAY) == 9 && calendar.get(Calendar.MINUTE) == 0)
				talkBinding.add(new TalkBinding("Track " + cont++));

			minute1 = (calendarAux.get(Calendar.HOUR_OF_DAY) - calendar.get(Calendar.HOUR_OF_DAY)) * 60;
			minute2 = calendarAux.get(Calendar.MINUTE) - calendar.get(Calendar.MINUTE);
			resultMinute = minute1 + minute2;

			for (Talk item : talks) {

				if (item.getMinutes() <= resultMinute) {

					talkBinding.add(setTalk(calendar, item.getName()));

					calendar.add(Calendar.MINUTE, item.getMinutes());

					talks.remove(item);

					break;
				}
				fijarHora = true;
			}

			hour = calendar.get(Calendar.HOUR_OF_DAY);

			if (talks.size() == 0) {
				if (hour <= 12) {

					setHour(calendar, 12, calendarAux, 16);

					talkBinding.add(lunchOrNetwork(true, calendar));
					talkBinding.add(lunchOrNetwork(false, calendarAux));

				} else if (hour <= 17) {
					talkBinding.add(lunchOrNetwork(false, calendar));
					setHour(calendar, 9, calendarAux, 12);
				}

			} else if (fijarHora) {

				if (hour <= 12) {

					setHour(calendar, 12, calendarAux, 0);

					talkBinding.add(lunchOrNetwork(true, calendar));
					setHour(calendar, 13, calendarAux, 17);

				} else if (hour <= 17) {

					talkBinding.add(lunchOrNetwork(false, calendar));
					setHour(calendar, 9, calendarAux, 12);
				}
				fijarHora = false;
			}

		} while (talks.size() != 0);

		return talkBinding;

	}

	private static TalkBinding lunchOrNetwork(Boolean lunch, Calendar calendar) {
		return setTalk(calendar, lunch ? "Lunch" : "Networking Event");
	}

	private static void setHour(Calendar calendar, int hour, Calendar calendarAux, int hourAux) {
		Util.setHourOfDay(calendar, hour);
		Util.setHourOfDay(calendarAux, hourAux);
	}

	private static TalkBinding setTalk(Calendar calendar, String text) {
		return new TalkBinding(SIMPLEDATEFORMAT.format(calendar.getTime()).replace("a. m.", "AM").replace("p. m.", "PM")
				+ "  " + text);
	}
}
