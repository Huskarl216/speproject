$(document).ready(function () {

	var dates=[{id:"1",date:"27/03/2019"},{id:"2",date:"28/03/2019"},{id:"3",date:"01/04/2019"}];

	S1={slotid:"1",dateid:"1",date:"27/03/2019",time:"10 to 11",num:"1"};
	S2={slotid:"2",dateid:"1",date:"27/03/2019",time:"11 to 12",num:"2"};
	S3={slotid:"3",dateid:"2",date:"28/03/2019",time:"10 to 11",num:"1"};
	S4={slotid:"4",dateid:"3",date:"01/04/2019",time:"10 to 11",num:"1"};

    slots=[S1,S2,S3,S4];
    
    R1={slotid:"1",studentname:"Mahith",requestid:"1"}
    R2={slotid:"1",studentname:"Praneeth",requestid:"2"}
    R3={slotid:"1",studentname:"Vamshi",requestid:"3"}
    R4={slotid:"2",studentname:"Gnaneswer",requestid:"4"}
    R5={slotid:"4",studentname:"Alan",requestid:"5"}

    requests=[R1,R2,R3,R4,R5];

    /*
	displayDates(dates);
    assignSlots(slots);
    assignRequests(requests);
    */
    
    getDatesByCounselorId(1);
    
    /*
	$(".classdatediv").on("click",function () {
		var dateid=$(this).attr("id").split("_")[1];
		$("#idslotlist_"+dateid).slideToggle("fast");
    });*/
    
	$("body").on("click",'.classdatediv',function () {
		var dateid=$(this).attr("id").split("_")[1];
		$("#idslotlist_"+dateid).slideToggle("fast");
	});

    

	function displayDates(table) {
		var str="";

		for (var i = 0; i < table.length; i++) {
			var id=table[i].idDate;
			var date=table[i].dateValue;
			var addition="<div class='classday'><div class='classdatediv' id='iddatediv_"+id+"'><a id='iddatea_"+id+"'> "+date+" </a></div> <div class='classslotlist' id='idslotlist_"+id+"'> </div></div>";
			str+=addition;
		}
		$("#iddateslist").append(str);
	}

	function assignSlots(table){
		for (var i = 0; i < table.length; i++) {
			var dateid=table[i].date_id;
			var time=table[i].slotValue;
			var status=table[i].status;
			var slot_id=table[i].idSlot;

			//Later based on booking condition you will change class name
			if(status==1){
				$("#idslotlist_"+dateid).append("<div ><div class='classslot bookedslot' id='idslot_"+slot_id+"' ><a id='idslota_"+slot_id+"'>"+time+"</a> <a class='classBookedStudentName' id='idBookedStudentName_"+slot_id+"'> </a></div> </div>");	
			}
			else{
				$("#idslotlist_"+dateid).append("<div ><div class='classslot' id='idslot_"+slot_id+"' ><a id='idslota_"+slot_id+"'>"+time+"</a> <a class='classBookedStudentName' id='idBookedStudentName_"+slot_id+"'> </a></div> <div id='idrequestlist_"+slot_id+"' > </div>  </div>");
			}
		}
    }
    
    function assignRequests(table){
        for(var i=0;i<table.length;i++){
            request=table[i];
			console.log(Object.keys(request));

            var slotid=request.Slot_id;
            var requestid=request.idRequest;
            var name=request.name;
            var addition="<div class='classrequest'> <a id='idrequest_"+requestid+"'>"+name+"</a> <button class='classacceptbutton' id='idacceptbutton_"+slotid+"_"+requestid+"'> Accept</button></div>";
            $("#idrequestlist_"+slotid).append(addition);
        }
    }


   	$("body").on("click",'.classacceptbutton',function () {
    	
        var id=$(this).attr("id");
        list=id.split("_");
        var slotid=list[1];
        var requestid=list[2];

        //MAKE CHANGES IN DATABASES

        $("#idslot_"+slotid).addClass("bookedslot");
        $("#idrequestlist_"+slotid).empty();
        
        acceptRequest(requestid);
        
    });
        
	function addNameToBookedSlot(slot_id,student_name){
		alert(slot_id);
		alert(student_name);
		$("#idBookedStudentName_"+slot_id).append(student_name);
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
	        		
//	        		getRequestsByStudentIdAndCounselorId(1,coun_id);
//	        		getBookingsByStudentIdAndCounselorId(1,coun_id);
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
	        		for(var i=0;i<data.length;i++){
	        			var slot=data[i];
	        			var slot_id=slot.idSlot;
	        			if(slot.status==0){
	        				getRequestsBySlotIdWithStudentNames(slot_id);
	        				
	        			}
	        			else{
	        				getBookingBySlotIdWithStudentNames(slot_id);
	        			}
	        		}
                }
	        	
	        	else
	        		alert("failed to get dates");
	        },
	        
	        error : function(data){
	        	alert("failed to get dates !");
	        }
	        
		});
	};
	
	function getRequestsBySlotIdWithStudentNames(slot_id){		
		$.ajax({
			url:"http://localhost:8080/Counselor_Appointment/webapi/Student_Slot_Request/getRequestsBySlotIdWithStudentNames/"+slot_id,
			type:"POST",
			cache:false,
			contentType:false,
			processData: false,
	        success : function(data){
	        	
	        	if(data)
	        	{
	        		assignRequests(data);
	        	}
	        	
	        	else
	        		alert("failed to get dates");
	        },
	        
	        error : function(data){
	        	alert("failed to get dates !");
	        }
	        
		});
	};

	function getBookingBySlotIdWithStudentNames(slot_id){		
		$.ajax({
			url:"http://localhost:8080/Counselor_Appointment/webapi/StudentBookingHistory/getBookingBySlotIdWithStudentNames/"+slot_id,
			type:"POST",
			cache:false,
			contentType:false,
			processData: false,
	        success : function(data){
	        	
	        	if(data)
	        	{
//	        		alert("Hi");
	        		console.log(Object.keys(data));
        			addNameToBookedSlot(data.slot_id,data.student_name);
	        	}
	        	
	        	else
	        		alert("failed to get bookings");
	        },
	        
	        error : function(data){
	        	alert("failed to get bookings !");
	        }
		});
	};
	
	function acceptRequest(request_id){
		var object = JSON.stringify({
			request_id:request_id,
		});
		
//		alert(student_id);
//		alert(slot_id)
		
		$.ajax({
			url:"http://localhost:8080/Counselor_Appointment/webapi/Counselor/acceptRequest",
			data: object,
//			url:"http://localhost:8080/flipkart/webapi/items/getByPrice",
//			url:"http://localhost:8080/flipkart/webapi/items/getReport",
			type:"POST",
			dataType: "json",
			contentType: "application/json",
	        success : function(data){
	        	
	        	if(data)
	        	{
	        		if(data=="-1"){
	        			alert("Some error with backend");
	        			location.reload();
	        			
	        		}
	        		else{
	        			console.log(Object.keys(data));
	        			addNameToBookedSlot(data.slot_id,data.student_name);
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