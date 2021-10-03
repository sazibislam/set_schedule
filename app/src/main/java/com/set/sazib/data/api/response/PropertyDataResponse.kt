package com.set.sazib.data.api.response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PropertyDataResponse(
    @SerializedName("data") @Expose var propertyData: Data? = null,
    @SerializedName("metadata") @Expose var metadata: Metadata? = null,
    @SerializedName("warnings") @Expose var warnings: List<Warning>? = null,
    @SerializedName("error") @Expose var error: ErrorResponse? = null
)

data class Warning(
    @SerializedName("code") @Expose var code: String? = "",
    @SerializedName("description") @Expose var description: String? = "",
    @SerializedName("metadata") @Expose var metadata: MetadataX? = null,
    @SerializedName("title") @Expose var title: String? = ""
)

data class Data(
    @SerializedName("address") @Expose var address: Address? = null,
    @SerializedName("assessments") @Expose var assessments: List<Assessment>? = null,
    @SerializedName("boundary") @Expose var boundary: Boundary? = null,
    @SerializedName("deeds") @Expose var deeds: List<Deed>? = null,
    @SerializedName("market_assessments") @Expose var marketAssessments: List<MarketAssessment>? = null,
    @SerializedName("metadata") @Expose var metadata: Metadata? = null,
    @SerializedName("owner") @Expose var owner: Owner? = null,
    @SerializedName("parcel") @Expose var parcel: Parcel? = null,
    @SerializedName("structure") @Expose var structure: Structure? = null,
    @SerializedName("taxes") @Expose var taxes: List<Taxe>? = null,
    @SerializedName("valuation") @Expose var valuation: Valuation? = null
)

data class Deed(
    @SerializedName("buyer2_first_name") @Expose var buyer2FirstName: Any? = null,
    @SerializedName("buyer2_last_name") @Expose var buyer2LastName: Any? = null,
    @SerializedName("buyer_address") @Expose var buyerAddress: String? = "",
    @SerializedName("buyer_city") @Expose var buyerCity: String? = "",
    @SerializedName("buyer_first_name") @Expose var buyerFirstName: String? = "",
    @SerializedName("buyer_last_name") @Expose var buyerLastName: String? = "",
    @SerializedName("buyer_state") @Expose var buyerState: String? = "",
    @SerializedName("buyer_unit_number") @Expose var buyerUnitNumber: Any? = null,
    @SerializedName("buyer_unit_type") @Expose var buyerUnitType: Any? = null,
    @SerializedName("buyer_zip_code") @Expose var buyerZipCode: String? = "",
    @SerializedName("buyer_zip_plus_four_code") @Expose var buyerZipPlusFourCode: String? = "",
    @SerializedName("deed_book") @Expose var deedBook: String? = "",
    @SerializedName("deed_page") @Expose var deedPage: String? = "",
    @SerializedName("distressed_sale") @Expose var distressedSale: Boolean? = null,
    @SerializedName("document_id") @Expose var documentId: String? = "",
    @SerializedName("document_type") @Expose var documentType: String? = "",
    @SerializedName("lender_name") @Expose var lenderName: Any? = null,
    @SerializedName("lender_type") @Expose var lenderType: Any? = null,
    @SerializedName("loan_amount") @Expose var loanAmount: Any? = null,
    @SerializedName("loan_due_date") @Expose var loanDueDate: Any? = null,
    @SerializedName("loan_finance_type") @Expose var loanFinanceType: Any? = null,
    @SerializedName("loan_interest_rate") @Expose var loanInterestRate: Any? = null,
    @SerializedName("loan_type") @Expose var loanType: Any? = null,
    @SerializedName("original_contract_date") @Expose var originalContractDate: String? = "",
    @SerializedName("real_estate_owned") @Expose var realEstateOwned: String? = "",
    @SerializedName("recording_date") @Expose var recordingDate: String? = "",
    @SerializedName("sale_price") @Expose var salePrice: Int? = null,
    @SerializedName("sale_price_description") @Expose var salePriceDescription: String? = "",
    @SerializedName("seller2_first_name") @Expose var seller2FirstName: Any? = null,
    @SerializedName("seller2_last_name") @Expose var seller2LastName: Any? = null,
    @SerializedName("seller_address") @Expose var sellerAddress: Any? = null,
    @SerializedName("seller_city") @Expose var sellerCity: Any? = null,
    @SerializedName("seller_first_name") @Expose var sellerFirstName: String? = "",
    @SerializedName("seller_last_name") @Expose var sellerLastName: String? = "",
    @SerializedName("seller_state") @Expose var sellerState: Any? = null,
    @SerializedName("seller_unit_number") @Expose var sellerUnitNumber: Any? = null,
    @SerializedName("seller_zip_code") @Expose var sellerZipCode: Any? = null,
    @SerializedName("seller_zip_plus_four_code") @Expose var sellerZipPlusFourCode: Any? = null,
    @SerializedName("transfer_tax") @Expose var transferTax: Double? = null
)

