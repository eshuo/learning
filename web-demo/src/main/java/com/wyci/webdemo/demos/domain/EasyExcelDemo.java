package com.wyci.webdemo.demos.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentFontStyle;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.alibaba.excel.enums.BooleanEnum;
import com.alibaba.excel.enums.poi.BorderStyleEnum;
import com.alibaba.excel.enums.poi.FillPatternTypeEnum;
import com.alibaba.excel.util.DateUtils;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description
 * @Author wangshuo
 * @Date 2024-09-11 11:20
 * @Version V1.0
 */


@ContentFontStyle(fontHeightInPoints = 10)
@ContentStyle(borderBottom = BorderStyleEnum.THIN, borderLeft = BorderStyleEnum.THIN, borderRight = BorderStyleEnum.THIN, borderTop = BorderStyleEnum.THIN)
@HeadFontStyle(fontHeightInPoints = 10, bold = BooleanEnum.FALSE)
@HeadStyle(fillPatternType = FillPatternTypeEnum.NO_FILL)
@ContentRowHeight(50)
@ColumnWidth(10)
public class EasyExcelDemo implements Serializable {

    @ExcelIgnore

    private String id;

    @ExcelIgnore

    //关联?
    private String submitBatchId;
    /**
     * 序号
     */

    @ExcelProperty(value = {"标题", "序号"}, index = 0)
    private String serialNumber;
    /**
     * 云名称
     */

    @ExcelProperty(value = {"标题", "云名称"}, index = 1)
    private String cloudName;
    /**
     * 建设时间
     */

    @DateTimeFormat(fallbackPatterns = DateUtils.DATE_FORMAT_19)
    @ColumnWidth(20)
    @ExcelProperty(value = {"标题", "建设时间"}, index = 2)
    private String constructionTime;
    /**
     * 是否涉密
     */

    @ExcelProperty(value = {"标题", "是否涉密"}, index = 3)
    private String isItClassified;
    /**
     * 云服务品牌
     */

    @ExcelProperty(value = {"标题", "云服务品牌"}, index = 4)
    private String cloudServiceBrand;
    /**
     * 运营单位
     */

    @ExcelProperty(value = {"标题", "运营单位"}, index = 5)
    private String operatingUnit;
    /**
     * 所属单位
     */

    @ExcelProperty(value = {"标题", "所属单位"}, index = 6)
    private String belongingUnit;
    /**
     * 云计算基础设施位置
     */

    @ExcelProperty(value = {"标题", "云计算基础设施位置"}, index = 7)
    private String location;
    /**
     * 服务范围
     */

    @ExcelProperty(value = {"标题", "服务范围"}, index = 8)
    private String serviceScope;
    /**
     * 部署模式
     */

    @ExcelProperty(value = {"标题", "部署模式"}, index = 9)
    private String deploymentMode;
    /**
     * 服务类型
     */

    @ExcelProperty(value = {"标题", "服务类型"}, index = 10)
    private String serviceType;
    /**
     * 已部署上云的应用系统数量
     */

    @ExcelProperty(value = {"标题", "已部署上云的应用系统数量"}, index = 11)
    private String numberDeployedCloud;
    /**
     * 等级保护测评情况
     */

    @ExcelProperty(value = {"标题", "安全管理情况", "等级保护测评情况"}, index = 12)
    private String levelProtectionEvaluationSituation;
    /**
     * 分级保护测评情况
     */

    @ExcelProperty(value = {"标题", "安全管理情况", "分级保护测评情况"}, index = 13)
    private String classificationProtectionEvaluationSituation;
    /**
     * 云计算服务安全评估情况
     */

    @ExcelProperty(value = {"标题", "安全管理情况", "云计算服务安全评估情况"}, index = 14)
    private String securityAssessmentCloudComputingServices;
    /**
     * 密码应用安全性评估情况
     */

    @ExcelProperty(value = {"标题", "安全管理情况", "密码应用安全性评估情况"}, index = 15)
    private String securityAssessmentPasswordApplications;
    /**
     * 飞腾
     */

    @ExcelProperty(value = {"标题", "建设模式", "自建", "物理服务器数量（台）", "飞腾"}, index = 16)
    private String feiTeng;
    /**
     * 龙芯
     */

    @ExcelProperty(value = {"标题", "建设模式", "自建", "物理服务器数量（台）", "龙芯"}, index = 17)
    private String loongSon;
    /**
     * 鲲鹏（含麒麟、盘古）
     */

    @ExcelProperty(value = {"标题", "建设模式", "自建", "物理服务器数量（台）", "鲲鹏（含麒麟、盘古）"}, index = 18)
    private String kunPeng;
    /**
     * 海光
     */

    @ExcelProperty(value = {"标题", "建设模式", "自建", "物理服务器数量（台）", "海光"}, index = 19)
    private String haiGuang;
    /**
     * 兆芯
     */

    @ExcelProperty(value = {"标题", "建设模式", "自建", "物理服务器数量（台）", "兆芯"}, index = 20)
    private String zhaoXin;
    /**
     * 中威
     */

