package com.kh.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.ReplyDTO;

@Mapper
public interface ReplyMapper {
	int insertReply(ReplyDTO reply);
	
	ReplyDTO getDetail(long replynum);
	ReplyDTO getLastReply(String userid);
	List<ReplyDTO> getList(Criteria cri, long boardnum);
	long getTotal(long boardnum);
	
	
	int updateReply(ReplyDTO reply);
	
	int deleteReply(long replynum);

	int deleteRepliesByBoardnum(long boardnum);
}
