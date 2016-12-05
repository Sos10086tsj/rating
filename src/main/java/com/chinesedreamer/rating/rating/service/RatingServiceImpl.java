package com.chinesedreamer.rating.rating.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinesedreamer.rating.attachment.model.Attachment;
import com.chinesedreamer.rating.common.io.ConfigPropertiesConstant;
import com.chinesedreamer.rating.common.io.PropertiesUtils;
import com.chinesedreamer.rating.common.utils.DateUtil;
import com.chinesedreamer.rating.common.utils.ExcelUtil;
import com.chinesedreamer.rating.common.utils.StringUtil;
import com.chinesedreamer.rating.common.vo.OptionTitle;
import com.chinesedreamer.rating.common.vo.SelectVo;
import com.chinesedreamer.rating.rating.logic.RatingLogic;
import com.chinesedreamer.rating.rating.logic.RatingResultLogic;
import com.chinesedreamer.rating.rating.logic.RatingScoreLogic;
import com.chinesedreamer.rating.rating.logic.RatingUserVoteItemLogic;
import com.chinesedreamer.rating.rating.logic.RatingUserVoteLogic;
import com.chinesedreamer.rating.rating.model.Rating;
import com.chinesedreamer.rating.rating.model.RatingResult;
import com.chinesedreamer.rating.rating.model.RatingScore;
import com.chinesedreamer.rating.rating.model.RatingStatus;
import com.chinesedreamer.rating.rating.model.RatingUserVote;
import com.chinesedreamer.rating.rating.model.RatingUserVoteItem;
import com.chinesedreamer.rating.rating.vo.RatingCreateVo;
import com.chinesedreamer.rating.rating.vo.RatingPageVo;
import com.chinesedreamer.rating.rating.vo.RatingTemplateVo;
import com.chinesedreamer.rating.rating.vo.RatingUserVo;
import com.chinesedreamer.rating.rating.vo.RatingUserVoteResult;
import com.chinesedreamer.rating.rating.vo.RatingUserVoteVo;
import com.chinesedreamer.rating.rating.vo.RatingVo;
import com.chinesedreamer.rating.rating.vo.RatingWeightVo;
import com.chinesedreamer.rating.system.config.ConfigConstant;
import com.chinesedreamer.rating.system.config.logic.ConfigLogic;
import com.chinesedreamer.rating.system.config.model.Config;
import com.chinesedreamer.rating.system.group.UserGroupLevel;
import com.chinesedreamer.rating.system.group.logic.UserGroupLogic;
import com.chinesedreamer.rating.system.group.model.UserGroup;
import com.chinesedreamer.rating.system.user.logic.UserLogic;
import com.chinesedreamer.rating.system.user.model.User;
import com.chinesedreamer.rating.template.OptionCategory;
import com.chinesedreamer.rating.template.logic.RatingSuppOptionLogic;
import com.chinesedreamer.rating.template.logic.RatingSuppTemplateLogic;
import com.chinesedreamer.rating.template.logic.RatingTemplateLogic;
import com.chinesedreamer.rating.template.logic.RatingTemplateOptionMappingLogic;
import com.chinesedreamer.rating.template.logic.RatingTemplateVoterLogic;
import com.chinesedreamer.rating.template.logic.RatingTmplOptionWeightLogic;
import com.chinesedreamer.rating.template.model.RatingSuppTemplate;
import com.chinesedreamer.rating.template.model.RatingTemplate;
import com.chinesedreamer.rating.template.model.RatingTemplateOptionMapping;
import com.chinesedreamer.rating.template.model.RatingTemplateVoter;
import com.chinesedreamer.rating.template.model.RatingTmplOptionWeight;
import com.chinesedreamer.rating.template.util.RatingSuppTmplScoerUtil;
import com.chinesedreamer.rating.template.util.TmplScoerVO;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201511:42:27 AM
 * @version beta
 */
@Service
public class RatingServiceImpl implements RatingService{
	private Logger logger = LoggerFactory.getLogger(RatingServiceImpl.class);
	
