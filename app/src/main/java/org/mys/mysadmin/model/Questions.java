package org.mys.mysadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Questions {

    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("ward")
    @Expose
    private String ward;
    @SerializedName("county")
    @Expose
    private String county;
    @SerializedName("sub_county")
    @Expose
    private String subCounty;
    @SerializedName("skill")
    @Expose
    private String skill;
    @SerializedName("zone")
    @Expose
    private String zone;
    @SerializedName("education_level")
    @Expose
    private String educationLevel;
    @SerializedName("professional_skills")
    @Expose
    private String professionalSkills;
    @SerializedName("opportunity_levels")
    @Expose
    private String opportunityLevels;
    @SerializedName("structure")
    @Expose
    private String structure;
    @SerializedName("Property")
    @Expose
    private String property;
    @SerializedName("family_size")
    @Expose
    private String familySize;
    @SerializedName("physically_challenged")
    @Expose
    private String physicallyChallenged;
    @SerializedName("Health_condition")
    @Expose
    private String healthCondition;
    @SerializedName("regional_activities")
    @Expose
    private String regionalActivities;
    @SerializedName("access_to_technology")
    @Expose
    private String accessToTechnology;
    @SerializedName("income_sources")
    @Expose
    private String incomeSource;
    @SerializedName("challenges_in_the_area")
    @Expose
    private String challengesInTheArea;

    /**
     * No args constructor for use in serialization
     *
     */
    public Questions() {
    }

    /**
     *
     * @param county
     * @param ward
     * @param professionalSkills
     * @param opportunityLevels
     * @param structure
     * @param accessToTechnology
     * @param incomeSource
     * @param physicallyChallenged
     * @param healthCondition
     * @param zone
     * @param educationLevel
     * @param regionalActivities
     * @param skill
     * @param userName
     * @param name
     * @param property
     * @param location
     * @param subCounty
     * @param challengesInTheArea
     * @param age
     * @param familySize
     */
    public Questions(String userName,String name, String age, String location, String ward, String county, String subCounty, String skill, String zone, String educationLevel, String professionalSkills, String opportunityLevels, String structure, String property, String familySize, String physicallyChallenged, String healthCondition, String regionalActivities, String accessToTechnology,String incomeSource, String challengesInTheArea) {
        super();
        this.userName = userName;
        this.name = name;
        this.age = age;
        this.location = location;
        this.ward = ward;
        this.county = county;
        this.subCounty = subCounty;
        this.skill = skill;
        this.zone = zone;
        this.educationLevel = educationLevel;
        this.professionalSkills = professionalSkills;
        this.opportunityLevels = opportunityLevels;
        this.structure = structure;
        this.property = property;
        this.familySize = familySize;
        this.physicallyChallenged = physicallyChallenged;
        this.healthCondition = healthCondition;
        this.regionalActivities = regionalActivities;
        this.accessToTechnology = accessToTechnology;
        this.incomeSource = incomeSource;
        this.challengesInTheArea = challengesInTheArea;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getSubCounty() {
        return subCounty;
    }

    public void setSubCounty(String subCounty) {
        this.subCounty = subCounty;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getProfessionalSkills() {
        return professionalSkills;
    }

    public void setProfessionalSkills(String professionalSkills) {
        this.professionalSkills = professionalSkills;
    }

    public String getOpportunityLevels() {
        return opportunityLevels;
    }

    public void setOpportunityLevels(String opportunityLevels) {
        this.opportunityLevels = opportunityLevels;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getFamilySize() {
        return familySize;
    }

    public void setFamilySize(String familySize) {
        this.familySize = familySize;
    }

    public String getPhysicallyChallenged() {
        return physicallyChallenged;
    }

    public void setPhysicallyChallenged(String physicallyChallenged) {
        this.physicallyChallenged = physicallyChallenged;
    }

    public String getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(String healthCondition) {
        this.healthCondition = healthCondition;
    }

    public String getRegionalActivities() {
        return regionalActivities;
    }

    public void setRegionalActivities(String regionalActivities) {
        this.regionalActivities = regionalActivities;
    }

    public String getAccessToTechnology() {
        return accessToTechnology;
    }

    public void setAccessToTechnology(String accessToTechnology) {
        this.accessToTechnology = accessToTechnology;
    }

    public String getIncomeSource() {
        return incomeSource;
    }

    public void setIncomeSource(String incomeSource) {
        this.incomeSource = incomeSource;
    }

    public String getChallengesInTheArea() {
        return challengesInTheArea;
    }

    public void setChallengesInTheArea(String challengesInTheArea) {
        this.challengesInTheArea = challengesInTheArea;
    }

}