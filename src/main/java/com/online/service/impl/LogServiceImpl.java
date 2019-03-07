package com.online.service.impl;



import org.springframework.stereotype.Service;

import com.online.entity.Log;
import com.online.service.LogService;
/**
 * 
 * @author 左志平
 * 
 * 日志服务实现
 *
 */
@Service("logServiceImpl")
public class LogServiceImpl extends BaseServiceImpl<Log, Long> implements LogService {
	
}