data class Owner(
    @SerializedName("city") @Expose var city: String? = "",
    @SerializedName("formatted_street_address") @Expose var formattedStreetAddress: String? = "",
    @SerializedName("name") @Expose var name: String? = "",
    @SerializedName("owner_occupied") @Expose var ownerOccupied: String? = "",
    @SerializedName("second_name") @Expose var secondName: Any? = null,
    @SerializedName("state") @Expose var state: String? = "",
    @SerializedName("unit_number") @Expose var unitNumber: Any? = null,
    @SerializedName("unit_type") @Expose var unitType: Any? = null,
    @SerializedName("zip_code") @Expose var zipCode: String? = "",
    @SerializedName("zip_plus_four_code") @Expose var zipPlusFourCode: String? = ""
)

data class Parcel(
    @SerializedName("apn_original") @Expose var apnOriginal: String? = "",
    @SerializedName("apn_previous") @Expose var apnPrevious: Any? = null,
    @SerializedName("apn_unformatted") @Expose var apnUnformatted: String? = "",
    @SerializedName("area_acres") @Expose var areaAcres: Double? = null,
    @SerializedName("area_sq_ft") @Expose var areaSqFt: Int? = null,
    @SerializedName("building_count") @Expose var buildingCount: Any? = null,
    @SerializedName("county_land_use_code") @Expose var countyLandUseCode: String? = "",
    @SerializedName("county_land_use_description") @Expose var countyLandUseDescription: String? = "",
    @SerializedName("county_name") @Expose var countyName: String? = "",
    @SerializedName("depth_ft") @Expose var depthFt: Double? = null,
    @SerializedName("fips_code") @Expose var fipsCode: String? = "",
    @SerializedName("frontage_ft") @Expose var frontageFt: Double? = null,
    @SerializedName("legal_description") @Expose var legalDescription: String? = "",
    @SerializedName("location_descriptions") @Expose var locationDescriptions: List<Any>? = null,
    @SerializedName("lot_code") @Expose var lotCode: Any? = null,
    @SerializedName("lot_number") @Expose var lotNumber: String? = "",
    @SerializedName("municipality") @Expose var municipality: String? = "",
    @SerializedName("section_township_range") @Expose var sectionTownshipRange: Any? = null,
    @SerializedName("standardized_land_use_category") @Expose var standardizedLandUseCategory: String? = "",
    @SerializedName("standardized_land_use_type") @Expose var standardizedLandUseType: String? = "",
    @SerializedName("subdivision") @Expose var subdivision: Any? = null,
    @SerializedName("tax_account_number") @Expose var taxAccountNumber: String? = "",
    @SerializedName("zoning") @Expose var zoning: String? = ""
)

data class Structure(
    @SerializedName("air_conditioning_type") @Expose var airConditioningType: String? = "",
    @SerializedName("amenities") @Expose var amenities: List<Any>? = null,
    @SerializedName("architecture_type") @Expose var architectureType: String? = "",
    @SerializedName("basement_type") @Expose var basementType: String? = "",
    @SerializedName("baths") @Expose var baths: Double? = null,
    @SerializedName("beds_count") @Expose var bedsCount: Int? = null,
    @SerializedName("condition") @Expose var condition: String? = "",
    @SerializedName("construction_type") @Expose var constructionType: Any? = null,
    @SerializedName("effective_year_built") @Expose var effectiveYearBuilt: Any? = null,
    @SerializedName("exterior_wall_type") @Expose var exteriorWallType: String? = "",
    @SerializedName("fireplaces") @Expose var fireplaces: String? = "",
    @SerializedName("flooring_types") @Expose var flooringTypes: List<Any>? = null,
    @SerializedName("foundation_type") @Expose var foundationType: Any? = null,
    @SerializedName("heating_fuel_type") @Expose var heatingFuelType: String? = "",
    @SerializedName("heating_type") @Expose var heatingType: String? = "",
    @SerializedName("interior_wall_type") @Expose var interiorWallType: Any? = null,
    @SerializedName("other_areas") @Expose var otherAreas: List<OtherArea>? = null,
    @SerializedName("other_features") @Expose var otherFeatures: List<Any>? = null,
    @SerializedName("other_improvements") @Expose var otherImprovements: List<OtherImprovement>? = null,
    @SerializedName("other_rooms") @Expose var otherRooms: List<String>? = null,
    @SerializedName("parking_spaces_count") @Expose var parkingSpacesCount: Int? = null,
    @SerializedName("parking_type") @Expose var parkingType: String? = "",
    @SerializedName("partial_baths_count") @Expose var partialBathsCount: Int? = null,
    @SerializedName("plumbing_fixtures_count") @Expose var plumbingFixturesCount: Any? = null,
    @SerializedName("pool_type") @Expose var poolType: Any? = null,
    @SerializedName("quality") @Expose var quality: String? = "",
    @SerializedName("roof_material_type") @Expose var roofMaterialType: Any? = null,
    @SerializedName("roof_style_type") @Expose var roofStyleType: Any? = null,
    @SerializedName("rooms_count") @Expose var roomsCount: Any? = null,
    @SerializedName("sewer_type") @Expose var sewerType: String? = "",
    @SerializedName("stories") @Expose var stories: String? = "",
    @SerializedName("total_area_sq_ft") @Expose var totalAreaSqFt: Int? = null,
    @SerializedName("units_count") @Expose var unitsCount: Any? = null,
    @SerializedName("water_type") @Expose var waterType: String? = "",
    @SerializedName("year_built") @Expose var yearBuilt: Int? = null
)

