CREATE TABLE location
(
    region_id BIGINT       NOT NULL auto_increment,
    latitude  DOUBLE       NOT NULL,
    longitude DOUBLE       NOT NULL,
    level1    VARCHAR(100) NOT NULL,
    level2    VARCHAR(100) NULL,
    level3    VARCHAR(100) NULL,
    level4    VARCHAR(100) NULL,

    CONSTRAINT pk_location PRIMARY KEY (region_id)
);