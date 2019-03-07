package com.online.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.DataAccessException;
import com.online.Filter;
import com.online.Filter.Operator;
import com.online.dao.EnterpriseDictionaryDao;
import com.online.entity.EnterpriseDictionary;
import com.online.entity.EnterpriseDictionaryRep;
import com.online.entity.online.TaskManager;
import com.online.excelexport.ExcelWriteUtils;
import com.online.service.EnterpriseDictionaryRepService;
import com.online.service.EnterpriseDictionaryService;
import com.online.service.TaskManagerService;
import com.online.util.Constants;
import com.online.util.SimilarityUtil;

/**
 * 企业名录库
 */
@Service("enterpriseDictionaryServiceImpl")
public class EnterpriseDictionaryServiceImpl extends BaseServiceImpl<EnterpriseDictionary, Integer>
		implements EnterpriseDictionaryService {

	@Autowired
	public EnterpriseDictionaryDao enterpriseDictionaryDao;

	@Autowired
	private TaskManagerService taskManagerService;

	@Autowired
	@Lazy
	public EnterpriseDictionaryRepService enterpriseDictionaryRepService;

	int num = 0;

	private static volatile boolean falg = false;

	@Override
	public EnterpriseDictionary saveEntity(EnterpriseDictionary enterpriseDictionary) {

		EnterpriseDictionary enterpriseDictionaryback = save(enterpriseDictionary);

		return enterpriseDictionaryback;
	}

	public EnterpriseDictionary updateEntity(EnterpriseDictionary entity) {
		EnterpriseDictionary enterpriseDictionaryback = update(entity, "status", "source", "opinion",
				"enterpriseDictionaryRep");
		// 郑有权
		return enterpriseDictionaryback;
	}

	public void deleteEntity(Integer id) {
		EnterpriseDictionary enterpriseDictionaryback = find(id);
		delete(enterpriseDictionaryback);

	}

	public void updateStatus(EnterpriseDictionary enterpriseDictionary) {

	}

	@Override
	@Transactional
	public void exportTask(String filePath, Integer projectId) throws DataAccessException {
		Sheet sheet = ExcelWriteUtils.getSheet(filePath, 0);
		if(sheet == null){
			throw new DataAccessException("文件错误");
		}
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			Row row = sheet.getRow(i);
			if (row == null || ExcelWriteUtils.isBlankRow(row))
				continue;
			row.getPhysicalNumberOfCells();
			EnterpriseDictionary enterpriseDictionary = new EnterpriseDictionary();
			enterpriseDictionary.setAlreadyEnterpriseId(ExcelWriteUtils.getIntegerValue(row, 0));
			enterpriseDictionary.setSign(ExcelWriteUtils.getStringValue(row, 1));
			enterpriseDictionary.setEnterpriseName(ExcelWriteUtils.getStringValue(row, 2));
			enterpriseDictionary.setCountyName(ExcelWriteUtils.getStringValue(row, 3));
			enterpriseDictionary.setDetailAddress(ExcelWriteUtils.getStringValue(row, 4));
			enterpriseDictionary.setInputIndustry(ExcelWriteUtils.getStringValue(row, 5));
			enterpriseDictionary.setCode(ExcelWriteUtils.getStringValue(row, 6));
			enterpriseDictionary.setContacts(ExcelWriteUtils.getStringValue(row, 7));
			enterpriseDictionary.setContactsPhone(ExcelWriteUtils.getStringValue(row, 8));
			enterpriseDictionary.setCorporation(ExcelWriteUtils.getStringValue(row, 9));
			enterpriseDictionary.setStatus(Constants.ENTERPRISE_DICTIONARY_STATUS_NEW);

			enterpriseDictionary.setProjectId(projectId);
			saveEntity(enterpriseDictionary);
		}

	}

	@Transactional
	@Override
	public void createTaskManager(String ids) throws Exception {

		if (StringUtils.isNotBlank(ids)) {
			String[] idstrs = ids.split(",");
			for (String id : idstrs) {
				EnterpriseDictionary entity = find(Integer.parseInt(id));
				if (!Constants.ENTERPRISE_DICTIONARY_STATUS_CREATETASK.equals(entity.getStatus())) {
					if (entity.getProjectId() == null) {
						throw new DataAccessException("项目不能为空");
					}
					TaskManager taskManager = new TaskManager();
					taskManager.setAlreadyEnterpriseId(entity.getAlreadyEnterpriseId());
					taskManager.setEnterpriseName(entity.getEnterpriseName());
					taskManager.setCountyName(entity.getCountyName());
					taskManager.setDetailAddress(entity.getDetailAddress());
					taskManager.setContactPerson(entity.getContacts());
					taskManager.setProjectId(entity.getProjectId());
					taskManager.setCode(entity.getCode());
					taskManager.setCorporation(entity.getCorporation());
					taskManager.setStatus(Constants.TASK_STATUS_NEW);
					taskManager.setInquirer(entity.getSource());
					taskManager.setContactPhone(entity.getContactsPhone());
					taskManager.setInputIndustry(entity.getInputIndustry());
					taskManagerService.save(taskManager);
					entity.setStatus(Constants.ENTERPRISE_DICTIONARY_STATUS_CREATETASK);
					update(entity);
				} else {
					throw new DataAccessException(entity.getEnterpriseName() + "已生成任务");
				}

			}
		}

	}

	@Override
	public boolean filtrate(EnterpriseDictionary enterpriseDictionary) {
		if (!falg) {

			new Thread(() -> {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				falg = true;
				List<Filter> filters = new ArrayList<>();
				if (enterpriseDictionary != null) {
					if (enterpriseDictionary.getProjectId() != null) {
						filters.add(new Filter("projectId", Operator.eq, enterpriseDictionary.getProjectId()));
					}
					if (StringUtils.isNotBlank(enterpriseDictionary.getEnterpriseName())) {
						filters.add(new Filter("enterpriseName", Operator.like,
								"%" + enterpriseDictionary.getEnterpriseName() + "%"));
					}
					if (StringUtils.isNotBlank(enterpriseDictionary.getCountyName())) {
						filters.add(new Filter("countyName", Operator.like,
								"%" + enterpriseDictionary.getCountyName() + "%"));
					}

				}

				List<EnterpriseDictionary> lists = findList(null, filters, null);
				Map<String, List<EnterpriseDictionary>> collect = lists.stream()
						.collect(Collectors.groupingBy(
								o1 -> StringUtils.isEmpty(o1.getCountyName()) ? "无区县" : o1.getCountyName(),
								Collectors.toList()));
				// List<EnterpriseDictionary> lists = findAll();
				Set<String> countys = collect.keySet();
				// List<Set<EnterpriseDictionary>> result = new Vector<>();
				List<Set<EnterpriseDictionary>> sets = new CopyOnWriteArrayList<>();
				List<EnterpriseDictionary> noCountys = collect.get("无区县");
				num = 0;

				// enterpriseDictionaryDao.setNull();
				enterpriseDictionaryRepService.deleteAll();

				if (noCountys != null) {
					List<EnterpriseDictionary> hasCountys = lists.stream().filter(o1 -> !noCountys.contains(o1))
							.collect(Collectors.toList());
					b(sets, noCountys, hasCountys);
				}
				for (String county : countys) {
					// System.out.println("county==" +county);
					// System.out.println("county=="
					// +collect.get(county).size());
					if ("无区县".equals(county)) {
						continue;
					}
					a(sets, collect.get(county));
				}
				System.out.println("对比结束");
				falg = false;

				enterpriseDictionaryRepService.updateRep(sets, enterpriseDictionary.getProjectId());

			}).start();
		}
		return falg;
		

	}

	public Double filter(EnterpriseDictionary entry1, EnterpriseDictionary entry2) {
		// 1.如果组织机构代码存在则比较组织机构代码
		if (StringUtils.isNotBlank(entry1.getCode()) && StringUtils.isNotBlank(entry2.getCode())) {
			if (entry1.getCode().equals(entry2.getCode())) {
				return 1.0;
			} else {
				return 0.0;
			}
			// 2.如有没有组织机构代码，把区县一样、名称相似度80%以上的筛选出来
		} else if (StringUtils.isNotBlank(entry1.getCountyName()) && StringUtils.isNotBlank(entry2.getCountyName())) {
			// 如果区县名称一样，判断企业名称
			if (entry1.getCountyName().equals(entry2.getCountyName())) {
				if (StringUtils.isNotBlank(entry1.getEnterpriseName())
						&& StringUtils.isNotBlank(entry2.getEnterpriseName())) {
					// return
					// Similarity.SimilarityBack(entry1.getEnterpriseName(),
					// entry2.getEnterpriseName());
					return SimilarityUtil.sim(entry1.getEnterpriseName(), entry2.getEnterpriseName());
					// return
					// AutomaticRating.SimilarDegree(entry1.getEnterpriseName(),
					// entry2.getEnterpriseName());
					// return
					// SimilarityRatio.getSimilarityRatio(entry1.getEnterpriseName(),
					// entry2.getEnterpriseName());
				} else {
					return 0.0;
				}
			} else {
				return 0.0;
			}
		} else if (StringUtils.isNotBlank(entry1.getEnterpriseName())
				&& StringUtils.isNotBlank(entry2.getEnterpriseName())) {
			// return Similarity.SimilarityBack(entry1.getEnterpriseName(),
			// entry2.getEnterpriseName());
			return SimilarityUtil.sim(entry1.getEnterpriseName(), entry2.getEnterpriseName());
			// return AutomaticRating.SimilarDegree(entry1.getEnterpriseName(),
			// entry2.getEnterpriseName());
			// return
			// SimilarityRatio.getSimilarityRatio(entry1.getEnterpriseName(),
			// entry2.getEnterpriseName());
		} else if (StringUtils.isNotBlank(entry1.getDetailAddress())
				&& StringUtils.isNotBlank(entry2.getDetailAddress())) {

			// return Similarity.SimilarityBack(entry1.getDetailAddress(),
			// entry2.getDetailAddress());
			return SimilarityUtil.sim(entry1.getDetailAddress(), entry2.getDetailAddress());
			// return AutomaticRating.SimilarDegree(entry1.getDetailAddress(),
			// entry2.getDetailAddress());
			// return
			// SimilarityRatio.getSimilarityRatio(entry1.getDetailAddress(),
			// entry2.getDetailAddress());

		} else {
			return 0.0;
		}

	}

	public List<Set<EnterpriseDictionary>> a(List<Set<EnterpriseDictionary>> sets,
			List<EnterpriseDictionary> enterpriseDictionarys) {
		int endSum = enterpriseDictionarys.size();
		for (int i = 0; i < endSum; i++) {
			EnterpriseDictionary source = enterpriseDictionarys.get(i);
			for (int y = i + 1; y < endSum; y++) {
				EnterpriseDictionary org = enterpriseDictionarys.get(y);
				// System.out.println("坐标i===" + i + "坐标y===" + y);
				double similarity = filter(source, org);
				if (similarity > 0.8) {
					if (sets.size() > 0) {
						boolean flag = false;
						for (Set<EnterpriseDictionary> setEnterpriseDictionary : sets) {
							if (setEnterpriseDictionary.contains(source) || setEnterpriseDictionary.contains(org)) {
								setEnterpriseDictionary.add(source);
								setEnterpriseDictionary.add(org);
								flag = true;
								break;

							}
						}
						if (!flag) {
							Set<EnterpriseDictionary> repetitionEnterpriseDictionary = new HashSet<>();
							repetitionEnterpriseDictionary.add(source);
							repetitionEnterpriseDictionary.add(org);
							sets.add(repetitionEnterpriseDictionary);
						}
					} else {
						Set<EnterpriseDictionary> repetitionEnterpriseDictionary = new HashSet<>();
						repetitionEnterpriseDictionary.add(source);
						repetitionEnterpriseDictionary.add(org);
						sets.add(repetitionEnterpriseDictionary);
					}
				}

				num++;
				if (num % 10000 == 0) {
					System.out.println("运算次数" + num);
				}
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return sets;
	}

	public List<Set<EnterpriseDictionary>> b(List<Set<EnterpriseDictionary>> sets,
			List<EnterpriseDictionary> sourceList, List<EnterpriseDictionary> orgList) {
		int sourceSize = sourceList.size();
		int orgSize = orgList.size();

		for (int i = 0; i < sourceSize; i++) {
			EnterpriseDictionary source = sourceList.get(i);
			for (int y = 0; y < orgSize; y++) {
				// System.out.println("源坐标i===" + i + "目标坐标y===" + y);
				EnterpriseDictionary org = orgList.get(y);
				if (source.getId().equals(org.getId())) {
					continue;
				}
				double similarity = filter(source, org);
				if (similarity > 0.8) {
					addResult(sets, source, org);
				}
				num++;

				if (num % 10000 == 0) {
					System.out.println("运算次数" + num);
				}

			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return sets;
	}

	public void addResult(List<Set<EnterpriseDictionary>> sets, EnterpriseDictionary source, EnterpriseDictionary org) {
		if (sets.isEmpty()) {
			Set<EnterpriseDictionary> repetitionEnterpriseDictionary = new HashSet<>();
			repetitionEnterpriseDictionary.add(source);
			repetitionEnterpriseDictionary.add(org);
			sets.add(repetitionEnterpriseDictionary);
		}
		boolean flag = false;
		for (Set<EnterpriseDictionary> setEnterpriseDictionary : sets) {
			if (setEnterpriseDictionary.contains(source) || setEnterpriseDictionary.contains(org)) {
				setEnterpriseDictionary.add(source);
				setEnterpriseDictionary.add(org);
				flag = true;
				break;

			}
		}
		if (!flag) {
			Set<EnterpriseDictionary> repetitionEnterpriseDictionary = new HashSet<>();
			repetitionEnterpriseDictionary.add(source);
			repetitionEnterpriseDictionary.add(org);
			sets.add(repetitionEnterpriseDictionary);
		}

	}

	@Override
	public void updateRepNull(EnterpriseDictionary enterpriseDictionary) {
		// 郑有权
		enterpriseDictionary.setEnterpriseDictionaryRep(null);
		update(enterpriseDictionary);
	}

}
