$(document).ready(function(){
	

	$('.ts').on('click',function(e){
		e.preventDefault();
		var from = $('#pcal1').val();
		var to = $('#pcal2').val();
		$.getJSON('http://192.168.84.28/search/date/?from='+from+'&to='+to , function(data){
			var boxes='<table class="table table-hover"  id="excel" data-tableName="Test Table 1"><thead><tr><td>نام و نام خانوادگی</td>'+
			'<td>دسته بندی</td><td>عنوان</td><td>توضیحات</td><td>تاريخ</td><td>وضعیت</td></tr></thead><tbody id="tbodyt">';
			for(i in data){	
			  boxes += "<tr><td>"+data[i].username+"</td>" +"<td>"+ data[i].state +"</td>"+
		  		"<td>"+ data[i].title +"</td><td>"+data[i].description+"</td><td>"+data[i].d_date+"</td><td>"+data[i].is_complete+"</td>" +		
		  		"</tr>";
				};
			
			
			var add = document.getElementById('tableadded');
			add.innerHTML = boxes+'</tbody><table><button onclick="ttoexcel()">Export to XLS</button>';
		});
	});
	
	$('.tu').on('change',function(e){
		e.preventDefault();
		var s = $('#findbyState').val();
		$.getJSON('http://192.168.84.28/search/state/?state='+s , function(data){
			var boxes='<table class="table table-hover"><thead><tr style="font-size: 16px; font-weight: bold;" ><td>نام و نام خانوادگی</td>'+
			'<td>دسته بندی</td><td>عنوان</td><td>توضیحات</td><td>تاريخ</td><td>وضعیت</td></tr></thead><tbody id="tbodyt">';
			for(i in data){	
			  boxes += "<tr><td>"+data[i].username+"</td>" +"<td>"+ data[i].state +"</td>"+
		  		"<td>"+ data[i].title +"</td><td>"+data[i].description+"</td><td>"+data[i].d_date+"</td><td>"+data[i].is_complete+"</td>" +		
		  		"</tr>";
				};
			
			
			var add = document.getElementById('tableadded');
			add.innerHTML = boxes+'</tbody><table><button onclick="ttoexcel()">Export to XLS</button>';
		});
	});
	

	$('#profile').on('click',function(e){
		e.preventDefault();
		$('.pmyForm #pexampleModal').modal(); 	
	});
	
});





















