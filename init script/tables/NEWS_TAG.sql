CREATE TABLE `NEWS_TAG`
(
    `NEWS_ID` VARCHAR(20)  NOT NULL,
    `TAG_ID`  VARCHAR(100) NOT NULL,
    PRIMARY KEY (`NEWS_ID`, `TAG_ID`)
);