data class Valuation(
    @SerializedName("date") @Expose var date: String? = "",
    @SerializedName("forecast_standard_deviation") @Expose var forecastStandardDeviation: Int? = null,
    @SerializedName("high") @Expose var high: Int? = null,
    @SerializedName("low") @Expose var low: Int? = null,
    @SerializedName("value") @Expose var value: Int? = null
)

data class OtherImprovement(
    @SerializedName("sq_ft") @Expose var sqFt: String? = "",
    @SerializedName("type") @Expose var type: String? = ""
)

data class Geojson(
    @SerializedName("coordinates") @Expose var coordinates: List<List<List<List<Double>>>>? = null,
    @SerializedName("type") @Expose var type: String? = ""
)

data class Metadata(
    @SerializedName("timestamp") @Expose var timestamp: String? = "",
    @SerializedName("version") @Expose var version: String? = ""
)

data class MarketAssessment(
    @SerializedName("improvement_value") @Expose var improvementValue: Int? = null,
    @SerializedName("land_value") @Expose var landValue: Int? = null,
    @SerializedName("total_value") @Expose var totalValue: Int? = null,
    @SerializedName("year") @Expose var year: Int? = null
)

data class OtherArea(
    @SerializedName("sq_ft") @Expose var sqFt: String? = "",
    @SerializedName("type") @Expose var type: String? = ""
)

data class MetadataX(
    @SerializedName("publishing_date") @Expose var publishingDate: String? = "",
)

data class ErrorResponse(
    @SerializedName("error") @Expose var error: Error? = null,
    @SerializedName("metadata") @Expose var metadata: MetadataX? = null
)

data class Error(
    @SerializedName("code") @Expose var code: String? = "",
    @SerializedName("description") @Expose var description: String? = "",
    @SerializedName("metadata") @Expose var metadata: kotlin.Metadata? = null,
    @SerializedName("status_code") @Expose var statusCode: Int? = null,
    @SerializedName("title") @Expose var title: String? = ""
)

data class Taxe(
    @SerializedName("amount") @Expose var amount: Int? = null,
    @SerializedName("exemptions") @Expose var exemptions: List<Any>? = null,
    @SerializedName("rate_code_area") @Expose var rateCodeArea: String? = "",
    @SerializedName("year") @Expose var year: Int? = null
)

data class Address(
    @SerializedName("carrier_code") @Expose var carrierCode: String? = "",
    @SerializedName("census_tract") @Expose var censusTract: String? = "",
    @SerializedName("city") @Expose var city: String? = "",
    @SerializedName("formatted_street_address") @Expose var formattedStreetAddress: String? = "",
    @SerializedName("geocoding_accuracy") @Expose var geocodingAccuracy: String? = "",
    @SerializedName("latitude") @Expose var latitude: Double? = null,
    @SerializedName("longitude") @Expose var longitude: Double? = null,
    @SerializedName("state") @Expose var state: String? = "",
    @SerializedName("street_name") @Expose var streetName: String? = "",
    @SerializedName("street_number") @Expose var streetNumber: String? = "",
    @SerializedName("street_post_direction") @Expose var streetPostDirection: Any? = null,
    @SerializedName("street_pre_direction") @Expose var streetPreDirection: Any? = null,
    @SerializedName("street_suffix") @Expose var streetSuffix: String? = "",
    @SerializedName("unit_number") @Expose var unitNumber: Any? = null,
    @SerializedName("unit_type") @Expose var unitType: Any? = null,
    @SerializedName("zip_code") @Expose var zipCode: String? = "",
    @SerializedName("zip_plus_four_code") @Expose var zipPlusFourCode: String? = ""
)

data class Assessment(
    @SerializedName("improvement_value") @Expose var improvementValue: Int? = null,
    @SerializedName("land_value") @Expose var landValue: Int? = null,
    @SerializedName("total_value") @Expose var totalValue: Int? = null,
    @SerializedName("year") @Expose var year: Int? = null
)

data class Boundary(
    @SerializedName("geojson") @Expose var geojson: Geojson? = null,
    @SerializedName("wkt") @Expose var wkt: String? = ""
)