package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.TaskHistoryDao;
import com.online.entity.online.TaskHistory;

/**
 * 任务历史
 */
@Repository("taskHistoryDaoImpl")
public class TaskHistoryDaoImpl extends BaseDaoImpl<TaskHistory, Integer> implements TaskHistoryDao {



}