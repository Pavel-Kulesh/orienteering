function initMap(lt, lg) {
		myMap = new ymaps.Map('map', {
		
		center : [ lt, lg ], 
		zoom : 8
	}, {
		searchControlProvider : 'yandex#search'
	}), myPlacemark = new ymaps.Placemark([ lt, lg ], {
		balloonContentHeader : "Main place",
		balloonContentBody : "Event center",
		balloonContentFooter : ""+lt+"-"+lg,

	});
	myMap.geoObjects.add(myPlacemark);
	
}
	
function initMapWithImage(mapId, lt1, lg1, lt2, lg2) {
		
	var map = new ymaps.Map('map', {
	        center: [(lt1+lt2)/2, (lg1+lg2)/2],
	        zoom: 8,
	        type: 'yandex#hybrid',
	        controls: ['zoomControl']
	    }, {
	               restrictMapArea: [[lt1-0.05, lg1-0.05], [lt2+0.05, lg2+0.05]] ,
	        }
		);
	    map.controls.get('zoomControl').options.set({size: 'small'});

	    var myPolygon1 = new ymaps.Polygon([
	        [[lt1-0.05, lg1-0.05],[lt2+0.05, lg1-0.05],[lt2+0.05, lg2+0.05],[lt1-0.05, lg2+0.05],]
	    ],
	
	    {},
	     {
	    	fillColor: "#FFFFFF",
	        fillMethod: 'stretch',
	        stroke: false
	    }
	);
	    map.geoObjects.add(myPolygon1);
	    
	    var myPolygon = new ymaps.Polygon([
            [[lt1, lg1],[lt2, lg1],[lt2, lg2],[lt1, lg2],]
        ],
      
        {
        
        }, {
           
       fillImageHref: "http://localhost:8081/orienteering/map/image/"+mapId,
            fillMethod: 'stretch',
            stroke: false
        }
    );
	    map.geoObjects.add(myPolygon);
	    myCollectionRoute = new ymaps.GeoObjectCollection();
	    myCollectionDistance = new ymaps.GeoObjectCollection();
	    
	    myCollectionRoute.events
        .add('mouseenter', function (e) {
         
            e.get('target').options.set('strokeWidth', 10);
        })
        .add('mouseleave', function (e) {
            e.get('target').options.set('strokeWidth',4);
        });

	    map.geoObjects.add(myCollectionRoute);
	    map.geoObjects.add(myCollectionDistance);

	    window.globalMapReference=map;
	}

function clearWay(){
	myCollectionRoute.removeAll(); 

}
function clearDistance(){
	myCollectionDistance.removeAll(); 

}
