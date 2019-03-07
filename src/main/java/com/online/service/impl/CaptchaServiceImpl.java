package com.online.service.impl;

import java.awt.image.BufferedImage;

import org.springframework.stereotype.Service;

import com.online.service.CaptchaService;



/**
 * Service - 验证码
 * 
 * 
 * 
 */
@Service("captchaServiceImpl")
public class CaptchaServiceImpl implements CaptchaService {

	@Override
	public BufferedImage buildImage(String captchaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValid(String captchaId, String captcha) {
		// TODO Auto-generated method stub
		return false;
	}



}