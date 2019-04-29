$(document).ready(function () {

	var dates=[{id:"1",date:"27/03/2019"},{id:"2",date:"28/03/2019"},{id:"3",date:"01/04/2019"}];

	S1={slotid:"1",dateid:"1",date:"27/03/2019",time:"10 to 11",num:"1",status:1};
	S2={slotid:"2",dateid:"1",date:"27/03/2019",time:"11 to 12",num:"2",status:0};
	S3={slotid:"3",dateid:"2",date:"28/03/2019",time:"10 to 11",num:"1",status:1};
	S4={slotid:"4",dateid:"3",date:"01/04/2019",time:"10 to 11",num:"1",status:0};

    slots=[S1,S2,S3,S4];

//    displayDates(dates);
//	assignSlots(slots);

	$(".classdatediv").on("click",function () {
		var dateid=$(this).attr("id").split("_")[1];
		$("#idslotlist_"+dateid).slideToggle("fast");
	});

	function displayDates(table) {
		var str="";

		for (var i = 0; i < table.length; i++) {
//			var id=table[i].id;
//			var date=table[i].date;
			var id=table[i].idDate;
			var date=table[i].DateValue;
			var addition="<div class='classday'><div class='classdatediv' id='iddatediv_"+id+"'><a id='iddatea_"+id+"'> "+date+" </a></div> <div class='classslotlist' id='idslotlist_"+id+"'> </div></div>";
			str+=addition;
		}
		$("#iddateslist").append(str);
	}

	function assignSlots(table){
		for (var i = 0; i < table.length; i++) {
//			var dateid=table[i].dateid;
//            var time=table[i].time;
//            var slotid =table[i].slotid;
//            var status=table[i].status;
			var dateid=table[i].Date_id;
			var time=table[i].SlotValue;
			var status=table[i].Status;
			var slot_id=table[i].idSlot;
            //Later based on booking condition you will change class name
            if(status==0){
                $("#idslotlist_"+dateid).append("<div ><div class='classslot' id='idslot_"+slotid+"' ><a id='idslota_"+slotid+"'>"+time+"</a></div> <div id='idrequestlist_"+slotid+"' > </div> </div>");	
            }
            else{
                $("#idslotlist_"+dateid).append("<div ><div class='classslot bookedslot' id='idslot_"+slotid+"' ><a id='idslota_"+slotid+"'>"+time+"</a></div> <div id='idrequestlist_"+slotid+"' > </div> </div>");	
            }
		}
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
	        		displayDates(data);
	        		for(var i=0;i<data.length;i++){
	        			getSlotsByDateId(data.idDate);
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
	        		alert("failed to get dates");
	        },
	        
	        error : function(data){
	        	alert("failed to get dates !");
	        }
	        
		});
	};
	
});