select substring_index(group_concat(id), ',', -1) as id, substring_index(group_concat(language_id), ',', -1) as language_id, keyword, null as value from dictionary group by keyword;

--
select
    section.id,
    (select value from dictionary where section.name_keyword = dictionary.keyword and language_id = 1 limit 1) as name_keyword,
    (select value from dictionary where section.description_keyword = dictionary.keyword and language_id = 1 limit 1) as description_keyword,
    (select value from dictionary where section.metadata_keyword = dictionary.keyword and language_id = 1 limit 1) as metadata_keyword
    from section;