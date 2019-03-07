package com.online.entity.listener;

import org.springframework.stereotype.Component;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 * Listener - 缓存
 * 
 * 
 * 
 */
@Component("cacheEventListener")
public class CacheEventListener implements net.sf.ehcache.event.CacheEventListener {



	/**
	 * 元素回收调用
	 * 
	 * @param ehcache
	 *            缓存
	 * @param element
	 *            元素
	 */
	public void notifyElementEvicted(Ehcache ehcache, Element element) {
	}

	/**
	 * 元素过期调用
	 * 
	 * @param ehcache
	 *            缓存
	 * @param element
	 *            元素
	 */
	public void notifyElementExpired(Ehcache ehcache, Element element) {
//		String cacheName = ehcache.getName();

	}

	/**
	 * 元素添加调用
	 * 
	 * @param ehcache
	 *            缓存
	 * @param element
	 *            元素
	 */
	public void notifyElementPut(Ehcache ehcache, Element element) throws CacheException {
	}

	/**
	 * 元素移除调用
	 * 
	 * @param ehcache
	 *            缓存
	 * @param element
	 *            元素
	 */
	public void notifyElementRemoved(Ehcache ehcache, Element element) throws CacheException {
	}

	/**
	 * 元素更新调用
	 * 
	 * @param ehcache
	 *            缓存
	 * @param element
	 *            元素
	 */
	public void notifyElementUpdated(Ehcache ehcache, Element element) throws CacheException {
	}

	/**
	 * 删除调用
	 * 
	 * @param ehcache
	 *            缓存
	 */
	public void notifyRemoveAll(Ehcache ehcache) {
	}

	/**
	 * 释放
	 */
	public void dispose() {
	}

	/**
	 * 克隆
	 * 
	 * @return 对象
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}