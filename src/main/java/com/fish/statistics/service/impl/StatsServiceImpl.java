package com.fish.statistics.service.impl;

import com.fish.statistics.dao.StatsDao;
import com.fish.statistics.domain.BaseEntity;
import com.fish.statistics.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StatsServiceImpl
 * @Description 统计分析的业务层接口实现类
 * @Author 柚子茶
 * @Date 2021/3/8 21:53
 * @Version 1.0
 */
@Service
public class StatsServiceImpl implements StatsService {

	@Autowired
	private StatsDao statsDao;

	/**
	 * @return Map<String, Object>
	 * @description 查询销售额
	 * @author 柚子茶
	 * @date 2021/3/8 22:38
	 **/
	@Override
	public Map<String, Object> loadSaleChartData() {
		// 查询出符合统计图的格式数据
		List<BaseEntity> baseEntityList = this.statsDao.querySaleChartData();

		// 创建List集合存储查询到的名称和数值
		List<String> names = new ArrayList<>();
		List<BigDecimal> values = new ArrayList<>();

		// 遍历存储名称和数值
		for (BaseEntity entity : baseEntityList) {
			names.add(entity.getName());
			values.add((BigDecimal) entity.getValue());
		}

		// 最后将List集合保存到Map中
		Map<String, Object> map = new HashMap<>(16);
		map.put("name", names);
		map.put("value", values);

		return map;
	}

	/**
	 * @return BigDecimal
	 * @description 查询总销售额
	 * @author 柚子茶
	 * @date 2021/3/8 23:13
	 **/
	@Override
	public BigDecimal loadSaleTotalAmount() {
		BaseEntity baseEntity = this.statsDao.querySaleTotalAmount();
		return baseEntity.getTotalAmount();
	}

	/**
	 * @return List<BaseEntity>
	 * @description 统计足疗项目的使用次数
	 * @author 柚子茶
	 * @date 2021/3/9 0:19
	 **/
	@Override
	public List<BaseEntity> queryFanChartDataByJson() {
		return this.statsDao.queryFanChartDataByJson();
	}
}
