package com.chinesedreamer.rating.attachment.service;

import org.springframework.web.multipart.MultipartFile;

import com.chinesedreamer.rating.attachment.model.Attachment;

public interface AttachmentService {
	/**
	 * 保存文件
	 * @param file
	 * @param userId
	 * @return
	 */
	public Attachment saveFile(MultipartFile file, Long userId);
}
