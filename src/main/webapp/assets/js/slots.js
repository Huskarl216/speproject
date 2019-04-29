$(document).ready(function () {

	/*
	var dates=[{id:"1",date:"27/03/2019"},{id:"2",date:"28/03/2019"},{id:"3",date:"01/04/2019"}];

	S1={slotid:"1",dateid:"1",date:"27/03/2019",time:"10 to 11",status:1};
	S2={slotid:"2",dateid:"1",date:"27/03/2019",time:"11 to 12",status:0};
	S3={slotid:"3",dateid:"2",date:"28/03/2019",time:"10 to 11",status:1};
	S4={slotid:"4",dateid:"3",date:"01/04/2019",time:"10 to 11",status:0};

	slots=[S1,S2,S3,S4];

	var counselor_id=1;
//	displayDates(dates);
//	assignSlots(slots);
	*/
	
	$.urlParam = function(name){
	    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
	    //alert(results);
	    if (results==null) {
	       return null;
	    }
	    return decodeURI(results[1]) || 0;
	}
	
	counselor_id = $.urlParam("id");
	//alert(counselor_id);
	getDatesByCounselorId(counselor_id);
	

	/*
	$(".classdatediv").on("click",function () {
		var dateid=$(this).attr("id").split("_")[1];
		$("#idslotlist_"+dateid).slideToggle("fast");
	});
	*/

	$("body").on("click",'.classdatediv',function () {
		var dateid=$(this).attr("id").split("_")[1];
		$("#idslotlist_"+dateid).slideToggle("fast");
	});
	
	$("body").on("click",'.classrequestbutton',function(){
		var slot_id=$(this).attr("id").split("_")[1];
		addRequest(1,slot_id);
		changeSingleSlotToRequestedSlot(slot_id);			
	});

	function displayDates(table) {
		var str="";

		
		for (var i = 0; i < table.length; i++) {
//			var id=table[i].id;
//			var date=table[i].date;
//			console.log(table[i]);
//			console.log(Object.keys(table[i]));

			var id=table[i].idDate;
			var date=table[i].dateValue;
//			alert(id);
//			alert(date);
			var addition="<div class='classday'><div class='classdatediv' id='iddatediv_"+id+"'><a id='iddatea_"+id+"'> "+date+" </a></div> <div class='classslotlist' id='idslotlist_"+id+"'> </div></div>";
			str+=addition;
		}
//		alert(str);
		$("#iddateslist").append(str);
	}

	function assignSlots(table){
		for (var i = 0; i < table.length; i++) {
//			var dateid=table[i].dateid;
//			var time=table[i].time;
			console.log(Object.keys(table[i]));
			var dateid=table[i].date_id;
			var time=table[i].slotValue;
			var status=table[i].status;
			var slot_id=table[i].idSlot;
//			alert(dateid);
//			alert(time);
//			alert(status);
//			alert(slot_id);
//			alert(status);
			if(status==0){				
				$("#idslotlist_"+dateid).append("<div class='classslot ' id='idslot_"+slot_id+"' > <a> "+time+" </a> <button class='classrequestbutton' id='idrequestbutton_"+slot_id+"'> Request </button> </div>");	
			}
			else{
				$("#idslotlist_"+dateid).append("<div class='classslot classslotBooked '  id='idslot_"+slot_id+"' > "+time+" </div>");	
			}
			//Later based on booking condition you will change class name
		}
	}
	
	function changeSlotsToRequestedSlots(table){
//		alert(table.length);
		for(var i=0;i<table.length;i++){
			console.log(Object.keys(table[i]));
			var object = table[i];
			var slot_id=object.slot_id;
//			alert(slot_id);
			
			changeSingleSlotToRequestedSlot(slot_id);
		}
	}
	
	function changeSingleSlotToRequestedSlot(slot_id){
//		alert(slot_id);
//		alert("#idrequestbutton_"+slot_id);
//		$("#idrequestbutton_"+slot_id).hide();	
		$("#idrequestbutton_"+slot_id).remove();	
//		alert(slot_id);
//		$("#idslot_"+slot_id).removeChild("#idrequestbutton_"+slot_id);
		$("#idslot_"+slot_id).addClass(" classslotRequested");
	}
	
	function changeSlotsToBookedSlots(table){
		alert(table);
		for(var i=0;i<table.length;i++){
			console.log(Object.keys(table[i]));
			var object = table[i];
			var slot_id=object.slot_id;			
			changeSingleSlotToBookedSlot(slot_id);
		}
	}
	
	function changeSingleSlotToBookedSlot(slot_id){
//		alert("Hi");
		$("#idrequestbutton_"+slot_id).remove();	
//		alert("Hello");
//		alert(slot_id);
		$("#idslot_"+slot_id).removeClass("classslotBooked");
		$("#idslot_"+slot_id).addClass(" classslotBookedByYou");
	}

	
	function getDatesByCounselorId(coun_id){
		
		$.ajax({
			url:"http://localhost:8080/Counselor_Appointment/webapi/Date/getDatesByCounselorId/"+coun_id,
			type:"POST",
			cache:false,
			contentType:false,
			processData: false,
	        success : function(data){
	        	
	        	if(data)
	        	{
//	        		alert(data);
	        		displayDates(data);

	        		for(var i=0;i<data.length;i++){
	        			getSlotsByDateId(data[i].idDate);
	        		}
	        		
	        		getRequestsByStudentIdAndCounselorId(1,coun_id);
	        		getBookingsByStudentIdAndCounselorId(1,coun_id);
                }
	        	
	        	else
	        		alert("failed to get dates");
	        },
	        
	        error : function(data){
	        	alert(data);
	        	alert("failed to get dates !");
	        }
	        
		});
	};
	
	function getSlotsByDateId(date_id){
		
		$.ajax({
			url:"http://localhost:8080/Counselor_Appointment/webapi/Slot/getSlotsByDateId/"+date_id,
			type:"POST",
			cache:false,
			contentType:false,
			processData: false,
	        success : function(data){
	        	
	        	if(data)
	        	{
	        		assignSlots(data);
                }
	        	
	        	else
	        		alert("failed to get slot");
	        },
	        
	        error : function(data){
	        	alert("failed to get slot !");
	        }
	        
		});
	};
	
	function getRequestsByStudentIdAndCounselorId(student_id,counselor_id){
		var object = JSON.stringify({
			student_id : student_id,
			counselor_id : counselor_id,
		});
		
		$.ajax({
			url:"http://localhost:8080/Counselor_Appointment/webapi/Student_Slot_Request/getRequestsByStudentIdAndCounselorId",
			data: object,
//			url:"http://localhost:8080/flipkart/webapi/items/getByPrice",
//			url:"http://localhost:8080/flipkart/webapi/items/getReport",
			type:"POST",
			dataType: "json",
			contentType: "application/json",
	        success : function(data){
	        	
	        	if(data)
	        	{
//	        		alert(data);
	        		changeSlotsToRequestedSlots(data);
                }
	        	
	        	else
	        		alert("failed to get requests");
	        },
	        
	        error : function(data){
	        	alert("failed to get requests !");
	        }
	        
		});
	};

	function getBookingsByStudentIdAndCounselorId(student_id,counselor_id){
		var object = JSON.stringify({
			student_id : student_id,
			counselor_id : counselor_id,
		});
		
		$.ajax({
			url:"http://localhost:8080/Counselor_Appointment/webapi/StudentBookingHistory/getBookingsByStudentIdAndCounselorId",
			data: object,
//			url:"http://localhost:8080/flipkart/webapi/items/getByPrice",
//			url:"http://localhost:8080/flipkart/webapi/items/getReport",
			type:"POST",
			dataType: "json",
			contentType: "application/json",
	        success : function(data){
	        	
	        	if(data)
	        	{
//	        		alert(data);
	        		changeSlotsToBookedSlots(data);
                }
	        	
	        	else
	        		alert("failed to get requests");
	        },
	        
	        error : function(data){
	        	alert("failed to get requests !");
	        }
	        
		});
	};

	
	function addRequest(student_id,slot_id){
		var object = JSON.stringify({
			student_id : student_id,
			slot_id : slot_id,
		});
		
//		alert(student_id);
//		alert(slot_id)
		
		$.ajax({
			url:"http://localhost:8080/Counselor_Appointment/webapi/Student_Slot_Request/addRequest",
			data: object,
//			url:"http://localhost:8080/flipkart/webapi/items/getByPrice",
//			url:"http://localhost:8080/flipkart/webapi/items/getReport",
			type:"POST",
			dataType: "json",
			contentType: "application/json",
	        success : function(data){
	        	
	        	if(data)
	        	{
	        		if(data==0){
	        			location.reload();
	        		}
	        		
                }
	        	
	        	else
	        		alert("failed to add request");
	        },
	        
	        error : function(data){
	        	//location.reload();
	        	alert("failed to add request !");
	        }
	        
		});		
	};
});