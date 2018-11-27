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

function initMapWithImage(image, lt1, lg1, lt2, lg2) {
	var map = new ymaps.Map('map1', {
        center: [(lt1+lt2)/2, (lg1+lg2)/2],
        zoom: 2,
        type: 'yandex#hybrid',
        controls: ['zoomControl']
    }, {
               restrictMapArea: [[lt1-0.2, lg1-0.2], [lt2+0.2, lg2+0.2]] ,
              
        }
    
	);
    map.controls.get('zoomControl').options.set({size: 'small'});

var myPolygon = new ymaps.Polygon([
            // .
            [
                [lt1, lg1], 
			[lt2, lg1],
                 [lt2, lg2], 
			[lt1, lg2],
              	             
            ]
        ],
        // property.
        {
            // balun.
            balloonContent: "my map"
        }, {
            // write option geoObject.
            // background image.
        	// fillImageHref: 'D:/java_train/images/vert.png',
          fillImageHref: 'http://1920x1080hdwallpapers.com/image/201505/food/1485/garnet-delicious-red-berries.jpg',
           // fillImageHref: image,
            // 
            fillMethod: 'stretch',
            // delete contour.
            stroke: false
        }
    );
   
    map.geoObjects.add(myPolygon);


}