package com.loupan.boot.service.log;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loupan.boot.base.BeanMapper;
import com.loupan.boot.base.DynamicSpecifications;
import com.loupan.boot.base.SearchFilter;
import com.loupan.boot.base.SearchFilter.Operator;
import com.loupan.boot.domain.centaec.estate.LdEstate;
import com.loupan.boot.domain.centaec.log.LdTbLog;
import com.loupan.boot.domain.centaec.log.LdTbLogContent;
import com.loupan.boot.dto.EstateDto;
import com.loupan.boot.repository.centaec.log.LdLogRepository;


@Service
public class LdLogService {

	private static Logger logger = LoggerFactory.getLogger(LdLogService.class);
	@Autowired
	private LdLogRepository ldLogRepository;
	
	@Transactional
	public LdTbLog saveOperation(LdTbLog ldTbLog) throws Exception{
		ldTbLog.setDt(new Date());
		return ldLogRepository.save(ldTbLog);
	}
	

	public Page<LdTbLog> findLdTbLogAll(Map<String, Object> searchParams,Pageable pageable,String projcetid) {
		Set<SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.add(new SearchFilter("projectId", Operator.EQ, projcetid));
		Specification<LdTbLog> spec = DynamicSpecifications.bySearchFilter(filters, LdTbLog.class);
		return ldLogRepository.findAll(spec, pageable);
	}
	
	
	public List<LdTbLogContent>  contrastObj(Object oldEntity, Object newEntity,List<Map<String,Object>> commonList) {
		List<LdTbLogContent> logContent = new ArrayList<LdTbLogContent>();
		Date dateObj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (oldEntity instanceof LdEstate && newEntity instanceof LdEstate) {
			EstateDto pojo1 = BeanMapper.map(oldEntity,EstateDto.class);
			EstateDto pojo2 = BeanMapper.map(newEntity,EstateDto.class);

			List textList = new ArrayList<String>();
			try {
				Class clazz = pojo1.getClass();
				Field[] fields = pojo1.getClass().getDeclaredFields();
				int i = 1;
				String str = "";
				for (Field field : fields) {
					if ("serialVersionUID".equals(field.getName())) {
					} else {
						PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
						Method getMethod = pd.getReadMethod();
						Object o1 = getMethod.invoke(pojo1);
						Object o2 = getMethod.invoke(pojo2);
						LdTbLogContent content = new LdTbLogContent();
						if (o2 == null) {
							o2 = "";
						}
						if (o2 instanceof Date) {
							o1 = sdf.format(o1);
							o2 = sdf.format(o2);
						}
						if (o1 == null) {
							o1 = "";
						}
						if (!o1.toString().equals(o2.toString())) {
							content.setNewdata(o2.toString());
							content.setOlddata(o1.toString());
							content.setTbvalue(getCommon(commonList, field.getName()));
							content.setTbkey(field.getName());
							logContent.add(content);
							if (i != 1) {
								str += ";";
							}
							str += i + "、字段名称" + field.getName() + ",旧值:" + o1 + ",新值:" + o2;
							i++;
						}
					}
				}
				System.out.println(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return logContent;
	}
	
	
	public static String getCommon(List<Map<String,Object>> commonList,String key){
		String baseKey = "";
		for(Map<String,Object> map : commonList){
			 baseKey = map.get("Field").toString().replaceAll("_", "");
			if(key.equalsIgnoreCase(baseKey)){
				baseKey = map.get("Comment").toString();
				break;
			}
		}
		return baseKey;
	}
}
