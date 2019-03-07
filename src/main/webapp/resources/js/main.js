$(function(){
	 /***联系我们***/
	 $("#linkMe",'.footer').click(function(){
		 location.href = "link.jhtml";
	 })
	 /**
	  * 招贤纳士
	  */
	$('#recruit','.footer').click(function(){
/*		  $.ajax({
			  url:'index/recruit.jhtml',
			  success:function(data){
				  var contain = $("#content",'.popup_content').html('');
				  $.each(data,function(i,v){
					  contain.append(v.content); 
					  contain.append("<br>");
					  contain.append("<br>");
				  })
					 $('.popup').show();  
				  }
		  })*/
		 location.href = "recruit.jhtml";
		
	 })
	
	 $('.close').click(function(){
		$('.popup').css('display','none')	
	 })
})