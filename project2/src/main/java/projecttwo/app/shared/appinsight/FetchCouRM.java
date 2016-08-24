package projecttwo.app.shared.appinsight;
import com.athena.server.dataengine.bizService.DTOMapperInterface;
import projecttwo.app.config.annotation.Complexity;
import projecttwo.app.config.annotation.SourceCodeAuthorClass;
import java.sql.Timestamp;
import com.athena.config.jsonHandler.CustomSqlTimestampJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.athena.config.jsonHandler.CustomSqlTimestampJsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "1", comments = "FetchCou", complexity = Complexity.MEDIUM)
public class FetchCouRM implements DTOMapperInterface {

    private String countryId;

    private String countryName;

    private String countryCode1;

    private String countryCode2;

    private String countryFlag;

    private String capital;

    private String currencyCode;

    private String currencyName;

    private String currencySymbol;

    private Integer capitalLatitude;

    private Integer capitalLongitude;

    private Integer isoNumeric;

    private Integer versionId;

    private String createdBy;

    @JsonSerialize(using = CustomSqlTimestampJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlTimestampJsonDeserializer.class)
    private Timestamp createdDate;

    private String updatedBy;

    @JsonSerialize(using = CustomSqlTimestampJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlTimestampJsonDeserializer.class)
    private Timestamp updatedDate;

    public FetchCouRM(Object[] aryObject) {
        if (aryObject != null) {
            countryId = (aryObject[0] == null ? null : new java.lang.String(aryObject[0].toString()));
            countryName = (aryObject[1] == null ? null : new java.lang.String(aryObject[1].toString()));
            countryCode1 = (aryObject[2] == null ? null : new java.lang.String(aryObject[2].toString()));
            countryCode2 = (aryObject[3] == null ? null : new java.lang.String(aryObject[3].toString()));
            countryFlag = (aryObject[4] == null ? null : new java.lang.String(aryObject[4].toString()));
            capital = (aryObject[5] == null ? null : new java.lang.String(aryObject[5].toString()));
            currencyCode = (aryObject[6] == null ? null : new java.lang.String(aryObject[6].toString()));
            currencyName = (aryObject[7] == null ? null : new java.lang.String(aryObject[7].toString()));
            currencySymbol = (aryObject[8] == null ? null : new java.lang.String(aryObject[8].toString()));
            capitalLatitude = (aryObject[9] == null ? null : new java.lang.Integer(aryObject[9].toString()));
            capitalLongitude = (aryObject[10] == null ? null : new java.lang.Integer(aryObject[10].toString()));
            isoNumeric = (aryObject[11] == null ? null : new java.lang.Integer(aryObject[11].toString()));
            versionId = (aryObject[12] == null ? null : new java.lang.Integer(aryObject[12].toString()));
            createdBy = (aryObject[13] == null ? null : new java.lang.String(aryObject[13].toString()));
            createdDate = (aryObject[14] == null ? null : (java.sql.Timestamp) aryObject[14]);
            updatedBy = (aryObject[15] == null ? null : new java.lang.String(aryObject[15].toString()));
            updatedDate = (aryObject[16] == null ? null : (java.sql.Timestamp) aryObject[16]);
        } else {
            countryId = null;
            countryName = null;
            countryCode1 = null;
            countryCode2 = null;
            countryFlag = null;
            capital = null;
            currencyCode = null;
            currencyName = null;
            currencySymbol = null;
            capitalLatitude = null;
            capitalLongitude = null;
            isoNumeric = null;
            versionId = null;
            createdBy = null;
            createdDate = null;
            updatedBy = null;
            updatedDate = null;
        }
    }

    public String getCountryId() {
        return countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCode1() {
        return countryCode1;
    }

    public String getCountryCode2() {
        return countryCode2;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public String getCapital() {
        return capital;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public Integer getCapitalLatitude() {
        return capitalLatitude;
    }

    public Integer getCapitalLongitude() {
        return capitalLongitude;
    }

    public Integer getIsoNumeric() {
        return isoNumeric;
    }

    public Integer getVersionId() {
        return versionId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }
}
