package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.TaskListDao;
import com.online.entity.online.TaskList;

/**
 * 
 * @author 左志平
 * 
 * dao-每日任务清单实现
 *
 */
@Repository("taskListDaoImpl")
public class TaskListDaoImpl extends BaseDaoImpl<TaskList, Integer> implements TaskListDao {



}