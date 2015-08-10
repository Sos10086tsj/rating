package com.chinesedreamer.rating.template.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.template.OptionCategory;
import com.chinesedreamer.rating.template.logic.RatingSuppOptionLogic;
import com.chinesedreamer.rating.template.model.RatingSuppOption;
import com.chinesedreamer.rating.template.vo.OptionVo;

/**
 * Description: 
 * @author Paris
 * @date Jul 17, 20153:41:51 PM
 * @version beta
 */
@Service
public class SuppOptionServiceImpl implements SuppOptionService{
	@Resource
	private RatingSuppOptionLogic logic;

	@Override
	public List<OptionVo> getAll() {
		List<OptionVo> vos = new ArrayList<OptionVo>();
		List<RatingSuppOption> options = this.logic.findAllOrderBySeqAsc();
		for (RatingSuppOption option : options) {
			OptionVo vo = new OptionVo();
			vo.setId(option.getId());
			vo.setName(option.getName());
			vo.setCategoryCode(option.getCategory().getCode());
			vo.setCategoryName(option.getCategory().getLabel());
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public void update(OptionVo vo) {
		RatingSuppOption option = null;
		if (null == vo.getId()) {
			option = new RatingSuppOption();
			option.setCode(UUID.randomUUID().toString());
		}else {
			option = this.logic.findOne(vo.getId());
		}
		option.setName(vo.getName());
		option.setCategory(OptionCategory.valueOf(vo.getCategoryCode()));
		this.logic.save(option);
	}

	@Override
	public void delete(Long id) {
		RatingSuppOption option = this.logic.findOne(id);
		if (null == option) {
			return;
		}
		option.setDeleted(Boolean.TRUE);
		this.logic.update(option);
	}

}
