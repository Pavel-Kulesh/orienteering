$(document).ready(function() {
	$('select').formSelect();

	$('.datepicker').datepicker({
		selectMonths : true, 
		selectYears : 15, 
		today : 'Today',
		clear : 'Clear',
		close : 'Ok',
		closeOnSelect : false,
		format : 'yyyy-mm-dd'
	
	});
	
	
	 $('.timepicker').timepicker();

});

