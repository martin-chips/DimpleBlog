/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.26 : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `test`;

/*Table structure for table `bg_file_item_info` */

DROP TABLE IF EXISTS `bg_file_item_info`;

CREATE TABLE `bg_file_item_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `hash` varchar(128) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `mime_type` varchar(128) DEFAULT NULL,
  `put_time` datetime DEFAULT NULL,
  `server_type` int(11) DEFAULT NULL,
  `path` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=291 DEFAULT CHARSET=utf8;

/*Data for the table `bg_file_item_info` */

insert  into `bg_file_item_info`(`id`,`name`,`hash`,`size`,`mime_type`,`put_time`,`server_type`,`path`) values 
(195,'00b2bef85bb96a7c9fbe5c550c8702a8.png','Fj8mpa7ej7BqJn3a2u7bLYTqeSQD',6659,'image/png','1970-01-19 06:41:30',2,'http://images.bianxiaofeng.com\\00b2bef85bb96a7c9fbe5c550c8702a8.png'),
(196,'0458ace0de50edfbb5d5a4bf33ead25f.jpg','Fn2nDvwwVlhRlDywPfeHCgGEdGlH',359126,'image/jpeg','1970-01-19 06:31:22',2,'http://images.bianxiaofeng.com\\0458ace0de50edfbb5d5a4bf33ead25f.jpg'),
(197,'055f5c9189b9259092c65fcf0567a9fb','FgWQeuhSEbetHQ4zGPxKC3Mc8_DU',41475,'image/png','1970-01-19 08:15:01',2,'http://images.bianxiaofeng.com\\055f5c9189b9259092c65fcf0567a9fb'),
(198,'0673a81655e93804c2f9bacea88b6998.jpg','FsbNFetjY_y6C_75h04VQKq6pG_j',345531,'image/jpeg','1970-01-19 06:31:22',2,'http://images.bianxiaofeng.com\\0673a81655e93804c2f9bacea88b6998.jpg'),
(199,'08e5ab4745b801db46abad2659f76519.png','FvCuog3z1O9akxFsNQobbU4CEbhM',24068,'image/png','1970-01-19 06:41:30',2,'http://images.bianxiaofeng.com\\08e5ab4745b801db46abad2659f76519.png'),
(200,'0a57cd5786f277552c214d355a3f840f.png','FpK7NmPhW7Jf8P2cCKGw6Wcdut60',28787,'image/png','1970-01-19 06:41:29',2,'http://images.bianxiaofeng.com\\0a57cd5786f277552c214d355a3f840f.png'),
(201,'0c0a416473445210c329e389b3fc5edc.png','FjNaxvRNnmAg2w0H9DSCcH0MKO4r',57697,'image/png','1970-01-19 06:41:30',2,'http://images.bianxiaofeng.com\\0c0a416473445210c329e389b3fc5edc.png'),
(202,'16e1c28ee16bd693b137cdd014227d36.png','Fk8b1QLWxAlrzzCq4jO5TpY8J76_',56673,'image/png','1970-01-19 06:41:30',2,'http://images.bianxiaofeng.com\\16e1c28ee16bd693b137cdd014227d36.png'),
(203,'175665caadc19bda8d43702d579972d9.png','FmGYMpCcb40CCO4Qp2_G_j5FB8Nr',29385,'image/png','1970-01-19 06:41:30',2,'http://images.bianxiaofeng.com\\175665caadc19bda8d43702d579972d9.png'),
(204,'18ebad814ff58915f92d17b2c407c4f9','FqxcFoGUAdd-r-EktAsLXUYrmSey',27826,'image/png','1970-01-19 07:56:24',2,'http://images.bianxiaofeng.com\\18ebad814ff58915f92d17b2c407c4f9'),
(205,'1abb2dc3d76311944ffdbe9980fbaadd.jpg','Fv3bpuQri6sJbShKFE7YgkYvRi5B',20900,'image/jpeg','1970-01-19 06:31:23',2,'http://images.bianxiaofeng.com\\1abb2dc3d76311944ffdbe9980fbaadd.jpg'),
(206,'21a05ce30419264ee53d68fb38049e23.jpg','FnwiSkSQOELWyqh1SaVCzBIidTOA',344456,'image/jpeg','1970-01-19 06:31:22',2,'http://images.bianxiaofeng.com\\21a05ce30419264ee53d68fb38049e23.jpg'),
(207,'26a492c384cc7ecf0b68ced3ba65c0f6','Fnuf3DMp8Zz3va5nP2u1J_TjCP40',60874,'image/png','1970-01-19 07:56:24',2,'http://images.bianxiaofeng.com\\26a492c384cc7ecf0b68ced3ba65c0f6'),
(208,'28592ad1bf2eb90bd1d5f0482a91095b.gif','Fp_Q6r3a3R350OwPmVONqbOUCtls',221200,'image/gif','1970-01-19 07:06:54',2,'http://images.bianxiaofeng.com\\28592ad1bf2eb90bd1d5f0482a91095b.gif'),
(209,'2b3994476d088b93917a88fa37a4e128.jpg','FjSQbB8WCbvVIn30K9vHeFjN4C3M',20093,'image/jpeg','1970-01-19 06:31:21',2,'http://images.bianxiaofeng.com\\2b3994476d088b93917a88fa37a4e128.jpg'),
(210,'2c08c7fa3ac1a532e1daead9e7bdd9c7.png','FuUK8tTw3UcdxEPOeKfTnN0F3N45',56127,'image/png','1970-01-19 07:06:51',2,'http://images.bianxiaofeng.com\\2c08c7fa3ac1a532e1daead9e7bdd9c7.png'),
(211,'2e03c48ad95cd4925f9ff167419f9c7d.png','Fq99spR65OTIxqMAmtmtjlceUDqp',53482,'image/png','1970-01-19 06:41:29',2,'http://images.bianxiaofeng.com\\2e03c48ad95cd4925f9ff167419f9c7d.png'),
(212,'364be8860e8d72b4358b5e88099a935a.png','FiJTrKuSramc2sVFsPs-Ts1Ia5mi',1881,'image/png','1970-01-19 07:40:08',2,'http://images.bianxiaofeng.com\\364be8860e8d72b4358b5e88099a935a.png'),
(213,'364c537db064657e9182724989bc7b7f.png','FseZS1BcOW8qkTYwILLee3wGRRBW',90281,'image/png','1970-01-19 07:06:54',2,'http://images.bianxiaofeng.com\\364c537db064657e9182724989bc7b7f.png'),
(214,'38023fe74a3474cd2b6181218d2ca958','FjsMh4lGza57NreKOInkzeQ2PaRk',72215,'image/png','1970-01-19 08:46:31',2,'http://images.bianxiaofeng.com\\38023fe74a3474cd2b6181218d2ca958'),
(215,'3aeae4dc46b0f75fc7c4f89abc173f98','FshwvO3c-uzX88lpG_dqlatEXGtO',58877,'image/jpeg','1970-01-19 07:56:17',2,'http://images.bianxiaofeng.com\\3aeae4dc46b0f75fc7c4f89abc173f98'),
(216,'3b5d357dd6f37f8b1d7de410c022224a.jpg','FvXJeJP5WPEanMg9dJMBFm_mOT6N',9857,'image/jpeg','1970-01-19 06:31:23',2,'http://images.bianxiaofeng.com\\3b5d357dd6f37f8b1d7de410c022224a.jpg'),
(217,'3f7a6d4d52cecebb7cbac9899949c875.gif','FvVcnryhIfYT8F2HQIzdJYzPuV3a',717306,'image/gif','1970-01-19 07:06:54',2,'http://images.bianxiaofeng.com\\3f7a6d4d52cecebb7cbac9899949c875.gif'),
(218,'440e766cb9b1cc7dd1618980885ed5ae.jpg','FkfgGQPrF2oStfqBuK4XolWYTUDk',274604,'image/jpeg','1970-01-19 06:31:22',2,'http://images.bianxiaofeng.com\\440e766cb9b1cc7dd1618980885ed5ae.jpg'),
(219,'4520f1ef3da1e34e051abc9dae22f6be.png','Fmwx_iJnhemeUx9WPDUqVOujUZnA',20764,'image/png','1970-01-19 06:41:28',2,'http://images.bianxiaofeng.com\\4520f1ef3da1e34e051abc9dae22f6be.png'),
(220,'4a3e9bf06aa4a468ccbbf1faeddaba82.png','Fmr3m4o3Z9HHQW-f-t7wfIpF8kig',12651,'image/png','1970-01-19 06:41:30',2,'http://images.bianxiaofeng.com\\4a3e9bf06aa4a468ccbbf1faeddaba82.png'),
(221,'5716bf2d792ce5402990feea3a407f45.png','FsmCndjCskwbcHGlQVI7TtuKHrji',14839,'image/png','1970-01-19 06:41:30',2,'http://images.bianxiaofeng.com\\5716bf2d792ce5402990feea3a407f45.png'),
(222,'57f897e9d2d1c78422651876a1ac19cc.png','Foo1xLLHibOfTpNIy-PFNffWIJ91',47829,'image/png','1970-01-19 06:41:30',2,'http://images.bianxiaofeng.com\\57f897e9d2d1c78422651876a1ac19cc.png'),
(223,'58c2df1506cd80bfbe521bdff205fe8d.gif','Fr-mShgcyYudHYoiT6ISv2RxxVyN',658947,'image/gif','1970-01-19 07:06:54',2,'http://images.bianxiaofeng.com\\58c2df1506cd80bfbe521bdff205fe8d.gif'),
(224,'5a89da91016ba365cd57488b4812156e.png','FqyAk9o4_RxMEu3tOYdKfH4DHDPI',6815,'image/png','1970-01-19 06:41:30',2,'http://images.bianxiaofeng.com\\5a89da91016ba365cd57488b4812156e.png'),
(225,'5bd444e20c71347a44ba45a1fbca2bb9.png','Fh1ArgPia73ebA7cylfcR3S9p8TS',80344,'image/png','1970-01-19 06:41:30',2,'http://images.bianxiaofeng.com\\5bd444e20c71347a44ba45a1fbca2bb9.png'),
(226,'615a231d32e95f7c17055c7b906d9c20.png','FvVixGJyyKSLaawFVVhsfrXiMbQD',22950,'image/png','1970-01-19 06:41:29',2,'http://images.bianxiaofeng.com\\615a231d32e95f7c17055c7b906d9c20.png'),
(227,'64b56e20e7edd8d9e8bc38ba896e9b64.png','FuusbbzOTAOqk-idvKw0dwwNsMRY',19598,'image/png','1970-01-19 06:31:23',2,'http://images.bianxiaofeng.com\\64b56e20e7edd8d9e8bc38ba896e9b64.png'),
(228,'657b07046abc1a8dd3de65ea80f37ded.jpg','Fs3O-Knk7zte5sOreMbfng79s4_4',438850,'image/jpeg','1970-01-19 06:31:22',2,'http://images.bianxiaofeng.com\\657b07046abc1a8dd3de65ea80f37ded.jpg'),
(229,'6c567650bddf97b8acddb5342bf7d23a','Fnuf3DMp8Zz3va5nP2u1J_TjCP40',60874,'image/png','1970-01-19 07:56:23',2,'http://images.bianxiaofeng.com\\6c567650bddf97b8acddb5342bf7d23a'),
(230,'730174dea79420034b3368022552fd68','FheGxj8pr2eqjUBUkbOU9WkApim9',73911,'image/jpeg','1970-01-19 07:56:22',2,'http://images.bianxiaofeng.com\\730174dea79420034b3368022552fd68'),
(231,'7519f60723840cd3a7d1ee385a46deaf','Ft8891RvhJmwuBudAoNAik3NhsAf',25949,'image/jpeg','1970-01-19 10:17:28',2,'http://images.bianxiaofeng.com\\7519f60723840cd3a7d1ee385a46deaf'),
(232,'75451ad928cd05c935072c1f12b51062.png','Fl7aM7Ht8k-QUXSGohXThd8Ixx-9',8817,'image/png','1970-01-19 06:31:24',2,'http://images.bianxiaofeng.com\\75451ad928cd05c935072c1f12b51062.png'),
(233,'75c0c7d78482671b30e81f3978bbb5f9','Fsz7cEt5iZJhs1bCgdRy30x7dpB0',14991,'image/jpeg','1970-01-19 07:43:10',2,'http://images.bianxiaofeng.com\\75c0c7d78482671b30e81f3978bbb5f9'),
(234,'7725d208415a957e91facd27ec740cbb.png','FhBgsIf3aKz2xcvNB-GUOzjuQYmv',68513,'image/png','1970-01-19 06:31:22',2,'http://images.bianxiaofeng.com\\7725d208415a957e91facd27ec740cbb.png'),
(235,'846a5c111f539bcc3d43726b2b7e7ed4','FtqB0EpkwNsOEZiDkMgF0vrHfyDj',11204,'image/jpeg','1970-01-19 10:18:30',2,'http://images.bianxiaofeng.com\\846a5c111f539bcc3d43726b2b7e7ed4'),
(236,'881e09921689bba69df1f307b2207be1.png','Flg7o3QtsDihH5I_SYkiJGeqb28K',56485,'image/png','1970-01-19 07:06:51',2,'http://images.bianxiaofeng.com\\881e09921689bba69df1f307b2207be1.png'),
(237,'8acfa54bfd191dbefe29a68a725f6f38.png','Fsm44ghUMmjzLPjJqYcjH2gGZGUQ',17903,'image/png','1970-01-19 06:31:24',2,'http://images.bianxiaofeng.com\\8acfa54bfd191dbefe29a68a725f6f38.png'),
(238,'8e0f119679a29c2c492cf483dda3650b.png','FkoUgEo8vuqr0RWoE9jDUrruDe5e',4797,'image/png','1970-01-19 06:41:28',2,'http://images.bianxiaofeng.com\\8e0f119679a29c2c492cf483dda3650b.png'),
(239,'90c384a1ea4d514243ed00d4531bdfc3.png','Fs51wrUdACGEKuzEBG_vo4OAWYkM',64711,'image/png','1970-01-19 06:41:29',2,'http://images.bianxiaofeng.com\\90c384a1ea4d514243ed00d4531bdfc3.png'),
(240,'962eb9aa80df78c5158c5cf0f2e43cf4.png','Fl9qEMwTI7lJclnsjqwQYYwU3LUZ',26029,'image/png','1970-01-19 06:41:29',2,'http://images.bianxiaofeng.com\\962eb9aa80df78c5158c5cf0f2e43cf4.png'),
(241,'968c09f90098bdc6239e5c33ce1dadfe.png','FhRlk3Q7G07_Tq_9QhUo1u_CcH6y',79536,'image/png','1970-01-19 06:41:30',2,'http://images.bianxiaofeng.com\\968c09f90098bdc6239e5c33ce1dadfe.png'),
(242,'QQ截图20190724201015.png20190724081021','FhvyGOfi6uq6IYFqzn8nO3pKlQuF',43317,'image/png','1970-01-19 10:26:10',2,'http://images.bianxiaofeng.com\\QQ截图20190724201015.png20190724081021'),
(243,'QQ截图20190724223216-20190724110244.png','FvRkPexoHuQXuJEQwYzR5E59QJTt',10766,'image/png','1970-01-19 10:26:21',2,'http://images.bianxiaofeng.com\\QQ截图20190724223216-20190724110244.png'),
(244,'QQ截图20190724223216.png20190724103230','FvRkPexoHuQXuJEQwYzR5E59QJTt',10766,'image/png','1970-01-19 10:26:19',2,'http://images.bianxiaofeng.com\\QQ截图20190724223216.png20190724103230'),
(245,'QQ截图20190724223216.png20190724104057','FvRkPexoHuQXuJEQwYzR5E59QJTt',10766,'image/png','1970-01-19 10:26:19',2,'http://images.bianxiaofeng.com\\QQ截图20190724223216.png20190724104057'),
(246,'QQ截图20190724223216.png20190724104301','FvRkPexoHuQXuJEQwYzR5E59QJTt',10766,'image/png','1970-01-19 10:26:19',2,'http://images.bianxiaofeng.com\\QQ截图20190724223216.png20190724104301'),
(247,'QQ截图20190724223216.png20190724104429','FvRkPexoHuQXuJEQwYzR5E59QJTt',10766,'image/png','1970-01-19 10:26:19',2,'http://images.bianxiaofeng.com\\QQ截图20190724223216.png20190724104429'),
(248,'a562375d9b1458bc22e0c43ddfeba579.png','Fh6euExoUs6XhJ0j-os0D-qitb2O',74013,'image/png','1970-01-19 06:41:29',2,'http://images.bianxiaofeng.com\\a562375d9b1458bc22e0c43ddfeba579.png'),
(249,'a64989a22987e24e88339b09a9b7f011.png','FgWz__1hTWjYADflDrrt8yuwTRGg',78227,'image/png','1970-01-19 06:41:30',2,'http://images.bianxiaofeng.com\\a64989a22987e24e88339b09a9b7f011.png'),
(250,'a8158a9e3fece563eecbaccdddaf6798','Fnuf3DMp8Zz3va5nP2u1J_TjCP40',60874,'image/png','1970-01-19 07:56:21',2,'http://images.bianxiaofeng.com\\a8158a9e3fece563eecbaccdddaf6798'),
(251,'a87f2f1604eb00e161a7c1174282e3dc','FshHbqAP16XRZEvYMXe7lqfOU6BD',19658,'image/png','1970-01-19 08:06:26',2,'http://images.bianxiaofeng.com\\a87f2f1604eb00e161a7c1174282e3dc'),
(252,'a998b9bdf62e2de288c16e5499a4493f.png','FvJCvjJW6XdHEShYlhYquWgtpYtK',210632,'image/png','1970-01-19 07:06:54',2,'http://images.bianxiaofeng.com\\a998b9bdf62e2de288c16e5499a4493f.png'),
(253,'a9c64edbbae3798c65dd510777d11596.png','FjC9gQo2fH7gw1NxOisE_3r1Ci0h',9536,'image/png','1970-01-19 06:41:29',2,'http://images.bianxiaofeng.com\\a9c64edbbae3798c65dd510777d11596.png'),
(254,'abdd69d94ca29e7ce4daa00a15af2a0d','Fl_6WUx-7VoxTl6dPEwryCdYE9v0',64817,'image/png','1970-01-19 08:43:29',2,'http://images.bianxiaofeng.com\\abdd69d94ca29e7ce4daa00a15af2a0d'),
(255,'ad680d982cc99cce26927d4927263dc0.png','Fj15x_wEvSnwOTOT9CROuLVcnf80',167596,'image/png','1970-01-19 06:31:21',2,'http://images.bianxiaofeng.com\\ad680d982cc99cce26927d4927263dc0.png'),
(256,'af4a9739b92b6ad71d8a3a4c7c25d53d.png','Fm7-YygoN3QSypRimfpHF_RXVCIo',58173,'image/png','1970-01-19 06:41:30',2,'http://images.bianxiaofeng.com\\af4a9739b92b6ad71d8a3a4c7c25d53d.png'),
(257,'b2bd059993649deadffa577b09489d11.png','FrQZuFREBHN8e-HG40C6__XVSGM7',72406,'image/png','1970-01-19 06:41:30',2,'http://images.bianxiaofeng.com\\b2bd059993649deadffa577b09489d11.png'),
(258,'b894162f895e8323cb220beb298c5699.png','FiXppUbJW8MfVop3N0OhQQZNPcZO',87507,'image/png','1970-01-19 06:41:30',2,'http://images.bianxiaofeng.com\\b894162f895e8323cb220beb298c5699.png'),
(259,'bcd58503e4a2de5218fb777beecb5955.png','Fs59_XleOYh0GzlwuqjNxMvhlEBj',32119,'image/png','1970-01-19 06:41:29',2,'http://images.bianxiaofeng.com\\bcd58503e4a2de5218fb777beecb5955.png'),
(260,'be0f85cd5790ba64e8963f38f416d9fc','Fnuf3DMp8Zz3va5nP2u1J_TjCP40',60874,'image/png','1970-01-19 07:56:21',2,'http://images.bianxiaofeng.com\\be0f85cd5790ba64e8963f38f416d9fc'),
(261,'be2c1d3bf6831bfa84674844bbed43cb.jpg','FpWabD6YNXAOVALgNpayw4qt-Yot',274731,'image/jpeg','1970-01-19 06:31:22',2,'http://images.bianxiaofeng.com\\be2c1d3bf6831bfa84674844bbed43cb.jpg'),
(262,'be9cdb13ac0d67414bb69ca6f4ab1e23.png','Fg4yyaa8SFZDgJaGfrGP6B9C6LL4',12767,'image/png','1970-01-19 06:41:30',2,'http://images.bianxiaofeng.com\\be9cdb13ac0d67414bb69ca6f4ab1e23.png'),
(263,'bfaa24a877f7f1b9a38aaf6f44c33214.png','FgUdNRG6EeN7sG2u7jGraS2qHCid',7214,'image/png','1970-01-19 06:41:29',2,'http://images.bianxiaofeng.com\\bfaa24a877f7f1b9a38aaf6f44c33214.png'),
(264,'c093f76af6e31b6be014ca4185b849b8.jpg','FpTQpFj0I770R9x_pmIsrF79hMVP',310623,'image/jpeg','1970-01-19 06:31:22',2,'http://images.bianxiaofeng.com\\c093f76af6e31b6be014ca4185b849b8.jpg'),
(265,'c395ec9d24ac314bf9ab182a1e7f05f9','Fnq1Xomf65gcQ1agECIXKZfjsTU6',31925,'image/png','1970-01-19 07:43:09',2,'http://images.bianxiaofeng.com\\c395ec9d24ac314bf9ab182a1e7f05f9'),
(266,'c8608ea4cff130f64a48b9070e83755d.jpg','Fs5o4lt7inZhL17qUkUHPX0ZZYip',242498,'image/jpeg','1970-01-19 06:31:22',2,'http://images.bianxiaofeng.com\\c8608ea4cff130f64a48b9070e83755d.jpg'),
(267,'ca5e64499f38d37d3ff7a273f4c0dc4f.png','FmqWyh5ptm8QQIRS9D2uikwFdHRe',29372,'image/png','1970-01-19 06:41:29',2,'http://images.bianxiaofeng.com\\ca5e64499f38d37d3ff7a273f4c0dc4f.png'),
(268,'cbdecbe49de0ba8d0e164a6a81431010.png','FkF1fb4liRaJz8BfmKxcykiAolBO',25120,'image/png','1970-01-19 06:41:30',2,'http://images.bianxiaofeng.com\\cbdecbe49de0ba8d0e164a6a81431010.png'),
(269,'ced846eab08578468670a87333e05b5f.jpg','FoasChyzw5sTJ4DTGVMcHrvlKVTI',336146,'image/jpeg','1970-01-19 06:31:22',2,'http://images.bianxiaofeng.com\\ced846eab08578468670a87333e05b5f.jpg'),
(270,'cf3fdf8fd09c5c08569c58bd1bf9b5bf.jpg','FlxyHd_Lh7CjKCGf6bxOp_38y7-p',11893,'image/jpeg','1970-01-19 06:41:33',2,'http://images.bianxiaofeng.com\\cf3fdf8fd09c5c08569c58bd1bf9b5bf.jpg'),
(271,'d08b92c9ac0287d16186cfbbc8398440','FuOadpdRYfvRKfli-Zbu8KFbJof8',79245,'image/png','1970-01-19 08:58:03',2,'http://images.bianxiaofeng.com\\d08b92c9ac0287d16186cfbbc8398440'),
(272,'d2dad8c62636d857e9139b426ce176b7.jpg','Fv3bpuQri6sJbShKFE7YgkYvRi5B',20900,'image/jpeg','1970-01-19 06:31:23',2,'http://images.bianxiaofeng.com\\d2dad8c62636d857e9139b426ce176b7.jpg'),
(273,'d41cd950f17ad2a1d9e90686d70bd915.png','FmiUoYc2ckpeUwQYS9Bv1PzxxUw9',25412,'image/png','1970-01-19 06:41:29',2,'http://images.bianxiaofeng.com\\d41cd950f17ad2a1d9e90686d70bd915.png'),
(274,'d59d1c8759311e5c61f31828a06c1c5e.png','FqZWPc-MA2dDDjk4YwES1M3OIc8s',35602,'image/png','1970-01-19 06:41:30',2,'http://images.bianxiaofeng.com\\d59d1c8759311e5c61f31828a06c1c5e.png'),
(275,'d5eb717cbb9fe8efc99730244194a323.png','Fg4lwxDvbISY_qKvruNksfPaRg5H',47665,'image/png','1970-01-19 07:06:52',2,'http://images.bianxiaofeng.com\\d5eb717cbb9fe8efc99730244194a323.png'),
(276,'d9ef8c602b4aa98833b448c5db86d883.jpg','FvdKvT2kRMFunrthwaknrWt9qB-i',22681,'image/jpeg','1970-01-19 06:31:24',2,'http://images.bianxiaofeng.com\\d9ef8c602b4aa98833b448c5db86d883.jpg'),
(277,'dcec1d4cc1c33f1d1ee23e171bf5a1ae.png','FsQ7OkMemMTu7bgQMhqH9Ho1BU1j',73950,'image/png','1970-01-19 06:41:30',2,'http://images.bianxiaofeng.com\\dcec1d4cc1c33f1d1ee23e171bf5a1ae.png'),
(278,'df38e3a21566f1b82c93220684d75fe4.gif','FkTk7zFjhyv36jG90tjOxC27alm9',13371,'image/gif','1970-01-19 06:41:28',2,'http://images.bianxiaofeng.com\\df38e3a21566f1b82c93220684d75fe4.gif'),
(279,'e48a73a824794940d5fb04d22e0b35dd','Fqb6hqWqubyg4ipc_MmjR2mFIqBj',301749,'image/png','1970-01-19 08:16:24',2,'http://images.bianxiaofeng.com\\e48a73a824794940d5fb04d22e0b35dd'),
(280,'e54eb4e637200e0c0ab37e84ef103ff6','Fi5I21zn2ZtJCT6zmR-DIWtURGgW',68098,'image/png','1970-01-19 08:43:29',2,'http://images.bianxiaofeng.com\\e54eb4e637200e0c0ab37e84ef103ff6'),
(281,'e7adf343904a81abdfc0fedc11e86ea0.png','FpoNcq8jeymr8V_AnpO2I3ahZH7S',14329,'image/png','1970-01-19 06:41:30',2,'http://images.bianxiaofeng.com\\e7adf343904a81abdfc0fedc11e86ea0.png'),
(282,'e933fc3b154ec202c1b705879660e552.png','FjaOLnHNifQuQNS6KMyOYohoC9ik',27108,'image/png','1970-01-19 06:41:29',2,'http://images.bianxiaofeng.com\\e933fc3b154ec202c1b705879660e552.png'),
(283,'ea2541cc18b70f5d0fb308acdef2470d.png','FraBwXTUK9xFr5t2rPFEpY5QMztl',60046,'image/png','1970-01-19 07:06:52',2,'http://images.bianxiaofeng.com\\ea2541cc18b70f5d0fb308acdef2470d.png'),
(284,'eada5b7b8544d2d78ac8fa6733b78b45.png','FrGF0iziy8bAGaVM3HdqwNe4Ldzj',58085,'image/png','1970-01-19 07:06:52',2,'http://images.bianxiaofeng.com\\eada5b7b8544d2d78ac8fa6733b78b45.png'),
(285,'eaf4bc2d199e636a63a6b3543fb7beca.jpg','FiqJkLP2e_KH6T2_3rzB22uH7bdp',311394,'image/jpeg','1970-01-19 06:31:22',2,'http://images.bianxiaofeng.com\\eaf4bc2d199e636a63a6b3543fb7beca.jpg'),
(286,'ecf170c9acadfb88b314e6135ab94476.png','FuGsuULXekFHMQeDDSWKUyQ0r7sm',6826,'image/png','1970-01-19 06:41:30',2,'http://images.bianxiaofeng.com\\ecf170c9acadfb88b314e6135ab94476.png'),
(287,'f6cb09b6ccd9ee4b6beec27b1d40fb26.jpg','FsUMNcZVvCFNry12jYOaStiUgi3Q',7426,'image/jpeg','1970-01-19 06:31:23',2,'http://images.bianxiaofeng.com\\f6cb09b6ccd9ee4b6beec27b1d40fb26.jpg'),
(288,'fb268104e65cc3c5adecbf3a2d85034a.jpg','FsbBa1ZGy9qDzgPQkT7ZfQ9hwtIz',369757,'image/jpeg','1970-01-19 06:31:22',2,'http://images.bianxiaofeng.com\\fb268104e65cc3c5adecbf3a2d85034a.jpg'),
(289,'fdce0a3332a69d7dec3350e449a1e9a3.jpg','FtirXAWeQm7HKfg_bYU5fCSt82C9',45380,'image/jpeg','1970-01-19 06:31:36',2,'http://images.bianxiaofeng.com\\fdce0a3332a69d7dec3350e449a1e9a3.jpg'),
(290,'ffe8708141ca50a2d498b6e099780a23','Fnuf3DMp8Zz3va5nP2u1J_TjCP40',60874,'image/png','1970-01-19 07:56:23',2,'http://images.bianxiaofeng.com\\ffe8708141ca50a2d498b6e099780a23');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
