$(document).ready(function(){
	

	$('.btnde').on('click',function(event){
		event.preventDefault();
		var userid = $(this).attr('href');
		
			  $.getJSON('http://localhost:8080/findAllTajhiz', function(data) {
				  var gencheckboxes = "";
				  for ( var i=0;i<data.length; i++) 
				      {
				           console.log(data[i].fullname);
				           gencheckboxes = gencheckboxes + '<input type="checkbox" name="tajhizats" value="'+ data[i].id +'">' + data[i].name + '<br></input>';
				      };
				  
				      var dwrap = document.getElementById('wrapperfortestnames');
				      dwrap.innerHTML=gencheckboxes; 
			  });
			  $('.tamyForm #taexampleModal').modal();  
		});	
	$('#txtsearch').keyup(function(e){
		e.preventDefault();
		var v = $('#txtsearch').val();
		window.location.replace('http://localhost:8080/members/?keyword='+v);
		
		
	});
	$('.tb').keyup(function(e){
		e.preventDefault();
		var s = $('#findbytajhiz').val();
		$.getJSON('http://localhost:8080/search/tajhiz/'+s , function(data){
			var boxes='<table class="table table-hover"><thead><tr><td>نام</td><td>شماره اموال</td><td>مدل</td></tr></thead><tbody id="tbodyt">';
			for(i in data){
			  boxes += "<tr><td>"+data[i].name+"</td>" +
		  		"<td>"+ data[i].amvalid+ "</td>   <td>"+data[i].model+"</td>" +
		  		"<td><a class=\"btn btn-primary qwBtn\" id="+data[i].id+" onclick=\"myFunc()\">گزارش</a></td>"+		
		  		"</tr>";
			};
			
			var add = document.getElementById('tableadded');
			add.innerHTML = boxes+"</tbody><table>";
		});
	});
	
	$('.tu').keyup(function(e){
		e.preventDefault();
		var s = $('#findbyuser').val();
		$.getJSON('http://localhost:8080/search/user/'+s , function(data){
			var boxes='<table class="table table-hover"><thead><tr><td>نام و نام خانوادگی</td><td>شماره پرسنلی</td><td>شغل</td></tr></thead><tbody id="tbodyt">';
			for(i in data){
			  boxes += "<tr><td>"+data[i].fullname+"</td>" +
		  		"<td>"+ data[i].personalId+ "</td>   <td>"+data[i].job+"</td>" +
		  		"<td><a class=\"btn btn-primary urBtn\" id="+data[i].personalId+" onclick=\"userFunc()\">گزارش</a></td>"+
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
	
	$('.qBtn').click(function(e){
		e.preventDefault();
		var href = $('.qBtn').attr('id');
		$.getJSON('http://localhost:8080/findAllReq/tajhiz/?id='+href,function(req){
	 		var gencheckboxes = "";
	 		var i=0;
	 		
			  for ( i;i<req.length; i++){
				 
				  gencheckboxes += "<tr><td>"+req[i].namedarkhastdahande+"</td>" +
				  		"<td>"+ req[i].tarikh+ "   "+req[i].saatd+"</td>" +
				  				"<td>"+req[i].tarikhtahvil +"   "+req[i].saatt+"</td></tr>";
				 
			  };
			  var dwrap = document.getElementById('tajhizdarkhastuser');
		      dwrap.innerHTML=gencheckboxes;
			  var num = document.getElementById('nu');
			  num.innerHTML="<h3> تعداد دفعات استفاده شده : "+i+"</h3>";
	 	});
	 	$.getJSON('http://localhost:8080/findOneTajhiz/?id='+href,function(taj){
	 		var ud = "";
	 		for(j in taj.userdeniyed){
	 			ud += "  <h4>"+ taj.userdeniyed[j].fullname+ "    " +taj.userdeniyed[j].personalId+"</h4> ";
	 		};
	 		var e = document.getElementById('ud');
	 		e.innerHTML = ud;
	 	});
		
		$('.qmyForm #qexampleModal').modal();
	});

});





















