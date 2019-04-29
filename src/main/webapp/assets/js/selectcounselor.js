$(document).ready(function(){
    C1={name:"Nalini",id:"1"};
    C2={name:"Kiran",id:"2"};

//    counselorList=[C1,C2];
    
//    displayCounselors(counselorList);

    getAllCounselor();
    /*
	$("body").on("click",'.classcounselor',function () {
		var counselorid=$(this).attr("id").split("_")[1];
		
	});*/

    
    function displayCounselors(table){
        var str="";
        for(var i=0;i<table.length;i++){
            counselor=table[i];
            var id= counselor.idCounselor;
            var name= counselor.name;
            var url = "http://localhost:8080/Counselor_Appointment/slots.html?id="+id;
//            var url="http://localhost:8080/Counselor_Appointment/slots.html";
            var addition="<div class='classcounselor' id='idcounselor_"+id+"'><a  href='"+url+"'>"+name+"</a></div>";
            str+=addition;
        }
        $("#idcouselorlist").append(str);
    }
    
    function getAllCounselor(){
		$.ajax({
			url:"http://localhost:8080/Counselor_Appointment/webapi/Counselor/getAllCounselors",
			type:"GET",
			cache:false,
			contentType:false,
			processData: false,
	        success : function(data){
	        	
	        	if(data){
	        		displayCounselors(data);
	        	}
	        	
	        	else
	        		alert("failed to get counselors");
	        },
	        
	        error : function(data){
	        	alert("failed to get counselors !");
	        }
	        
		});
    	
    }
    
});