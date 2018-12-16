package com.itacademy.jd2.pk.hop.web.gpx;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.itacademy.jd2.pk.hop.dao.api.entity.IPoint;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.Point;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Track;
import io.jenetics.jpx.TrackSegment;
import io.jenetics.jpx.WayPoint;

public class MyGPX {

	public static List<IPoint> seeTrack(InputStream inputStream) throws IOException {
		List<IPoint> myListPoint = new ArrayList<>();
		GPX gpx1 = null;
		gpx1 = GPX.read(inputStream);
		List<Track> listTrack = gpx1.getTracks();
		for (Track track : listTrack) {
			List<TrackSegment> segment = track.getSegments();
			for (TrackSegment trackSegment : segment) {
				List<WayPoint> listPoint = trackSegment.getPoints();
				WayPoint pStart = listPoint.get(0);

				for (WayPoint wayPoint : listPoint) {
					IPoint point = new Point();
					WayPoint nextPoint = wayPoint;
					Optional<ZonedDateTime> time = wayPoint.getTime();

					if (time.isPresent()) {
						ZonedDateTime zonedDateTime = time.get();
						java.util.Date date = Date.from(zonedDateTime.toInstant());
						point.setCreated(date);
						point.setUpdated(date);
					} else {
						point.setCreated(new java.util.Date());
						point.setUpdated(new java.util.Date());
					}

					point.setLatitude(wayPoint.getLatitude().doubleValue());
					point.setLongitude(wayPoint.getLongitude().doubleValue());
					long diffInSeconds = ChronoUnit.SECONDS.between(pStart.getTime().get(), nextPoint.getTime().get());
					pStart = wayPoint;
					point.setLatitude(wayPoint.getLatitude().doubleValue());
					point.setLongitude(wayPoint.getLongitude().doubleValue());
					point.setDiffTime((int) (long) diffInSeconds);
					myListPoint.add(point);
				}
			}
		}
		return myListPoint;
	}
}