    @ExcelProperty(value = {"标题", "建设模式", "自建", "物理服务器数量（台）", "中威"}, index = 21)
    private String zhongWei;
    /**
     * Intel等技术路线
     */

    @ExcelProperty(value = {"标题", "建设模式", "自建", "物理服务器数量（台）", "Intel等技术路线"}, index = 22)
    private String intelTechnologyRoadmap;
    /**
     * 物理服务器合计
     */

    @ExcelProperty(value = {"标题", "建设模式", "自建", "物理服务器数量（台）", "合计"}, index = 23)
    private String totalPhysicalServers;
// 数量（套）
    /**
     * 银河麒麟
     */

    @ExcelProperty(value = {"标题", "建设模式", "自建", "数量（套）", "银河麒麟"}, index = 24)
    private String galacticQiLin;
    /**
     * 统信UOS
     */

    @ExcelProperty(value = {"标题", "建设模式", "自建", "数量（套）", "统信UOS"}, index = 25)
    private String tongXinUOS;
    /**
     * 中科方德
     */

    @ExcelProperty(value = {"标题", "建设模式", "自建", "数量（套）", "中科方德"}, index = 26)
    private String zhongKeFangDe;
    /**
     * 微软、ContOS等操作系统
     */

    @ExcelProperty(value = {"标题", "建设模式", "自建", "数量（套）", "微软、ContOS等操作系统"}, index = 27)
    private String other;
    /**
     * 物理操作系统合计
     */

    @ExcelProperty(value = {"标题", "建设模式", "自建", "数量（套）", "合计"}, index = 28)
    private String totalPhysicalOperatingSystem;
//租用
    /**
     * 物理服务器技术路线
     */

    @ExcelProperty(value = {"标题", "建设模式", "租用", "物理服务器技术路线"}, index = 29)
    private String physicalServerTechnologyRoadmap;
    /**
     * 计算资源情况（CPU核数，单位：个）
     */

    @ExcelProperty(value = {"标题", "建设模式", "租用", "计算资源情况（CPU核数，单位：个）"}, index = 30)
    private String CPUNumberCores;
    /**
     * 存储资源情况（单位：TB）
     */

    @ExcelProperty(value = {"标题", "建设模式", "租用", "存储资源情况（单位：TB）"}, index = 31)
    private String storageResources;
    /**
     * 网络带宽（单位：兆）
     */

    @ExcelProperty(value = {"标题", "建设模式", "租用", "网络带宽（单位：兆）"}, index = 32)
    private String networkBandwidth;
    /**
     * 租用期限（单位：年）
     */

    @ExcelProperty(value = {"标题", "建设模式", "租用", "租用期限（单位：年）"}, index = 33)
    private String leaseTerm;
    /**
     * 云服务提供商
     */

    @ExcelProperty(value = {"标题", "建设模式", "租用", "云服务提供商"}, index = 34)
    private String cloudServiceProvider;

    /**
     * 累计投资金额（万元）
     */

    @ColumnWidth(20)
    @ExcelProperty(value = {"标题", "累计投资金额（万元）"}, index = 35)
    private String accumulatedInvestmentAmount;


    public String getAccumulatedInvestmentAmount() {
        return accumulatedInvestmentAmount;
    }

    public void setAccumulatedInvestmentAmount(String accumulatedInvestmentAmount) {
        this.accumulatedInvestmentAmount = accumulatedInvestmentAmount;
    }

    public String getBelongingUnit() {
        return belongingUnit;
    }

    public void setBelongingUnit(String belongingUnit) {
        this.belongingUnit = belongingUnit;
    }

    public String getClassificationProtectionEvaluationSituation() {
        return classificationProtectionEvaluationSituation;
    }

    public void setClassificationProtectionEvaluationSituation(String classificationProtectionEvaluationSituation) {
        this.classificationProtectionEvaluationSituation = classificationProtectionEvaluationSituation;
    }

    public String getCloudName() {
        return cloudName;
    }

    public void setCloudName(String cloudName) {
        this.cloudName = cloudName;
    }

    public String getCloudServiceBrand() {
        return cloudServiceBrand;
    }

    public void setCloudServiceBrand(String cloudServiceBrand) {
        this.cloudServiceBrand = cloudServiceBrand;
    }

    public String getCloudServiceProvider() {
        return cloudServiceProvider;
    }

    public void setCloudServiceProvider(String cloudServiceProvider) {
        this.cloudServiceProvider = cloudServiceProvider;
    }

    public String getConstructionTime() {
        return constructionTime;
    }

    public void setConstructionTime(String constructionTime) {
        this.constructionTime = constructionTime;
    }

    public String getCPUNumberCores() {
        return CPUNumberCores;
    }

    public void setCPUNumberCores(String CPUNumberCores) {
        this.CPUNumberCores = CPUNumberCores;
    }

    public String getDeploymentMode() {
        return deploymentMode;
    }

