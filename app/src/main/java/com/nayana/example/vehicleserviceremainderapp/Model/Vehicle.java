package com.nayana.example.vehicleserviceremainderapp.Model;

public class Vehicle {

    public String vehicleImage;
    public String vehicleName;
    public String vehicleNumber;
    public String recentServiceDate;
    public String recentServiceMetreReading;
    public String nextServiceDate;
    public String nextServiceMetreReading;
    public String vehicleInsuranceDate;
    public String vehicleInsuranceExpiryDate;
    public String engineOilReplacedDate;
    public String engineServiceDate;

    public String brakeServicedDate;
    public String clutchServicedDate;
    public String batteryReplacedDate;
    public String mirrorsReplacedDate;
    public String airConditionServiceDate;
    public String radiatorServicedDate;

    public String chainReplacedDate;
    public String backTyreReplacedDate;
    public String frontTyreReplacedDate;
    public String vehicleSeatReplacedDate;
    public String headlightReplacedDate;
    public String indicatorReplacedDate;
    public String aboutVehicle;

    public Vehicle(){ }

    public Vehicle(String vehicleName, String vehicleNumber, String recentServiceDate, String recentServiceMetreReading, String nextServiceDate, String nextServiceMetreReading) {
        this.vehicleName = vehicleName;
        this.vehicleNumber = vehicleNumber;
        this.recentServiceDate = recentServiceDate;
        this.recentServiceMetreReading = recentServiceMetreReading;
        this.nextServiceDate = nextServiceDate;
        this.nextServiceMetreReading = nextServiceMetreReading;
    }

    public Vehicle(String vehicleImage, String vehicleName, String vehicleNumber, String recentServiceDate, String recentServiceMetreReading, String nextServiceDate, String nextServiceMetreReading, String vehicleInsuranceDate, String vehicleInsuranceExpiryDate, String engineOilReplacedDate, String engineServiceDate, String chainReplacedDate, String backTyreReplacedDate, String frontTyreReplacedDate, String vehicleSeatReplacedDate, String headlightReplacedDate, String indicatorReplacedDate, String aboutVehicle) {
        this.vehicleImage = vehicleImage;
        this.vehicleName = vehicleName;
        this.vehicleNumber = vehicleNumber;
        this.recentServiceDate = recentServiceDate;
        this.recentServiceMetreReading = recentServiceMetreReading;
        this.nextServiceDate = nextServiceDate;
        this.nextServiceMetreReading = nextServiceMetreReading;
        this.vehicleInsuranceDate = vehicleInsuranceDate;
        this.vehicleInsuranceExpiryDate = vehicleInsuranceExpiryDate;
        this.engineOilReplacedDate = engineOilReplacedDate;
        this.engineServiceDate = engineServiceDate;
        this.chainReplacedDate = chainReplacedDate;
        this.backTyreReplacedDate = backTyreReplacedDate;
        this.frontTyreReplacedDate = frontTyreReplacedDate;
        this.vehicleSeatReplacedDate = vehicleSeatReplacedDate;
        this.headlightReplacedDate = headlightReplacedDate;
        this.indicatorReplacedDate = indicatorReplacedDate;
        this.aboutVehicle = aboutVehicle;
    }

    public Vehicle(String vehicleImage, String vehicleName, String vehicleNumber, String recentServiceDate, String recentServiceMetreReading, String nextServiceDate, String nextServiceMetreReading, String vehicleInsuranceDate, String vehicleInsuranceExpiryDate, String engineOilReplacedDate, String engineServiceDate, String brakeServicedDate, String clutchServicedDate, String batteryReplacedDate, String mirrorsReplacedDate, String airConditionServiceDate, String radiatorServicedDate, String backTyreReplacedDate, String frontTyreReplacedDate, String vehicleSeatReplacedDate, String headlightReplacedDate, String indicatorReplacedDate, String aboutVehicle) {
        this.vehicleImage = vehicleImage;
        this.vehicleName = vehicleName;
        this.vehicleNumber = vehicleNumber;
        this.recentServiceDate = recentServiceDate;
        this.recentServiceMetreReading = recentServiceMetreReading;
        this.nextServiceDate = nextServiceDate;
        this.nextServiceMetreReading = nextServiceMetreReading;
        this.vehicleInsuranceDate = vehicleInsuranceDate;
        this.vehicleInsuranceExpiryDate = vehicleInsuranceExpiryDate;
        this.engineOilReplacedDate = engineOilReplacedDate;
        this.engineServiceDate = engineServiceDate;
        this.brakeServicedDate = brakeServicedDate;
        this.clutchServicedDate = clutchServicedDate;
        this.batteryReplacedDate = batteryReplacedDate;
        this.mirrorsReplacedDate = mirrorsReplacedDate;
        this.airConditionServiceDate = airConditionServiceDate;
        this.radiatorServicedDate = radiatorServicedDate;
        this.backTyreReplacedDate = backTyreReplacedDate;
        this.frontTyreReplacedDate = frontTyreReplacedDate;
        this.vehicleSeatReplacedDate = vehicleSeatReplacedDate;
        this.headlightReplacedDate = headlightReplacedDate;
        this.indicatorReplacedDate = indicatorReplacedDate;
        this.aboutVehicle = aboutVehicle;
    }

