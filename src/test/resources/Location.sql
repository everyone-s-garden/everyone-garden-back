ALTER TABLE location ADD FULLTEXT INDEX addressN (fullAddress)
WITH PARSER ngram;
