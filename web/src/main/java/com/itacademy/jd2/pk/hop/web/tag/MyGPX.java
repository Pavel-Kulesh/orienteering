package com.itacademy.jd2.pk.hop.web.tag;

import java.io.IOException;
import java.io.InputStream;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.itacademy.jd2.pk.hop.dao.api.entity.IPoint;
import com.itacademy.jd2.pk.hop.dao.orm.impl.entity.Point;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Track;
import io.jenetics.jpx.TrackSegment;
import io.jenetics.jpx.WayPoint;

public class MyGPX {

	public static List<IPoint> seeTrack(InputStream inputStream) {
		List<IPoint> myListPoint = new ArrayList<>();
		GPX gpx1 = null;
		try {
			gpx1 = GPX.read(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * System.out.println(gpx1); System.out.println(gpx1.toString() + " to string");
		 * System.out.println(gpx1.getMetadata() + " metedata");
		 * System.out.println(gpx1.getRoutes() + " routes");
		 * System.out.println(gpx1.getTracks() + " tracks");
		 * System.out.println(gpx1.getWayPoints() + " way points");
		 * 
		 * List<Route> list = gpx1.getRoutes(); for (Route route : list) {
		 * System.out.println(route.getName() + " name");
		 * System.out.println(route.getType() + " type"); List<WayPoint> pointList =
		 * route.getPoints(); for (WayPoint wayPoint : pointList) {
		 * System.out.println(wayPoint.getLatitude() + " latitude ---  " +
		 * wayPoint.getLongitude() + " long");
		 * 
		 * } }
		 */
		List<Track> listTrack = gpx1.getTracks();
		for (Track track : listTrack) {
			List<TrackSegment> segment = track.getSegments();
			System.out.println("segment");
			for (TrackSegment trackSegment : segment) {
				List<WayPoint> listPoint = trackSegment.getPoints();
				System.out.println(" point:  ");

				WayPoint pStart = listPoint.get(0);

				for (WayPoint wayPoint : listPoint) {
					IPoint point = new Point();
					WayPoint nextPoint = wayPoint;

					point.setLatitude(wayPoint.getLatitude().doubleValue());
					point.setLongitude(wayPoint.getLongitude().doubleValue());

					// System.out.print(wayPoint.getLatitude() + " latitude --- " +
					// wayPoint.getLongitude() + " long");
					// System.out.println("time- " + wayPoint.getTime());

					// nextPoint.getTime().get().getSecond()-pStart.getTime().get().getSecond();
					long diffInSeconds = ChronoUnit.SECONDS.between(pStart.getTime().get(), nextPoint.getTime().get());

					pStart = wayPoint;
					/*
					 * int second = nextPoint.getTime().get().getSecond();
					 */

					point.setLatitude(wayPoint.getLatitude().doubleValue());
					point.setLongitude(wayPoint.getLongitude().doubleValue());
					point.setDiffTime((int) (long) diffInSeconds);
					myListPoint.add(point);
					/*
					 * System.out.println("seconds: " + second); System.out.println("dif time = " +
					 * diffInSeconds); System.out.println();
					 */
				}

			}

		}
		/*
		 * final Length length =
		 * gpx1.tracks().flatMap(Track::segments).flatMap(TrackSegment::points)
		 * .collect(Geoid.WGS84.toPathLength()); System.out.println("length - " +
		 * length);
		 * 
		 * Unit unit = Unit.valueOf("KILOMETERS_PER_HOUR"); Speed x = Speed.of(3.6,
		 * unit); System.out.println("speed= " + x);
		 * System.out.println(Unit.valueOf("KILOMETERS_PER_HOUR"));
		 * System.out.println("speed double: " + Speed.class);
		 */
		return myListPoint;

	}

}