    public String getVehicleImage() {
        return vehicleImage;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getRecentServiceDate() {
        return recentServiceDate;
    }

    public String getRecentServiceMetreReading() {
        return recentServiceMetreReading;
    }

    public String getNextServiceDate() {
        return nextServiceDate;
    }

    public String getNextServiceMetreReading() {
        return nextServiceMetreReading;
    }

    public String getVehicleInsuranceDate() {
        return vehicleInsuranceDate;
    }

    public String getVehicleInsuranceExpiryDate() {
        return vehicleInsuranceExpiryDate;
    }

    public String getEngineOilReplacedDate() {
        return engineOilReplacedDate;
    }

    public String getEngineServiceDate() {
        return engineServiceDate;
    }

    public String getChainReplacedDate() {
        return chainReplacedDate;
    }

    public String getBackTyreReplacedDate() {
        return backTyreReplacedDate;
    }

    public String getFrontTyreReplacedDate() {
        return frontTyreReplacedDate;
    }

    public String getVehicleSeatReplacedDate() {
        return vehicleSeatReplacedDate;
    }

    public String getHeadlightReplacedDate() {
        return headlightReplacedDate;
    }

    public String getIndicatorReplacedDate() {
        return indicatorReplacedDate;
    }

    public String getAboutVehicle() {
        return aboutVehicle;
    }

    public void setAboutVehicle(String aboutVehicle) {
        this.aboutVehicle = aboutVehicle;
    }

    public void setVehicleImage(String vehicleImage) {
        this.vehicleImage = vehicleImage;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void setRecentServiceDate(String recentServiceDate) {
        this.recentServiceDate = recentServiceDate;
    }

    public void setRecentServiceMetreReading(String recentServiceMetreReading) {
        this.recentServiceMetreReading = recentServiceMetreReading;
    }

    public void setNextServiceDate(String nextServiceDate) {
        this.nextServiceDate = nextServiceDate;
    }

    public void setNextServiceMetreReading(String nextServiceMetreReading) {
        this.nextServiceMetreReading = nextServiceMetreReading;
    }

    public void setVehicleInsuranceDate(String vehicleInsuranceDate) {
        this.vehicleInsuranceDate = vehicleInsuranceDate;
    }

    public void setVehicleInsuranceExpiryDate(String vehicleInsuranceExpiryDate) {
        this.vehicleInsuranceExpiryDate = vehicleInsuranceExpiryDate;
    }

    public void setEngineOilReplacedDate(String engineOilReplacedDate) {
        this.engineOilReplacedDate = engineOilReplacedDate;
    }

    public void setEngineServiceDate(String engineServiceDate) {
        this.engineServiceDate = engineServiceDate;
    }

    public void setChainReplacedDate(String chainReplacedDate) {
        this.chainReplacedDate = chainReplacedDate;
    }

    public void setBackTyreReplacedDate(String backTyreReplacedDate) {
        this.backTyreReplacedDate = backTyreReplacedDate;
    }

    public void setFrontTyreReplacedDate(String frontTyreReplacedDate) {
        this.frontTyreReplacedDate = frontTyreReplacedDate;
    }

    public void setVehicleSeatReplacedDate(String vehicleSeatReplacedDate) {
        this.vehicleSeatReplacedDate = vehicleSeatReplacedDate;
    }

    public void setHeadlightReplacedDate(String headlightReplacedDate) {
        this.headlightReplacedDate = headlightReplacedDate;
    }

    public void setIndicatorReplacedDate(String indicatorReplacedDate) {
        this.indicatorReplacedDate = indicatorReplacedDate;
    }

    public String getBrakeServicedDate() {
        return brakeServicedDate;
    }

    public void setBrakeServicedDate(String brakeServicedDate) {
        this.brakeServicedDate = brakeServicedDate;
    }

    public String getClutchServicedDate() {
        return clutchServicedDate;
    }

    public void setClutchServicedDate(String clutchServicedDate) {
        this.clutchServicedDate = clutchServicedDate;
    }

    public String getBatteryReplacedDate() {
        return batteryReplacedDate;
    }

    public void setBatteryReplacedDate(String batteryReplacedDate) {
        this.batteryReplacedDate = batteryReplacedDate;
    }

    public String getMirrorsReplacedDate() {
        return mirrorsReplacedDate;
    }

    public void setMirrorsReplacedDate(String mirrorsReplacedDate) {
        this.mirrorsReplacedDate = mirrorsReplacedDate;
    }

    public String getAirConditionServiceDate() {
        return airConditionServiceDate;
    }

    public void setAirConditionServiceDate(String airConditioningServiceDate) {
        this.airConditionServiceDate = airConditioningServiceDate;
    }

    public String getRadiatorServicedDate() {
        return radiatorServicedDate;
    }

    public void setRadiatorServicedDate(String radiatorServicedDate) {
        this.radiatorServicedDate = radiatorServicedDate;
    }
}
