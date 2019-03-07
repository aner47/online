package com.online.dao;

import java.util.List;

import com.online.entity.online.Kiln;

/**
 * 
 * @author zuozhiping
 * 
 * dao-窑炉
 *
 */
public interface KilnDao extends BaseDao<Kiln, Integer> {

    List<Kiln> findSourceNameById(Integer id);


}