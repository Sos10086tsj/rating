package com.chinesedreamer.rating.template.service;

import java.util.List;

import com.chinesedreamer.rating.template.vo.OptionVo;

/**
 * Description: 
 * @author Paris
 * @date Jul 17, 20153:41:42 PM
 * @version beta
 */
public interface SuppOptionService {
	public List<OptionVo> getAll();
	public void update(OptionVo vo);
	public void delete(Long id);
}
