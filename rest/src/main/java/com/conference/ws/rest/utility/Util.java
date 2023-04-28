package com.conference.ws.rest.utility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.conference.ws.rest.entity.Talk;
import com.conference.ws.rest.modelentity.TalkBinding;

public class Util {

	public static List<Talk> convertStringToTalk(List<TalkBinding> lista) {

		List<Talk> talks = new ArrayList<Talk>();

		for (TalkBinding item : lista) {

			String[] array = item.getDetail().split(" ");

			String minute = array[array.length - 1].trim();

			int minuteNumber = minute.equals("lightning") ? 5 : Integer.parseInt(minute.replace("min", ""));

			talks.add(new Talk(item.getDetail(), minuteNumber));
		}
		return talks;
	}

	public static void setHourOfDay(Calendar calendar, int hour) {
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, 0);
	}
}
