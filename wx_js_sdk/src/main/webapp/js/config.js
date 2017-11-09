var accessToken;
//获取access_token 
function getAccessToken(){
	$.ajax({
		type:"get",
		url:"wxApi/getAccessToken",
		data:{"appId":"wx287ec711f3bdb1c6"},
		async:true,
		success:function(data){
			if(data != null){
				console.log(data.access_token);
				accessToken = data.access_token;
			}
		},
		error:function(data){
			console.log(data)
		}
	});
}

function getJsApiTicket(accessToken){
	$.ajax({
		type:"get",
		url:"wxApi/getTicket",
		data:{"accessToken":accessToken},
		async:true,
		success:function(data){
			console.log(data);
		},
		error:function(data){
			console.log(data)
		}
	});
}