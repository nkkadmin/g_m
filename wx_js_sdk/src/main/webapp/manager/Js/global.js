/**
 * 通用函数
 */
function showForm(urladress){
	$.ajax({
		url: urladress,
		type: 'post',
		data: $("form").serialize(),
		dataType: 'json',
		success: function(data){
			console.log(data);
			
			$("#tips").text(data.message);
		},
		error: function(data){
			console.log("error");
		}
	})
}