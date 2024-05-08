const replyService = {
	insert:function(data,callback){
		console.log("전송 :",data);
		$.ajax({
			type:"POST",
			url:"/reply/regist",
			data:JSON.stringify(data),
			contentType:"application/json;charset=utf-8",
			success:function(result,status,xhr){
				callback(result)
			},
			error:function(result,status,xhr){
				
			}
		})
	},
	selectAll:function(data,callback){
		let boardnum = data.boardnum;
		let pagenum = data.pagenum;
		
		$.getJSON(
			`/reply/list/${boardnum}/${pagenum}`,
			function(data){
				//data : 응답되는 JSON ({ replyCnt:댓글갯수, list:[...] })
				callback(data.replyCnt, data.list);
			}
		)
	},
	delete:function(replynum,callback){
		$.ajax({
			type:"DELETE",
			url:`/reply/${replynum}`,
			success:function(result){
				callback(result);
			},
			error:function(result){
				error(result);
			}
		})
	},
	update:function(data,callback){
		$.ajax({
			type:"PUT",
			url:"/reply/"+data.replynum,
			data:JSON.stringify(data),
			contentType:"application/json;charset=utf-8",
			success:function(result){
				callback(result);
			}
		})
	}
	
}











