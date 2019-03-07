package com.online.dao.impl.a9agriculture;

import org.springframework.stereotype.Repository;

import com.online.entity.online.a9agriculture.Crop;
import com.online.dao.a9agriculture.CropDao;
import com.online.dao.impl.BaseDaoImpl;

/**
 * dao-农作物产量及秸秆利用实现
 *
 */
@Repository("cropDaoImpl")
public class CropDaoImpl extends BaseDaoImpl<Crop, Integer> implements CropDao {



}