	@Resource
	private RatingLogic logic;
	@Resource
	private RatingTemplateLogic templateLogic;
	@Resource
	private RatingSuppTemplateLogic suppTemplateLogic;
	@Resource
	private RatingTemplateVoterLogic templateVoterLogic;
	@Resource
	private RatingUserVoteLogic ratingUserVoteLogic;
	@Resource
	private RatingUserVoteItemLogic ratingUserVoteItemLogic;
	@Resource
	private RatingTemplateOptionMappingLogic templateOptionMappingLogic;
	@Resource
	private UserLogic userLogic;
	@Resource
	private RatingScoreLogic scoreLogic;
	@Resource
	private UserGroupLogic userGroupLogic;
	@Resource
	private RatingTmplOptionWeightLogic optionWeightLogic;
	@Resource
	private RatingSuppOptionLogic suppOptionLogic;
	@Resource
	private ConfigLogic configLogic;
	@Resource
	private RatingResultLogic ratingResultLogic;
	
	@Override
	public void saveRating(RatingCreateVo vo) {
		if (null == vo.getTemplateIds() || vo.getTemplateIds().length == 0) {
			return;
		}
		//1. 保存投票事件信息
		Rating rating = new Rating();
		rating.setName(vo.getName());
		rating.setStatus(RatingStatus.ACTIVE);
		if (StringUtils.isNotEmpty(vo.getEffFromStr())) {
			rating.setEffFrom(DateUtil.format(vo.getEffFromStr(), "yyyy-MM-dd"));
		}
		if (StringUtils.isNotEmpty(vo.getEffToStr())) {
			rating.setEffTo(DateUtil.format(vo.getEffToStr(), "yyyy-MM-dd"));
		}
		rating = this.logic.save(rating);
		
		//2. 保存模板
		this.templateLogic.copySuppTemplateToRating(rating, vo.getTemplateIds());
	}
	@Override
	public List<RatingVo> getAllRatings() {
		//20150712 add template associated templates
		List<RatingVo> vos = new ArrayList<RatingVo>();
		List<Rating> ratings = this.logic.findAll();
		for (Rating rating : ratings) {
			vos.add(this.convert2RatingVo(rating));
		}
		return vos;
	}
	private RatingVo convert2RatingVo(Rating rating) {
		RatingVo vo = new RatingVo();
		vo.setId(rating.getId());
		vo.setName(rating.getName());
		vo.setEffFrom(rating.getEffFrom());
		vo.setEffTo(rating.getEffTo());
		if (null != rating.getEffTo() && rating.getEffTo().after(new Date())) {//未到期
			vo.setOverdue(false);
		}else {
			vo.setOverdue(true);
		}
		List<RatingTemplate> templates = this.templateLogic.findByRatingId(rating.getId());
		List<SelectVo> vos = new ArrayList<SelectVo>();
		for (RatingTemplate template : templates) {
			vos.add(new SelectVo(template.getId().toString(), template.getName()));
		}
		vo.setTemplates(vos);
		return vo;
	}
	
	@Override
	public List<SelectVo> getAllTemplates() {
		List<SelectVo> vos = new ArrayList<SelectVo>();
		List<RatingSuppTemplate> suppTemplates = this.suppTemplateLogic.findAll();
		for (RatingSuppTemplate suppTemplate : suppTemplates) {
			vos.add(new SelectVo(suppTemplate.getId().toString(), suppTemplate.getName()));
		}
		return vos;
	}
	@Override
	public List<RatingUserVo> getRatingUserVos(User user) {
		List<RatingUserVo> vos = new ArrayList<RatingUserVo>();
		List<Rating> ratings = this.logic.findAll();
		for (Rating rating : ratings) {
			vos.add(this.getRatingUserVo(user, rating));
		}
		return vos;
	}

