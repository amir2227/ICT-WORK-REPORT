function myFunc(){
		$('.table .qwBtn').on('click',function(){
		var href = $(this).attr('id');
		$.getJSON('http://localhost:8080/findAllReq/tajhiz/?id='+href,function(req){
	 		var gencheckboxes = "";
	 		var i=0;
	 		var te = "";
	 		
	 		
	 		
			  for ( i;i<req.length; i++){
		console.log(req[i].monghazi);	 
				  switch(req[i].monghazi){
					  case 0:
						  te="وسایل هنوز به انباردار تحویل داده نشده است";
						  break;
					  case 1:
						  te="وسایل بصورت کامل و سالم به انباردار تحویل داده شده است";
						  break;
					  case 2:
						  te="وسایل امروز باید به انباردار تحویل داده شود";
						  break;
					  case 3:
						  te="وسایل بصورت ناقص به انباردار تحویل داده شده است";
						  break;
					  default :
						  te="انباردار هنوز به کاربر وسایل را تحویل نداده است";
						  break;
				  }
				  
				  gencheckboxes += "<tr><td>"+req[i].namedarkhastdahande+"</td>" +
				  		"<td>"+ req[i].tarikh+ "   "+req[i].saatd+"</td>" +
				  				"<td>"+req[i].tarikhtahvil +"   "+req[i].saatt+"</td>"+
				  				"<td>"+te+"</td></tr>";
				 console.log("tajhiz============>"+req[i].tajhizats)
			  };
			  var dwrap = document.getElementById('tajhizdarkhastuser');
		      dwrap.innerHTML=gencheckboxes;
			  var num = document.getElementById('nu');
			  num.innerHTML="<h3> تعداد دفعات استفاده شده : "+i+"</h3>";
	 	});
	 	$.getJSON('http://localhost:8080/findOneTajhiz/?id='+href,function(taj){
	 		var ud = " <h3>"+taj.state+"</h3>";
	 		
	 		var e = document.getElementById('ud');
	 		e.innerHTML = ud;
	 	});
		
		$('.qmyForm #qexampleModal').modal();
		});
	}
function userFunc(){
	$('.table .urBtn').on('click',function(){
		var pid = $(this).attr('id');
		$.getJSON('http://localhost:8080/findAllReq/user/?name='+pid,function(user){
	 		var gencheckboxes = "";
	 		var main="";
	 		var i=0;
	 		var q=0;
	 		var te = "";
	 		var tj = [];
	 		var str = [];

	 		for (i; i<user.length;i++){
	 			str[i] = "";
	 			for(var j=0;j<user[i].tajhizats.length;j++){
	 			
	 				tj[q]= user[i].tajhizats[j].name;
	 				
	 				str[i] += user[i].tajhizats[j].name +"-"; 
	 			 q++;
	 			};
	 			
	 			  switch(user[i].monghazi){
				  case 0:
					  te="وسایل هنوز به انباردار تحویل داده نشده است";
					  break;
				  case 1:
					  te="وسایل بصورت کامل و سالم به انباردار تحویل داده شده است";
					  break;
				  case 2:
					  te="وسایل امروز باید به انباردار تحویل داده شود";
					  break;
				  case 3:
					  te="وسایل بصورت ناقص به انباردار تحویل داده شده است";
					  break;
				  default :
					  te="انباردار هنوز به کاربر وسایل را تحویل نداده است";
					  break;
			  }
			  
			  main += "<tr><td>"+user[i].id+"</td>" +
			  		"<td>"+ user[i].tarikh+ "   "+user[i].saatd+"</td>" +
			  				"<td>"+user[i].tarikhtahvil +"   "+user[i].saatt+"</td>"+
			  				"<td>"+str[i]+"</td>"+
			  				"<td>"+te+"</td></tr>";
			console.log("str[i]-->"+str[i]);
		  };
		  var ss = counterv(tj);
		 console.log(ss["camera"]);
		  for(var x=0;x<unique(tj).length;x++){
			  gencheckboxes += "<tr><td>"+tj[x]+"</td>" +
		  		"<td>"+ss[tj[x]]+"</td>"+
		  		"</tr>";
		  }
		  console.log("unique----->"+unique(tj));
		  console.log("tj----->"+tj);
		  var dwrap = document.getElementById('userRepo');
	      dwrap.innerHTML=gencheckboxes;
	      var add= document.getElementById('userRepomain');
	      add.innerHTML = main;
		  var num = document.getElementById('nu');
		  num.innerHTML="<h3> تعداد دفعات استفاده شده : "+i+"</h3>";
	 		
		});
		
		$('.usmyForm #usexampleModal').modal();
	});
}
function unique(list) {
	  var result = [];
	  $.each(list, function(i, e) {
	    if ($.inArray(e, result) == -1) result.push(e);
	  });
	  return result;
	}
function counterv(arr){
	
   var counts = {};

jQuery.each(arr, function(key,value) {
  if (!counts.hasOwnProperty(value)) {
    counts[value] = 1;
  } else {
    counts[value]++;
  }
});
return counts;
}