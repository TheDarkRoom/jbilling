#Create Trend Items

#ITEMS
INSERT INTO item VALUES(1,'B-01',1,NULL,0,0,0,1);
INSERT INTO item VALUES(2,'F-01',1,NULL,0,0,0,1);
INSERT INTO item VALUES(11,'T-01',1,6.0E0,0,0,0,1);
INSERT INTO item VALUES(12,'P-01',1,NULL,0,0,0,1);
INSERT INTO item VALUES(13,'B-02',1,NULL,0,0,0,1);
INSERT INTO item VALUES(14,'B-03',1,NULL,0,0,0,1);
INSERT INTO item VALUES(15,'D-01',1,-10.0E0,0,0,0,1);
INSERT INTO item VALUES(16,'B-04',1,NULL,0,0,0,1);
INSERT INTO item VALUES(17,'F-02',1,NULL,0,0,0,1);

#international item description
INSERT INTO international_description VALUES(14,1,'description',1,'Front page banner - monthly fee');
INSERT INTO international_description VALUES(14,2,'description',1,'Account maintenance fee');
INSERT INTO international_description VALUES(14,11,'description',1,'GST 6%');
INSERT INTO international_description VALUES(14,12,'description',1,'Start-Up Plan -  1000 monthly clicks');
INSERT INTO international_description VALUES(14,13,'description',1,'Click on front page banner - included in plan');
INSERT INTO international_description VALUES(14,14,'description',1,'Click on front page banner');
INSERT INTO international_description VALUES(14,15,'description',1,'Volume discount - 10%');
INSERT INTO international_description VALUES(14,16,'description',1,'Click on front page banner - generic');
INSERT INTO international_description VALUES(14,17,'description',1,'Account maintenance fee - Plan discount');

#check
select a.id, a.id, a.internal_number, b.content 
from item a, international_description b, jbilling_table c,
     entity e
where a.entity_id = e.id
and e.id = 1
and a.deleted = 0
and b.table_id = c.id
and c.name = 'item'
and b.foreign_id = a.id
and b.language_id = e.language_id
and b.psudo_column = 'description'
order by a.internal_number;

#ITEM PRICES
INSERT INTO item_price VALUES(1,1,1,100.0E0,1);
INSERT INTO item_price VALUES(2,2,1,10.0E0,1);
INSERT INTO item_price VALUES(11,12,1,50.0E0,1);
INSERT INTO item_price VALUES(12,13,1,0.0E0,1);
INSERT INTO item_price VALUES(13,14,1,0.10000000149011612E0,1);
INSERT INTO item_price VALUES(14,16,1,0.0E0,1);
INSERT INTO item_price VALUES(15,17,1,5.0E0,1);

#ITEM TYPES
INSERT INTO item_type VALUES(1,1,'Banners',1,1);
INSERT INTO item_type VALUES(2,1,'Miscellaneous fees',1,1);
INSERT INTO item_type VALUES(11,1,'Taxes',2,1);
INSERT INTO item_type VALUES(12,1,'Bundles',1,1);
INSERT INTO item_type VALUES(13,1,'Promotions',1,1);

#item type maps
INSERT INTO item_type_map VALUES(1,1);
INSERT INTO item_type_map VALUES(2,2);
INSERT INTO item_type_map VALUES(11,11);
INSERT INTO item_type_map VALUES(12,12);
INSERT INTO item_type_map VALUES(13,1);
INSERT INTO item_type_map VALUES(14,1);
INSERT INTO item_type_map VALUES(15,13);
INSERT INTO item_type_map VALUES(16,1);
INSERT INTO item_type_map VALUES(17,2);

