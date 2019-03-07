package com.online.dao;

import com.online.entity.online.Project;

/**
 * 
 * @author zuozhiping
 * 
 * dao-项目
 *
 */
public interface ProjectDao extends BaseDao<Project, Integer> {

	Project findProjectByInvitationCode(String invitationCode);
	
	/**
	 * 根据验证码查询项目
	 * @author 郑有权
	 * @date 创建时间：2018年2月27日 上午11:46:48 
	 * @param invitationCode  验证码
	 * @param isPastDate  是否验证是否过期
	 * @return
	 */
	public Project findProjectByInvitationCode(String invitationCode,boolean isPastDate);
	
}