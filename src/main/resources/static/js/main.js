/**
 * 
 */

$(document).ready(function(){
	
	$('.mnBtn, .tmnBtn, .locnBtn, .table .meBtn, .empBtn, .table .empeBtn, .table .locBtn, .table .teBtn, .table .feBtn, .fnBtn').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		var text = $(this).attr('id');
	
		 if(text=='EditUser'){ 
			
			$.get(href,function(member,status){
				$('.umyForm #id').val(member.id);
				$('.umyForm #personalId').val(member.personalId);
				$('.umyForm #FName').val(member.fname);
				$('.umyForm #Lname').val(member.lname);
				$('.umyForm #job').val(member.job);
			});
			
		$('.umyForm #uexampleModal').modal();
		 }else if(text=='QTajhiz'){
			  
		 }else if(text == 'nLoc'){
			 $('.locmyForm #id').val();
			 $('.locmyForm #name').val();
			 $('.locmyForm #locexampleModal').modal();
		 }else if(text == 'ELoc'){
			 $.get(href,function(data){
					$('.locmyForm #id').val(data.id);
					$('.locmyForm #name').val(data.name);
					
				});
				
			 $('.locmyForm #locexampleModal').modal();
		 }
		 else if(text == 'OpenForm'){
			
			 $('.omyForm #fid').val(href);
			 $('.omyForm #oexampleModal').modal();
		 }
		 else if(text=='EditEmployee'){
			 
			 $.get(href,function(employee,status){
					$('.emyForm #id').val(employee.id);
					$('.emyForm #barcode').val(employee.barcode);
					$('.emyForm #FirstName').val(employee.first_name);
					$('.emyForm #LastName').val(employee.last_name);
					$('.emyForm #Mac').val(employee.mac_address);
					$('.emyForm #imei').val(employee.imei);
				});
				
			$('.emyForm #eexampleModal').modal(); 
			 
		}else if(text=='newoffice'){
			
			$('.fmyForm #fexampleModal').modal(); 	
		}
		 else if(text=='EditTajhiz'){
		
			$.get(href,function(tajhiz,status){
				$('.tmyForm #id').val(tajhiz.id);
				$('.tmyForm #amvalid').val(tajhiz.amvalid);
				$('.tmyForm #state').val(tajhiz.state);
				$('.tmyForm #serial').val(tajhiz.serial_number);
				$('.tmyForm #name').val(tajhiz.name);
				$('.tmyForm #model').val(tajhiz.model);
				$('.tmyForm #brand').val(tajhiz.brand);
				$('.tmyForm #garanti').val(tajhiz.garanti);
				$('.tmyForm #description').val(tajhiz.description);
			});
			
		$('.tmyForm #texampleModal').modal(); 
		}else if(text=='NTajhiz'){
		
			$.get(href,function(tajhiz,status){
				$('.tmyForm #id').val();
				$('.tmyForm #amvalid').val();
				$('.tmyForm #type').val();
				$('.tmyForm #daste').val();
				$('.tmyForm #name').val();
				$('.tmyForm #model').val();
				$('.tmyForm #brand').val();
				$('.tmyForm #garanti').val();
				$('.tmyForm #description').val();
			});
			
		$('.tmyForm #texampleModal').modal(); 
		}else if(text=='NUser'){
			$('.umyForm #uexampleModal').modal();
			$('.umyForm #id').val('');
			$('.umyForm #personalId').val('');
			$('.umyForm #FName').val('');
			$('.umyForm #Lname').val('');
			$('.umyForm #DMac').val('');
		}else{
			$('.emyForm #eexampleModal').modal();
			$('.emyForm #id').val('');
			$('.emyForm #barcode').val('');
			$('.emyForm #FirstName').val('');
			$('.emyForm #LastName').val('');
			$('.emyForm #Mac').val('');
		}
	});
	
	$('.table .empdelBtn, .table .tdelBtn,.table .ldelBtn').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		var text = $(this).attr('id');
		if(text == 'DeleteTajhiz'){
			$('#tdelModal #tdelRef').attr('href' ,href);
			$('#tdelModal').modal();
		}else if(text == 'delLoc'){
			$('#lmyModal #ldelRef').attr('href' ,href);
			$('#lmyModal').modal();
		}
		else{
		
		$('#empdelModal #empdelRef').attr('href' ,href);
		$('#empdelModal').modal();
		}
	});
	$('.print').on('click',function(ev){
		ev.preventDefault();
		   var mode ='iframe';
		   var close = mode =="popup";
		   var options = {mode : mode, popClose : close};
		  $('div.PrintArea').printArea(options); 
	   });
	   
});