	/**
	 * 封装用户查看的投票vo
	 * @param user
	 * @param rating
	 * @return
	 */
	private RatingUserVo getRatingUserVo(User user, Rating rating){
		RatingUserVo vo = new RatingUserVo();
		vo.setId(rating.getId());
		vo.setName(rating.getName());
		vo.setEffFrom(rating.getEffFrom());
		vo.setEffTo(rating.getEffTo());
		if (rating.getEffTo().after(new Date())) {
			vo.setOverdue(false);
		}else {
			vo.setOverdue(true);
		}
		List<RatingTemplateVo> templateVos = new ArrayList<RatingTemplateVo>();
		//获取所有模板 RatingTemplateVo
		List<RatingTemplate> templates = this.templateLogic.findByRatingId(rating.getId());
		for (RatingTemplate template : templates) {
			List<RatingTemplateVoter> votes = null;
			UserGroup userGroup = this.userGroupLogic.findOne(user.getGroupId());
			if (userGroup.getLevel().equals(UserGroupLevel.ZONGTI)) {//总体组
				votes = this.templateVoterLogic.findByTmplIdAndGroupId(template.getId(), user.getGroupId());//总体组可以查到
			}else {
				votes = this.templateVoterLogic.findByTmplIdAndGroupIdAndPositionId(template.getId(), user.getGroupId(), user.getPositionId());//非总体组
			}
			if (null != votes && !votes.isEmpty()) {
				for (RatingTemplateVoter vote : votes) {//找到A、D或者B、C
					RatingTemplateVo rtVo = new RatingTemplateVo();
					rtVo.setId(vote.getTmplId());
					rtVo.setName(template.getName());
					rtVo.setVoted((null == this.ratingUserVoteLogic.findByRatingIdAndTmplIdAndUserId(rating.getId(), template.getId(), user.getId())) ? Boolean.FALSE : Boolean.TRUE);
					templateVos.add(rtVo);
				}
			}
		}
		vo.setTemplates(templateVos);
		return vo;
	}
	@Override
	public RatingPageVo getRatingVotePage(Long tmplId) {
		RatingPageVo vo = new RatingPageVo();
		RatingTemplate rt = this.templateLogic.findOne(tmplId);
		vo.setTmplId(tmplId);
		vo.setTmplName(rt.getName());
		vo.setRatingId(rt.getRatingId());
		Rating r = this.logic.findOne(rt.getRatingId());
		vo.setRatingName(r.getName());
		return vo;
	}
	@Override
	public List<OptionTitle> getTmplOptions(Long tmplId) {
		List<OptionTitle> vos = new ArrayList<OptionTitle>();
		List<RatingTemplateOptionMapping> options = this.templateOptionMappingLogic.findByTmplId(tmplId);
		for (RatingTemplateOptionMapping option : options) {
			String name = option.getOption().getName();
			Integer width = (name.length() > 4 ? name.length() * 20 : 4 * 20);
			vos.add(new OptionTitle("option_" + option.getOptionId(), name , width));
		}
		return vos;
	}
	
