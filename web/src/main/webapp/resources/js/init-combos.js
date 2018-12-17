function initSelectElement(htmlElementId, jsonArray) {
	$('#' + htmlElementId).find('option').remove().end(); 
	$('#' + htmlElementId).append($("<option></option>").attr({
		"disabled" : '',
		"selected" : '',
		"value" : ''
	}).text(' -- select -- '));

	$.each(jsonArray, function(key, value) {
		$('#' + htmlElementId)
				.append(
						$("<option></option>").attr("value", value.id).text(
								value.name));
	});
	$('#' + htmlElementId).formSelect();
}

function initComboboxes(contextUrl) {
	$.get(contextUrl + "/registration/countries", function(countriesArray) {
		initSelectElement('countryId', countriesArray);

		$("#countryId").change(
				function() {
					var selectedId = $(this).val();
					$.get(contextUrl + "/registration/cities?countryId="
							+ selectedId, function(citiesArray) {
						initSelectElement('cityId', citiesArray);
					})
				});
	});
}
