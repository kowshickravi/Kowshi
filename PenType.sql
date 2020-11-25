CREATE TABLE IF NOT EXISTS dbo."PenType"
(
    "PenTypeId"                 INTEGER                                   NOT NULL,
    "PenType"                   VARCHAR(50)  COLLATE dbo.case_insensitive NOT NULL,
    "DateCreated"               TIMESTAMP                                 NOT NULL,
    "DateAmended"               TIMESTAMP                                 NOT NULL,
    "WhoAmended_nt_username"    VARCHAR(255) COLLATE dbo.case_insensitive NOT NULL,
    "WhoAmended_hostname"       VARCHAR(255) COLLATE dbo.case_insensitive NOT NULL,
    "AuditId"                   UUID                                      NULL,
    "AuditActionType"           CHAR(2)      COLLATE dbo.case_insensitive NOT NULL,
    "AuditSequenceNo"           INTEGER      GENERATED ALWAYS AS IDENTITY NOT NULL ,
    "LoginName"                 VARCHAR(256) COLLATE dbo.case_insensitive NULL,
    "HostName"                  VARCHAR(256) COLLATE dbo.case_insensitive NULL,
    "ActionTime"                TIMESTAMP                                 NULL,
 
    CONSTRAINT "PK_PenType" PRIMARY KEY 
    (
        "AuditSequenceNo"
    )
);
CREATE UNIQUE INDEX IF NOT EXISTS "IX_PenType_NC01"
ON dbo."PenType"
    (
        "AuditId" ASC NULLS FIRST
    );

GRANT INSERT ON dbo."PenType" TO PUBLIC;