	/**
	 * 生成已投票数据JSON数据源
	 * @param voteVos
	 * @param options
	 * @return
	 */
	private List<Map<String, Object>> generateRatingVoteJsonDatasource(List<RatingUserVoteVo> voteVos, List<RatingTemplateOptionMapping> options) {
		List<Map<String, Object>> rst = new ArrayList<Map<String,Object>>();
		for (RatingUserVoteVo voteVo : voteVos) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("scorerId", voteVo.getUserId());
			item.put("scorerName", voteVo.getUserName());
			item.put("group", this.userLogic.findOne(voteVo.getUserId()).getUserGroup().getName());
			Map<Long, RatingUserVoteItem> voteVoMap = voteVo.getVoteItems();
			for (RatingTemplateOptionMapping option : options) {
				if (voteVoMap.keySet().contains(option.getOptionId())) {
					item.put("option_" + option.getOptionId(), voteVoMap.get(option.getOptionId()).getScore());
				}
//				else{
//					item.put("option_" + option.getId(), "-1");
//				}
			}
			rst.add(item);
		}
		return rst;
	}
	
	@Override
	public Map<String, Object> getUserRatingVote(Long tmplId, User user) {
		Map<String, Object> rstMap = new HashMap<String, Object>();
		List<RatingUserVoteVo> voteVos = new ArrayList<RatingUserVoteVo>();
		
		RatingTemplate rt = this.templateLogic.findOne(tmplId);
		RatingUserVote userVote = this.ratingUserVoteLogic.findByRatingIdAndTmplIdAndUserId(
				rt.getRatingId(), tmplId, user.getId());
		if (null != userVote) {//已投过票
			//所有投票数据
			List<RatingUserVoteItem> voteItems = this.ratingUserVoteItemLogic.findByUserVoteId(userVote.getId());
			Map<Long, List<RatingUserVoteItem>> voteMap = new HashMap<Long, List<RatingUserVoteItem>>();
			for (RatingUserVoteItem voteItem : voteItems) {
				Long key = voteItem.getScorer();
				List<RatingUserVoteItem> tmpItems = null;
				if (voteMap.containsKey(key)) {
					tmpItems = voteMap.get(key);
				}else {
					tmpItems = new ArrayList<RatingUserVoteItem>();
				}
				tmpItems.add(voteItem);
				voteMap.put(key, tmpItems);
			}
			for (Long key : voteMap.keySet()) {
				voteVos.add(this.getVoteVo(voteMap.get(key)));
			}
		}
		//所有投票项
		List<Map<String, Object>> scorers = new ArrayList<Map<String,Object>>();
		List<RatingTemplateOptionMapping> options = this.templateOptionMappingLogic.findByTmplId(tmplId);
		if (null != voteVos && !voteVos.isEmpty()) {//未进行投票时，默认提供全部待投票用户
			scorers.addAll(this.generateRatingVoteJsonDatasource(voteVos, options));
		}
		this.initAllVoters(rt.getCode(), options, user,scorers);
		rstMap.put("total", scorers.size());
		rstMap.put("rows", scorers);
		
		return rstMap;
	}
	
	/**
	 * 初始化所有投票人
	 * @param code
	 * @param options
	 * @return
	 */
	private void initAllVoters(String code, List<RatingTemplateOptionMapping> options, User currentUser,List<Map<String, Object>> scorers){
		Set<String> exists = new HashSet<String>();
		for (Map<String, Object> scorer : scorers) {
			exists.add(scorer.get("scorerId").toString());
		}
		List<TmplScoerVO> scoers = RatingSuppTmplScoerUtil.getTemplateScores(code);
		for (TmplScoerVO scoer : scoers) {
			//1. 查询总体组
			List<User> users = null;
			if (scoer.getGroup().equals(UserGroupLevel.ZONGTI)) {
				users = this.userLogic.findByGroupLevel(UserGroupLevel.ZONGTI);
			}else if (scoer.getGroup().equals(UserGroupLevel.PUTONG)) {//2. 非总体组
				users = this.userLogic.findByGroupLevelAndPosition(UserGroupLevel.PUTONG, scoer.getPosition().getValue());
			}
			if (null != users && !users.isEmpty()) {
				for (User user : users) {
					if(exists.contains(user.getId().toString())){
						continue;
					}
					Map<String, Object> tmpMap = new HashMap<String, Object>();
					tmpMap.put("scorerId", user.getId());
					tmpMap.put("scorerName", user.getName());
					tmpMap.put("group", user.getUserGroup().getName());
					for (RatingTemplateOptionMapping option : options) {
						tmpMap.put("option_" + option.getOptionId(), "4.0");
					}
					scorers.add(tmpMap);
				}
			}
		}
	}
	
	/**
	 * 瓶装投票row，一个得分人一行
	 * @param voteItem
	 * @param options
	 * @return
	 */
	private RatingUserVoteVo getVoteVo(List<RatingUserVoteItem> voteItems) {
		RatingUserVoteVo vo = new RatingUserVoteVo();
		RatingUserVoteItem first = voteItems.get(0);
		vo.setUserId(first.getScorer());
		vo.setUserName(this.userLogic.findOne(first.getScorer()).getName());
		Map<Long, RatingUserVoteItem> itemMap = new HashMap<Long, RatingUserVoteItem>();
		for (RatingUserVoteItem voteItem : voteItems) {
			itemMap.put(voteItem.getOptionId(), voteItem);
		}
		vo.setVoteItems(itemMap);
		return vo;
	}
	@Override
	public List<SelectVo> getAllScores() {
		List<SelectVo> vos = new ArrayList<SelectVo>();
		List<RatingScore> scores = this.scoreLogic.findAll();
		for (RatingScore score : scores) {
			vos.add(new SelectVo(score.getId().toString(), score.getName()));
		}
		return vos;
	}
	@Override
	public void submitVote(String datasource, Long tmplId, User user){
		
		JSONArray jsonArray = JSONArray.parseArray(datasource);
		if (null == jsonArray || jsonArray.size() == 0) {
			return;
		}
		//投票记录
		RatingTemplate template = this.templateLogic.findOne(tmplId);
		RatingUserVote vote = this.ratingUserVoteLogic.findByRatingIdAndTmplIdAndUserId(template.getRatingId(), tmplId, user.getId());
		if (null == vote) {
			vote = new RatingUserVote();
		}	
		vote.setGroupId(user.getGroupId());
		vote.setPositionId(user.getPositionId());
		vote.setRatingId(template.getRatingId());
		vote.setTmplId(tmplId);
		vote.setUserId(user.getId());
		vote.setVoteDate(new Date());
		this.ratingUserVoteLogic.save(vote);
		//投票具体结果
		Set<RatingUserVoteItem> voteItems = new HashSet<RatingUserVoteItem>();
		List<RatingTemplateOptionMapping> options = this.templateOptionMappingLogic.findByTmplId(tmplId);
		for (Object object : jsonArray) {
			JSONObject voteRow = (JSONObject)object;
			Long scorerId = voteRow.getLong("scorerId");
			for (RatingTemplateOptionMapping option : options) {
				if (null != voteRow.getFloat("option_" + option.getOptionId())) {
					RatingUserVoteItem vi = this.ratingUserVoteItemLogic.findByUserVoteIdAndOptionIdAndScorer(vote.getId(), option.getOptionId(),scorerId);
					if (null == vi) {
						vi = new RatingUserVoteItem();
					}
					vi.setUserVoteId(vote.getId());
					vi.setOptionId(option.getOptionId());
					//vi.setScoreId(voteRow.getLong("option_" + option.getOptionId()));
					vi.setScore(voteRow.getFloat("option_" + option.getOptionId()));
					vi.setScorer(scorerId);
					User scorer = this.userLogic.findOne(scorerId);
					vi.setScorerGroup(scorer.getGroupId());
					vi.setScorerPosition(scorer.getPositionId());
					voteItems.add(vi);
				}
			}
		}
		for (RatingUserVoteItem voteItem : voteItems) {
			this.ratingUserVoteItemLogic.save(voteItem);
		}
	}
	@Override
	public List<RatingUserVo> getStatisticsRatings(User user) {
		List<RatingUserVo> vos = new ArrayList<RatingUserVo>();
		List<Rating> ratings = this.logic.findAll();
		for (Rating rating : ratings) {
			vos.add(this.convert2StatisticsVo(rating,user));
		}
		return vos;
	}
	private RatingUserVo convert2StatisticsVo(Rating rating,User user) {
		RatingUserVo vo = new RatingUserVo();
		vo.setId(rating.getId());
		vo.setName(rating.getName());
		vo.setEffFrom(rating.getEffFrom());
		vo.setEffTo(rating.getEffTo());
		
		List<RatingTemplateVo> templateVos = new ArrayList<RatingTemplateVo>();
		//获取所有模板 RatingTemplateVo
		List<RatingTemplate> templates = this.templateLogic.findByRatingId(rating.getId());
		for (RatingTemplate template : templates) {
			RatingTemplateVo rtVo = new RatingTemplateVo();
			rtVo.setId(template.getId());
			rtVo.setCode(template.getCode());
			rtVo.setName(template.getName());
			templateVos.add(rtVo);
		}
		vo.setTemplates(templateVos);
		
		return vo;
	}
	@Override
	public Rating findOne(Long id) {
		return this.logic.findOne(id);
	}
	@Override
	public Rating getByTmplId(Long tmplId) {
		RatingTemplate rt = this.templateLogic.findOne(tmplId);
		return this.logic.findOne(rt.getRatingId());
	}
	@Override
	public List<OptionTitle> getUserRartingOption(Long userId, Long ratingId) {
		List<String> codes = RatingSuppTmplScoerUtil.getTmplCodeByUser(this.userLogic.findOne(userId));
		List<OptionTitle> options = new ArrayList<OptionTitle>();
		for (String code : codes) {
			RatingTemplate template = this.templateLogic.findByRatingIdAndCode(ratingId, code);
			List<OptionTitle> tmp = this.getTmplOptions(template.getId());
			if (tmp.size() > options.size()) {
				options = tmp;
			}
		}
		return options;
	}
	@Override
	public List<RatingWeightVo> getRatingTmplWeightVos(Long templateId) {
		List<RatingWeightVo> weightVos = new ArrayList<RatingWeightVo>();
		Set<OptionCategory> categories = new HashSet<OptionCategory>();
		Set<Long> selected = new HashSet<Long>();
		List<RatingTemplateOptionMapping> templateOptionMappings = this.templateOptionMappingLogic.findByTmplId(templateId);
		for (RatingTemplateOptionMapping optionMapping : templateOptionMappings) {
			RatingWeightVo vo = new RatingWeightVo();
			vo.setId(optionMapping.getOptionId());
			vo.setName(optionMapping.getOption().getName());
			vo.setWeight(this.optionWeightLogic.findByTmplIdAndOptionId(optionMapping.getTmplId(), optionMapping.getOptionId()).getWeight());
			vo.setCategory(optionMapping.getOption().getCategory().getLabel());
			vo.setCategoryCode(optionMapping.getOption().getCategory().getCode());
			
			weightVos.add(vo);
			categories.add(optionMapping.getOption().getCategory());
			selected.add(optionMapping.getOptionId());
		}
		//不能在创建后变更权重，应该在模板选择时就确定了得分项
//		for (OptionCategory category : categories) {
//			List<RatingSuppOption> options = this.suppOptionLogic.findByCategoryOrderBySeqAsc(category);
//			for (RatingSuppOption option : options) {
//				if (!selected.contains(option.getId())) {
//					RatingWeightVo vo = new RatingWeightVo();
//					vo.setId(option.getId());
//					vo.setName(option.getName());
//					vo.setWeight(new BigDecimal("0"));
//					vo.setCategory(category.getLabel());
//					vo.setCategoryCode(category.getCode());
//					weightVos.add(vo);
//				}
//			}
//		}
		return weightVos;
	}
	@Override
	public void updateTmplWeight(Long templateId, String options) {
		JSONArray jsonArray = JSONArray.parseArray(options);
		for (Object object : jsonArray) {
			JSONObject option = (JSONObject)object;
			RatingTmplOptionWeight weight = this.optionWeightLogic.findByTmplIdAndOptionId(templateId, option.getLong("id"));
			weight.setWeight(option.getBigDecimal("weight"));
			this.optionWeightLogic.update(weight);
		}
	}
	
	private HSSFCellStyle commonStyle;
	private void initCommonStyle(HSSFWorkbook workbook){
		commonStyle = workbook.createCellStyle();
		commonStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		commonStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		commonStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		commonStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		commonStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		commonStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		HSSFFont font = workbook.createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short)10);
		commonStyle.setFont(font);
	}
	
	@Override
	public File generateVoteExcel(List<List<String>> rows, Long tmplId) {
		//1. 创建excel
		HSSFWorkbook workbook = new HSSFWorkbook();
		PropertiesUtils propertiesUtils = new PropertiesUtils("config.properties");
		String outputPath = propertiesUtils.getProperty(ConfigPropertiesConstant.FILE_OUTPUT_PATH);
		File folder = new File(outputPath);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		RatingTemplate rt = this.templateLogic.findOne(tmplId);
		Rating rating = this.logic.findOne(rt.getRatingId());
		File outputFile = new File(folder + "/" +  rating.getName() + "-" + rt.getName() + "卷" + System.currentTimeMillis() + ".xls");
		HSSFSheet sheet = workbook.createSheet();
		
		//2. 第一行title
		HSSFRow titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(20.25f);
		List<String> titleRowData = rows.get(0);
		
		int columnNum = titleRowData.size();
		
		for (int i = 0; i < columnNum; i++) {
			String data = titleRowData.get(i);
			HSSFCell titleCell = titleRow.createCell(i);
			HSSFCellStyle titleCellStyle = workbook.createCellStyle();
			titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			titleCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			titleCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			titleCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			titleCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			titleCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
			//titleCellStyle.setFillBackgroundColor(HSSFColor.LIGHT_GREEN.index);
			titleCellStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
			HSSFFont titleFont = workbook.createFont();
			titleFont.setFontName("Times New Roman");
			titleFont.setFontHeightInPoints((short)16);
			titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			titleCellStyle.setFont(titleFont);
			titleCell.setCellStyle(titleCellStyle);
			titleCell.setCellType(Cell.CELL_TYPE_STRING);
			titleCell.setCellValue(data);
		}
		
		//3. 第二行开始逐行打印数据
		int rowNum = rows.size();
		this.initCommonStyle(workbook);
		for (int i = 1; i < rowNum; i++) {
			List<String> userRowData = rows.get(i);
			HSSFRow userRow = sheet.createRow(i);
			
			for (int j = 0; j < columnNum; j++) {
				HSSFCell userCell = userRow.createCell(j);
				userCell.setCellStyle(this.commonStyle);
				userCell.setCellType(Cell.CELL_TYPE_STRING);
				userCell.setCellValue(userRowData.get(j));
			}
		}
		//自适应
		sheet.autoSizeColumn(0, true);
		for (int i = 1; i < columnNum; i++) {
//			sheet.autoSizeColumn(i, true);
			HSSFCell cell = sheet.getRow(0).getCell(i);
			sheet.setColumnWidth(i, (int)(cell.getStringCellValue().getBytes().length * 1.2 * 256) );
		}

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(outputFile);
			workbook.write(fos);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				fos.close();
				workbook.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return outputFile;
	}
	@Override
	public Map<String, Object> saveVoteExcel(List<OptionTitle> options, User user, Long tmplId, Attachment voteExcel) {
		
		List<RatingUserVoteResult> results = new ArrayList<RatingUserVoteResult>();
		List<String> ignoreUsers = new ArrayList<String>();
		
		//1. 保存user vote
		RatingTemplate template = this.templateLogic.findOne(tmplId);
		RatingUserVote vote = this.ratingUserVoteLogic.findByRatingIdAndTmplIdAndUserId(template.getRatingId(), tmplId, user.getId());
		if (null == vote) {
			vote = new RatingUserVote();
		}	
		vote.setGroupId(user.getGroupId());
		vote.setPositionId(user.getPositionId());
		vote.setRatingId(template.getRatingId());
		vote.setTmplId(tmplId);
		vote.setUserId(user.getId());
		vote.setVoteDate(new Date());
		vote.setExcelId(voteExcel.getId());
		this.ratingUserVoteLogic.save(vote);
		//2. 根据excel保存user vote item
		this.logger.info("********** readExcel:" + voteExcel.getFileName() + " start ********");
		FileInputStream is = null;
		Workbook workbook = null;
		String filePath = voteExcel.getFilePath();
		try {
			is = new FileInputStream(filePath);
			if (filePath.endsWith(".xls")) {
				workbook = new HSSFWorkbook(is);
			}else if (filePath.endsWith(".xlsx")) {
				//TODO 07
			}else {
				logger.info("not support file:{}", filePath);
				return new HashMap<String, Object>();
			}
			
			//读取excel
			Sheet sheet = workbook.getSheetAt(0);
			if (null != sheet){
				int rows = sheet.getLastRowNum();
				rows ++;
				if (rows < 1) {
					this.logger.info("sheet:{} is empty.",sheet.getSheetName());
				}
				
				//从第二行开始解析
				
				Config config = this.configLogic.findByProperty(ConfigConstant.STATISTICS_FORMAT);
				
				for (int i = 1; i < rows; i++) {
					Row row = sheet.getRow(i);
					if (null == row) {
						this.logger.info("row:{} is empty, continue", i);
						continue;
					}
					String scorerNameWitGroup = row.getCell(0).getStringCellValue();
					int index = scorerNameWitGroup.indexOf("(");
					String scorerName = scorerNameWitGroup;
					if (index != -1) {
						scorerName = scorerNameWitGroup.substring(0, index );
					}
					User scorer = this.userLogic.findByName(scorerName);
					// 判断此用户是否为有效得分用户
					if (!this.isValidVote(scorer, tmplId)) {
						ignoreUsers.add(scorerNameWitGroup);
						continue;
					}
					
					RatingUserVoteResult result = new RatingUserVoteResult();
					result.setName(scorerName);
					
					List<RatingResult> exists = this.ratingResultLogic.findVote(tmplId, user.getId(), scorer.getId());
					if (null != exists && !exists.isEmpty()) {
						this.ratingResultLogic.deletes(exists);
					}
					
					for (int j = 0; j < options.size(); j++) {
						OptionTitle ot = options.get(j);
						Long optionId = Long.valueOf(ot.getValue().substring("option_".length()));
						Float score = ExcelUtil.getCellFloatValue(row.getCell(j+1));
								//Float.valueOf(row.getCell(j+1).getStringCellValue());
						
						RatingUserVoteItem vi = this.ratingUserVoteItemLogic.findByUserVoteIdAndOptionIdAndScorer(vote.getId(), optionId, scorer.getId());
						if (null == vi) {
							vi = new RatingUserVoteItem();
						}
						vi.setUserVoteId(vote.getId());
						vi.setOptionId(optionId);
						vi.setScore(score);
						vi.setScorer(scorer.getId());
						vi.setScorerGroup(scorer.getGroupId());
						vi.setScorerPosition(scorer.getPositionId());
						this.ratingUserVoteItemLogic.save(vi);

						RatingResult rr = new RatingResult();
						rr.setTmplId(tmplId);
						rr.setVoterId(user.getId());
						rr.setVoterGroupId(user.getGroupId());
						rr.setVoterPositionId(user.getPositionId());
						rr.setScorer(scorer.getId());
						rr.setScorerGroup(scorer.getGroupId());
						rr.setScorerPosition(scorer.getPositionId());
						rr.setScore(score);
						rr.setOptionId(optionId);
						this.ratingResultLogic.save(rr);
						
						
						//插入取代视图的 rating result
						result.getScores().add(StringUtil.formatScore(score, config));
					}
					
					results.add(result);
				}
			}else {
				this.logger.info("No sheet exist, break;");
			}
			
		} catch (Exception e) {
			this.logger.error("{}",e);
		}finally{
			if (null != workbook) {
				try {
					workbook.close();
				} catch (IOException e) {
					this.logger.error("{}",e);
				}
			}
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					this.logger.error("{}",e);
				}
			}
		}
		this.logger.info("********** readExcel:" + voteExcel.getFileName() + " end ********");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", results);
		map.put("ignore", ignoreUsers);
		
		return map;
	}

	private boolean isValidVote(User scorer,Long tmplId) {
		boolean valid = false;
		List<RatingTemplateVoter> templateVoters = this.templateVoterLogic.findByTmplId(tmplId);
		for (RatingTemplateVoter templateVoter : templateVoters) {
			if (templateVoter.getGroupId().equals(scorer.getGroupId())) {
				if (null == templateVoter.getPositionId()) {
					valid = true;
					break;
				}else {
					if (templateVoter.getPositionId().equals(scorer.getPositionId())) {
						valid = true;
						break;
					}
				}
			}
		}
		return valid;
	}
}
