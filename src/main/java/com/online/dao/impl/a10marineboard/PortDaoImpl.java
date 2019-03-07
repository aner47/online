package com.online.dao.impl.a10marineboard;

import org.springframework.stereotype.Repository;

import com.online.entity.online.a10marineboard.Port;
import com.online.dao.a10marineboard.PortDao;
import com.online.dao.impl.BaseDaoImpl;

/**
 * dao-港口信息实现
 *
 */
@Repository("portDaoImpl")
public class PortDaoImpl extends BaseDaoImpl<Port, Integer> implements PortDao {



}