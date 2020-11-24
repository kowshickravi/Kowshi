CREATE TABLE IF NOT EXISTS dbo."ProvisionTemplate"
(
    "CustomerId"          INTEGER                                   NOT NULL,
    "DomainId"            INTEGER                                   NOT NULL,
    "TemplateType"        CHAR(2)      COLLATE dbo.case_insensitive NOT NULL,
    "Region"              VARCHAR(10)  COLLATE dbo.case_insensitive NOT NULL,
    "AuditId"             UUID                                      NULL,
    "AuditActionType"     CHAR(2)      COLLATE dbo.case_insensitive NOT NULL,
    "AuditSequenceNo"     INTEGER      GENERATED ALWAYS AS IDENTITY NOT NULL, 
    "LoginName"           VARCHAR(256) COLLATE dbo.case_insensitive NULL,                          
    "HostName"            VARCHAR(256) COLLATE dbo.case_insensitive NULL,
    "ActionTime"          TIMESTAMP                                 NULL,
     CONSTRAINT  "PK_ProvisionTemplate_CU01" PRIMARY KEY
    (
	  "AuditSequenceNo"
    )
);
GRANT INSERT ON dbo."ProvisionTemplate" TO PUBLIC;