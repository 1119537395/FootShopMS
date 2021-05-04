package com.fish.business.dao;

import com.fish.business.domain.Pedicure;
import com.fish.business.vo.PedicureVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @InterfaceName RoomDao
 * @Description 足疗(Pedicure)数据访问层
 * @Author 柚子茶
 * @Date 2021/3/5 11:24
 * @Version 1.0
 */
public interface PedicureDao {

	/**
	 * @param pedicureId 足疗项目ID
	 * @return int
	 * @description 根据ID删除项目信息
	 * @author 柚子茶
	 * @date 2021/3/5 11:31
	 **/
	int deleteByPrimaryKey(Integer pedicureId);

	/**
	 * @param record 足疗项目实体类
	 * @return int
	 * @description 添加项目信息
	 * @author 柚子茶
	 * @date 2021/3/5 11:31
	 **/
	int insert(Pedicure record);

	/**
	 * @param record
	 * @return int
	 * @description 根据条件添加足疗项目信息
	 * @author 柚子茶
	 * @date 2021/3/5 11:31
	 **/
	int insertSelective(Pedicure record);

	/**
	 * @param pedicureId 足疗项目ID
	 * @return Pedicure
	 * @description 根据ID查询项目信息
	 * @author 柚子茶
	 * @date 2021/3/5 11:33
	 **/
	Pedicure selectByPrimaryKey(Integer pedicureId);

	/**
	 * @param record
	 * @return int
	 * @description 根据条件更新项目信息
	 * @author 柚子茶
	 * @date 2021/3/5 11:33
	 **/
	int updateByPrimaryKeySelective(Pedicure record);

	/**
	 * @param record
	 * @return int
	 * @description 更新项目信息
	 * @author 柚子茶
	 * @date 2021/3/5 11:37
	 **/
	int updateByPrimaryKey(Pedicure record);

	/**
	 * @param pedicure
	 * @return List<Pedicure>
	 * @description 根据条件查询项目信息
	 * @author 柚子茶
	 * @date 2021/3/5 12:20
	 **/
	List<Pedicure> selectPedicureInfoByList(Pedicure pedicure);

	/**
	 * @return List<Pedicure>
	 * @description 查询足疗项目信息
	 * @author 柚子茶
	 * @date 2021/3/7 19:44
	 **/
	List<Pedicure> queryPedicureInfo();

	/**
	 * @param pedicureId 项目ID
	 * @return Pedicure
	 * @description 根据项目ID查询项目金额
	 * @author 柚子茶
	 * @date 2021/3/7 20:42
	 **/
	Pedicure queryPedicureByName(@Param("pedicureId") Integer pedicureId);
}