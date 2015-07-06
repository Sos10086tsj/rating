package com.chinesedreamer.rating.template.util;

import java.util.ArrayList;
import java.util.List;

import com.chinesedreamer.rating.system.group.UserGroupLevel;
import com.chinesedreamer.rating.system.user.UserPositionType;

/**
 * Description: 
 * @author Paris
 * @date Jul 6, 20153:31:04 PM
 * @version beta
 */
public class RatingSuppTmplScoerUtil {
	/**
	 * 获取模板参与用户
	 * A	组员之间
	 * B	组长、总体组队组员
	 * C	组长、总体组之间
	 * D	组员对组长、总体组
	 * @param code
	 * @return
	 */
	public static List<TmplScoerVO> getTemplateScores(String code){
		List<TmplScoerVO> scoers = new ArrayList<TmplScoerVO>();
		if (code.equals("A") || code.equals("D")) {
			TmplScoerVO vo = new TmplScoerVO();
			vo.setPosition(UserPositionType.TEAM_MATE);
			vo.setGroup(UserGroupLevel.PUTONG);
			scoers.add(vo);
		}else if (code.equals("B") || code.equals("C")) {
			TmplScoerVO vo1 = new TmplScoerVO();
			vo1.setPosition(UserPositionType.LEADER);
			vo1.setGroup(UserGroupLevel.PUTONG);
			scoers.add(vo1);
			
			TmplScoerVO vo2 = new TmplScoerVO();
			vo2.setGroup(UserGroupLevel.ZONGTI);
			scoers.add(vo2);
		}
		return scoers;
	}
}
