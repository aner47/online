package com.online.dao;

import java.util.List;

import com.online.entity.online.Section;

/**
 * 
 * @author zuozhiping
 * 
 * dao-工段
 *
 */
public interface SectionDao extends BaseDao<Section, Integer> {

    List<Section> findSourceNameById(Integer id);


}