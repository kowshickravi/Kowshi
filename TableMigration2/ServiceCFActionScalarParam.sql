CREATE TABLE IF NOT EXISTS dbo."ServiceCFActionScalarParam"
(	
    "ScalarParamId"             INTEGER                                   NOT NULL,
    "ActionId"                  INTEGER                                   NOT NULL,
    "ScalarValue"               VARCHAR(255) COLLATE dbo.case_insensitive NULL,
    "ParamType"                 VARCHAR(10)  COLLATE dbo.case_insensitive NOT NULL,
    "DateCreated"               TIMESTAMP                                 NOT NULL,
    "DateAmended"               TIMESTAMP                                 NOT NULL,
    "DateDeleted"               TIMESTAMP                                 NOT NULL,
    "WhoAmended_nt_username"    VARCHAR(255) COLLATE dbo.case_insensitive NOT NULL,
    "WhoAmended_hostname"       VARCHAR(255) COLLATE dbo.case_insensitive NOT NULL,
    "AuditId"                   UUID                                  NULL,
    "AuditActionType"           CHAR(2)      COLLATE dbo.case_insensitive NOT NULL,
    "AuditSequenceNo"           INTEGER      GENERATED ALWAYS AS IDENTITY NOT NULL,
    "LoginName"                 VARCHAR(256) COLLATE dbo.case_insensitive NULL,
    "HostName"                  VARCHAR(256) COLLATE dbo.case_insensitive NULL,
    "ActionTime"                TIMESTAMP                                 NULL,
    "ScalarValueBinary"         BYTEA                                     NULL,
     CONSTRAINT "PK_ServiceCFActionScalarParam" PRIMARY KEY  
     (
	    "AuditSequenceNo"
     )  
);
CREATE UNIQUE INDEX IF NOT EXISTS "IX_ServiceCFActionScalarParam_NC01" 
ON dbo."ServiceCFActionScalarParam"
     (
	    "AuditId" ASC NULLS FIRST
     );


GRANT  INSERT  ON dbo."ServiceCFActionScalarParam"  TO PUBLIC;
