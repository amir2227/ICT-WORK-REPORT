$(document).ready(function(){
	

		
	$('#txtsearch').keyup(function(e){
		e.preventDefault();
		var v = $('#txtsearch').val();
		window.location.replace('http://localhost:8080/members/?keyword='+v);
		
		
	});
	$('.tb').keyup(function(e){
		e.preventDefault();
		var s = $('#findbyDate').val();
		$.getJSON('http://localhost:8080/search/date/?keyword='+s , function(data){
			var boxes='<table class="table table-hover"><thead><tr><td>نام و نام خانوادگی</td>'+
			'<td>عنوان</td><td>توضیحات</td><td>وضعیت</td></tr></thead><tbody id="tbodyt">';
			for(i in data){	
			  boxes += "<tr><td>"+data[i].username+"</td>" +
		  		"<td>"+ data[i].title +"</td><td>"+data[i].description+"</td><td>"+data[i].is_complete+"</td>" +		
		  		"</tr>";
				};
			
			
			var add = document.getElementById('tableadded');
			add.innerHTML = boxes+"</tbody><table>";
		});
	});
	

	$('#profile').on('click',function(e){
		e.preventDefault();
		$('.pmyForm #pexampleModal').modal(); 	
	});
	
});





















