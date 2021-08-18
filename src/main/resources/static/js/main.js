/**
 * 
 */

$(document).ready(function(){
	
	$('.mnBtn, .table .meBtn, .table .proBtn, .stnBtn, .table .stBtn').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		var text = $(this).attr('id');
	
		 if(text=='EditUser'){ 
			
			$.get(href,function(member,status){
				$('.umyForm #id').val(member.id);
				$('.umyForm #personalId').val(member.personalId);
				$('.umyForm #FName').val(member.fname);
				$('.umyForm #Lname').val(member.lname);
				
			});
			
		$('.umyForm #uexampleModal').modal();
		 }else if(text=='EditState'){ 
				
				$.get(href,function(state,status){
					$('.smyForm #id').val(state.id);
					$('.smyForm #name').val(state.name);
				});
				
			$('.smyForm #sexampleModal').modal();
			 }else if(text=='NUser'){
			$('.umyForm #id').val('');
			$('.umyForm #personalId').val('');
			$('.umyForm #FName').val('');
			$('.umyForm #Lname').val('');
			$('.umyForm #uexampleModal').modal();
		}else if(text=='NState'){
			$('.smyForm #id').val('');
			$('.smyForm #name').val('');
			$('.smyForm #sexampleModal').modal();
		}else{
			$.getJSON(href, function(data){
				var boxes='<table class="table table-hover"><thead><tr><td>Id</td><td>تاریخ</td></tr></thead><tbody id="tbodyt">';
				for(i in data){
					  boxes += "<tr><td>"+data[i].id+"</td><td>"+data[i].d_date+"</td>" +
				  		
				  		"<td><a class=\"btn btn-primary urBtn\" href=\"ViewReports/?bid="+data[i].id+"\" >گزارش</a></td>"+
				  				"</tr>";
					};
					var add = document.getElementById('RUser');
					add.innerHTML = boxes+"</tbody><table>";
			});
			$('.repmyForm #repexampleModal').modal();
		}
	});
	
	$('.table .mdelBtn').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		var text = $(this).attr('id');
		if(text == 'DeleteUser'){
			$('#udelModal #udelRef').attr('href' ,href);
			$('#udelModal').modal();
		}
		
	});   
});