    public void setDeploymentMode(String deploymentMode) {
        this.deploymentMode = deploymentMode;
    }

    public String getFeiTeng() {
        return feiTeng;
    }

    public void setFeiTeng(String feiTeng) {
        this.feiTeng = feiTeng;
    }

    public String getGalacticQiLin() {
        return galacticQiLin;
    }

    public void setGalacticQiLin(String galacticQiLin) {
        this.galacticQiLin = galacticQiLin;
    }

    public String getHaiGuang() {
        return haiGuang;
    }

    public void setHaiGuang(String haiGuang) {
        this.haiGuang = haiGuang;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntelTechnologyRoadmap() {
        return intelTechnologyRoadmap;
    }

    public void setIntelTechnologyRoadmap(String intelTechnologyRoadmap) {
        this.intelTechnologyRoadmap = intelTechnologyRoadmap;
    }

    public String getIsItClassified() {
        return isItClassified;
    }

    public void setIsItClassified(String isItClassified) {
        this.isItClassified = isItClassified;
    }

    public String getKunPeng() {
        return kunPeng;
    }

    public void setKunPeng(String kunPeng) {
        this.kunPeng = kunPeng;
    }

    public String getLeaseTerm() {
        return leaseTerm;
    }

    public void setLeaseTerm(String leaseTerm) {
        this.leaseTerm = leaseTerm;
    }

    public String getLevelProtectionEvaluationSituation() {
        return levelProtectionEvaluationSituation;
    }

    public void setLevelProtectionEvaluationSituation(String levelProtectionEvaluationSituation) {
        this.levelProtectionEvaluationSituation = levelProtectionEvaluationSituation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLoongSon() {
        return loongSon;
    }

    public void setLoongSon(String loongSon) {
        this.loongSon = loongSon;
    }

    public String getNetworkBandwidth() {
        return networkBandwidth;
    }

    public void setNetworkBandwidth(String networkBandwidth) {
        this.networkBandwidth = networkBandwidth;
    }

    public String getNumberDeployedCloud() {
        return numberDeployedCloud;
    }

    public void setNumberDeployedCloud(String numberDeployedCloud) {
        this.numberDeployedCloud = numberDeployedCloud;
    }

    public String getOperatingUnit() {
        return operatingUnit;
    }

    public void setOperatingUnit(String operatingUnit) {
        this.operatingUnit = operatingUnit;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getPhysicalServerTechnologyRoadmap() {
        return physicalServerTechnologyRoadmap;
    }

    public void setPhysicalServerTechnologyRoadmap(String physicalServerTechnologyRoadmap) {
        this.physicalServerTechnologyRoadmap = physicalServerTechnologyRoadmap;
    }

    public String getSecurityAssessmentCloudComputingServices() {
        return securityAssessmentCloudComputingServices;
    }

    public void setSecurityAssessmentCloudComputingServices(String securityAssessmentCloudComputingServices) {
        this.securityAssessmentCloudComputingServices = securityAssessmentCloudComputingServices;
    }

    public String getSecurityAssessmentPasswordApplications() {
        return securityAssessmentPasswordApplications;
    }

    public void setSecurityAssessmentPasswordApplications(String securityAssessmentPasswordApplications) {
        this.securityAssessmentPasswordApplications = securityAssessmentPasswordApplications;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getServiceScope() {
        return serviceScope;
    }

    public void setServiceScope(String serviceScope) {
        this.serviceScope = serviceScope;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getStorageResources() {
        return storageResources;
    }

    public void setStorageResources(String storageResources) {
        this.storageResources = storageResources;
    }

    public String getSubmitBatchId() {
        return submitBatchId;
    }

    public void setSubmitBatchId(String submitBatchId) {
        this.submitBatchId = submitBatchId;
    }

    public String getTongXinUOS() {
        return tongXinUOS;
    }

    public void setTongXinUOS(String tongXinUOS) {
        this.tongXinUOS = tongXinUOS;
    }

    public String getTotalPhysicalOperatingSystem() {
        return totalPhysicalOperatingSystem;
    }

    public void setTotalPhysicalOperatingSystem(String totalPhysicalOperatingSystem) {
        this.totalPhysicalOperatingSystem = totalPhysicalOperatingSystem;
    }

    public String getTotalPhysicalServers() {
        return totalPhysicalServers;
    }

    public void setTotalPhysicalServers(String totalPhysicalServers) {
        this.totalPhysicalServers = totalPhysicalServers;
    }

    public String getZhaoXin() {
        return zhaoXin;
    }

    public void setZhaoXin(String zhaoXin) {
        this.zhaoXin = zhaoXin;
    }

    public String getZhongKeFangDe() {
        return zhongKeFangDe;
    }

    public void setZhongKeFangDe(String zhongKeFangDe) {
        this.zhongKeFangDe = zhongKeFangDe;
    }

    public String getZhongWei() {
        return zhongWei;
    }

    public void setZhongWei(String zhongWei) {
        this.zhongWei = zhongWei;
    }
}