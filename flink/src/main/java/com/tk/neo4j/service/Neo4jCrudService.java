//package com.tk.neo4j.service;
//
//import cn.tk.cip.common.CipConstant;
//import cn.tk.cip.common.Consts;
//import cn.tk.cip.common.Consts.ItemType;
//import cn.tk.cip.common.util.DictionaryUtil;
//import cn.tk.cip.domain.node.*;
//import cn.tk.cip.domain.relationship.RelationShip;
//import cn.tk.cip.dto.RelationDto;
//import cn.tk.cip.dto.sourcedata.*;
//import cn.tk.cip.repository.*;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.CopyOnWriteArrayList;
//
//@SuppressWarnings("rawtypes")
//@Service
//public class Neo4jCrudService {
//
//	private Logger log = LoggerFactory.getLogger(Neo4jCrudService.class);
//
//	@Autowired
//	private NaturalPersonRepository personRepository;
//	@Autowired
//	private RelationShipRepository relationshipRepository;
//	@Autowired
//	private PolicyRepository policyRepository;
//	@Autowired
//	private OrganizationRepository orgRepository;
//	@Autowired
//	private AccountRepository accountRepository;
//	@Autowired
//	@Qualifier("taskExecutor")
//	private ThreadPoolTaskExecutor taskExecutor;
//
//
//	@Transactional
//	public void saveCtmPhones(List<CustomerContactInfoDto> dtos) {
//		List<RelationShip> relations = new ArrayList<>();
//		for (CustomerContactInfoDto customerContactInfoDto : dtos) {
//			NaturalPerson natural = new NaturalPerson();
//			natural.setCustomerId(customerContactInfoDto.getCustomerId());
//
//			ContactInfo contact = new ContactInfo();
//			contact.setId(customerContactInfoDto.getContactType()+customerContactInfoDto.getContactMode());
//			contact.setType(customerContactInfoDto.getContactType());
//			contact.setNumber(customerContactInfoDto.getContactMode());
//
//			RelationShip<NaturalPerson, ContactInfo> relationship = new RelationShip<>(
//					natural, contact, Consts.Owns, CipConstant.NEO4J_RELATION_PERSON_TO_CONTACT);
//			relations.add(relationship);
//		}
//		relationshipRepository.saveContactRelations(relations);
//	}
//
//	@Transactional
//	public void savePolicyRoles(List<PolicyRoleDto> policyRoleDtos) {
//		List<RelationShip> relations = new ArrayList<>();
//		for (PolicyRoleDto policyRoleDto : policyRoleDtos) {
//			String relCode = policyRoleDto.getPolicyRole();
//
//			NaturalPerson natural = new NaturalPerson();
//			natural.setCustomerId(policyRoleDto.getCustomerId());
//
//			Policy policy = new Policy();
//			policy.setPolicyNo(policyRoleDto.getPolicyNo());
//
//			String rel = DictionaryUtil.getDictValueByCode(ItemType.PCRole.name(), relCode);
//			if (rel == null) {
//				rel = relCode;
//			}
//			RelationShip<NaturalPerson, Policy> relationship = new RelationShip<>(
//					natural, policy, rel, CipConstant.NEO4J_RELATION_PERSON_TO_POLICY);
//			relations.add(relationship);
//		}
//		relationshipRepository.savePolicyRelations(relations);
//	}
//
//	@Transactional
//	public void saveCtmAccounts(List<CustomerAccountDto> dtos) {
//		List<RelationShip> accountRelations = new ArrayList<>();
//		List<RelationShip> orgAccountRelations = new ArrayList<>();
//		List<RelationShip> cityRelations = new ArrayList<>();
//
//		for (CustomerAccountDto customerAccountDto : dtos) {
//
//			Account account = new Account();
//			account.setAccountNo(customerAccountDto.getAccountNo());
//			account.setAccountName(customerAccountDto.getAccountName());
//			account.setPayeeBankName(customerAccountDto.getPayeeBankName());
//
//			String customerId = customerAccountDto.getCustomerId();
//
//			if (DictionaryUtil.isLegalPerson(customerId)){
//				Organization org = new Organization();
//				org.setCustomerId(customerId);
//
//				RelationShip<Organization, Account> relationship = new RelationShip<>(
//						org, account, Consts.Owns, CipConstant.NEO4J_RELATION_PERSON_TO_ACCOUNT);
//				orgAccountRelations.add(relationship);
//			} else {
//				NaturalPerson natural = new NaturalPerson();
//				natural.setCustomerId(customerId);
//
//				RelationShip<NaturalPerson, Account> relationship = new RelationShip<>(
//						natural, account, Consts.Owns, CipConstant.NEO4J_RELATION_PERSON_TO_ACCOUNT);
//				accountRelations.add(relationship);
//			}
//			if (StringUtils.isNotEmpty(customerAccountDto.getPayeeBankCity())) {
//				String cityCode = DictionaryUtil.getDictCodeByValue(ItemType.CityCode.name(), customerAccountDto.getPayeeBankCity());
//				if (cityCode == null) {
//					continue;
//				}
//				City city = new City();
//				city.setCityCode(cityCode);
//				city.setCityName(customerAccountDto.getPayeeBankCity());
//
//				RelationShip<Account, City> relationship1 = new RelationShip<>(
//						account, city, Consts.Place, CipConstant.NEO4J_RELATION_ACCOUNT_BANKCITY);
//				cityRelations.add(relationship1);
//			}
//		}
//		relationshipRepository.saveAccountRelations(accountRelations);
//		relationshipRepository.saveOrgAccountRelations(orgAccountRelations);
//		relationshipRepository.saveAccountCityRelations(cityRelations);
//	}
//
//	@Transactional
//	public void saveCtmRelations(CopyOnWriteArrayList<CustomerRelationDto> dtos) {
//		List<RelationShip> relations = new ArrayList<>();
//		for (CustomerRelationDto customerRelationDto : dtos) {
//			String relCode = customerRelationDto.getRelation();
//
//			NaturalPerson n = new NaturalPerson();
//			n.setCustomerId(customerRelationDto.getCustomerId1());
//
//			NaturalPerson n1 = new NaturalPerson();
//			n1.setCustomerId(customerRelationDto.getCustomerId2());
//
//			String rel = DictionaryUtil.getDictValueByCode(ItemType.CustomerRelation.name(), relCode);
//			if (rel == null) {
//				rel = relCode;
//			}
//			RelationShip<NaturalPerson, NaturalPerson> relationship = new RelationShip<>(
//					n, n1, rel, CipConstant.NEO4J_RELATION_PERSON_TO_PERSON);
//			relations.add(relationship);
//		}
//		relationshipRepository.saveCtmRelations(relations);
//
//		taskExecutor.execute(new RelationCheckTask(dtos));
//	}
//
//	@Transactional
//	public void saveNaturalPersons(List<CustomerBasicInfoDto> dtos) {
//		List<NaturalPerson> naturalPersons = new ArrayList<>();
//
//		for (CustomerBasicInfoDto customerBasicInfoDto : dtos) {
//			NaturalPerson natural = new NaturalPerson();
//			natural.setCustomerId(customerBasicInfoDto.getCustomerId());
//			natural.setName(customerBasicInfoDto.getName());
//			natural.setGender(customerBasicInfoDto.getGender());
//			natural.setIdentifyNumber(customerBasicInfoDto.getIdentifyNumber());
//			natural.setIdentifyType(customerBasicInfoDto.getIdentifyType());
//			natural.setBirthday(customerBasicInfoDto.getBirthday());
//			natural.setNationality(customerBasicInfoDto.getNationnality());
//			natural.setCardValidityStartDate(customerBasicInfoDto.getCardValidityStartDate());
//			natural.setCardValidityEndDate(customerBasicInfoDto.getCardValidityEndDate());
//			natural.setLabel(customerBasicInfoDto.getLabel());
//			naturalPersons.add(natural);
//		}
//		personRepository.saveAllPerson(naturalPersons);
//	}
//
//	@Transactional
//	public void saveLabels(List<CustomerLabelDto> dtos) {
//		List<NaturalPerson> naturalPersons = new ArrayList<>();
//
//		for (CustomerLabelDto customerLabelDto : dtos) {
//			NaturalPerson natural = new NaturalPerson();
//			natural.setCustomerId(customerLabelDto.getCustomerId());
//			natural.setLabel(customerLabelDto.getLabel());
//			naturalPersons.add(natural);
//		}
//		personRepository.saveLabels(naturalPersons);
//	}
//
//	@Transactional
//	public void savePolicys(List<PolicySummary> dtos) {
//		List<Policy> policys = new ArrayList<>();
//		List<RelationShip> relations = new ArrayList<>();
//		for (PolicySummary ps : dtos) {
//			Policy policy = new Policy();
//			policy.setPolicyNo(ps.getPolicyNo());
//			policy.setFromId(ps.getFromId());
//			policy.setProductCode(ps.getProductCode());
//			policy.setRiskCode(ps.getRiskCode());
//			policy.setSystemId(ps.getSystemId());
//			policy.setFlowId(ps.getFlowId());
//			policys.add(policy);
//
//			if (!DictionaryUtil.filterCode(ItemType.CityCode.name(), ps.getPolicyLocationCode())) {
//				String cityName = DictionaryUtil.getDictValueByCode(ItemType.CityCode.name(),ps.getPolicyLocationCode());
//				City city = new City();
//				city.setCityCode(ps.getPolicyLocationCode());
//				city.setCityName(cityName);
//
//				RelationShip<Policy, City> relationship = new RelationShip<>(
//						policy, city, Consts.Place, CipConstant.NEO4J_RELATION_POLICY_LOCATION_CITY);
//				relations.add(relationship);
//			}
//		}
//		policyRepository.saveAllPolicy(policys);
//		relationshipRepository.savePolicyCityRelations(relations);
//	}
//
//	@Transactional
//	public void saveOrganizations(List<OrganizationDto> dtos) {
//		List<Organization> organizations = new ArrayList<>();
//
//		for (OrganizationDto dto : dtos) {
//			Organization org = new Organization();
//			org.setCustomerId(dto.getCustomerId());
//			org.setOrganizationName(dto.getOrganizationName());
//			org.setContactName(dto.getContactName());
//			org.setContactMobilePhone(dto.getContactMobilePhone());
//			org.setIdentifyNumber(dto.getCompanyCode());
//			org.setIdentifyType(dto.getCompanyType());
//			org.setLegalPersonName(dto.getLegalPersonName());
//			org.setLegalPersonCardValidityStartDate(dto.getLegalPersonCardValidityStartDate());
//			org.setLegalPersonCardValidityEndDate(dto.getLegalPersonCardValidityEndDate());
//			org.setOrganizationAddress(dto.getOrganizationAddress());
//			organizations.add(org);
//		}
//		orgRepository.saveAll(organizations);
//	}
//
//	@Transactional
//	public void saveCtmCity(List<CustomerAddress> dtos) {
//		List<RelationShip> relations = new ArrayList<>();
//		for (CustomerAddress customerAddress : dtos) {
//			if(DictionaryUtil.filterCode(ItemType.CityCode.name(), customerAddress.getCityCode())){
//				continue;
//			}
//
//			NaturalPerson natural = new NaturalPerson();
//			natural.setCustomerId(customerAddress.getCustomerId());
//			City city = new City();
//			city.setCityCode(customerAddress.getCityCode());
//			String cityName = DictionaryUtil.getDictValueByCode(ItemType.CityCode.name(), customerAddress.getCityCode());
//			city.setCityName(cityName);
//			RelationShip<NaturalPerson, City> relationShip = new RelationShip<>(
//					natural, city, Consts.Place, CipConstant.NEO4J_RELATION_PERSON_ADDRESS);
//			relations.add(relationShip);
//		}
//		relationshipRepository.saveCtmCityRelations(relations);
//	}
//
//	@Transactional
//	public void changeCtmRelation(List<RelationDto> relations) {
//		relationshipRepository.changeCtmRelation(relations);
//	}
//
//}
