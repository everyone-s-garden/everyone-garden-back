UPDATE location
SET full_address = CONCAT_WS('', level1, level2, level3, level4)
where true;
