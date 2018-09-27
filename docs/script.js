ymaps.ready(init);

function init() {
	var map = new ymaps.Map('map', {
		center : [ 53.7, 24 ],
		zoom : 3,
		type : 'yandex#hybrid',
		controls : [ 'zoomControl' ]
	}, {
		restrictMapArea : [ [ 53.65, 23.7 ], [ 53.72, 24.3 ] ]
	});
	map.controls.get('zoomControl').options.set({
		size : 'small'
	});

	var data = ymaps.geoXml
			.load(
					'https://drive.google.com/uc?export=download&id=1OEGk5BpFE5ULSxrcGR7nePA0W4rEsDUg')
			.then(function(res) {
			//	alert('success');
				map.geoObjects.add(res.geoObjects);
			})	.catch(function (err){
				//alert('error'+JSON.stringify(err)+JSON.stringify(details));		
			});

	
	
	console.log(JSON.stringify(data));

}