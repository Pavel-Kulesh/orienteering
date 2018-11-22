package com.itacademy.jd2.pk.hop.web.tag;

import java.io.DataInput;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Length;
import io.jenetics.jpx.Route;
import io.jenetics.jpx.Speed;
import io.jenetics.jpx.Speed.Unit;
import io.jenetics.jpx.Track;
import io.jenetics.jpx.TrackSegment;
import io.jenetics.jpx.WayPoint;
import io.jenetics.jpx.geom.Geoid;

public class MYGPX {

	public static void seeTrack(String resultParse, MultipartFile input) {

		GPX gpx1 = null;
		try {
		//	gpx1 = GPX.read("D:/workspase/project/docs/track.gpx");
			gpx1 = GPX.read(resultParse);
				} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(gpx1);
		System.out.println(gpx1.toString() + " to string");
		System.out.println(gpx1.getMetadata() + " metedata");
		System.out.println(gpx1.getRoutes() + " routes");
		System.out.println(gpx1.getTracks() + " tracks");
		System.out.println(gpx1.getWayPoints() + " way points");

		List<Route> list = gpx1.getRoutes();
		for (Route route : list) {
			System.out.println(route.getName() + " name");
			System.out.println(route.getType() + " type");
			List<WayPoint> pointList = route.getPoints();
			for (WayPoint wayPoint : pointList) {
				System.out.println(wayPoint.getLatitude() + " latitude ---  " + wayPoint.getLongitude() + " long");

			}
		}
		List<Track> listTrack = gpx1.getTracks();
		for (Track track : listTrack) {
			List<TrackSegment> segment = track.getSegments();
			System.out.println("segment");
			for (TrackSegment trackSegment : segment) {
				List<WayPoint> listPoint = trackSegment.getPoints();
				System.out.println(" point:  ");
				int p = 0;
				Optional<ZonedDateTime> t1 = null;
				for (WayPoint wayPoint : listPoint) {
					if (p == 0) {
						System.out.println("diff time=" + 0);
						p = 1;
						t1 = wayPoint.getTime();
					}
					Optional<Speed> speed = wayPoint.getSpeed();

					System.out.print("speed= " + speed + "  /" + wayPoint.getLatitude() + " latitude ---  "
							+ wayPoint.getLongitude() + " long");
					System.out.println("time- " + wayPoint.getTime());
					System.out.println("dif time = " + wayPoint.getTime());
					System.out.println();
				}

			}

		}
		final Length length = gpx1.tracks().flatMap(Track::segments).flatMap(TrackSegment::points)
				.collect(Geoid.WGS84.toPathLength());
		System.out.println("length - " + length);

		Unit unit = Unit.valueOf("KILOMETERS_PER_HOUR");
		Speed x = Speed.of(3.6, unit);
		System.out.println("speed= " + x);
		System.out.println(Unit.valueOf("KILOMETERS_PER_HOUR"));
		System.out.println("speed double: " + Speed.class);

		/*
		 * Speed speed = Speed.of(1, KILOMETERS_PER_HOUR);
		 * System.out.println(speed.doubleValue()+" speed");
		 */
	}

	public static void seeTrack(InputStream inputStream) {
		GPX gpx1 = null;
		try {
			gpx1 = GPX.read(inputStream);
				} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(gpx1);
		System.out.println(gpx1.toString() + " to string");
		System.out.println(gpx1.getMetadata() + " metedata");
		System.out.println(gpx1.getRoutes() + " routes");
		System.out.println(gpx1.getTracks() + " tracks");
		System.out.println(gpx1.getWayPoints() + " way points");

		List<Route> list = gpx1.getRoutes();
		for (Route route : list) {
			System.out.println(route.getName() + " name");
			System.out.println(route.getType() + " type");
			List<WayPoint> pointList = route.getPoints();
			for (WayPoint wayPoint : pointList) {
				System.out.println(wayPoint.getLatitude() + " latitude ---  " + wayPoint.getLongitude() + " long");

			}
		}
		List<Track> listTrack = gpx1.getTracks();
		for (Track track : listTrack) {
			List<TrackSegment> segment = track.getSegments();
			System.out.println("segment");
			for (TrackSegment trackSegment : segment) {
				List<WayPoint> listPoint = trackSegment.getPoints();
				System.out.println(" point:  ");
				int p = 0;
				Optional<ZonedDateTime> t1 = null;
				for (WayPoint wayPoint : listPoint) {
					if (p == 0) {
						System.out.println("diff time=" + 0);
						p = 1;
						t1 = wayPoint.getTime();
					}
					Optional<Speed> speed = wayPoint.getSpeed();

					System.out.print("speed= " + speed + "  /" + wayPoint.getLatitude() + " latitude ---  "
							+ wayPoint.getLongitude() + " long");
					System.out.println("time- " + wayPoint.getTime());
					System.out.println("dif time = " + wayPoint.getTime());
					System.out.println();
				}

			}

		}
		final Length length = gpx1.tracks().flatMap(Track::segments).flatMap(TrackSegment::points)
				.collect(Geoid.WGS84.toPathLength());
		System.out.println("length - " + length);

		Unit unit = Unit.valueOf("KILOMETERS_PER_HOUR");
		Speed x = Speed.of(3.6, unit);
		System.out.println("speed= " + x);
		System.out.println(Unit.valueOf("KILOMETERS_PER_HOUR"));
		System.out.println("speed double: " + Speed.class);

		/*
		 * Speed speed = Speed.of(1, KILOMETERS_PER_HOUR);
		 * System.out.println(speed.doubleValue()+" speed");
		 */
		
	}
	
}
