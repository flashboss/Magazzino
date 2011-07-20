-- If you are using Hibernate as the JPA provider, you can use this file to load seed data into the database using SQL statements
-- The portable approach is to use a startup component (such as the @PostConstruct method of a @Startup @Singleton) or observe a lifecycle event fired by Seam Servlet
insert into receipt (number,date,cause,description) values (21256,'11/05/2009','causale 1','description 1');
insert into receipt (number,date,cause,description) values (23226,'02/05/2009','causale 2','description 2');
insert into receipt (number,date,cause,description) values (11213,'11/10/2009','causale 3','description 3');
insert into receipt (number,date,cause,description) values (4343,'11/05/2007','causale 4','description 4');
insert into receipt (number,date,cause,description) values (5453,'11/01/2003','causale 5','description 5');
insert into receipt (number,date,cause,description) values (666,'02/05/2007','causale 6','description 6');
insert into receipt (number,date,cause,description) values (32322,'03/03/2002','causale 7','description 7');
insert into receipt (number,date,cause,description) values (528,'11/03/2007','causale 8','description 8');
insert into receipt (number,date,cause,description) values (95433,'01/05/2007','causale 9','description 9');
insert into receipt (number,date,cause,description) values (5432,'11/12/2007','causale 10','description 10');
insert into receipt (number,date,cause,description) values (33333,'12/05/2001','causale 11','description 11');
insert into customer (code,name,ragSocial) values (19987,'cliente 1','rag soc 1');
insert into customer (code,name,ragSocial) values (1177,'cliente 2','rag soc 2');
insert into customer (code,name,ragSocial) values (98766,'cliente 3','rag soc 3');
insert into customer (code,name,ragSocial) values (11121,'cliente 4','rag soc 4');
insert into customer (code,name,ragSocial) values (54566,'cliente 5','rag soc 5');
insert into customer (code,name,ragSocial) values (34322,'cliente 6','rag soc 6');
insert into customer (code,name,ragSocial) values (22222,'cliente 7','rag soc 7');
insert into customer (code,name,ragSocial) values (55555,'cliente 8','rag soc 8');
insert into customer (code,name,ragSocial) values (325,'cliente 9','rag soc 9');
insert into customer (code,name,ragSocial) values (6433,'cliente 10','rag soc 10');
insert into magazzino (number,date,code,cause,compensation,codCustomer,ragSoc1,ragSoc2,numberDoc,dateDoc) values (21344,'01/06/1999',23432,'cause 1','compensation 1',2654654,'rag soc 1','rag soc 2',111,'01/05/1998');
insert into magazzino (number,date,code,cause,compensation,codCustomer,ragSoc1,ragSoc2,numberDoc,dateDoc) values (21345,'02/06/1981',543534,'cause 2','compensation 2',2654654,'rag soc 3','rag soc 4',111,'01/01/1998');
insert into magazzino (number,date,code,cause,compensation,codCustomer,ragSoc1,ragSoc2,numberDoc,dateDoc) values (21346,'06/02/1999',6546,'cause 3','compensation 3',213443,'rag soc 5','rag soc 6',111,'05/05/1977');
insert into magazzino (number,date,code,cause,compensation,codCustomer,ragSoc1,ragSoc2,numberDoc,dateDoc) values (21347,'06/01/1999',2342,'cause 4','compensation 4',453534,'rag soc 7','rag soc 8',111,'05/04/1976');
insert into magazzino (number,date,code,cause,compensation,codCustomer,ragSoc1,ragSoc2,numberDoc,dateDoc) values (21348,'06/06/1980',434,'cause 5','compensation 5',2787,'rag soc 9','rag soc 10',111,'04/04/1998');
insert into magazzino (number,date,code,cause,compensation,codCustomer,ragSoc1,ragSoc2,numberDoc,dateDoc) values (21349,'06/06/1982',22,'cause 6','compensation 6',53453,'rag soc 11','rag soc 12',111,'04/04/1998');
insert into magazzino (number,date,code,cause,compensation,codCustomer,ragSoc1,ragSoc2,numberDoc,dateDoc) values (21310,'06/03/1983',55,'cause 7','compensation 7',4333,'rag soc 13','rag soc 14',111,'04/01/1998');
insert into magazzino (number,date,code,cause,compensation,codCustomer,ragSoc1,ragSoc2,numberDoc,dateDoc) values (21311,'06/02/1984',34224,'cause 8','compensation 8',23433,'rag soc 15','rag soc 16',111,'01/04/1998');
insert into magazzino (number,date,code,cause,compensation,codCustomer,ragSoc1,ragSoc2,numberDoc,dateDoc) values (21314,'06/01/1985',534543,'cause 9','compensation 9',54546,'rag soc 17','rag soc 18',111,'04/02/1992');
insert into magazzino (number,date,code,cause,compensation,codCustomer,ragSoc1,ragSoc2,numberDoc,dateDoc) values (213334,'02/06/1986',23432,'cause 10','compensation 10',6544,'rag soc 19','rag soc 20',111,'04/04/1998');
insert into magazzino (number,date,code,cause,compensation,codCustomer,ragSoc1,ragSoc2,numberDoc,dateDoc) values (212344,'03/06/1987',65464,'cause 11','compensation 11',34322,'rag soc 21','rag soc 22',111,'05/04/1992');
insert into magazzino (number,date,code,cause,compensation,codCustomer,ragSoc1,ragSoc2,numberDoc,dateDoc) values (21234,'04/06/1988',5646,'cause 12','compensation 12',6567,'rag soc 23','rag soc 24',111,'04/05/1998');
insert into magazzino (number,date,code,cause,compensation,codCustomer,ragSoc1,ragSoc2,numberDoc,dateDoc) values (1344,'05/06/1989',645645,'cause 13','compensation 13',5454,'rag soc 25','rag soc 26',111,'04/04/1995');
insert into article (code,barCode,description,um,mis,catMerc,imponible,prize,cost,provider,rate,ca,sc1,sc2,sc3,qtmin,qtmax,pack,health,volume,making,notes) values (898989,656565,'description 1',3213,34534,23423,67545,234234,323,'provider 1',234234,675765,322,5454,3232,545,656,'pack 1',232,5454,'making 1','notes 1');
insert into article (code,barCode,description,um,mis,catMerc,imponible,prize,cost,provider,rate,ca,sc1,sc2,sc3,qtmin,qtmax,pack,health,volume,making,notes) values (2121,23131,'description 2',7567567,876867,5675,345345,786867,22,'provider 2',2121,43242,6546,4343,765724,5443,4324653,'pack 2',656,65433,'making 2','notes 2');
insert into article (code,barCode,description,um,mis,catMerc,imponible,prize,cost,provider,rate,ca,sc1,sc2,sc3,qtmin,qtmax,pack,health,volume,making,notes) values (3453,6462,'description 3',65472,645272,5136,53436,6754382,4343,'provider 3',65462,76475,5431,53477,534512,6456,434,'pack 3',65645,4234423,'making 3','notes 3');
insert into article (code,barCode,description,um,mis,catMerc,imponible,prize,cost,provider,rate,ca,sc1,sc2,sc3,qtmin,qtmax,pack,health,volume,making,notes) values (653724,65467,'description 4',756737,53456,745737,655,76574653,32,'provider 4',5435667,654677,765765,53456,65467,6546,765765,'pack 4',534534,65464,'making 4','notes 4');
insert into article (code,barCode,description,um,mis,catMerc,imponible,prize,cost,provider,rate,ca,sc1,sc2,sc3,qtmin,qtmax,pack,health,volume,making,notes) values (1234314,5345345,'description 5',654645,4234324,6456546,423423423,546546,54343,'provider 5',42434,5466,34536,64563,234,6565,656545,'pack 5',434,545,'making 5','notes 5');
insert into article (code,barCode,description,um,mis,catMerc,imponible,prize,cost,provider,rate,ca,sc1,sc2,sc3,qtmin,qtmax,pack,health,volume,making,notes) values (123523,4324235,'description 6',4324,534543,42342,4355,645654,54533,'provider 6',42342,53453,656,534534,645654,435,654654,'pack 6',423423,534543,'making 6','notes 6');
insert into article (code,barCode,description,um,mis,catMerc,imponible,prize,cost,provider,rate,ca,sc1,sc2,sc3,qtmin,qtmax,pack,health,volume,making,notes) values (6213,743423,'description 7',534567,42356,4234,67545,234234,664,'provider 7',234234,675765,322,5454,3232,545,656,'pack 7',232,5454,'making 7','notes 7');
insert into article (code,barCode,description,um,mis,catMerc,imponible,prize,cost,provider,rate,ca,sc1,sc2,sc3,qtmin,qtmax,pack,health,volume,making,notes) values (567567,23423,'description 8',5454,65464,756765,53453,7657,3322,'provider 8',42342,64564,7657,5435,7567,86786,6456,'pack 8',535,756756,'making 8','notes 8');
insert into article (code,barCode,description,um,mis,catMerc,imponible,prize,cost,provider,rate,ca,sc1,sc2,sc3,qtmin,qtmax,pack,health,volume,making,notes) values (87687634,5435,'description 9',65346,243423,645645,42343,645654,744,'provider 9',424,64564,5345,65465,4234,86785,52524,'pack 9',534543,65465,'making 9','notes 9');
insert into article (code,barCode,description,um,mis,catMerc,imponible,prize,cost,provider,rate,ca,sc1,sc2,sc3,qtmin,qtmax,pack,health,volume,making,notes) values (3222,5435344,'description 10',3232,4343,46576476,423423,645654,3233,'provider 10',42342,8767856,5454,222,555,44433,555,'pack 10',42342,7656755,'making 10','notes 10');
insert into article (code,barCode,description,um,mis,catMerc,imponible,prize,cost,provider,rate,ca,sc1,sc2,sc3,qtmin,qtmax,pack,health,volume,making,notes) values (34655673,4242,'description 11',34333,555,222,5345465,54356,654745,'provider 11',534536,633,2423,74675,534543,65465,5435,'pack 11',23423,645645,'making 11','notes 11');
insert into article (code,barCode,description,um,mis,catMerc,imponible,prize,cost,provider,rate,ca,sc1,sc2,sc3,qtmin,qtmax,pack,health,volume,making,notes) values (663,65466,'description 12',4242,5433,534543,456456,42342,54,'provider 12',777,444,333,555,5674,4242,78878,'pack 12',33543,42343232,'making 12','notes 12');
insert into article (code,barCode,description,um,mis,catMerc,imponible,prize,cost,provider,rate,ca,sc1,sc2,sc3,qtmin,qtmax,pack,health,volume,making,notes) values (73267,4567,'description 13',7654,8765,534674,53467,5346,657,'provider 13',76475,53457,53453,6546,765746,5345,65666,'pack 13',5,5435,'making 13','notes 13');
insert into article (code,barCode,description,um,mis,catMerc,imponible,prize,cost,provider,rate,ca,sc1,sc2,sc3,qtmin,qtmax,pack,health,volume,making,notes) values (23333,4753,'description 14',645645,34534,42343,67545,234234,332,'provider 14',234234,64564,322,5454,3232,2342,42342,'pack 14',42235,645653,'making 14','notes 14');
insert into article (code,barCode,description,um,mis,catMerc,imponible,prize,cost,provider,rate,ca,sc1,sc2,sc3,qtmin,qtmax,pack,health,volume,making,notes) values (5435322,53453,'description 15',35356,34534,23423,67545,234234,677456,'provider 15',234234,675765,322,5454,3232,545,656,'pack 15',232,5454,'making 15','notes 15');
insert into article (code,barCode,description,um,mis,catMerc,imponible,prize,cost,provider,rate,ca,sc1,sc2,sc3,qtmin,qtmax,pack,health,volume,making,notes) values (3334,5345,'description 16',3213,456456,74675,534634,345353,4224,'provider 16',234234,675765,322,5454,3232,545,656,'pack 16',232,5454,'making 16','notes 16');

        