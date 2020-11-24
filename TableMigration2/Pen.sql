CREATE TABLE IF NOT EXISTS dbo."Pen"
(
    "PenId"                     INTEGER                                   NOT NULL,
    "PenTypeID"                 INTEGER                                   NOT NULL,
    "DisplayName"               VARCHAR(50)  COLLATE dbo.case_insensitive NOT NULL,
    "PenHostName"               VARCHAR(255) COLLATE dbo.case_insensitive NOT NULL,
    "IPAddress"                 VARCHAR(15)  COLLATE dbo.case_insensitive NOT NULL,
    "Description"               VARCHAR(255) COLLATE dbo.case_insensitive NOT NULL,
    "DateCreated"               TIMESTAMP                                 NOT NULL,
    "DateAmended"               TIMESTAMP                                 NOT NULL,
    "WhoAmended_nt_username"    VARCHAR(255) COLLATE dbo.case_insensitive NOT NULL,
    "WhoAmended_hostname"       VARCHAR(255) COLLATE dbo.case_insensitive NOT NULL,
    "AuditId"                   UUID                                      NULL,
    "AuditActionType"           CHAR(2)      COLLATE dbo.case_insensitive NOT NULL,
    "AuditSequenceNo"           INTEGER      GENERATED ALWAYS AS IDENTITY NOT NULL,
    "LoginName"                 VARCHAR(256) COLLATE dbo.case_insensitive NULL,
    "HostName"                  VARCHAR(256) COLLATE dbo.case_insensitive NULL,
    "ActionTime"                TIMESTAMP                                 NULL,
	
     CONSTRAINT "PK_Pen" PRIMARY KEY
    (
	    "AuditSequenceNo"
    )
);
CREATE UNIQUE INDEX IF NOT EXISTS "INDEX IX_Pen_NC01" 
ON dbo."Pen"
    (
        "AuditId" ASC NULLS FIRST
    );