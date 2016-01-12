package com.chinesedreamer.rating.attachment.service;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chinesedreamer.rating.attachment.logic.AttachmentLogic;
import com.chinesedreamer.rating.attachment.model.Attachment;
import com.chinesedreamer.rating.common.io.ConfigPropertiesConstant;
import com.chinesedreamer.rating.common.io.PropertiesUtils;

@Service
public class AttachmentServiceImpl implements AttachmentService {
	
	private Logger logger = LoggerFactory.getLogger(AttachmentServiceImpl.class);
	
	@Resource
	private AttachmentLogic logic;

	@Override
	public Attachment saveFile(MultipartFile file, Long userId) {
		// 1. 保存文件
		PropertiesUtils pu = new PropertiesUtils("config.properties");
		Calendar calendar = Calendar.getInstance();
		String prefix = pu.getProperty(ConfigPropertiesConstant.FILE_UPLOAD_PREFIX) + calendar.get(Calendar.YEAR) + "/"
				+ (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/";
		File folder = new File(prefix);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		File uploadFile = new File(prefix + file.getOriginalFilename());
		try {
			file.transferTo(uploadFile);
		} catch (Exception e) {
			this.logger.error("{}", e);
		}
		// 2. 保存到db
		Attachment attachment = new Attachment();
		attachment.setFileName(file.getOriginalFilename());
		attachment.setFilePath(prefix + file.getOriginalFilename());
		attachment.setFileSize(file.getSize());
		attachment.setUploadDate(new Date());
		attachment.setUploadUser(userId);
		return this.logic.save(attachment);
	}

}
