CREATE TABLE `NEWS`
(
    `ID`               VARCHAR(20)  NOT NULL,
    `TITLE`            VARCHAR(200) NULL,
    `CONTENT`          BLOB         NULL,
    `AUTHOR`           VARCHAR(100) NULL,
    `LAST_UPDATE_DATE` DATETIME     NULL,
    `LAST_UPDATE_BY`   VARCHAR(100) NULL,
    PRIMARY KEY (`ID`)
);
