function initMap(lt, lg) {
		myMap = new ymaps.Map('map', {
		
		center : [ lt, lg ], 
		zoom : 8
	}, {
		searchControlProvider : 'yandex#search'
	}), myPlacemark = new ymaps.Placemark([ lt, lg ], {
		balloonContentHeader : "Main place",
		balloonContentBody : "Event center",
		balloonContentFooter : "see tutorial",

	});

	myMap.geoObjects.add(myPlacemark);
	

}