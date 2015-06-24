INSERT INTO `rating`.`rating_supp_template` (`name`) VALUES ('A');
INSERT INTO `rating`.`rating_supp_template` (`name`) VALUES ('B');
INSERT INTO `rating`.`rating_supp_template` (`name`) VALUES ('C');
INSERT INTO `rating`.`rating_supp_template` (`name`) VALUES ('D');

insert into rating.rating_supp_options(code,name,category) select '62e7d685-de66-4b85-b401-b5c69c836030','本年度工作统计','BXDF' from dual where not exists (select 1 from rating.rating_supp_options where code = '62e7d685-de66-4b85-b401-b5c69c836030');
insert into rating.rating_supp_options(code,name,category) select 'eadb13de-f617-4f4f-8c3c-9f755ddc663c','技术或工艺改进','BXDF' from dual where not exists (select 1 from rating.rating_supp_options where code = 'eadb13de-f617-4f4f-8c3c-9f755ddc663c');
insert into rating.rating_supp_options(code,name,category) select 'f8bd4c60-c4cd-40a4-b13a-a8349bf66a24','责任心','BXDF' from dual where not exists (select 1 from rating.rating_supp_options where code = 'f8bd4c60-c4cd-40a4-b13a-a8349bf66a24');
insert into rating.rating_supp_options(code,name,category) select '7ccafc20-d860-4adc-8ad0-5f926a56bf61','协作精神','BXDF' from dual where not exists (select 1 from rating.rating_supp_options where code = '7ccafc20-d860-4adc-8ad0-5f926a56bf61');
insert into rating.rating_supp_options(code,name,category) select 'cf84cd1d-62c4-433a-9b08-f85cd25c20e5','劳动纪律','BXDF' from dual where not exists (select 1 from rating.rating_supp_options where code = 'cf84cd1d-62c4-433a-9b08-f85cd25c20e5');
insert into rating.rating_supp_options(code,name,category) select 'b7f8079c-8310-461c-ae4c-c63eb63dc9f6','规范操作','BXDF' from dual where not exists (select 1 from rating.rating_supp_options where code = 'b7f8079c-8310-461c-ae4c-c63eb63dc9f6');
insert into rating.rating_supp_options(code,name,category) select '72cece1e-09ad-4bb9-9c7c-32465412da4e','事件汇报','BXDF' from dual where not exists (select 1 from rating.rating_supp_options where code = '72cece1e-09ad-4bb9-9c7c-32465412da4e');
insert into rating.rating_supp_options(code,name,category) select '7d51f7da-02e4-4850-b71a-996661f8861a','安全保密','BXDF' from dual where not exists (select 1 from rating.rating_supp_options where code = '7d51f7da-02e4-4850-b71a-996661f8861a');
insert into rating.rating_supp_options(code,name,category) select '3aeb5d80-ec23-46c2-b24e-d3f9a05ab076','完成任务情况','NLDF' from dual where not exists (select 1 from rating.rating_supp_options where code = '3aeb5d80-ec23-46c2-b24e-d3f9a05ab076');
insert into rating.rating_supp_options(code,name,category) select 'f63773ea-cc76-4ede-af42-46285ba0959a','解决问题能力','NLDF' from dual where not exists (select 1 from rating.rating_supp_options where code = 'f63773ea-cc76-4ede-af42-46285ba0959a');
insert into rating.rating_supp_options(code,name,category) select 'e634146c-5e7f-43ba-a36f-4bea95d372c5','学习问题能力','NLDF' from dual where not exists (select 1 from rating.rating_supp_options where code = 'e634146c-5e7f-43ba-a36f-4bea95d372c5');
insert into rating.rating_supp_options(code,name,category) select '2d1b01c9-e9e6-40d6-b1ec-d4517d6298a7','学习和应用新技术','NLDF' from dual where not exists (select 1 from rating.rating_supp_options where code = '2d1b01c9-e9e6-40d6-b1ec-d4517d6298a7');
insert into rating.rating_supp_options(code,name,category) select '50df5443-e051-4bef-916c-9994f9ee9859','分析统计能力','NLDF' from dual where not exists (select 1 from rating.rating_supp_options where code = '50df5443-e051-4bef-916c-9994f9ee9859');
insert into rating.rating_supp_options(code,name,category) select '1e50cb11-8e7f-475c-9099-ed439e8674ad','组内完成任务情况','WCRWQK' from dual where not exists (select 1 from rating.rating_supp_options where code = '1e50cb11-8e7f-475c-9099-ed439e8674ad');
insert into rating.rating_supp_options(code,name,category) select '1c4abcae-e1e1-4605-b5b2-996f0829e7e7','组内工艺质量与控制','WCRWQK' from dual where not exists (select 1 from rating.rating_supp_options where code = '1c4abcae-e1e1-4605-b5b2-996f0829e7e7');
insert into rating.rating_supp_options(code,name,category) select 'cc38460e-6ffa-469e-b44c-2cc03115c926','组内技术发展','WCRWQK' from dual where not exists (select 1 from rating.rating_supp_options where code = 'cc38460e-6ffa-469e-b44c-2cc03115c926');
insert into rating.rating_supp_options(code,name,category) select 'a8a10a0c-9195-40c9-bb11-c410ab611372','室内外协作','ZZNL' from dual where not exists (select 1 from rating.rating_supp_options where code = 'a8a10a0c-9195-40c9-bb11-c410ab611372');
insert into rating.rating_supp_options(code,name,category) select '5994e4cd-6de9-473a-a199-c91c94073222','实验室管理','ZZNL' from dual where not exists (select 1 from rating.rating_supp_options where code = '5994e4cd-6de9-473a-a199-c91c94073222');
insert into rating.rating_supp_options(code,name,category) select 'c63727b6-4e57-4b9d-9d51-cc8a90d9f72c','培养人才','ZHNL' from dual where not exists (select 1 from rating.rating_supp_options where code = 'c63727b6-4e57-4b9d-9d51-cc8a90d9f72c');
insert into rating.rating_supp_options(code,name,category) select '178d2802-8e54-460c-9fa3-a29b0377c4c8','实验室建设','ZHNL' from dual where not exists (select 1 from rating.rating_supp_options where code = '178d2802-8e54-460c-9fa3-a29b0377c4c8');
insert into rating.rating_supp_options(code,name,category) select 'fd49cfcf-2d6e-423b-b36a-27b05b6a5b30','本人研究任务完成情况','ZHNL' from dual where not exists (select 1 from rating.rating_supp_options where code = 'fd49cfcf-2d6e-423b-b36a-27b05b6a5b30');
insert into rating.rating_supp_options(code,name,category) select '06ade6a9-12f5-417c-b161-cdc59519b8ed','本人解决的关键技术','ZHNL' from dual where not exists (select 1 from rating.rating_supp_options where code = '06ade6a9-12f5-417c-b161-cdc59519b8ed');
insert into rating.rating_supp_options(code,name,category) select '3cd9360d-9e97-4eb3-b6b8-85966abc2673','本人工作亮点','ZHNL' from dual where not exists (select 1 from rating.rating_supp_options where code = '3cd9360d-9e97-4eb3-b6b8-85966abc2673');
insert into rating.rating_supp_options(code,name,category) select 'fa428765-09d0-478e-902d-acbe2947e4ae','文章专利与报告','ZHNL' from dual where not exists (select 1 from rating.rating_supp_options where code = 'fa428765-09d0-478e-902d-acbe2947e4ae');
insert into rating.rating_supp_options(code,name,category) select '6bead800-3481-4e5a-9fd3-1b89b93b105b','安全与保密','ZHNL' from dual where not exists (select 1 from rating.rating_supp_options where code = '6bead800-3481-4e5a-9fd3-1b89b93b105b');


insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'A'),(select id from rating.rating_supp_options where code = '62e7d685-de66-4b85-b401-b5c69c836030')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'A') and option_id =(select id from rating.rating_supp_options where code = '62e7d685-de66-4b85-b401-b5c69c836030') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'A'),(select id from rating.rating_supp_options where code = 'eadb13de-f617-4f4f-8c3c-9f755ddc663c')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'A') and option_id =(select id from rating.rating_supp_options where code = 'eadb13de-f617-4f4f-8c3c-9f755ddc663c') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'A'),(select id from rating.rating_supp_options where code = 'f8bd4c60-c4cd-40a4-b13a-a8349bf66a24')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'A') and option_id =(select id from rating.rating_supp_options where code = 'f8bd4c60-c4cd-40a4-b13a-a8349bf66a24') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'A'),(select id from rating.rating_supp_options where code = '7ccafc20-d860-4adc-8ad0-5f926a56bf61')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'A') and option_id =(select id from rating.rating_supp_options where code = '7ccafc20-d860-4adc-8ad0-5f926a56bf61') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'A'),(select id from rating.rating_supp_options where code = 'cf84cd1d-62c4-433a-9b08-f85cd25c20e5')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'A') and option_id =(select id from rating.rating_supp_options where code = 'cf84cd1d-62c4-433a-9b08-f85cd25c20e5') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'A'),(select id from rating.rating_supp_options where code = 'b7f8079c-8310-461c-ae4c-c63eb63dc9f6')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'A') and option_id =(select id from rating.rating_supp_options where code = 'b7f8079c-8310-461c-ae4c-c63eb63dc9f6') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'A'),(select id from rating.rating_supp_options where code = '72cece1e-09ad-4bb9-9c7c-32465412da4e')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'A') and option_id =(select id from rating.rating_supp_options where code = '72cece1e-09ad-4bb9-9c7c-32465412da4e') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'A'),(select id from rating.rating_supp_options where code = '7d51f7da-02e4-4850-b71a-996661f8861a')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'A') and option_id =(select id from rating.rating_supp_options where code = '7d51f7da-02e4-4850-b71a-996661f8861a') );

insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'B'),(select id from rating.rating_supp_options where code = '62e7d685-de66-4b85-b401-b5c69c836030')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'B') and option_id =(select id from rating.rating_supp_options where code = '62e7d685-de66-4b85-b401-b5c69c836030') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'B'),(select id from rating.rating_supp_options where code = 'eadb13de-f617-4f4f-8c3c-9f755ddc663c')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'B') and option_id =(select id from rating.rating_supp_options where code = 'eadb13de-f617-4f4f-8c3c-9f755ddc663c') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'B'),(select id from rating.rating_supp_options where code = 'f8bd4c60-c4cd-40a4-b13a-a8349bf66a24')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'B') and option_id =(select id from rating.rating_supp_options where code = 'f8bd4c60-c4cd-40a4-b13a-a8349bf66a24') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'B'),(select id from rating.rating_supp_options where code = '7ccafc20-d860-4adc-8ad0-5f926a56bf61')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'B') and option_id =(select id from rating.rating_supp_options where code = '7ccafc20-d860-4adc-8ad0-5f926a56bf61') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'B'),(select id from rating.rating_supp_options where code = 'cf84cd1d-62c4-433a-9b08-f85cd25c20e5')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'B') and option_id =(select id from rating.rating_supp_options where code = 'cf84cd1d-62c4-433a-9b08-f85cd25c20e5') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'B'),(select id from rating.rating_supp_options where code = 'b7f8079c-8310-461c-ae4c-c63eb63dc9f6')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'B') and option_id =(select id from rating.rating_supp_options where code = 'b7f8079c-8310-461c-ae4c-c63eb63dc9f6') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'B'),(select id from rating.rating_supp_options where code = '72cece1e-09ad-4bb9-9c7c-32465412da4e')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'B') and option_id =(select id from rating.rating_supp_options where code = '72cece1e-09ad-4bb9-9c7c-32465412da4e') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'B'),(select id from rating.rating_supp_options where code = '7d51f7da-02e4-4850-b71a-996661f8861a')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'B') and option_id =(select id from rating.rating_supp_options where code = '7d51f7da-02e4-4850-b71a-996661f8861a') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'B'),(select id from rating.rating_supp_options where code = '3aeb5d80-ec23-46c2-b24e-d3f9a05ab076')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'B') and option_id =(select id from rating.rating_supp_options where code = '3aeb5d80-ec23-46c2-b24e-d3f9a05ab076') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'B'),(select id from rating.rating_supp_options where code = 'f63773ea-cc76-4ede-af42-46285ba0959a')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'B') and option_id =(select id from rating.rating_supp_options where code = 'f63773ea-cc76-4ede-af42-46285ba0959a') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'B'),(select id from rating.rating_supp_options where code = 'e634146c-5e7f-43ba-a36f-4bea95d372c5')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'B') and option_id =(select id from rating.rating_supp_options where code = 'e634146c-5e7f-43ba-a36f-4bea95d372c5') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'B'),(select id from rating.rating_supp_options where code = '2d1b01c9-e9e6-40d6-b1ec-d4517d6298a7')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'B') and option_id =(select id from rating.rating_supp_options where code = '2d1b01c9-e9e6-40d6-b1ec-d4517d6298a7') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'B'),(select id from rating.rating_supp_options where code = '50df5443-e051-4bef-916c-9994f9ee9859')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'B') and option_id =(select id from rating.rating_supp_options where code = '50df5443-e051-4bef-916c-9994f9ee9859') );

insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'C'),(select id from rating.rating_supp_options where code = '1e50cb11-8e7f-475c-9099-ed439e8674ad')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'C') and option_id =(select id from rating.rating_supp_options where code = '1e50cb11-8e7f-475c-9099-ed439e8674ad') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'C'),(select id from rating.rating_supp_options where code = '1c4abcae-e1e1-4605-b5b2-996f0829e7e7')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'C') and option_id =(select id from rating.rating_supp_options where code = '1c4abcae-e1e1-4605-b5b2-996f0829e7e7') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'C'),(select id from rating.rating_supp_options where code = 'cc38460e-6ffa-469e-b44c-2cc03115c926')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'C') and option_id =(select id from rating.rating_supp_options where code = 'cc38460e-6ffa-469e-b44c-2cc03115c926') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'C'),(select id from rating.rating_supp_options where code = 'a8a10a0c-9195-40c9-bb11-c410ab611372')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'C') and option_id =(select id from rating.rating_supp_options where code = 'a8a10a0c-9195-40c9-bb11-c410ab611372') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'C'),(select id from rating.rating_supp_options where code = '5994e4cd-6de9-473a-a199-c91c94073222')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'C') and option_id =(select id from rating.rating_supp_options where code = '5994e4cd-6de9-473a-a199-c91c94073222') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'C'),(select id from rating.rating_supp_options where code = 'c63727b6-4e57-4b9d-9d51-cc8a90d9f72c')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'C') and option_id =(select id from rating.rating_supp_options where code = 'c63727b6-4e57-4b9d-9d51-cc8a90d9f72c') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'C'),(select id from rating.rating_supp_options where code = '178d2802-8e54-460c-9fa3-a29b0377c4c8')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'C') and option_id =(select id from rating.rating_supp_options where code = '178d2802-8e54-460c-9fa3-a29b0377c4c8') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'C'),(select id from rating.rating_supp_options where code = 'fd49cfcf-2d6e-423b-b36a-27b05b6a5b30')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'C') and option_id =(select id from rating.rating_supp_options where code = 'fd49cfcf-2d6e-423b-b36a-27b05b6a5b30') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'C'),(select id from rating.rating_supp_options where code = '06ade6a9-12f5-417c-b161-cdc59519b8ed')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'C') and option_id =(select id from rating.rating_supp_options where code = '06ade6a9-12f5-417c-b161-cdc59519b8ed') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'C'),(select id from rating.rating_supp_options where code = '3cd9360d-9e97-4eb3-b6b8-85966abc2673')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'C') and option_id =(select id from rating.rating_supp_options where code = '3cd9360d-9e97-4eb3-b6b8-85966abc2673') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'C'),(select id from rating.rating_supp_options where code = 'fa428765-09d0-478e-902d-acbe2947e4ae')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'C') and option_id =(select id from rating.rating_supp_options where code = 'fa428765-09d0-478e-902d-acbe2947e4ae') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'C'),(select id from rating.rating_supp_options where code = '6bead800-3481-4e5a-9fd3-1b89b93b105b')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'C') and option_id =(select id from rating.rating_supp_options where code = '6bead800-3481-4e5a-9fd3-1b89b93b105b') );

insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'D'),(select id from rating.rating_supp_options where code = '1e50cb11-8e7f-475c-9099-ed439e8674ad')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'D') and option_id =(select id from rating.rating_supp_options where code = '1e50cb11-8e7f-475c-9099-ed439e8674ad') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'D'),(select id from rating.rating_supp_options where code = '1c4abcae-e1e1-4605-b5b2-996f0829e7e7')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'D') and option_id =(select id from rating.rating_supp_options where code = '1c4abcae-e1e1-4605-b5b2-996f0829e7e7') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'D'),(select id from rating.rating_supp_options where code = 'cc38460e-6ffa-469e-b44c-2cc03115c926')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'D') and option_id =(select id from rating.rating_supp_options where code = 'cc38460e-6ffa-469e-b44c-2cc03115c926') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'D'),(select id from rating.rating_supp_options where code = 'a8a10a0c-9195-40c9-bb11-c410ab611372')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'D') and option_id =(select id from rating.rating_supp_options where code = 'a8a10a0c-9195-40c9-bb11-c410ab611372') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'D'),(select id from rating.rating_supp_options where code = '5994e4cd-6de9-473a-a199-c91c94073222')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'D') and option_id =(select id from rating.rating_supp_options where code = '5994e4cd-6de9-473a-a199-c91c94073222') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'D'),(select id from rating.rating_supp_options where code = 'c63727b6-4e57-4b9d-9d51-cc8a90d9f72c')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'D') and option_id =(select id from rating.rating_supp_options where code = 'c63727b6-4e57-4b9d-9d51-cc8a90d9f72c') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'D'),(select id from rating.rating_supp_options where code = '178d2802-8e54-460c-9fa3-a29b0377c4c8')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'D') and option_id =(select id from rating.rating_supp_options where code = '178d2802-8e54-460c-9fa3-a29b0377c4c8') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'D'),(select id from rating.rating_supp_options where code = 'fd49cfcf-2d6e-423b-b36a-27b05b6a5b30')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'D') and option_id =(select id from rating.rating_supp_options where code = 'fd49cfcf-2d6e-423b-b36a-27b05b6a5b30') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'D'),(select id from rating.rating_supp_options where code = '06ade6a9-12f5-417c-b161-cdc59519b8ed')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'D') and option_id =(select id from rating.rating_supp_options where code = '06ade6a9-12f5-417c-b161-cdc59519b8ed') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'D'),(select id from rating.rating_supp_options where code = '3cd9360d-9e97-4eb3-b6b8-85966abc2673')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'D') and option_id =(select id from rating.rating_supp_options where code = '3cd9360d-9e97-4eb3-b6b8-85966abc2673') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'D'),(select id from rating.rating_supp_options where code = 'fa428765-09d0-478e-902d-acbe2947e4ae')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'D') and option_id =(select id from rating.rating_supp_options where code = 'fa428765-09d0-478e-902d-acbe2947e4ae') );
insert into rating.rating_supp_tmpl_option(supp_tmpl_id,option_id) select (select id from rating.rating_supp_template where name = 'D'),(select id from rating.rating_supp_options where code = '6bead800-3481-4e5a-9fd3-1b89b93b105b')  from dual where not exists (select 1 from rating.rating_supp_tmpl_option where supp_tmpl_id = (select id from rating.rating_supp_template where name = 'D') and option_id =(select id from rating.rating_supp_options where code = '6bead800-3481-4e5a-9fd3-1b89b93b105b') );


INSERT INTO `rating`.`sys_resource` (`code`, `name`, `url`, `show`, `parent_code`, `seq`) VALUES ('RATING_MGMT', '投票管理', 'system/rating', '1', 'SYS_MGMT', '1.20');
