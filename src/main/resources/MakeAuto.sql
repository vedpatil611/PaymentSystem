alter table paymentdb.account modify sequence_id int(11) unique not null auto_increment;
alter table paymentdb.bill modify sequence_id int(11) auto_increment;
