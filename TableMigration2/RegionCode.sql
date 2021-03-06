CREATE TABLE  IF NOT EXISTS dbo."RegionCode"
(
    "CountryCode"        CHAR(2)  COLLATE dbo.case_insensitive     NOT NULL,
    "RegionCode"         VARCHAR(30)  COLLATE dbo.case_insensitive NOT NULL,
    "Description"        VARCHAR(255) COLLATE dbo.case_insensitive NOT NULL,
    "DateCreated"        TIMESTAMP                                 NOT NULL,
    "DateAmended"        TIMESTAMP                                 NOT NULL,
    "AuditId"            UUID                                      NULL,
    "AuditActionType"    CHAR(2)      COLLATE dbo.case_insensitive NOT NULL,
    "AuditSequenceNo"    INTEGER      GENERATED ALWAYS AS IDENTITY NOT NULL,
    "LoginName"          VARCHAR(256) COLLATE dbo.case_insensitive NULL,
    "HostName"           VARCHAR(256) COLLATE dbo.case_insensitive NULL,
    "ActionTime"         TIMESTAMP                                 NULL,
    CONSTRAINT "PK_RegionCode" PRIMARY KEY  
    (
        "AuditSequenceNo"
    )	
);	

GRANT  INSERT  ON dbo."RegionCode"  TO PUBLIC;