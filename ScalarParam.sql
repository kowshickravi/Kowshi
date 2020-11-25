CREATE TABLE IF NOT EXISTS dbo."ScalarParam"
(
    "ScalarParamId"             INTEGER                                    NOT NULL,
    "ScalarParamGroupId"        INTEGER                                    NOT NULL,
    "ScalarParamKeyId"          SMALLINT                                   NOT NULL,
    "ScalarValue"               VARCHAR(1000) COLLATE dbo.case_insensitive NULL,
    "DateCreated"               TIMESTAMP                                  NOT NULL,
    "DateAmended"               TIMESTAMP                                  NOT NULL,
    "WhoAmended_nt_username"    VARCHAR(255) COLLATE dbo.case_insensitive  NOT NULL,
    "WhoAmended_hostname"       VARCHAR(255) COLLATE dbo.case_insensitive  NOT NULL,
    "AuditId"                   UUID                                       NULL,
    "AuditActionType"           CHAR(2)      COLLATE dbo.case_insensitive  NOT NULL,
    "AuditSequenceNo"           INTEGER      GENERATED ALWAYS AS IDENTITY  NOT NULL,
    "LoginName"                 VARCHAR(256) COLLATE dbo.case_insensitive  NULL,
    "HostName"                  VARCHAR(256) COLLATE dbo.case_insensitive  NULL,
    "ActionTime"                TIMESTAMP                                  NULL
    CONSTRAINT PK_ScalarParam_CU01 PRIMARY KEY CLUSTERED 
    (
        AuditSequenceNo
    )
);

GRANT INSERT ON dbo."ScalarParam" TO PUBLIC;