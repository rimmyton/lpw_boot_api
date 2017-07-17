package com.loupan.boot.domain.centaec.estate;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the ld_estate database table.
 * 
 */
@Entity
@Table(name = "ld_estate")
@NamedQuery(name = "LdEstate.findAll", query = "SELECT l FROM LdEstate l")
public class LdEstate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(name = "AD_NAME", length = 50)
	private String adName;

	@Column(name = "AGENCY_MODE", length = 255)
	private String agencyMode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AGENCY_VALID_BEGIN")
	private Date agencyValidBegin;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AGENCY_VALID_END")
	private Date agencyValidEnd;

	@Column(name = "ARCHITECTURE_DEPARTMENT", length = 255)
	private String architectureDepartment;

	private Integer baopan;

	@Column(name = "black_names", length = 200)
	private String blackNames;

	@Column(name = "BUILDING_ID")
	private Integer buildingId;

	@Column(name = "CAN_REG")
	private Integer canReg;

	@Column(name = "CAN_REG_VERIFY")
	private Integer canRegVerify;

	@Column(name = "CAN_VIEW_DAIKAN")
	private Integer canViewDaikan;

	@Column(name = "CAN_VIEW_VERIFY")
	private byte canViewVerify;

	@Lob
	@Column(name = "CASH_PRIZES")
	private String cashPrizes;

	@Column(name = "CITY_CODE", length = 20)
	private String cityCode;

	@Column(precision = 10, scale = 2)
	private BigDecimal commission;

	@Column(name = "COMMISSION_DESC", length = 20)
	private String commissionDesc;

	@Column(name = "COMMISSION_JUMP")
	private Integer commissionJump;

	@Column(name = "COMMISSION_MAX", precision = 10, scale = 2)
	private BigDecimal commissionMax;

	@Lob
	@Column(name = "COMMISSION_MEANS")
	private String commissionMeans;

	@Column(name = "COMMISSION_MIN", precision = 10, scale = 2)
	private BigDecimal commissionMin;

	@Lob
	@Column(name = "COMMISSION_POLICIES")
	private String commissionPolicies;

	@Column(name = "COMMISSION_SCALE")
	private Integer commissionScale;

	@Lob
	@Column(name = "CONTRACT_EXPLAIN")
	private String contractExplain;

	@Column(name = "CONTRACT_STATUS")
	private Integer contractStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	private Date createTime;

	@Lob
	@Column(name = "CUSTOMER_CONFIRM")
	private String customerConfirm;

	@Column(name = "DEPARTMENT_ID")
	private Integer departmentId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date enddate;

	@Column(name = "EST_COMPANY_CODE", length = 50)
	private String estCompanyCode;

	@Column(name = "GSCOPE_ID")
	private Integer gscopeId;

	@Column(name = "IS_DELETE")
	private Integer isDelete;

	@Column(name = "IS_ONLINE")
	private Integer isOnline;

	@Column(name = "LIULAN_CNT")
	private Integer liulanCnt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MOD_DATE")
	private Date modDate;

	@Lob
	@Column(name = "OTHER_PRIZES")
	private String otherPrizes;

	@Column(name = "PLAN_AREA", precision = 10, scale = 2)
	private BigDecimal planArea;

	@Column(name = "PLAN_UNIT")
	private Integer planUnit;

	@Column(name = "PROTECTION_DAY")
	private Integer protectionDay;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PROTECTION_DAY_TIME")
	private Date protectionDayTime;

	@Column(name = "PROTECTION_HOUR_BAOBEI")
	private Integer protectionHourBaobei;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PROTECTION_HOUR_TIME")
	private Date protectionHourTime;

	@Column(name = "PROTECTION_TYPE")
	private Integer protectionType;

	private Integer recommend;

	@Column(name = "RENCOU_CNT")
	private Integer rencouCnt;

	private Integer rexiao;

	@Lob
	private String rhetoric;

	@Lob
	@Column(name = "RISK_TIP")
	private String riskTip;

	@Column(name = "SHARE_CNT")
	private Integer shareCnt;

	@Column(name = "SORT_NUM")
	private Integer sortNum;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startdate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date timestamp;

	@Column(name = "UNIT_COMMISSION", precision = 10, scale = 2)
	private BigDecimal unitCommission;

	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "SELLING_POINTS")
	private String sellingPoints;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public String getAgencyMode() {
		return agencyMode;
	}

	public void setAgencyMode(String agencyMode) {
		this.agencyMode = agencyMode;
	}

	public Date getAgencyValidBegin() {
		return agencyValidBegin;
	}

	public void setAgencyValidBegin(Date agencyValidBegin) {
		this.agencyValidBegin = agencyValidBegin;
	}

	public Date getAgencyValidEnd() {
		return agencyValidEnd;
	}

	public void setAgencyValidEnd(Date agencyValidEnd) {
		this.agencyValidEnd = agencyValidEnd;
	}

	public String getArchitectureDepartment() {
		return architectureDepartment;
	}

	public void setArchitectureDepartment(String architectureDepartment) {
		this.architectureDepartment = architectureDepartment;
	}

	public Integer getBaopan() {
		return baopan;
	}

	public void setBaopan(Integer baopan) {
		this.baopan = baopan;
	}

	public String getBlackNames() {
		return blackNames;
	}

	public void setBlackNames(String blackNames) {
		this.blackNames = blackNames;
	}

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public Integer getCanReg() {
		return canReg;
	}

	public void setCanReg(Integer canReg) {
		this.canReg = canReg;
	}

	public Integer getCanRegVerify() {
		return canRegVerify;
	}

	public void setCanRegVerify(Integer canRegVerify) {
		this.canRegVerify = canRegVerify;
	}

	public Integer getCanViewDaikan() {
		return canViewDaikan;
	}

	public void setCanViewDaikan(Integer canViewDaikan) {
		this.canViewDaikan = canViewDaikan;
	}

	public byte getCanViewVerify() {
		return canViewVerify;
	}

	public void setCanViewVerify(byte canViewVerify) {
		this.canViewVerify = canViewVerify;
	}

	public String getCashPrizes() {
		return cashPrizes;
	}

	public void setCashPrizes(String cashPrizes) {
		this.cashPrizes = cashPrizes;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public String getCommissionDesc() {
		return commissionDesc;
	}

	public void setCommissionDesc(String commissionDesc) {
		this.commissionDesc = commissionDesc;
	}

	public Integer getCommissionJump() {
		return commissionJump;
	}

	public void setCommissionJump(Integer commissionJump) {
		this.commissionJump = commissionJump;
	}

	public BigDecimal getCommissionMax() {
		return commissionMax;
	}

	public void setCommissionMax(BigDecimal commissionMax) {
		this.commissionMax = commissionMax;
	}

	public String getCommissionMeans() {
		return commissionMeans;
	}

	public void setCommissionMeans(String commissionMeans) {
		this.commissionMeans = commissionMeans;
	}

	public BigDecimal getCommissionMin() {
		return commissionMin;
	}

	public void setCommissionMin(BigDecimal commissionMin) {
		this.commissionMin = commissionMin;
	}

	public String getCommissionPolicies() {
		return commissionPolicies;
	}

	public void setCommissionPolicies(String commissionPolicies) {
		this.commissionPolicies = commissionPolicies;
	}

	public Integer getCommissionScale() {
		return commissionScale;
	}

	public void setCommissionScale(Integer commissionScale) {
		this.commissionScale = commissionScale;
	}

	public String getContractExplain() {
		return contractExplain;
	}

	public void setContractExplain(String contractExplain) {
		this.contractExplain = contractExplain;
	}

	public Integer getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(Integer contractStatus) {
		this.contractStatus = contractStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCustomerConfirm() {
		return customerConfirm;
	}

	public void setCustomerConfirm(String customerConfirm) {
		this.customerConfirm = customerConfirm;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getEstCompanyCode() {
		return estCompanyCode;
	}

	public void setEstCompanyCode(String estCompanyCode) {
		this.estCompanyCode = estCompanyCode;
	}

	public Integer getGscopeId() {
		return gscopeId;
	}

	public void setGscopeId(Integer gscopeId) {
		this.gscopeId = gscopeId;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}

	public Integer getLiulanCnt() {
		return liulanCnt;
	}

	public void setLiulanCnt(Integer liulanCnt) {
		this.liulanCnt = liulanCnt;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public String getOtherPrizes() {
		return otherPrizes;
	}

	public void setOtherPrizes(String otherPrizes) {
		this.otherPrizes = otherPrizes;
	}

	public BigDecimal getPlanArea() {
		return planArea;
	}

	public void setPlanArea(BigDecimal planArea) {
		this.planArea = planArea;
	}

	public Integer getPlanUnit() {
		return planUnit;
	}

	public void setPlanUnit(Integer planUnit) {
		this.planUnit = planUnit;
	}

	public Integer getProtectionDay() {
		return protectionDay;
	}

	public void setProtectionDay(Integer protectionDay) {
		this.protectionDay = protectionDay;
	}

	public Date getProtectionDayTime() {
		return protectionDayTime;
	}

	public void setProtectionDayTime(Date protectionDayTime) {
		this.protectionDayTime = protectionDayTime;
	}

	public Integer getProtectionHourBaobei() {
		return protectionHourBaobei;
	}

	public void setProtectionHourBaobei(Integer protectionHourBaobei) {
		this.protectionHourBaobei = protectionHourBaobei;
	}

	public Date getProtectionHourTime() {
		return protectionHourTime;
	}

	public void setProtectionHourTime(Date protectionHourTime) {
		this.protectionHourTime = protectionHourTime;
	}

	public Integer getProtectionType() {
		return protectionType;
	}

	public void setProtectionType(Integer protectionType) {
		this.protectionType = protectionType;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public Integer getRencouCnt() {
		return rencouCnt;
	}

	public void setRencouCnt(Integer rencouCnt) {
		this.rencouCnt = rencouCnt;
	}

	public Integer getRexiao() {
		return rexiao;
	}

	public void setRexiao(Integer rexiao) {
		this.rexiao = rexiao;
	}

	public String getRhetoric() {
		return rhetoric;
	}

	public void setRhetoric(String rhetoric) {
		this.rhetoric = rhetoric;
	}

	public String getRiskTip() {
		return riskTip;
	}

	public void setRiskTip(String riskTip) {
		this.riskTip = riskTip;
	}

	public Integer getShareCnt() {
		return shareCnt;
	}

	public void setShareCnt(Integer shareCnt) {
		this.shareCnt = shareCnt;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public BigDecimal getUnitCommission() {
		return unitCommission;
	}

	public void setUnitCommission(BigDecimal unitCommission) {
		this.unitCommission = unitCommission;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public String getSellingPoints() {
		return sellingPoints;
	}

	public void setSellingPoints(String sellingPoints) {
		this.sellingPoints = sellingPoints;
	}

}