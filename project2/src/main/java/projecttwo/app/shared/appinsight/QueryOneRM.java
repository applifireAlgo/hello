package projecttwo.app.shared.appinsight;
import com.athena.server.dataengine.bizService.DTOMapperInterface;
import projecttwo.app.config.annotation.Complexity;
import projecttwo.app.config.annotation.SourceCodeAuthorClass;
import java.sql.Timestamp;
import com.athena.config.jsonHandler.CustomSqlTimestampJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.athena.config.jsonHandler.CustomSqlTimestampJsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "1", comments = "TestQueryOne", complexity = Complexity.MEDIUM)
public class QueryOneRM implements DTOMapperInterface {

    private String bugId;

    private String bugName;

    private String component;

    private String priority;

    private Integer versionId;

    private String createdBy;

    @JsonSerialize(using = CustomSqlTimestampJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlTimestampJsonDeserializer.class)
    private Timestamp createdDate;

    private String updatedBy;

    @JsonSerialize(using = CustomSqlTimestampJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlTimestampJsonDeserializer.class)
    private Timestamp updatedDate;

    public QueryOneRM(Object[] aryObject) {
        if (aryObject != null) {
            bugId = (aryObject[0] == null ? null : new java.lang.String(aryObject[0].toString()));
            bugName = (aryObject[1] == null ? null : new java.lang.String(aryObject[1].toString()));
            component = (aryObject[2] == null ? null : new java.lang.String(aryObject[2].toString()));
            priority = (aryObject[3] == null ? null : new java.lang.String(aryObject[3].toString()));
            versionId = (aryObject[4] == null ? null : new java.lang.Integer(aryObject[4].toString()));
            createdBy = (aryObject[5] == null ? null : new java.lang.String(aryObject[5].toString()));
            createdDate = (aryObject[6] == null ? null : (java.sql.Timestamp) aryObject[6]);
            updatedBy = (aryObject[7] == null ? null : new java.lang.String(aryObject[7].toString()));
            updatedDate = (aryObject[8] == null ? null : (java.sql.Timestamp) aryObject[8]);
        } else {
            bugId = null;
            bugName = null;
            component = null;
            priority = null;
            versionId = null;
            createdBy = null;
            createdDate = null;
            updatedBy = null;
            updatedDate = null;
        }
    }

    public String getBugId() {
        return bugId;
    }

    public String getBugName() {
        return bugName;
    }

    public String getComponent() {
        return component;
    }

    public String getPriority() {
        return priority;
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
