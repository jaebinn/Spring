const replyService = {
	insert:function(data,callback){
		$.ajax({
			type:"POST",
			url:"/reply/regist",
			data:JSON.stringify(data),
			contentType:"application/json;charset=utf-8",
			success:function(result){
				callback(result)
			},
			error:function(result){
				
			}
		})
	},
	selectAll:function(data,callback){
		let boardnum = data.boardnum;
		let pagenum = data.pagenum;
		
		$.getJSON(
			`/reply/list/${boardnum}/${pagenum}`,
			function(data){
				//data : 응답되는 JSON	 {{replycnt:댓글갯수, list:[...]}}
				callback(data.replycnt, data.list);
			}
		)
	}
}