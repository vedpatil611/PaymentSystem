use bankapp;
select * from account;
insert into account values(1,1000000,500000.0,"riolu@gmail.com","Riolu");
insert into account values(2,1000001,200000.0,"mienfoo@gmail.com","Mienfoo");
insert into account values(3,1000002,400000.0,"machop@gmail.com","Machop");
insert into account values(4,1000003,300000.0,"kunal@gmail.com","kunal");
insert into account values(5,1000004,600000.0,"vedangi@gmail.com","vedangi");

insert into master_biller values("B001","Vodafone");
insert into master_biller values("B002","Airtel");
insert into master_biller values("B003","Jio");
insert into master_biller values("B004","Tata Electricity");
insert into master_biller values("B005","Adani Electricity");

insert into role values(1,"MANAGER");
insert into role values(2,"ACCOUNT");


insert into registered_biller values(1,false,null,123,1000000,"VI");
insert into registered_biller values(2,true,null,133,1000001,"VI");
insert into registered_biller values(3,true,null,123,1000000,"Jio");
insert into registered_biller values(4,false,null,143,1000002,"VI");
insert into registered_biller values(5,true,null,153,1000003,"Airtel");

commit;